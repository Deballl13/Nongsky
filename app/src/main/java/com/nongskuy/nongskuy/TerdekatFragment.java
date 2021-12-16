package com.nongskuy.nongskuy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class TerdekatFragment extends Fragment {

    private SupportMapFragment supportMapFragment;
    private SearchView searchView;


       public TerdekatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_terdekat, container, false);

        //Initialize view search
//        searchView = find
        //Initialize Map Fragment
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        //Async Map
        supportMapFragment.getMapAsync(googleMap -> {
            //When map is loaded
            googleMap.setOnMapClickListener(latLng -> {
                //when clicked on map
                //initialized marker
                MarkerOptions markerOptions = new MarkerOptions();
                //set marker position
                markerOptions.position(latLng);
                //set Title marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                //remove all marker
                googleMap.clear();
                //Animating to zoom marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng, 8
                ));
            //Add marker on map
            googleMap.addMarker(markerOptions);
            });
        });
        return view;
    }
}