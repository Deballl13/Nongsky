package com.nongskuy.nongskuy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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


        //tampilan profil user dan guest
        if(MainActivity.userEmail != null){
            view.findViewById(R.id.layoutUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutGuest).setVisibility(view.INVISIBLE);
        }


        //Get response
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode() == Activity.RESULT_OK){
                        String response = result.getData().getStringExtra("TOPROFIL");
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                }
        );


        //Intent Fragment Profil ke Ubah Profil
        btnUbahProfil.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), UbahProfilActivity.class);
            intentResult.launch(intent);
        });


        //Intent Fragment Profil ke Ganti Kata Sandi
        btnGantiKataSandi.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), GantiKataSandiActivity.class);
            intentResult.launch(intent);
        });


        //Intent logout ke halaman login
        btnLogout.setOnClickListener(view1 -> {
            MainActivity.userEmail = null;
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
            Toast.makeText(getActivity(), "berhasil keluar", Toast.LENGTH_SHORT).show();
        });


        //Inflate the layout for this fragment
        return view;
    }
}