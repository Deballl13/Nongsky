package com.nongskuy.nongskuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class BerandaFragment extends Fragment {

    RecyclerView recyclerViewBerandaPopuler;
    TextView namaUser;
    MaterialButton btnRiwayatPemesananTempat;
    MaterialButton btnLihatSemuaPopuler;
    ConstraintLayout contentBeranda;

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
        contentBeranda = view.findViewById(R.id.contentBeranda);

        //Mengambil email login user
        namaUser = view.findViewById(R.id.textName);

        if(MainActivity.userEmail != null){
            namaUser.setText("William Wahyu");
            btnRiwayatPemesananTempat.setVisibility(view.VISIBLE);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) contentBeranda.getLayoutParams();
            layoutParams.topMargin = 0;
            contentBeranda.setLayoutParams(layoutParams);
        }

        //Intent Fragment Beranda ke Activity Riwayat Pesan Tempat
        btnRiwayatPemesananTempat.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), RiwayatPemesananTempatActivity.class);
            startActivity(intent);
        });

        //Intent ke Fragment Populer
//        btnLihatSemuaPopuler.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//            }
//        });



        return view;
    }

}