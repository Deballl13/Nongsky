package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class UbahProfilActivity extends AppCompatActivity {

    private MaterialButton buttonUbahProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

//        buttonUbahProfil = findViewById(R.id.buttonUbahProfil1);
//        buttonUbahProfil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                buttonUbahProfil.setVisibility(View.GONE);
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.containerUbahProfil, new ProfilFragment()).commit();
//            }
//        });
    }

}