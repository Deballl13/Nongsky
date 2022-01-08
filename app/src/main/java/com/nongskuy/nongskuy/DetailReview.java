package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.adapter.ReviewDetailAdapter;
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class DetailReview extends AppCompatActivity {

    ImageView imageDetailReview;
    TextView textNamaDetailReview;
    RecyclerView rvReviewDetail;
    ReviewDetailAdapter reviewDetailAdapter;

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

        //Recycler view Review Detail
        //Review Recycler View
        reviewDetailAdapter = new ReviewDetailAdapter();
        reviewDetailAdapter.setListReview(dataDummyReview());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvReviewDetail = findViewById(R.id.rvReviewDetail);
        rvReviewDetail.setAdapter(reviewDetailAdapter);
        rvReviewDetail.setLayoutManager(linearLayoutManager);
    }

    public ArrayList<Review> dataDummyReview(){
        ArrayList<Review> listReview = new ArrayList<>();
        listReview.add(new Review(
                1, 1,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2021",
                "4.5"
        ));
        listReview.add(new Review(
                2, 2,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2022",
                "4.8"
        ));
        listReview.add(new Review(
                3, 3,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2023",
                "4.7"
        ));

        return listReview;
    }
}