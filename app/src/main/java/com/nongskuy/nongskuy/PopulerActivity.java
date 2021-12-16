package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nongskuy.nongskuy.adapter.PopulerAdapter;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class PopulerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationViewPopuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populer);

        recyclerView = findViewById(R.id.rvPopulerNongskuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PopulerAdapter(getData()));

        bottomNavigationViewPopuler = findViewById(R.id.BottomNavigationMenuPopuler);
        bottomNavigationViewPopuler.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("Fragment", String.valueOf(item));
        setResult(RESULT_OK, intent);
        finish();

        return true;
    }

    public ArrayList<Store> getData(){
        ArrayList<Store> listPopuler = new ArrayList<>();
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));

        return listPopuler;
    }
}