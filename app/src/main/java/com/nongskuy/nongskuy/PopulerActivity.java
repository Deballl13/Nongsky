package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nongskuy.nongskuy.adapter.PopulerAdapter;
import com.nongskuy.nongskuy.adapter.PromoAdapter;
import com.nongskuy.nongskuy.data.TokoPopulerData;
import com.nongskuy.nongskuy.model.Toko;
import com.nongskuy.nongskuy.model.TokoPopulerClass;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
        loadDataTokoPopuler();

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

    public void loadDataTokoPopuler(){
        recyclerView.setAdapter(new PromoAdapter(null));
        Call<TokoPopulerClass> call = config.configRetrofit().tokoPopuler();
        call.enqueue(new Callback<TokoPopulerClass>() {
            @Override
            public void onResponse(Call<TokoPopulerClass> call, Response<TokoPopulerClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        TokoPopulerClass tokoPopulerClass = response.body();
                        List<TokoPopulerData> listTokoPopuler = tokoPopulerClass.getTokoPopuler();
                        ArrayList<Toko> arrayListTokoPopuler = new ArrayList<>();
                        PopulerAdapter populerAdapter = new PopulerAdapter(arrayListTokoPopuler);

                        for(TokoPopulerData tokoPopulerData : listTokoPopuler){
                            Toko toko = new Toko(
                                    tokoPopulerData.getId(),
                                    tokoPopulerData.getGambar(),
                                    tokoPopulerData.getNamaToko(),
                                    tokoPopulerData.getAlamat(),
                                    tokoPopulerData.getTipe(),
                                    4.5,
                                    tokoPopulerData.getRating()
                            );

                            arrayListTokoPopuler.add(toko);
                            populerAdapter.setShimmer(false);
                            recyclerView.setAdapter(populerAdapter);
                            populerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TokoPopulerClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}