package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.adapter.ReviewDetailAdapter;
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class DetailReview extends AppCompatActivity {

    private double rate;

    ImageView imageDetailReview;
    TextView textNamaDetailReview;
    RecyclerView rvReviewDetail;
    ReviewDetailAdapter reviewDetailAdapter;
    RatingBar ratingStars;
    TextView nilaiRating;
    TextInputEditText textKomentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        //Id
        imageDetailReview = findViewById(R.id.imageDetailReview);
        textNamaDetailReview = findViewById(R.id.textNamaReview);
        ratingStars = findViewById(R.id.ratingBarReview);
        textKomentar = findViewById(R.id.textFieldKomentar);
        nilaiRating = findViewById(R.id.angkaReview);



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

    public void simpanReview(View view){
        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = ratingBar.getRating();
                double rates = (double) rate;
                Log.i("rating", String.valueOf(rates));
                //nilaiRating.setText(String.valueOf(rates));
            }
        });

        String komentar = String.valueOf(textKomentar.getText());
        //Float rates = Double.parseDouble(rate);
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
                "“Kurang suka. Terlalu ramai. Waktu makan siang habis hanya menunggu pesanan tiba”",
                "9/7/2021",
                "2.5"
        ));
        listReview.add(new Review(
                3, 3,1,
                "“Gue suka nongkrong disini karena ice coffee jellynya enak. Dibandingkan K*C lebih suka ini”",
                "10/1/2022",
                "4.7"
        ));

        return listReview;
    }
}