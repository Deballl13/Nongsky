package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        SharedPreferences sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", null);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if (token != null){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GetStartedActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}