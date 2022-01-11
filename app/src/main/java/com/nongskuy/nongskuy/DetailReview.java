package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nongskuy.nongskuy.adapter.FasilitasAdapter;
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.adapter.ReviewDetailAdapter;
import com.nongskuy.nongskuy.data.NongskuyData;
import com.nongskuy.nongskuy.data.ReviewData;
import com.nongskuy.nongskuy.model.MessageClass;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.NongskuyClass;
import com.nongskuy.nongskuy.model.Review;
import com.nongskuy.nongskuy.model.ReviewClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReview extends AppCompatActivity {

    private ImageView imageDetailReview;
    private TextView textNamaDetailReview;
    private RecyclerView rvReviewDetail;
    private Integer idToko;
    private String token;
    private Config config;
    private RatingBar ratingStars;
    private Double rates;
    private TextInputEditText textKomentar;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        //Id
        imageDetailReview = findViewById(R.id.imageDetailReview);
        textNamaDetailReview = findViewById(R.id.textNamaReview);
        ratingStars = findViewById(R.id.ratingBarReview);
        textKomentar = findViewById(R.id.textFieldKomentar);

        config = new Config();
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", null);

        //Get Data Intent
        Intent intent = getIntent();
        idToko = intent.getIntExtra("IdToko", 0);
        textNamaDetailReview.setText(intent.getStringExtra("NamaToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(intent.getStringExtra("GambarToko")))
                .into(imageDetailReview);

        //Recycler view Review Detail
        //Review Recycler View
//        reviewDetailAdapter = new ReviewDetailAdapter();
//        reviewDetailAdapter.setListReview(dataDummyReview());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvReviewDetail = findViewById(R.id.rvReviewDetail);
//        rvReviewDetail.setAdapter(reviewDetailAdapter);
        rvReviewDetail.setLayoutManager(linearLayoutManager);
        //loadDataDetailReview(idToko);
    }

    public void simpanReview(View view) {
        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                rates = ratingBar.getRating();
                rates = Double.parseDouble(String.valueOf(rating));
            }
        });

        //mengambil nilai
        String komentar = String.valueOf(textKomentar.getText());
        Toast.makeText(this, String.valueOf(rates) + " " + komentar + " " + idToko + " " + token, Toast.LENGTH_SHORT).show();

//        if(validation(rates, komentar).equals(1)){
//            Call<MessageClass> call = config.configRetrofit().addReview(idToko, rates, komentar, token);
//            call.enqueue(new Callback<MessageClass>() {
//                @Override
//                public void onResponse(Call<MessageClass> call, Response<MessageClass> response) {
//                    String message = null;
//                    JSONObject jsonObject = null;
//
//                    if (response.code() == 200){
//                        if (response.isSuccessful()) {
//                            message = response.body().getMessage();
//                        }
//                    }
//                    else if(response.code() == 403){
//                        if(!response.isSuccessful()){
//                            try {
//                                jsonObject = new JSONObject(response.errorBody().string());
//                                message = jsonObject.getString("message");
//                            } catch (JSONException | IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<MessageClass> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

    }

    public Integer validation(Double rates, String komentar) {
        Integer valid = 1;
        TextInputLayout komentarLayout = findViewById(R.id.textLayoutKomentar);

        //validasi komentar
        if(komentar.isEmpty()){
            komentarLayout.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            komentarLayout.setError("Masukkan komentar!");
            valid = 0;
        }
        else{
            komentarLayout.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        //validasi rating
        if(rates == null){
            Toast.makeText(this, "Masukkan rating!", Toast.LENGTH_SHORT).show();
            valid = 0;
        }

        return valid;
    }


//    private void loadDataDetailReview(Integer idToko) {
//        Call<ReviewClass> call = config.configRetrofit().review(idToko);
//        call.enqueue(new Callback<ReviewClass>() {
//            @Override
//            public void onResponse(Call<ReviewClass> call, Response<ReviewClass> response) {
//                if(response.code() == 200){
//                    if(response.isSuccessful()){
//                        ReviewClass reviewClass = response.body();
//                        List<ReviewData> listReviewDetail = reviewClass.getReview();
//                        ArrayList<Review> arrayListReviewDetail = new ArrayList<>();
//                        ReviewDetailAdapter reviewDetailAdapter = new ReviewDetailAdapter(arrayListReviewDetail);
//
//                        for (ReviewData reviewData : listReviewDetail){
//                            Review review = new Review(
//                                    reviewData.getId(),
//                                    reviewData.getNama(),
//                                    reviewData.getKomentar(),
//                                    reviewData.getTanggal(),
//                                    reviewData.getRating()
//                            );
//                            arrayListReviewDetail.add(review);
////                            reviewAdapter.setShimmer(false);
//                            rvReviewDetail.setAdapter(reviewDetailAdapter);
//                            reviewDetailAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ReviewClass> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public ArrayList<Review> dataDummyReview(){
//        ArrayList<Review> listReview = new ArrayList<>();
//        listReview.add(new Review(
//                1, 1,1,
//                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
//                "19/10/2021",
//                "4.5"
//        ));
//        listReview.add(new Review(
//                2, 2,1,
//                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
//                "19/10/2022",
//                "4.8"
//        ));
//        listReview.add(new Review(
//                3, 3,1,
//                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
//                "19/10/2023",
//                "4.7"
//        ));
//
//        return listReview;
//    }
}