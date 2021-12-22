package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import com.nongskuy.nongskuy.adapter.PencarianAdapter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class PencarianActivity extends AppCompatActivity {

    private RecyclerView rvPencarian;
    private PencarianAdapter pencarianAdapter;
    private ConstraintLayout layoutPencarianDitemukan;
    private ConstraintLayout layoutPencarianTidakDitemukan;
    private SearchView searchViewPencarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        layoutPencarianDitemukan = findViewById(R.id.layoutPencarianDitemukan);
        layoutPencarianTidakDitemukan = findViewById(R.id.layoutPencarianTidakDitemukan);
        searchViewPencarian = findViewById(R.id.searchViewPencarian);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra("Keyword");
        searchViewPencarian.setQuery(keyword, false);
        loadDataSearch(keyword);


        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
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
        Toast.makeText(getApplicationContext(), keyword, Toast.LENGTH_SHORT).show();
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