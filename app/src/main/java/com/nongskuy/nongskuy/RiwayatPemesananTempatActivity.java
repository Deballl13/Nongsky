package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

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

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        rvRiwayatNongskuy = findViewById(R.id.rvRiwayatNongskuy);
        rvRiwayatNongskuy.setAdapter(riwayatNongskuyAdapter);
        rvRiwayatNongskuy.setLayoutManager(layoutManager);

        riwayatNongskuyAdapter = new RiwayatNongskuyAdapter();
        riwayatNongskuyAdapter.setListRiwayatNongskuy(dataDummy());
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