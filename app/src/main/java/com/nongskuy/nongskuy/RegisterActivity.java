package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.MessageClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputNama, inputNoHp, inputPassword, inputConfirmPassword;
    private CheckBox checkBoxAgrement;
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = findViewById(R.id.inputEmailRegister);
        inputNama = findViewById(R.id.inputNamaRegister);
        inputNoHp = findViewById(R.id.inputPhoneRegister);
        inputPassword = findViewById(R.id.inputPasswordRegister);
        inputConfirmPassword = findViewById(R.id.inputConfirmPasswordRegister);
        checkBoxAgrement = (CheckBox) findViewById(R.id.checkBoxAgrement);

        // jika checkbox agrement dicentang
        checkBoxAgrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxAgrement.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FFCC00")));
            }
        });
    }

    public void toLogin(View view){
        String email = inputEmail.getText().toString().trim();
        String nama = inputNama.getText().toString().trim();
        String no_hp = inputNoHp.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirm_passowrd = inputConfirmPassword.getText().toString().trim();

        // jika checkbox agrement tidak dicentang
        if(!checkBoxAgrement.isChecked()){
            checkBoxAgrement.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
        }

        if(validation(email, nama, no_hp, password, confirm_passowrd).equals(1) && checkBoxAgrement.isChecked()){
            config = new Config();

            Call<MessageClass> call = config.configRetrofit().register(email, nama, no_hp, password);
            toggleViewProgressBar(true);
            call.enqueue(new Callback<MessageClass>() {
                @Override
                public void onResponse(Call<MessageClass> call, Response<MessageClass> response) {
                    if (response.code() == 200){
                        if (response.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            toggleViewProgressBar(false);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void toggleViewProgressBar(Boolean active){
        MaterialButton buttonRegister = findViewById(R.id.buttonRegister);
        ProgressBar progressBar = findViewById(R.id.progressBarRegister);

        if(active){
            inputEmail.getText().clear();
            inputNama.getText().clear();
            inputNoHp.getText().clear();
            inputPassword.getText().clear();
            inputConfirmPassword.getText().clear();
            checkBoxAgrement.setChecked(false);
            inputEmail.setEnabled(false);
            inputNama.setEnabled(false);
            inputNoHp.setEnabled(false);
            inputPassword.setEnabled(false);
            inputConfirmPassword.setEnabled(false);
            checkBoxAgrement.setEnabled(false);
            buttonRegister.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            inputEmail.setEnabled(true);
            inputNama.setEnabled(true);
            inputNoHp.setEnabled(true);
            inputPassword.setEnabled(true);
            inputConfirmPassword.setEnabled(true);
            checkBoxAgrement.setEnabled(true);
            buttonRegister.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        inputEmail.getBackground().clearColorFilter();
        inputNama.getBackground().clearColorFilter();
        inputNoHp.getBackground().clearColorFilter();
        inputPassword.getBackground().clearColorFilter();
        inputConfirmPassword.getBackground().clearColorFilter();
        finish();
    }

    public Integer validation(String email, String nama, String no_hp, String password, String confirm_password){
        Integer valid = 1;

        // validasi email
        if(email.isEmpty()){
            inputEmail.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputEmail.setError("Masukkan email!");
            valid = 0;
        }
        else{
            inputEmail.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi nama
        if(nama.isEmpty()){
            inputNama.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNama.setError("Masukkan nama!");
            valid = 0;
        }
        else{
            inputNama.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi no hp
        if(no_hp.isEmpty()){
            inputNoHp.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHp.setError("Masukkan No Hp!");
            valid = 0;
        }
        else if(no_hp.length() < 12 || no_hp.length() > 12){
            inputNoHp.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHp.setError("Panjang nomor harus 12!");
            valid = 0;
        }
        else if(!no_hp.matches("^[0]{1}[8]{1}[0-9]{10}")){
            inputNoHp.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputNoHp.setError("Format tidak sesuai!");
            valid = 0;
        }
        else{
            inputNoHp.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi password
        if(password.isEmpty()){
            inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputPassword.setError("Masukkan Password!");
            valid = 0;
        }
        else{
            inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi confirm password
        if(confirm_password.isEmpty()){
            inputConfirmPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            inputConfirmPassword.setError("Masukkan Konfirmasi Password!");
            valid = 0;
        }
        else{
            inputConfirmPassword.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // validasi password sama dengan confirmasi password
        if(!password.isEmpty() && !confirm_password.isEmpty()){
            if(!password.equals(confirm_password)){
                inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                inputPassword.setError("Password tidak sesuai!");
                inputConfirmPassword.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                inputConfirmPassword.setError("Password tidak sesuai!");
                valid = 0;
            }
            else{
                inputPassword.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
                inputConfirmPassword.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
                inputPassword.setError(null);
                inputConfirmPassword.setError(null);
            }
        }

        return valid;
    }
}