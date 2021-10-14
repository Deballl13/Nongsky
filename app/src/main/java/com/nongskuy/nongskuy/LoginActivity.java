package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        String logout = intent.getStringExtra("LOGOUT");
        if (logout != null){
            Toast.makeText(this, logout, Toast.LENGTH_SHORT).show();
        }

    }

    public void toHome(View view){

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(email.equals("admin") && password.equals("admin123")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "login gagal", Toast.LENGTH_SHORT).show();
        }

    }



}