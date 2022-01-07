package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
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


public class GantiKataSandiActivity extends AppCompatActivity {

    private EditText inputPasswordBaru, inputConfirmPasswordBaru;
    private SharedPreferences sharedPreferences;
    private Config config;

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
            config = new Config();
            String token = sharedPreferences.getString("Token", null);

            Call<MessageClass> call = config.configRetrofit().ubahPassword(token, password);
            toggleViewProgressBar(true);
            call.enqueue(new Callback<MessageClass>() {
                @Override
                public void onResponse(Call<MessageClass> call, Response<MessageClass> response) {
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
                    toggleViewProgressBar(false);
                    finish();
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
        MaterialButton buttonGantiPassowrd = findViewById(R.id.buttonGantiKataSandi);
        ProgressBar progressBar = findViewById(R.id.progressBarGantiKataSandi);

        if(active){
            inputPasswordBaru.getText().clear();
            inputConfirmPasswordBaru.getText().clear();
            inputPasswordBaru.setEnabled(false);
            inputConfirmPasswordBaru.setEnabled(false);
            buttonGantiPassowrd.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            inputPasswordBaru.setEnabled(true);
            inputConfirmPasswordBaru.setEnabled(true);
            buttonGantiPassowrd.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inputPasswordBaru.getBackground().clearColorFilter();
        inputConfirmPasswordBaru.getBackground().clearColorFilter();
        finish();
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