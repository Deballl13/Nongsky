package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText inputEmail;
    EditText inputPassword;
    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail1);
        inputPassword = findViewById(R.id.inputPassword1);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);

    }

    public void toHome(View view){

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if(email.equals("admin") && password.equals("admin123")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("EMAIL", email);

            //remember me
//            SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
//            String checkbox = preferences.getString("remember", "");

            startActivity(intent);
            finish();
            Toast.makeText(this, "login berhasil", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "login gagal", Toast.LENGTH_SHORT).show();
        }

    }

    //remember me
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

//        if (compoundButton.isChecked()){
//            SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("remember", "true");
//            editor.apply();
//        }
//        else if (!compoundButton.isChecked()){
//            SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("remember", "false");
//            editor.apply();
//        }
    }
}