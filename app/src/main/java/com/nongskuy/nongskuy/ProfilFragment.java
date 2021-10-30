package com.nongskuy.nongskuy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
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
            view.findViewById(R.id.layoutProfilUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutProfilGuest).setVisibility(view.INVISIBLE);
        }


        //Get response
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            String response = intent.getStringExtra("TOPROFIL");
                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.app_name)
                    .setMessage("Yakin mau keluar?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.userEmail = null;
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                            Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });


        //Inflate the layout for this fragment
        return view;
    }
}