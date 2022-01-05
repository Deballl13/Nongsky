package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailReview extends AppCompatActivity {

    ImageView imageDetailReview;
    TextView textNamaDetailReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        //Id
        imageDetailReview = findViewById(R.id.imageDetailReview);
        textNamaDetailReview = findViewById(R.id.textNamaReview);

        //Get Data Intent
        Intent intent = getIntent();
        textNamaDetailReview.setText(intent.getStringExtra("NamaToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(intent.getStringExtra("GambarToko")))
                .into(imageDetailReview);
    }
}