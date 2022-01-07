package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.MessageClass;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilActivity extends AppCompatActivity {

    private EditText inputNamaBaru, inputNoHpBaru;
    private SharedPreferences sharedPreferences;
    private Config config;

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
            config = new Config();
            String token = sharedPreferences.getString("Token", null);

            Call<MessageClass> call = config.configRetrofit().ubahProfil(token, nama, no_hp);
            toggleViewProgressBar(true);
            call.enqueue(new Callback<MessageClass>() {
                @Override
                public void onResponse(Call<MessageClass> call, Response<MessageClass> response) {
                    String message = null;
                    JSONObject jsonObject = null;

                    if (response.code() == 200){
                        if (response.isSuccessful()) {
                            message = response.body().getMessage();
                            Intent intent = new Intent();
                            intent.putExtra("Nama", nama);
                            intent.putExtra("NoHp", no_hp);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                    else if(response.code() == 403){
                        if(!response.isSuccessful()){
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                message = jsonObject.getString("message");
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    toggleViewProgressBar(false);
                }

                @Override
                public void onFailure(Call<MessageClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    toggleViewProgressBar(false);
                }
            });
        }
    }

    public void toggleViewProgressBar(Boolean active){
        MaterialButton buttonUbahProfil = findViewById(R.id.buttonUbahProfil1);
        ProgressBar progressBar = findViewById(R.id.progressBarUbahProfil);

        if(active){
            inputNamaBaru.getText().clear();
            inputNoHpBaru.getText().clear();
            inputNamaBaru.setEnabled(false);
            inputNoHpBaru.setEnabled(false);
            buttonUbahProfil.setEnabled(false);
            buttonUbahProfil.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            inputNamaBaru.setEnabled(true);
            inputNoHpBaru.setEnabled(true);
            buttonUbahProfil.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inputNamaBaru.getBackground().clearColorFilter();
        inputNoHpBaru.getBackground().clearColorFilter();
        finish();
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