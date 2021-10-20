package com.nongskuy.nongskuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

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

        //Mengambil email login user
        namaUser = view.findViewById(R.id.textName);

        if(MainActivity.userEmail != null){
            namaUser.setText("William Wahyu");
        }

        //Intent Fragment Beranda ke Activity Riwayat Pesan Tempat
        btnRiwayatPemesananTempat.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), RiwayatPemesananTempatActivity.class);
            startActivity(intent);
        });

        return view;
    }

}