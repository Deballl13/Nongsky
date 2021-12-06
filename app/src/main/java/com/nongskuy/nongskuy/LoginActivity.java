package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nongskuy.nongskuy.model.AuthClass;
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

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmailLogin);
        inputPassword = findViewById(R.id.inputPasswordLogin);
    }

    public void toHome(View view){
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if(validation(email, password).equals(1)){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Route route = retrofit.create(Route.class);

            Call<AuthClass> call = route.login(email, password);
            call.enqueue(new Callback<AuthClass>() {
                @Override
                public void onResponse(Call<AuthClass> call, Response<AuthClass> response) {
                    String message = null;
                    JSONObject jsonObject = null;

                    if(response.code() == 200){
                        if (response.isSuccessful()){
                            AuthClass authClass = response.body();
                            String token = authClass.getUser().getToken();
                            String nama = authClass.getUser().getNama();
                            String email = authClass.getUser().getEmail();
                            String no_hp = authClass.getUser().getNoHp();
                            message = authClass.getMessage();

                            SharedPreferences sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("TOKEN", token);
                            editor.putString("NAMA", nama);
                            editor.putString("EMAIL", email);
                            editor.putString("NO_HP", no_hp);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else if(response.code() == 400){
                        if(!response.isSuccessful()) {
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                message = jsonObject.getString("message");
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
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
                }

                @Override
                public void onFailure(Call<AuthClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public Integer validation(String email, String password){
        Integer valid = 1;
        if(email.isEmpty()){
            inputEmail.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputEmail.setError("Masukkan email!");
            valid = 0;
        }
        else{
            inputEmail.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        if(password.isEmpty()){
            inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputPassword.setError("Masukkan password");
            valid = 0;
        }
        else{
            inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        return valid;
    }

}