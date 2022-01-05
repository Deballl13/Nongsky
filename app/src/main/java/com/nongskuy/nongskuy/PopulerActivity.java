package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nongskuy.nongskuy.adapter.PopulerAdapter;
import com.nongskuy.nongskuy.adapter.PromoAdapter;
import com.nongskuy.nongskuy.data.NongskuyPopulerData;
import com.nongskuy.nongskuy.model.Nongskuy;
import com.nongskuy.nongskuy.model.NongskuyPopulerClass;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PopulerAdapter.OnPopulerViewHolderClick {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationViewPopuler;
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populer);

        config = new Config();
        recyclerView = findViewById(R.id.rvPopulerNongskuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadDataNongskuyPopuler();

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

    public void loadDataNongskuyPopuler(){
        // get latitude and longitude
        SharedPreferences sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        Double latitude = Double.parseDouble(sharedPreferences.getString("Latitude", null));
        Double longitude = Double.parseDouble(sharedPreferences.getString("Longitude", null));

        recyclerView.setAdapter(new PromoAdapter(null));
        Call<NongskuyPopulerClass> call = config.configRetrofit().tokoPopuler(latitude, longitude);
        call.enqueue(new Callback<NongskuyPopulerClass>() {
            @Override
            public void onResponse(Call<NongskuyPopulerClass> call, Response<NongskuyPopulerClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        NongskuyPopulerClass nongskuyPopulerClass = response.body();
                        List<NongskuyPopulerData> listTokoPopuler = nongskuyPopulerClass.getTokoPopuler();
                        ArrayList<Nongskuy> arrayListTokoPopuler = new ArrayList<>();
                        PopulerAdapter populerAdapter = new PopulerAdapter(arrayListTokoPopuler);

                        for(NongskuyPopulerData nongskuyPopulerData : listTokoPopuler){
                            Nongskuy nongskuy = new Nongskuy(
                                    nongskuyPopulerData.getId(),
                                    nongskuyPopulerData.getGambar(),
                                    nongskuyPopulerData.getNamaToko(),
                                    nongskuyPopulerData.getAlamat(),
                                    nongskuyPopulerData.getTipe(),
                                    4.5,
                                    nongskuyPopulerData.getRating(),
                                    Double.parseDouble(nongskuyPopulerData.getLatitude()),
                                    Double.parseDouble(nongskuyPopulerData.getLongitude())
                            );

                            arrayListTokoPopuler.add(nongskuy);
                            populerAdapter.setShimmer(false);
                            recyclerView.setAdapter(populerAdapter);
                            populerAdapter.notifyDataSetChanged();
                        }
                        populerAdapter.setPopulerClickObject(PopulerActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<NongskuyPopulerClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPopulerClick(Nongskuy nongskuy) {
        Intent intent = new Intent(this, DetailNongskuy.class);
        intent.putExtra("IdToko", nongskuy.getIdToko());
        intent.putExtra("NamaToko", nongskuy.getNamaToko());
        intent.putExtra("GambarToko", nongskuy.getGambarToko());
        intent.putExtra("AlamatToko", nongskuy.getAlamatToko());
        intent.putExtra("LatToko", nongskuy.getLatToko());
        intent.putExtra("LongToko", nongskuy.getLongToko());
        startActivity(intent);
    }
}