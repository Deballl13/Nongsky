package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nongskuy.nongskuy.adapter.RiwayatNongskuyAdapter;
import com.nongskuy.nongskuy.model.RiwayatItem;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;
import com.nongskuy.nongskuy.model.RiwayatNongskuyClass;
import com.nongskuy.nongskuy.route.Route;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatPemesananTempatActivity extends AppCompatActivity {

    RecyclerView rvRiwayatNongskuy;
    RiwayatNongskuyAdapter riwayatNongskuyAdapter;
    ConstraintLayout layoutRiwayatPesanDitemukan;
    ConstraintLayout layoutRiwayatPesanTidakDitemukan;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemesanan_tempat);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        layoutRiwayatPesanDitemukan = findViewById(R.id.layoutRiwayatPesanDitemukan);
        layoutRiwayatPesanTidakDitemukan = findViewById(R.id.layoutRiwayatPesanTidakDitemukan);

        riwayatNongskuyAdapter = new RiwayatNongskuyAdapter();
//        riwayatNongskuyAdapter.setListRiwayatNongskuy(dataDummy());
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN", null);
        loadDataFromServer(token);

        if(riwayatNongskuyAdapter.getItemCount() > 0){
            rvRiwayatNongskuy = findViewById(R.id.rvRiwayatNongskuy);
            rvRiwayatNongskuy.setAdapter(riwayatNongskuyAdapter);
            rvRiwayatNongskuy.setLayoutManager(layoutManager);

            layoutRiwayatPesanDitemukan.setVisibility(View.VISIBLE);
            layoutRiwayatPesanTidakDitemukan.setVisibility(View.INVISIBLE);
        }

    }

    public ArrayList<RiwayatNongskuy> dataDummy(){
        ArrayList<RiwayatNongskuy> listRiwayatNongskuy = new ArrayList<>();
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Khatib",
                1,
                4,
                40000,
                "OVO",
                "30-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Ahmad Yani",
                1,
                2,
                20000,
                "Gopay",
                "31-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Air Tawar",
                0,
                3,
                30000,
                "ATM",
                "30-09-2021",
                "10:00"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Khatib",
                1,
                4,
                40000,
                "OVO",
                "30-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Ahmad Yani",
                1,
                2,
                20000,
                "Gopay",
                "31-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Air Tawar",
                0,
                3,
                30000,
                "ATM",
                "30-09-2021",
                "10:00"));

        return listRiwayatNongskuy;
    }

}