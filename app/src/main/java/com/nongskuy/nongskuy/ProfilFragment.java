package com.nongskuy.nongskuy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ProfilFragment extends Fragment {
    MaterialButton btnUbahProfil;
    MaterialButton btnGantiKataSandi;
    MaterialButton btnLogout;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        btnUbahProfil = (MaterialButton) view.findViewById(R.id.buttonUbahProfil);
        btnGantiKataSandi = (MaterialButton) view.findViewById(R.id.buttonGantiKataSandi);
        btnLogout = (MaterialButton) view.findViewById(R.id.buttonKeluar);


        //Toast pemberitahuan
        String notif = getActivity().getIntent().getStringExtra("TOPROFIL");
        if(notif != null){
            Toast.makeText(getActivity(), notif, Toast.LENGTH_SHORT).show();
        }

        //Intent Fragment Profil ke Ubah Profil
        btnUbahProfil.setOnClickListener(view1 -> {
        Intent intent = new Intent(getActivity(), UbahProfilActivity.class);
        getActivity().finish();
        startActivity(intent);
        });


        //Intent Fragment Profil ke Ganti Kata Sandi
        btnGantiKataSandi.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), GantiKataSandiActivity.class);
            getActivity().finish();
            startActivity(intent);
        });


        //Intent logout ke halaman login
        btnLogout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().finish();
            intent.putExtra("LOGOUT", "berhasil keluar");
            startActivity(intent);
        });


        //Inflate the layout for this fragment
        return view;
    }
}