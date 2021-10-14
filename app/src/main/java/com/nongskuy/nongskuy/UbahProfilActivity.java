package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class UbahProfilActivity extends AppCompatActivity {

    private MaterialButton buttonUbahProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
    }

    public void toProfil(View view){
        Intent intent = new Intent();
        intent.putExtra("TOPROFIL", "profil berhasil diubah");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }

}