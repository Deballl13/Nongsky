package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class GantiKataSandiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_kata_sandi);
    }

    public void toProfil(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TOPROFIL", "kata sandi berhasil diubah");
        startActivity(intent);
        finish();
    }

}