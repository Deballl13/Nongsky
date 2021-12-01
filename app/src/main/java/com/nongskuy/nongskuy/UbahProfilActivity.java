package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.MessageResponse;
import com.nongskuy.nongskuy.route.Route;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UbahProfilActivity extends AppCompatActivity {

    EditText inputNamaBaru, inputNoHpBaru;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);

        inputNamaBaru = findViewById(R.id.ubahNama);
        inputNoHpBaru = findViewById(R.id.ubahNoTelepon);
    }

    public void toProfil(View view){
        //ambil inputan user
        String nama = inputNamaBaru.getText().toString().trim();
        String no_hp = inputNoHpBaru.getText().toString().trim();

        //validasi inputan
        if(validation(nama, no_hp).equals(1)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Route route = retrofit.create(Route.class);
            String token = sharedPreferences.getString("TOKEN", null);

            Call<MessageResponse> call = route.ubahProfil(token, nama, no_hp);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    MessageResponse messageResponse = response.body();
                    if (response.code() == 200){
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public Integer validation(String nama, String no_hp) {
        Integer valid = 1;

        // validasi nama
        if(nama.isEmpty()){
            inputNamaBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNamaBaru.setError("Masukkan nama!");
            valid = 0;
        }
        else{
            inputNamaBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi no hp
        if(no_hp.isEmpty()){
            inputNoHpBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHpBaru.setError("Masukkan No Hp!");
            valid = 0;
        }
        else if(no_hp.length() < 12 || no_hp.length() > 12){
            inputNoHpBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHpBaru.setError("Panjang nomor harus 12!");
            valid = 0;
        }
        else if(!no_hp.matches("^[0]{1}[8]{1}[0-9]{10}")){
            inputNoHpBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHpBaru.setError("Format tidak sesuai!");
            valid = 0;
        }
        else{
            inputNoHpBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        return valid;
    }

}