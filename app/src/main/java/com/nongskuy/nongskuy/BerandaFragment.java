package com.nongskuy.nongskuy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.adapter.BerandaPopulerAdapter;
import com.nongskuy.nongskuy.adapter.BerandaPromoAdapter;
import com.nongskuy.nongskuy.adapter.BerandaTerdekatAdapter;
import com.nongskuy.nongskuy.data.PromoData;
import com.nongskuy.nongskuy.data.TokoPopulerData;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.PromoClass;
import com.nongskuy.nongskuy.model.Toko;
import com.nongskuy.nongskuy.model.TokoPopulerClass;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaFragment extends Fragment {

    private TextView namaUser;
    private MaterialButton btnRiwayatPemesananTempat, btnLihatSemuaPopuler,
            btnLihatSemuaPromo, btnLihatSemuaTerdekat;
    private RecyclerView recyclerViewPopuler, recyclerViewPromo, recyclerViewTerdekat;
    private ScrollView scrollViewBeranda;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout refreshLayout;
    private Config config;
    private SearchView searchViewBeranda;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String nama = sharedPreferences.getString("Nama", null);
        String token = sharedPreferences.getString("Token", null);


        config = new Config();
        btnRiwayatPemesananTempat = view.findViewById(R.id.buttonPesanTempat);
        btnLihatSemuaPopuler = view.findViewById(R.id.buttonLihatSemuaPopuler);
        btnLihatSemuaPromo = view.findViewById(R.id.buttonLihatSemuaPromo);
        btnLihatSemuaTerdekat = view.findViewById(R.id.buttonLihatSemuaTerdekat);
        scrollViewBeranda = view.findViewById(R.id.scrollViewBeranda);
        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.BottomNavigationMenu);
        namaUser = view.findViewById(R.id.textName);
        searchViewBeranda = view.findViewById(R.id.searchViewBeranda);



        // refresh halaman
        refreshLayout  = view.findViewById(R.id.refreshBeranda);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPage(token);
            }
        });



        //cek ketersediaan token
        if (token != null) {
            namaUser.setText(nama.toString());
            btnRiwayatPemesananTempat.setVisibility(View.VISIBLE);

            // atur margin text populer beranda
            TextView textBerandaPopuler = view.findViewById(R.id.textBerandaPopuler);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textBerandaPopuler.getLayoutParams();
            layoutParams.setMargins(24,30,0,0);
            textBerandaPopuler.setLayoutParams(layoutParams);

            // atur margin button lihat semua populer
            ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) btnLihatSemuaPopuler.getLayoutParams();
            layoutParams1.setMargins(0,15,12,0);
            btnLihatSemuaPopuler.setLayoutParams(layoutParams1);
        }



        // recycler view populer
        LinearLayoutManager linearLayoutManagerPopuler = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopuler = view.findViewById(R.id.recyclerViewBerandaPopuler);
        recyclerViewPopuler.setLayoutManager(linearLayoutManagerPopuler);
        loadDataTokoPopuler();

        // recyclerview promo
        if (token != null) {
            recyclerViewPromo = view.findViewById(R.id.recyclerViewBerandaPromo);

            view.findViewById(R.id.textBerandaPromo).setVisibility(View.VISIBLE);
            btnLihatSemuaPromo.setVisibility(View.VISIBLE);
            recyclerViewPromo.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManagerPromo = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewPromo.setLayoutManager(linearLayoutManagerPromo);
            loadDataPromo(token);
        }

        // recyclerview terdekat
        LinearLayoutManager linearLayoutManagerTerdekat = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTerdekat = view.findViewById(R.id.recyclerViewBerandaTerdekat);
        recyclerViewTerdekat.setLayoutManager(linearLayoutManagerTerdekat);
        recyclerViewTerdekat.setAdapter(new BerandaTerdekatAdapter(getDataTerdekat()));



        // getFragment
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            String data = intent.getStringExtra("Fragment");
                            menuNavigation(data);
                        }
                    }
                });

        //Intent Fragment Beranda ke Activity Riwayat Pesan Tempat
        btnRiwayatPemesananTempat.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), RiwayatPemesananTempatActivity.class);
            startActivity(intent);
        });

        //Intent ke Activity Populer
        btnLihatSemuaPopuler.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), PopulerActivity.class);
            intentResult.launch(intent);
        });

        // Intent ke fragment promo
        btnLihatSemuaPromo.setOnClickListener(view1 -> {
            menuNavigation("Promo");
        });

        // Intent ke fragment terdekat
        btnLihatSemuaTerdekat.setOnClickListener(view1 -> {
            menuNavigation("Terdekat");
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // menghilangkan keyword pencarian yang ada
        searchViewBeranda.setQuery("", false);
        searchViewBeranda.clearFocus();

        // jika keyword di enter/submit akan diarahkan ke halaman pencarian
        searchViewBeranda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getActivity(), PencarianActivity.class);
                intent.putExtra("Keyword", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // jika menyentuh layoout diluar search view
//        scrollViewBeranda.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(getActivity(), "ontouch", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
        }

        return true;
    }

    public void menuNavigation(String data){
        switch (data){
            case "Beranda":
                loadFragment(new BerandaFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
                break;
            case "Promo":
                loadFragment(new PromoFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_promo);
                break;
            case "Terdekat":
                loadFragment(new TerdekatFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_terdekat);
                break;
            case "Profil":
                loadFragment(new ProfilFragment());
                bottomNavigationView.setSelectedItemId(R.id.menu_profil);
                break;

        }
    }

    public void refreshPage(String token){
        loadDataTokoPopuler();
        loadDataPromo(token);
        refreshLayout.setRefreshing(false);
    }

    public void loadDataTokoPopuler(){
        recyclerViewPopuler.setAdapter(new BerandaPopulerAdapter(null));
        Call<TokoPopulerClass> call = config.configRetrofit().tokoPopuler();
        call.enqueue(new Callback<TokoPopulerClass>() {
            @Override
            public void onResponse(Call<TokoPopulerClass> call, Response<TokoPopulerClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        TokoPopulerClass tokoPopulerClass = response.body();
                        List<TokoPopulerData> listTokoPopuler = tokoPopulerClass.getTokoPopuler();
                        ArrayList<Toko> arrayListTokoPopuler = new ArrayList<>();
                        BerandaPopulerAdapter berandaPopulerAdapter = new BerandaPopulerAdapter(arrayListTokoPopuler);

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
                            berandaPopulerAdapter.setShimmer(false);
                            recyclerViewPopuler.setAdapter(berandaPopulerAdapter);
                            berandaPopulerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TokoPopulerClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadDataPromo(String token){
        recyclerViewPromo.setAdapter(new BerandaPromoAdapter(null));
        Call<PromoClass> call = config.configRetrofit().promo(token);
        call.enqueue(new Callback<PromoClass>() {
            @Override
            public void onResponse(Call<PromoClass> call, Response<PromoClass> response) {
                if(response.code() == 200){
                    if (response.isSuccessful()){
                        PromoClass promoClass = response.body();
                        List<PromoData> listPromo = promoClass.getPromo();
                        ArrayList<Promo> arrayListPromo = new ArrayList<>();
                        BerandaPromoAdapter berandaPromoAdapter = new BerandaPromoAdapter(arrayListPromo);

                        for (PromoData promoData: listPromo) {
                            Promo promo = new Promo(
                                    promoData.getIdToko(),
                                    promoData.getNamaToko(),
                                    promoData.getNamaMenu(),
                                    promoData.getHarga(),
                                    promoData.getGambar(),
                                    promoData.getPersentase(),
                                    promoData.getJenisPromo()
                            );

                            arrayListPromo.add(promo);
                            berandaPromoAdapter.setShimmer(false);
                            recyclerViewPromo.setAdapter(berandaPromoAdapter);
                            berandaPromoAdapter.notifyDataSetChanged();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PromoClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Toko> getDataTerdekat() {
        ArrayList<Toko> listTerdekatBeranda = new ArrayList<>();
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Toko(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));

        return listTerdekatBeranda;
    }
}