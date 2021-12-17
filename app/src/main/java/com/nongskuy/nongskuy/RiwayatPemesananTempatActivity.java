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
import com.nongskuy.nongskuy.data.RiwayatNongskuyData;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;
import com.nongskuy.nongskuy.model.RiwayatNongskuyClass;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemesananTempatActivity extends AppCompatActivity {

    private RecyclerView rvRiwayatNongskuy;
    private ConstraintLayout layoutRiwayatPesanDitemukan;
    private ConstraintLayout layoutRiwayatPesanTidakDitemukan;
    private SharedPreferences sharedPreferences;
    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemesanan_tempat);

        layoutRiwayatPesanDitemukan = findViewById(R.id.layoutRiwayatPesanDitemukan);
        layoutRiwayatPesanTidakDitemukan = findViewById(R.id.layoutRiwayatPesanTidakDitemukan);

        config = new Config();
        sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", null);

        rvRiwayatNongskuy = findViewById(R.id.rvRiwayatNongskuy);
        rvRiwayatNongskuy.setLayoutManager(new LinearLayoutManager(this));
        loadDataRiwayatNongskuy(token);
    }

    public void loadDataRiwayatNongskuy(String token) {
        Call<RiwayatNongskuyClass> call = config.configRetrofit().riwayat(token);
        call.enqueue(new Callback<RiwayatNongskuyClass>() {
            @Override
            public void onResponse(Call<RiwayatNongskuyClass> call, Response<RiwayatNongskuyClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        RiwayatNongskuyClass riwayatNongskuyClass = response.body();
                        List<RiwayatNongskuyData> listRiwayatNongskuy = riwayatNongskuyClass.getRiwayat();
                        ArrayList<RiwayatNongskuy>  arrayListRiwayatNongskuy = new ArrayList<>();
                        RiwayatNongskuyAdapter riwayatNongskuyAdapter = new RiwayatNongskuyAdapter(arrayListRiwayatNongskuy);

                        //cek isi arraylist listRiwayatNongskuy
                        if(listRiwayatNongskuy.size() == 0){
                            layoutRiwayatPesanDitemukan.setVisibility(View.INVISIBLE);
                            layoutRiwayatPesanTidakDitemukan.setVisibility(View.VISIBLE);
                        }

                        //perulangan data item
                        for (RiwayatNongskuyData riwayatNongskuyData: listRiwayatNongskuy) {
                            RiwayatNongskuy riwayatNongskuy = new RiwayatNongskuy(
                                    riwayatNongskuyData.getNamaToko(),
                                    riwayatNongskuyData.getGambarToko(),
                                    riwayatNongskuyData.getStatus(),
                                    riwayatNongskuyData.getJumlahKursi(),
                                    riwayatNongskuyData.getDp(),
                                    riwayatNongskuyData.getMetodeBayar(),
                                    riwayatNongskuyData.getTanggal(),
                                    riwayatNongskuyData.getWaktu()
                            );

                            arrayListRiwayatNongskuy.add(riwayatNongskuy);
                            rvRiwayatNongskuy.setAdapter(riwayatNongskuyAdapter);
                            riwayatNongskuyAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RiwayatNongskuyClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}