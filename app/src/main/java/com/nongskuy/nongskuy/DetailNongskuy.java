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
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class DetailNongskuy extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView rvMenu, rvReview, rvFasilitas;
    private MenuAdapter menuAdapter;
    private ReviewAdapter reviewAdapter;
    private FasilitasAdapter fasilitasAdapter;
    private MaterialButton btnLihatSemuaReview, btnTambahReview;
    private ImageView imageDetailNongskuy;
    private TextView textNamaNongskuy, textAlamatNongskuy;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nongskuy);

        //get id
        btnLihatSemuaReview = findViewById(R.id.btnLihatSemuaReview);
        btnTambahReview = findViewById(R.id.btnTambahReview);
        imageDetailNongskuy = findViewById(R.id.imageDetailStore);
        textNamaNongskuy = findViewById(R.id.textNamaToko);
        textAlamatNongskuy = findViewById(R.id.textAlamatToko);

        //Get Data Intent
        Intent intent = getIntent();
        String idToko = intent.getStringExtra("IdToko");
        textNamaNongskuy.setText(intent.getStringExtra("NamaToko"));
        textAlamatNongskuy.setText(intent.getStringExtra("AlamatToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(intent.getStringExtra("GambarToko")))
                .into(imageDetailNongskuy);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_store);
        supportMapFragment.getMapAsync(this);

        //Menu Recycler View
        menuAdapter = new MenuAdapter();
        menuAdapter.setListMenu(dataDummyMenu());

        LinearLayoutManager linearLayoutManagerMenu = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMenu = findViewById(R.id.rvMenu);
        rvMenu.setAdapter(menuAdapter);
        rvMenu.setLayoutManager(linearLayoutManagerMenu);

        //Review Recycler View
        reviewAdapter = new ReviewAdapter();
        reviewAdapter.setListReview(dataDummyReview());

        LinearLayoutManager linearLayoutManagerReview = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvReview = findViewById(R.id.rvReviewStore);
        rvReview.setAdapter(reviewAdapter);
        rvReview.setLayoutManager(linearLayoutManagerReview);

        //Fasilitas Recyclerview
        fasilitasAdapter = new FasilitasAdapter();
        fasilitasAdapter.setListFasilitas(dataDummyFasilitas());

        LinearLayoutManager linearLayoutManagerFasilitas = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFasilitas = findViewById(R.id.rvFasilitas);
        rvFasilitas.setAdapter(fasilitasAdapter);
        rvFasilitas.setLayoutManager(linearLayoutManagerFasilitas);
    }

    public void toPesanTempat(View view){
        Intent intent = new Intent(DetailNongskuy.this, PesanTempatActivity.class);

        startActivity(intent);
    }

    public void toReview(View view){
        Intent intent = new Intent(DetailNongskuy.this, DetailReview.class);
        startActivity(intent);
    }

    public ArrayList<Menu> dataDummyMenu(){
        ArrayList<Menu> listMenu = new ArrayList<>();
        listMenu.add(new Menu(
                1,
                1,
                "Paket Hemat 1",
                20000
        ));
        listMenu.add(new Menu(
                2,
                1,
                "Paket Hemat 2",
                21000
        ));
        listMenu.add(new Menu(
                3,
                1,
                "Paket Hemat 3",
                25000
        ));

        return listMenu;
    }

    public ArrayList<Review> dataDummyReview(){
        ArrayList<Review> listReview = new ArrayList<>();
        listReview.add(new Review(
                1, 1,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2021",
                4.5
        ));
        listReview.add(new Review(
                2, 2,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2022",
                4.8
        ));
        listReview.add(new Review(
                3, 3,1,
                "“Tempatnya nyaman banget, makanan juga enak. Cuma fasilitas udah banyak rusak”",
                "19/10/2023",
                4.7
        ));

        return listReview;
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