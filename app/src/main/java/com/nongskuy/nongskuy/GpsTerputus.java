package com.nongskuy.nongskuy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import com.google.android.material.button.MaterialButton;

public class GpsTerputus extends AppCompatActivity {

    private MaterialButton buttonGps;
    private LocationManager locationManager;
    private Boolean isGpsEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_terputus);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // request location active
        buttonGps = findViewById(R.id.buttonGpsTerputus);
        buttonGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 103);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // jika gps hidup
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGpsEnabled) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 103){
            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGpsEnabled) {
                Intent intent = new Intent(GpsTerputus.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}