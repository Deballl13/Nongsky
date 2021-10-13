package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.BottomNavigationMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,new BerandaFragment()).commit();
    }

    //Listener Nav Bar
   BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected_fragment = null;

            switch(item.getItemId()){
                case R.id.menu_beranda:
                    selected_fragment = new BerandaFragment();
                    break;

                case R.id.menu_promo:
                    selected_fragment = new PromoFragment();
                    break;

                case R.id.menu_terdekat:
                    selected_fragment = new TerdekatFragment();
                    break;

                case R.id.menu_profil:
                    selected_fragment = new ProfilFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,selected_fragment).commit();
            return true;
        }
    };
}