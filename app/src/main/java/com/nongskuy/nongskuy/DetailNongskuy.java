package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.adapter.FasilitasAdapter;
import com.nongskuy.nongskuy.adapter.MenuAdapter;
import com.nongskuy.nongskuy.adapter.PopulerAdapter;
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.adapter.RiwayatNongskuyAdapter;
import com.nongskuy.nongskuy.data.MenuData;
import com.nongskuy.nongskuy.data.NongskuyPopulerData;
import com.nongskuy.nongskuy.data.ReviewData;
import com.nongskuy.nongskuy.data.RiwayatNongskuyData;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.MenuClass;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.NongskuyPopulerClass;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.Review;
import com.nongskuy.nongskuy.model.ReviewClass;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;
import com.nongskuy.nongskuy.model.RiwayatNongskuyClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNongskuy extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView rvMenu, rvReview, rvFasilitas;
    private MenuAdapter menuAdapter;
    private ReviewAdapter reviewAdapter;
    private FasilitasAdapter fasilitasAdapter;
    private MaterialButton btnLihatSemuaReview, btnTambahReview, btnPesanTempat;
    private ImageView imageDetailNongskuy;
    private TextView textNamaNongskuy, textAlamatNongskuy;
    private SupportMapFragment supportMapFragment;
    private Integer idToko;
    private String namaToko, gambarToko;
    private Config config;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nongskuy);

        //get id
        btnLihatSemuaReview = findViewById(R.id.btnLihatSemuaReview);
        btnTambahReview = findViewById(R.id.btnTambahReview);
        btnPesanTempat = findViewById(R.id.btnPesanTempat);
        imageDetailNongskuy = findViewById(R.id.imageDetailStore);
        textNamaNongskuy = findViewById(R.id.textNamaToko);
        textAlamatNongskuy = findViewById(R.id.textAlamatToko);

        config = new Config();
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", null);

        if(token != null){
            btnLihatSemuaReview.setVisibility(View.VISIBLE);
            btnTambahReview.setEnabled(true);
            btnPesanTempat.setVisibility(View.VISIBLE);
        }

        //Get Data Intent
        Intent intent = getIntent();
        idToko = intent.getIntExtra("IdToko", 0);
        namaToko = intent.getStringExtra("NamaToko");
        gambarToko = intent.getStringExtra("GambarToko");

        //Set Detail Data Nongskuy
        textNamaNongskuy.setText(namaToko);
        textAlamatNongskuy.setText(intent.getStringExtra("AlamatToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(gambarToko))
                .into(imageDetailNongskuy);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_store);
        supportMapFragment.getMapAsync(this);

       // Menu Recycler View
        LinearLayoutManager linearLayoutManagerMenu = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMenu = findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(linearLayoutManagerMenu);
        loadDataMenu(idToko, token);


        //Review Recycler View
        LinearLayoutManager linearLayoutManagerReview = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvReview = findViewById(R.id.rvReviewStore);
        rvReview.setLayoutManager(linearLayoutManagerReview);
        loadDataReview(idToko);


        //Fasilitas Recyclerview
        fasilitasAdapter = new FasilitasAdapter();
        fasilitasAdapter.setListFasilitas(dataDummyFasilitas());

        LinearLayoutManager linearLayoutManagerFasilitas = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFasilitas = findViewById(R.id.rvFasilitas);
        rvFasilitas.setAdapter(fasilitasAdapter);
        rvFasilitas.setLayoutManager(linearLayoutManagerFasilitas);
    }

    //Intent ke halaman pesan tempat
    public void toPesanTempat(View view){
        Intent intent = new Intent(DetailNongskuy.this, PesanTempatActivity.class);
        intent.putExtra("IdToko", idToko);
        intent.putExtra("NamaToko", namaToko);
        intent.putExtra("GambarToko", gambarToko);
        startActivity(intent);
    }

    //Intent ke halaman detail review
    public void toReview(View view){
        Intent intent = new Intent(DetailNongskuy.this, DetailReview.class);
        intent.putExtra("IdToko", idToko);
        intent.putExtra("NamaToko", namaToko);
        intent.putExtra("GambarToko", gambarToko);
        startActivity(intent);
    }

    private void loadDataMenu(Integer idToko, String token) {
        Call<MenuClass> call;
        rvMenu.setAdapter(new MenuAdapter(null));

        if(token != null){
             call = config.configRetrofit().menu(idToko);

        }
        else{
            call = config.configRetrofit().menu(idToko, true);
        }

        call.enqueue(new Callback<MenuClass>() {
            @Override
            public void onResponse(Call<MenuClass> call, Response<MenuClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        MenuClass menuClass = response.body();
                        List<MenuData> listMenu = menuClass.getMenu();
                        ArrayList<Menu> arrayListMenu = new ArrayList<>();
                        MenuAdapter menuAdapter = new MenuAdapter(arrayListMenu);

//                        if(token != null){
                            for(MenuData menuData : listMenu){
                                Menu menu = new Menu(
                                        menuData.getId(),
                                        menuData.getNamaMenu(),
                                        menuData.getGambar(),
                                        menuData.getHarga(),
                                        menuData.getJenisPromo(),
                                        menuData.getPersentase()
                                );
                                arrayListMenu.add(menu);
                                menuAdapter.setShimmer(false);
                                rvMenu.setAdapter(menuAdapter);
                                menuAdapter.notifyDataSetChanged();
                            }
                        //}

//                        else{
//                            for(MenuData menuData : listMenu){
//                                Menu menu = new Menu(
//                                        menuData.getId(),
//                                        menuData.getNamaMenu(),
//                                        menuData.getGambar(),
//                                        menuData.getHarga(),
//                                        menuData.get
//                                );
//                                arrayListMenu.add(menu);
//                                menuAdapter.setShimmer(false);
//                                rvMenu.setAdapter(menuAdapter);
//                                menuAdapter.notifyDataSetChanged();
//                            }
//
//                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataReview(Integer idToko) {
        rvReview.setAdapter(new ReviewAdapter(null));
        Call<ReviewClass> call = config.configRetrofit().review(idToko);
        call.enqueue(new Callback<ReviewClass>() {
            @Override
            public void onResponse(Call<ReviewClass> call, Response<ReviewClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        ReviewClass reviewClass = response.body();
                        List<ReviewData> listReview = reviewClass.getReview();
                        ArrayList<Review> arrayListReview = new ArrayList<>();
                        ReviewAdapter reviewAdapter = new ReviewAdapter(arrayListReview);

                        //cek isi list
                        if(listReview.size() == 0){
                            // menghilangkan recyclerview
                            rvReview.setVisibility(View.GONE);
                            btnLihatSemuaReview.setVisibility(View.GONE);

                            // menampilkan review tidak ada
                            findViewById(R.id.ReviewTidakDitemukan).setVisibility(View.VISIBLE);
                        }

                        for (ReviewData reviewData : listReview){
                            Review review = new Review(
                                    reviewData.getId(),
                                    reviewData.getNama(),
                                    reviewData.getKomentar(),
                                    reviewData.getTanggal(),
                                    reviewData.getRating()
                            );
                            arrayListReview.add(review);
                            reviewAdapter.setShimmer(false);
                            rvReview.setAdapter(reviewAdapter);
                            reviewAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Nongskuy> dataDummyFasilitas(){
        ArrayList<Nongskuy> listFasilitas = new ArrayList<>();
        listFasilitas.add(new Nongskuy(
            "24 Hours"
        ));
        listFasilitas.add(new Nongskuy(
                "WIFI"
        ));

        return listFasilitas;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Intent intent = getIntent();
        LatLng latLng = new LatLng(intent.getDoubleExtra("LatToko", 0.0),
                intent.getDoubleExtra("LongToko", 0.0));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        googleMap.addMarker(markerOptions);
    }
}