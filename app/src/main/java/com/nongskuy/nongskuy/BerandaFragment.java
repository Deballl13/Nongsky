package com.nongskuy.nongskuy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.adapter.BerandaPopulerAdapter;
import com.nongskuy.nongskuy.adapter.BerandaPromoAdapter;
import com.nongskuy.nongskuy.adapter.BerandaTerdekatAdapter;
import com.nongskuy.nongskuy.data.NongskuyTerdekatData;
import com.nongskuy.nongskuy.data.PromoData;
import com.nongskuy.nongskuy.data.NongskuyPopulerData;
import com.nongskuy.nongskuy.model.NongskuyTerdekatClass;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.PromoClass;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.NongskuyPopulerClass;

import org.riversun.promise.Func;
import org.riversun.promise.Promise;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaFragment extends Fragment implements BerandaPopulerAdapter.OnPopulerViewHolderClick,
        BerandaPromoAdapter.OnPromoViewHolderClick, BerandaTerdekatAdapter.OnTerdekatViewHolderClick {

    private TextView namaUser;
    private MaterialButton btnRiwayatPemesananTempat, btnLihatSemuaPopuler,
            btnLihatSemuaPromo, btnLihatSemuaTerdekat;
    private RecyclerView recyclerViewPopuler, recyclerViewPromo, recyclerViewTerdekat;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;
    private Config config;
    private SearchView searchViewBeranda;
    private String token;
    private SwipeRefreshLayout refreshLayout;
    private LocationManager locationManager;
    private Boolean isGpsEnabled;
    private Double latitude;
    private Double longitude;
    private CardView cardViewTerdekatTidakDitemukan;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String nama = sharedPreferences.getString("Nama", null);
        token = sharedPreferences.getString("Token", null);


        config = new Config();
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        btnRiwayatPemesananTempat = view.findViewById(R.id.buttonPesanTempat);
        btnLihatSemuaPopuler = view.findViewById(R.id.buttonLihatSemuaPopuler);
        btnLihatSemuaPromo = view.findViewById(R.id.buttonLihatSemuaPromo);
        btnLihatSemuaTerdekat = view.findViewById(R.id.buttonLihatSemuaTerdekat);
        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.BottomNavigationMenu);
        namaUser = view.findViewById(R.id.textName);
        refreshLayout = view.findViewById(R.id.refreshBeranda);
        searchViewBeranda = view.findViewById(R.id.searchViewBeranda);
        cardViewTerdekatTidakDitemukan = view.findViewById(R.id.tokoTerdekatTidakDitemukan);

        // cek permission android
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        } else {
            ((MainActivity) getActivity()).requestGpsActive();
        }

        // cek status gps
        if (isGpsEnabled) {
            getCurrentLocation();
        }


        //cek ketersediaan token
        if (token != null) {
            namaUser.setText(nama.toString());
            btnRiwayatPemesananTempat.setVisibility(View.VISIBLE);

            // atur margin text populer beranda
            TextView textBerandaPopuler = view.findViewById(R.id.textBerandaPopuler);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textBerandaPopuler.getLayoutParams();
            layoutParams.setMargins(24, 30, 0, 0);
            textBerandaPopuler.setLayoutParams(layoutParams);

            // atur margin button lihat semua populer
            ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) btnLihatSemuaPopuler.getLayoutParams();
            layoutParams1.setMargins(0, 15, 12, 0);
            btnLihatSemuaPopuler.setLayoutParams(layoutParams1);
        }


        // recycler view populer
        LinearLayoutManager linearLayoutManagerPopuler = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopuler = view.findViewById(R.id.recyclerViewBerandaPopuler);
        recyclerViewPopuler.setLayoutManager(linearLayoutManagerPopuler);
        recyclerViewPopuler.setAdapter(new BerandaPopulerAdapter(null));

        // recyclerview promo
        if (token != null) {
            recyclerViewPromo = view.findViewById(R.id.recyclerViewBerandaPromo);

            view.findViewById(R.id.textBerandaPromo).setVisibility(View.VISIBLE);
            btnLihatSemuaPromo.setVisibility(View.VISIBLE);
            recyclerViewPromo.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManagerPromo = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewPromo.setLayoutManager(linearLayoutManagerPromo);
            recyclerViewPromo.setAdapter(new BerandaPromoAdapter(null));
        }

        // recyclerview terdekat
        LinearLayoutManager linearLayoutManagerTerdekat = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTerdekat = view.findViewById(R.id.recyclerViewBerandaTerdekat);
        recyclerViewTerdekat.setLayoutManager(linearLayoutManagerTerdekat);
        recyclerViewTerdekat.setAdapter(new BerandaTerdekatAdapter(null));

        // refresh halaman
        refreshLayout = view.findViewById(R.id.refreshBeranda);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout();
            }
        });

        // getFragment
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            menuNavigation(intent.getStringExtra("Fragment"));
                        }
                    }
                });

        //Intent Fragment Beranda ke Activity Riwayat Pesan Tempat
        btnRiwayatPemesananTempat.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), RiwayatPemesananTempatActivity.class);
            startActivity(intent);
        });

        //Intent ke Activity Populer
        btnLihatSemuaPopuler.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), PopulerActivity.class);
            intent.putExtra("Latitude", latitude);
            intent.putExtra("Longitude", longitude);
            intentResult.launch(intent);
        });

        // Intent ke fragment promo
        btnLihatSemuaPromo.setOnClickListener(view1 -> {
            menuNavigation("Promo");
        });

        // Intent ke fragment terdekat
        btnLihatSemuaTerdekat.setOnClickListener(view1 -> {
            menuNavigation("Terdekat");
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // menghilangkan keyword pencarian yang ada
        searchViewBeranda.setQuery("", false);
        searchViewBeranda.clearFocus();

        // jika keyword di enter/submit akan diarahkan ke halaman pencarian
        searchViewBeranda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (latitude != null && longitude != null) {
                    Intent intent = new Intent(getActivity(), PencarianActivity.class);
                    intent.putExtra("Keyword", s);
                    intent.putExtra("Latitude", latitude);
                    intent.putExtra("Longitude", longitude);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "data sedang diunduh", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
        }

        return true;
    }

    public void menuNavigation(String data) {
        switch (data) {
            case "Beranda":
                loadFragment(new BerandaFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
                break;
            case "Promo":
                loadFragment(new PromoFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_promo);
                break;
            case "Terdekat":
                loadFragment(new TerdekatFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_terdekat);
                break;
            case "Profil":
                loadFragment(new ProfilFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_profil);
                break;
        }
    }

    public void refreshLayout() {
        // cek gps hidup atau tidak
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // reset latitude dan longitude
        latitude = null;
        longitude = null;

        // disabled button
        btnLihatSemuaPopuler.setEnabled(false);
        btnLihatSemuaPromo.setEnabled(false);
        btnLihatSemuaTerdekat.setEnabled(false);

        // active shimmer
        recyclerViewPopuler.setAdapter(new BerandaPopulerAdapter(null));
        recyclerViewPromo.setAdapter(new BerandaPromoAdapter(null));
        recyclerViewTerdekat.setAdapter(new BerandaTerdekatAdapter(null));

        if (isGpsEnabled) {
            getCurrentLocation();
        } else {
            ((MainActivity) getActivity()).requestGpsActive();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    Func loadDataNongskuyPopuler = (action, data) -> {
        // load data
        Call<NongskuyPopulerClass> call = config.configRetrofit().tokoPopuler(latitude, longitude);
        call.enqueue(new Callback<NongskuyPopulerClass>() {
            @Override
            public void onResponse(Call<NongskuyPopulerClass> call, Response<NongskuyPopulerClass> response) {
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        NongskuyPopulerClass nongskuyPopulerClass = response.body();
                        List<NongskuyPopulerData> listTokoPopuler = nongskuyPopulerClass.getTokoPopuler();
                        ArrayList<Nongskuy> arrayListTokoPopuler = new ArrayList<>();
                        BerandaPopulerAdapter berandaPopulerAdapter = new BerandaPopulerAdapter(arrayListTokoPopuler);

                        for (NongskuyPopulerData nongskuyPopulerData : listTokoPopuler) {
                            Nongskuy nongskuy = new Nongskuy(
                                    nongskuyPopulerData.getId(),
                                    nongskuyPopulerData.getGambar(),
                                    nongskuyPopulerData.getNamaToko(),
                                    nongskuyPopulerData.getAlamat(),
                                    nongskuyPopulerData.getTipe(),
                                    nongskuyPopulerData.getJarak(),
                                    nongskuyPopulerData.getRating(),
                                    Double.parseDouble(nongskuyPopulerData.getLatitude()),
                                    Double.parseDouble(nongskuyPopulerData.getLongitude())
                            );

                            arrayListTokoPopuler.add(nongskuy);
                            berandaPopulerAdapter.setShimmer(false);
                            recyclerViewPopuler.setAdapter(berandaPopulerAdapter);
                            berandaPopulerAdapter.notifyDataSetChanged();
                        }

                        //set populer on click
                        berandaPopulerAdapter.setPopulerClickObject(BerandaFragment.this);

                        // enabled click button
                        btnLihatSemuaPopuler.setEnabled(true);
                        action.resolve();
                    }
                }
            }

            @Override
            public void onFailure(Call<NongskuyPopulerClass> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    };

    Func loadDataPromo = (action, data) -> {
        // load data
        Call<PromoClass> call = config.configRetrofit().promo(sharedPreferences.getString("Token", null));
        call.enqueue(new Callback<PromoClass>() {
            @Override
            public void onResponse(Call<PromoClass> call, Response<PromoClass> response) {
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        PromoClass promoClass = response.body();
                        List<PromoData> listPromo = promoClass.getPromo();
                        ArrayList<Promo> arrayListPromo = new ArrayList<>();
                        BerandaPromoAdapter berandaPromoAdapter = new BerandaPromoAdapter(arrayListPromo);

                        for (PromoData promoData : listPromo) {
                            Promo promo = new Promo(
                                    promoData.getIdToko(),
                                    promoData.getNamaToko(),
                                    promoData.getGambarToko(),
                                    promoData.getAlamatToko(),
                                    Double.parseDouble(promoData.getLatitude()),
                                    Double.parseDouble(promoData.getLongitude()),
                                    promoData.getNamaMenu(),
                                    promoData.getHarga(),
                                    promoData.getGambarMenu(),
                                    promoData.getJenisPromo(),
                                    promoData.getPersentase()
                            );

                            arrayListPromo.add(promo);
                            berandaPromoAdapter.setShimmer(false);
                            recyclerViewPromo.setAdapter(berandaPromoAdapter);
                            berandaPromoAdapter.notifyDataSetChanged();
                        }

                        //set promo on click
                        berandaPromoAdapter.setPromoClickObject(BerandaFragment.this);

                        // enabled click button
                        btnLihatSemuaPromo.setEnabled(true);
                        action.resolve();
                    }
                }
            }

            @Override
            public void onFailure(Call<PromoClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    };

    Func loadDataNongskuyTerdekat = (action, data) -> {
        // menampilkan recyclerview dan button terdekat
        recyclerViewTerdekat.setVisibility(View.VISIBLE);
        btnLihatSemuaTerdekat.setVisibility(View.VISIBLE);

        // menghilangkan cardview tidak ditemukan
        cardViewTerdekatTidakDitemukan.setVisibility(View.GONE);

        // load data
        Call<NongskuyTerdekatClass> call = config.configRetrofit().terdekat(latitude, longitude);
        call.enqueue(new Callback<NongskuyTerdekatClass>() {
            @Override
            public void onResponse(Call<NongskuyTerdekatClass> call, Response<NongskuyTerdekatClass> response) {
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        NongskuyTerdekatClass nongskuyTerdekatClass = response.body();
                        List<NongskuyTerdekatData> listTokoTerdekat = nongskuyTerdekatClass.getToko();
                        ArrayList<Nongskuy> arrayListTokoTerdekat = new ArrayList<>();
                        BerandaTerdekatAdapter berandaTerdekatAdapter = new BerandaTerdekatAdapter(arrayListTokoTerdekat);

                        //cek jumlah data
                        if (nongskuyTerdekatClass.getJumlah() == 0) {
                            // menampilkan cardview tidak ditemukan
                            cardViewTerdekatTidakDitemukan.setVisibility(View.VISIBLE);

                            // menghilangkan recyclerview terdekat dan tombol lihat semua
                            recyclerViewTerdekat.setVisibility(View.GONE);
                            btnLihatSemuaTerdekat.setVisibility(View.GONE);
                        } else {
                            for (NongskuyTerdekatData nongskuyTerdekatData : listTokoTerdekat) {
                                Nongskuy nongskuy = new Nongskuy(
                                        nongskuyTerdekatData.getId(),
                                        nongskuyTerdekatData.getGambar(),
                                        nongskuyTerdekatData.getNamaToko(),
                                        nongskuyTerdekatData.getAlamat(),
                                        nongskuyTerdekatData.getTipe(),
                                        nongskuyTerdekatData.getJarak(),
                                        Double.parseDouble(nongskuyTerdekatData.getLatitude()),
                                        Double.parseDouble(nongskuyTerdekatData.getLongitude())
                                );

                                arrayListTokoTerdekat.add(nongskuy);
                                berandaTerdekatAdapter.setShimmer(false);
                                recyclerViewTerdekat.setAdapter(berandaTerdekatAdapter);
                                berandaTerdekatAdapter.notifyDataSetChanged();
                            }

                            // set terdekat onclick
                            berandaTerdekatAdapter.setTerdekatClickObject(BerandaFragment.this);

                            // enabled click button
                            btnLihatSemuaTerdekat.setEnabled(true);
                        }

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

    Func loadData = (action, data) -> {
        latitude = ((Location) data).getLatitude();
        longitude = ((Location) data).getLongitude();

        // load data
        Promise.all(loadDataNongskuyPopuler, loadDataPromo, loadDataNongskuyTerdekat).start();

        action.resolve();
    };

    public void getCurrentLocation() {
        Promise.resolve()
                .then(new Promise(((MainActivity) getActivity()).getCurrentLocation))
                .then(new Promise(loadData))
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

    @Override
    public void onPopulerBerandaClick(Nongskuy nongskuy) {
        Intent intent = new Intent(requireActivity(), DetailNongskuy.class);
        intent.putExtra("IdToko", nongskuy.getIdToko());
        intent.putExtra("NamaToko", nongskuy.getNamaToko());
        intent.putExtra("GambarToko", nongskuy.getGambarToko());
        intent.putExtra("AlamatToko", nongskuy.getAlamatToko());
        intent.putExtra("LatToko", nongskuy.getLatToko());
        intent.putExtra("LongToko", nongskuy.getLongToko());
        startActivity(intent);
    }

    @Override
    public void onPromoBerandaClick(Promo promo) {
        Intent intent = new Intent(requireActivity(), DetailNongskuy.class);
        intent.putExtra("IdToko", promo.getIdToko());
        intent.putExtra("NamaToko", promo.getNamaToko());
        intent.putExtra("GambarToko", promo.getGambarToko());
        intent.putExtra("AlamatToko", promo.getAlamatToko());
        intent.putExtra("LatToko", promo.getLatToko());
        intent.putExtra("LongToko", promo.getLongToko());
        startActivity(intent);
    }

    @Override
    public void onTerdekatBerandaClick(Nongskuy nongskuy) {
        Intent intent = new Intent(requireActivity(), DetailNongskuy.class);
        intent.putExtra("IdToko", nongskuy.getIdToko());
        intent.putExtra("NamaToko", nongskuy.getNamaToko());
        intent.putExtra("GambarToko", nongskuy.getGambarToko());
        intent.putExtra("AlamatToko", nongskuy.getAlamatToko());
        intent.putExtra("LatToko", nongskuy.getLatToko());
        intent.putExtra("LongToko", nongskuy.getLongToko());
        startActivity(intent);
    }
}