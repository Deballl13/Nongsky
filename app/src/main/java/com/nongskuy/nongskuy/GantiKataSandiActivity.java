package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class GantiKataSandiActivity extends AppCompatActivity {

    private MaterialButton buttonGantiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_kata_sandi);

//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("NAVIGATION", R.id.menu_profil)
//        startActivity(intent);
    }

}