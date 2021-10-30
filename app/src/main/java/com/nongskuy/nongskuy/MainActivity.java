package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    static String userEmail = null;
    String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationMenu);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("EMAIL");
        intent.removeExtra("EMAIL");

        loadFragments(new BerandaFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public boolean loadFragments(Fragment fragment){

        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,fragment)
                    .commit();
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){
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

        if (bottomNavigationView.getSelectedItemId() == R.id.menu_beranda){
            super.onBackPressed();

            if(userEmail != null){
                this.finishAffinity();
            }
            else{
                finish();
            }
        }
        else{
            bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
        }

    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        final String data = this.getIntent().getStringExtra("FRAGMENT");
//        if(data != null){
//            switch (data){
//                case "Beranda":
//                    loadFragments(new BerandaFragment());
//                    bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
//                    break;
//                case "Promo":
//                    loadFragments(new PromoFragment());
//                    bottomNavigationView.setSelectedItemId(R.id.menu_promo);
//                    break;
//                case "Terdekat":
//                    loadFragments(new TerdekatFragment());
//                    bottomNavigationView.setSelectedItemId(R.id.menu_terdekat);
//                    break;
//                case "Profil":
//                    loadFragments(new ProfilFragment());
//                    bottomNavigationView.setSelectedItemId(R.id.menu_profil);
//                    break;
//            }
//        }
//    }
}