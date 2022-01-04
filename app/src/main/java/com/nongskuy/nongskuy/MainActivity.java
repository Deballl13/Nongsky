package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;
    private LocationRequest locationRequest;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationMenu);
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", MODE_PRIVATE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        loadFragments(new BerandaFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public boolean loadFragments(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_beranda:
                fragment = new BerandaFragment();
                break;

            case R.id.menu_promo:
                fragment = new PromoFragment();
                break;

            case R.id.menu_terdekat:
                fragment = new TerdekatFragment();
                break;

            case R.id.menu_profil:
                fragment = new ProfilFragment();
                break;
        }

        return loadFragments(fragment);
    }

    @Override
    public void onBackPressed() {
        String token = sharedPreferences.getString("Token", null);

        if (bottomNavigationView.getSelectedItemId() == R.id.menu_beranda) {
            super.onBackPressed();

            if (token != null) {
                this.finishAffinity();
            } else {
                finish();
            }
        } else {
            bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void requestGpsActive() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MainActivity.this, 101);
                            } catch (IntentSender.SendIntentException sendIntentException) {
                                sendIntentException.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });
    }

    public void getCurrentLocation() {
        // get current location
        CancellationTokenSource cts = new CancellationTokenSource();
        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cts.getToken());
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                if (location != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Latitude", String.valueOf(location.getLatitude()));
                    editor.putString("Longitude", String.valueOf(location.getLongitude()));
                    editor.apply();
                }
            }
        });
    }
}