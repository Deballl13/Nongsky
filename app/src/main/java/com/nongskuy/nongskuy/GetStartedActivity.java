package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
    }

    public void toLogin(View view){
        Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void toRegister(View view){
        Intent intent = new Intent(GetStartedActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void toHomeAsGuest(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}