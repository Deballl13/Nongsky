package com.nongskuy.nongskuy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfilFragment extends Fragment {
    Button btnUbahProfil;
    Button btnGantiKataSandi;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        btnUbahProfil = (Button) view.findViewById(R.id.buttonUbahProfil);
        btnGantiKataSandi = (Button) view.findViewById(R.id.buttonGantiKataSandi);

        //Intent Fragment Profil ke Ubah Profil
        btnUbahProfil.setOnClickListener(view1 -> {
        Intent intent = new Intent(getActivity(), UbahProfilActivity.class);
        startActivity(intent);
        });

        //Intent Fragment Profil ke Ganti Kata Sandi
        btnGantiKataSandi.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), GantiKataSandiActivity.class);
            startActivity(intent);
        });

        //Inflate the layout for this fragment
        return view;
    }
}