package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationMenu);
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", MODE_PRIVATE);

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
            if (resultCode == Activity.RESULT_OK) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}