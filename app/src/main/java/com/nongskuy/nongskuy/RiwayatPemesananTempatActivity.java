package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nongskuy.nongskuy.adapter.RiwayatNongskuyAdapter;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;

import java.util.ArrayList;

public class RiwayatPemesananTempatActivity extends AppCompatActivity {

    RecyclerView rvRiwayatNongskuy;
    RiwayatNongskuyAdapter riwayatNongskuyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemesanan_tempat);

        riwayatNongskuyAdapter = new RiwayatNongskuyAdapter();
        riwayatNongskuyAdapter.setListRiwayatNongskuy(dataDummy());

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        rvRiwayatNongskuy = findViewById(R.id.rvRiwayatNongskuy);
        rvRiwayatNongskuy.setAdapter(riwayatNongskuyAdapter);
        rvRiwayatNongskuy.setLayoutManager(layoutManager);
    }

    public ArrayList<RiwayatNongskuy> dataDummy(){
        ArrayList<RiwayatNongskuy> listRiwayatNongskuy = new ArrayList<>();
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Khatib",
                "Dipesan",
                4,
                40000,
                "OVO",
                "30-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Ahmad Yani",
                "Dipesan",
                2,
                20000,
                "Gopay",
                "31-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Air Tawar",
                "Dibatalkan",
                3,
                30000,
                "ATM",
                "30-09-2021",
                "10:00"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Khatib",
                "Dipesan",
                4,
                40000,
                "OVO",
                "30-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Ahmad Yani",
                "Dipesan",
                2,
                20000,
                "Gopay",
                "31-09-2021",
                "09:30"));
        listRiwayatNongskuy.add(new RiwayatNongskuy("McDonalds Air Tawar",
                "Dibatalkan",
                3,
                30000,
                "ATM",
                "30-09-2021",
                "10:00"));

        return listRiwayatNongskuy;
    }
}