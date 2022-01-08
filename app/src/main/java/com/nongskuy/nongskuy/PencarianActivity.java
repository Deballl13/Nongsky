package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.nongskuy.nongskuy.adapter.PencarianAdapter;
import com.nongskuy.nongskuy.data.PencarianData;
import com.nongskuy.nongskuy.model.PencarianClass;
import com.nongskuy.nongskuy.model.Nongskuy;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PencarianActivity extends AppCompatActivity implements PencarianAdapter.OnPencarianViewHolderClick {

    private RecyclerView rvPencarian;
    private SearchView searchViewPencarian;
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        searchViewPencarian = findViewById(R.id.searchViewPencarian);

        config = new Config();
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("Keyword");
        searchViewPencarian.setQuery(keyword, false);

        rvPencarian = findViewById(R.id.rvPencarian);
        rvPencarian.setLayoutManager(new LinearLayoutManager(this));
        loadDataSearch(keyword);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // jika keyword di enter/submit akan dilakukan load data
        searchViewPencarian.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                loadDataSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // jika menyentuh layoout diluar search view
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean b) {
                if (!b){
                    searchViewPencarian.clearFocus();
                }
            }
        });
    }

    public void loadDataSearch(String keyword){
        // menampilkan recyclerview pencarian
        rvPencarian.setVisibility(View.VISIBLE);
        findViewById(R.id.textHasilCari).setVisibility(View.VISIBLE);

        // menampilkan pencarian tidak ada
        findViewById(R.id.noSearchFound).setVisibility(View.GONE);
        findViewById(R.id.textUtamaPencarian).setVisibility(View.GONE);
        findViewById(R.id.textPelengkapPencarian).setVisibility(View.GONE);

        // get latitude and longitude
        Intent intent = getIntent();

        rvPencarian.setAdapter(new PencarianAdapter(null));
        Call<PencarianClass> call = config.configRetrofit().search(keyword,
                intent.getDoubleExtra("Latitude", 0.0),
                intent.getDoubleExtra("Longitude", 0.0));
        call.enqueue(new Callback<PencarianClass>() {
            @Override
            public void onResponse(Call<PencarianClass> call, Response<PencarianClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        PencarianClass pencarianClass = response.body();
                        List<PencarianData> listPencarian = pencarianClass.getSearchResult();
                        ArrayList<Nongskuy> arrayListPencarianToko = new ArrayList<>();
                        PencarianAdapter pencarianAdapter = new PencarianAdapter(arrayListPencarianToko);

                        //cek jumlah data
                        if(pencarianClass.getJumlah() == 0){
                            //gone komponen
                            rvPencarian.setVisibility(View.GONE);
                            findViewById(R.id.textHasilCari).setVisibility(View.GONE);

                            // menampilkan pencarian tidak ada
                            findViewById(R.id.noSearchFound).setVisibility(View.VISIBLE);
                            findViewById(R.id.textUtamaPencarian).setVisibility(View.VISIBLE);
                            findViewById(R.id.textPelengkapPencarian).setVisibility(View.VISIBLE);
                        }
                        else{
                            //perulangan item data
                            for (PencarianData pencarianData : listPencarian) {
                                Nongskuy nongskuy = new Nongskuy(
                                        pencarianData.getId(),
                                        pencarianData.getGambar(),
                                        pencarianData.getNamaToko(),
                                        pencarianData.getAlamat(),
                                        pencarianData.getJarak(),
                                        Double.parseDouble(pencarianData.getLatitude()),
                                        Double.parseDouble(pencarianData.getLongitude())
                                );

                                arrayListPencarianToko.add(nongskuy);
                                pencarianAdapter.setShimmer(false);
                                rvPencarian.setAdapter(pencarianAdapter);
                                pencarianAdapter.notifyDataSetChanged();
                            }

                            // set pencarian onclick
                            pencarianAdapter.setPencarianClickObject(PencarianActivity.this);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PencarianClass> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(searchViewPencarian.getQuery().length() > 0) {
            searchViewPencarian.setQuery("", false);
            searchViewPencarian.clearFocus();
        }
        else{
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public void onPencarianClick(Nongskuy nongskuy) {
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