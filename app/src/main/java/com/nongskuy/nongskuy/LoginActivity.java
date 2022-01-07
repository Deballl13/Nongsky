package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.AuthClass;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private Config config;

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
            config = new Config();
            Call<AuthClass> call = config.configRetrofit().login(email, password);
            toggleViewProgressBar(true);
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
                            editor.putString("Token", token);
                            editor.putString("Nama", nama);
                            editor.putString("Email", email);
                            editor.putString("NoHp", no_hp);
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

                    // menampilkan pesan
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    toggleViewProgressBar(false);
                }

                @Override
                public void onFailure(Call<AuthClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    toggleViewProgressBar(false);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inputEmail.getBackground().clearColorFilter();
        inputPassword.getBackground().clearColorFilter();
        finish();
    }

    public void toggleViewProgressBar(Boolean active){
        MaterialButton buttonLogin = findViewById(R.id.buttonLogin);
        ProgressBar progressBar = findViewById(R.id.progressBarLogin);

        if(active){
            inputEmail.getText().clear();
            inputPassword.getText().clear();
            inputEmail.setEnabled(false);
            inputPassword.setEnabled(false);
            buttonLogin.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            inputEmail.setEnabled(true);
            inputPassword.setEnabled(true);
            buttonLogin.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    public Integer validation(String email, String password){
        Integer valid = 1;
        if(email.isEmpty()){
            inputEmail.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
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