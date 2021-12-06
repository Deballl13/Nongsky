package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nongskuy.nongskuy.model.MessageResponse;
import com.nongskuy.nongskuy.route.Route;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GantiKataSandiActivity extends AppCompatActivity {

    EditText inputPasswordBaru, inputConfirmPasswordBaru;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_kata_sandi);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);

        inputPasswordBaru = findViewById(R.id.inputPasswordBaru);
        inputConfirmPasswordBaru = findViewById(R.id.inputConfirmPasswordBaru);
    }

    public void toProfil(View view){
        //ambil inputan user
        String password = inputPasswordBaru.getText().toString().trim();
        String confirm_password = inputConfirmPasswordBaru.getText().toString().trim();

        //kondisi validasi inputan
        if(validation(password, confirm_password).equals(1)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Route route = retrofit.create(Route.class);
            String token = sharedPreferences.getString("TOKEN", null);

            Call<MessageResponse> call = route.ubahPassword(token, password);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    String message = null;
                    JSONObject jsonObject = null;

                    if (response.code() == 200){
                        if (response.isSuccessful()) {
                            message = response.body().getMessage();
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
                    finish();
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public Integer validation(String password, String confirm_password){
        Integer valid = 1;

        // validasi password
        if(password.isEmpty()){
            inputPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputPasswordBaru.setError("Masukkan Password!");
            valid = 0;
        }
        else{
            inputPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi confirm password
        if(confirm_password.isEmpty()){
            inputConfirmPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputConfirmPasswordBaru.setError("Masukkan Konfirmasi Password!");
            valid = 0;
        }
        else{
            inputConfirmPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi password sama dengan confirmasi password
        if(!password.isEmpty() && !confirm_password.isEmpty()){
            if(!password.equals(confirm_password)){
                inputPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                inputPasswordBaru.setError("Password tidak sesuai!");
                inputConfirmPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                inputConfirmPasswordBaru.setError("Password tidak sesuai!");
                valid = 0;
            }
            else{
                inputPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
                inputConfirmPasswordBaru.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
                inputPasswordBaru.setError(null);
                inputConfirmPasswordBaru.setError(null);
            }
        }
        return valid;
    }


}