package com.nongskuy.nongskuy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.adapter.BerandaPopulerAdapter;
import com.nongskuy.nongskuy.adapter.BerandaPromoAdapter;
import com.nongskuy.nongskuy.adapter.BerandaTerdekatAdapter;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class BerandaFragment extends Fragment {

    TextView namaUser;
    MaterialButton btnRiwayatPemesananTempat, btnLihatSemuaPopuler,
            btnLihatSemuaPromo, btnLihatSemuaTerdekat;
    RecyclerView recyclerViewPopuler, recyclerViewPromo;
    ConstraintLayout contentBeranda;
    BottomNavigationView bottomNavigationView;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        btnRiwayatPemesananTempat = view.findViewById(R.id.buttonPesanTempat);
        btnLihatSemuaPopuler = view.findViewById(R.id.buttonLihatSemuaPopuler);
        btnLihatSemuaPromo = view.findViewById(R.id.buttonLihatSemuaPromo);
        btnLihatSemuaTerdekat = view.findViewById(R.id.buttonLihatSemuaTerdekat);
        contentBeranda = view.findViewById(R.id.contentBeranda);
        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.BottomNavigationMenu);

        //Mengambil email login user
        namaUser = view.findViewById(R.id.textName);

        if (MainActivity.userEmail != null) {
            namaUser.setText("William Wahyu");
            btnRiwayatPemesananTempat.setVisibility(view.VISIBLE);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) contentBeranda.getLayoutParams();
            layoutParams.topMargin = 0;
            contentBeranda.setLayoutParams(layoutParams);
        }

        // recycler view populer
        LinearLayoutManager linearLayoutManagerPopuler = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopuler = view.findViewById(R.id.recyclerViewBerandaPopuler);
        recyclerViewPopuler.setLayoutManager(linearLayoutManagerPopuler);
        recyclerViewPopuler.setAdapter(new BerandaPopulerAdapter(getDataPopuler()));

        // recyclerview promo
        LinearLayoutManager linearLayoutManagerPromo = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPromo = view.findViewById(R.id.recyclerViewBerandaPromo);
        recyclerViewPromo.setLayoutManager(linearLayoutManagerPromo);
        recyclerViewPromo.setAdapter(new BerandaPromoAdapter(getDataPromo()));

        // recyclerview terdekat
        LinearLayoutManager linearLayoutManagerTerdekat = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPromo = view.findViewById(R.id.recyclerViewBerandaTerdekat);
        recyclerViewPromo.setLayoutManager(linearLayoutManagerTerdekat);
        recyclerViewPromo.setAdapter(new BerandaTerdekatAdapter(getDataTerdekat()));

        // getFragment
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            String data = intent.getStringExtra("FRAGMENT");
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
            loadFragment(new PromoFragment());
            bottomNavigationView.setSelectedItemId(R.id.menu_promo);
        });

        // Intent ke fragment terdekat
        btnLihatSemuaTerdekat.setOnClickListener(view1 -> {
            loadFragment(new TerdekatFragment());
            bottomNavigationView.setSelectedItemId(R.id.menu_terdekat);
        });

        return view;
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

    public ArrayList<Store> getDataPopuler() {
        ArrayList<Store> listPopulerBeranda = new ArrayList<>();
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopulerBeranda.add(new Store(
                4.2,
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));

        return listPopulerBeranda;
    }

    public ArrayList<Promo> getDataPromo() {
        ArrayList<Promo> listPromoBeranda = new ArrayList<>();
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));
        listPromoBeranda.add(new Promo(
                "Sate Taichan",
                "Taichan Mimi Peri",
                "Diskon 5%"
        ));

        return listPromoBeranda;
    }

    public ArrayList<Store> getDataTerdekat() {
        ArrayList<Store> listTerdekatBeranda = new ArrayList<>();
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));
        listTerdekatBeranda.add(new Store(
                "McDonald’s Padang",
                "Cepat saji",
                4.5,
                "km"
        ));

        return listTerdekatBeranda;
    }
}