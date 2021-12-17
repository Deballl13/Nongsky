package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.nongskuy.nongskuy.adapter.PencarianAdapter;
import com.nongskuy.nongskuy.model.Toko;
import java.util.ArrayList;

public class PencarianActivity extends AppCompatActivity {

    RecyclerView rvPencarian;
    PencarianAdapter pencarianAdapter;
    ConstraintLayout layoutPencarianDitemukan;
    ConstraintLayout layoutPencarianTidakDitemukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        layoutPencarianDitemukan = findViewById(R.id.layoutPencarianDitemukan);
        layoutPencarianTidakDitemukan = findViewById(R.id.layoutPencarianTidakDitemukan);

        pencarianAdapter = new PencarianAdapter();
//        pencarianAdapter.setListPencarian(dataDummy());

        if(pencarianAdapter.getItemCount() > 0){
            rvPencarian = findViewById(R.id.rvPencarian);
            rvPencarian.setAdapter(pencarianAdapter);
            rvPencarian.setLayoutManager(layoutManager);

            layoutPencarianDitemukan.setVisibility(View.VISIBLE);
            layoutPencarianTidakDitemukan.setVisibility(View.INVISIBLE);
        }
    }

//    public ArrayList<Toko> dataDummy(){
//        ArrayList<Toko> listPencarian = new ArrayList<>();
//        listPencarian.add(new Toko(
//                "4.2",
//                "McDonald’s Padang",
//                "Jl. Khatib Sulaeman",
//                "Cepat saji",
//                4.5,
//                "km"
//        ));
//        listPencarian.add(new Toko(
//                "4.2",
//                "McDonald’s Padang",
//                "Jl. Khatib Sulaeman",
//                "Cepat saji",
//                4.5,
//                "km"
//        ));
//        listPencarian.add(new Toko(
//                "4.2",
//                "McDonald’s Padang",
//                "Jl. Khatib Sulaeman",
//                "Cepat saji",
//                4.5,
//                "km"
//        ));listPencarian.add(new Toko(
//                "4.2",
//                "McDonald’s Padang",
//                "Jl. Khatib Sulaeman",
//                "Cepat saji",
//                4.5,
//                "km"
//        ));
//        listPencarian.add(new Toko(
//                "4.2",
//                "McDonald’s Padang",
//                "Jl. Khatib Sulaeman",
//                "Cepat saji",
//                4.5,
//                "km"
//        ));
//
//
//        return listPencarian;
//    }
}