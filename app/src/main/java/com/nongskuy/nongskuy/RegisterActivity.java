package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nongskuy.nongskuy.model.MessageResponse;
import com.nongskuy.nongskuy.route.Route;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText inputEmail, inputNama, inputNoHp, inputPassword, inputConfirmPassword;
    CheckBox checkBoxAgrement;

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
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Route route = retrofit.create(Route.class);

            Log.i("sadasd", email+" "+nama+" "+no_hp+" "+password+" "+confirm_passowrd);
            Call<MessageResponse> call = route.register(email, nama, no_hp, password);
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    MessageResponse messageResponse = response.body();
                    if (response.code() == 200){
                        if (response.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            Toast.makeText(getApplicationContext(), messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
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