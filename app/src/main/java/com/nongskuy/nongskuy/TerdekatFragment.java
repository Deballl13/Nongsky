package com.nongskuy.nongskuy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nongskuy.nongskuy.data.NongskuyTerdekatData;
import com.nongskuy.nongskuy.model.NongskuyTerdekatClass;
import com.nongskuy.nongskuy.services.FetchAddressIntentService;
import org.riversun.promise.Func;
import org.riversun.promise.Promise;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerdekatFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment supportMapFragment;
    private TextView textAlamatGps;
    private ResultReceiver resultReceiver;
    private LocationManager locationManager;
    private Boolean isGpsEnabled;
    private Double latitude;
    private Double longitude;
    private List<NongskuyTerdekatData> listTokoTerdekat;

    public TerdekatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_terdekat, container, false);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        textAlamatGps = view.findViewById(R.id.textAlamatGPS);
        resultReceiver = new AddressResultReceiver(new Handler());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // cek permission android
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
        else if (isGpsEnabled) {
            getCurrentLocation();
        }
        else {
            ((MainActivity) getActivity()).requestGpsActive();
        }

        return view;
    }

    // request permission location
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = null;
                        Boolean coarseLocationGranted = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                        }

                        if (fineLocationGranted && coarseLocationGranted) {
                            ((MainActivity) getActivity()).requestGpsActive();
                        }
                    }
            );

    Func loadDataNongskuyTerdekat = (action, data) -> {
        Config config = new Config();

        // set nilai latitude dan longitude
        latitude = ((Location) data).getLatitude();
        longitude = ((Location) data).getLongitude();

        // load data
        Call<NongskuyTerdekatClass> call = config.configRetrofit().terdekat(latitude, longitude);
        call.enqueue(new Callback<NongskuyTerdekatClass>() {
            @Override
            public void onResponse(Call<NongskuyTerdekatClass> call, Response<NongskuyTerdekatClass> response) {
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        NongskuyTerdekatClass nongskuyTerdekatClass = response.body();
                        listTokoTerdekat = nongskuyTerdekatClass.getToko();
                        action.resolve();
                    }
                }
            }

            @Override
            public void onFailure(Call<NongskuyTerdekatClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    };

    Func mapAsync = (action, data) -> {

        // menampilkan lokasi
        supportMapFragment.getMapAsync(this);

        action.resolve();
    };

    public void getCurrentLocation() {
        // setup progress dialog mencari lokasi
        textAlamatGps.setText("Mencari lokasi...");

        Promise.resolve()
                .then(new Promise(((MainActivity) getActivity()).getCurrentLocation))
                .then(new Promise(loadDataNongskuyTerdekat))
                .then(new Promise(mapAsync))
                .start();// start Promise operation
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentLocation();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Intent intent = new Intent(getActivity(), GpsTerputus.class);
                startActivity(intent);
                getActivity().finish();
            }
        }
    }

    public void fetchAddressFromLatLong(Location location) {
        Intent intent = new Intent(getContext(), FetchAddressIntentService.class);
        intent.putExtra(Config.RECEIVER, resultReceiver);
        intent.putExtra(Config.LOCATION_DATA_EXTRA, location);
        getActivity().startService(intent);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // setup location on google maps
        LatLng latLng = new LatLng(latitude,
                longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        googleMap.addMarker(markerOptions);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // get location name
        Location location1 = new Location("providerNA");
        location1.setLatitude(latitude);
        location1.setLongitude(longitude);
        fetchAddressFromLatLong(location1);


        if (listTokoTerdekat.size() != 0) {
            for (NongskuyTerdekatData nongskuyTerdekatData : listTokoTerdekat) {
                LatLng latLng2 = new LatLng(Double.parseDouble(nongskuyTerdekatData.getLatitude()),
                        Double.parseDouble(nongskuyTerdekatData.getLongitude()));
                MarkerOptions markerOptions2 = new MarkerOptions().position(latLng2)
                        .title(nongskuyTerdekatData.getNamaToko());
                googleMap.addMarker(markerOptions2);
            }
        }
    }

    public class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Config.SUCCESS_RESULT) {
                textAlamatGps.setText(resultData.getString(Config.RESULT_DATA_KEY));
            }
        }
    }
}