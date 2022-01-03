package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.adapter.FasilitasAdapter;
import com.nongskuy.nongskuy.adapter.MenuAdapter;
import com.nongskuy.nongskuy.adapter.ReviewAdapter;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class DetailNongskuy extends AppCompatActivity {

    private RecyclerView rvMenu, rvReview, rvFasilitas;
    private MenuAdapter menuAdapter;
    private ReviewAdapter reviewAdapter;
    private FasilitasAdapter fasilitasAdapter;

    private MaterialButton btnLihatSemuaReview, btnTambahReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nongskuy);

        btnLihatSemuaReview = findViewById(R.id.btnLihatSemuaReview);
        btnTambahReview = findViewById(R.id.btnTambahReview);

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
}