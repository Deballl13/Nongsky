package com.nongskuy.nongskuy;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.MessageResponse;
import com.nongskuy.nongskuy.route.Route;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilFragment extends Fragment {
    MaterialButton btnUbahProfil;
    MaterialButton btnGantiKataSandi;
    MaterialButton btnLogout;
    TextView namaProfil, emailProfil, noHpProfil;
    SharedPreferences sharedPreferences;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN", null);
        String nama = sharedPreferences.getString("NAMA", null);
        String email = sharedPreferences.getString("EMAIL", null);
        String no_hp = sharedPreferences.getString("NO_HP", null);

        btnUbahProfil = (MaterialButton) view.findViewById(R.id.buttonUbahProfil);
        btnGantiKataSandi = (MaterialButton) view.findViewById(R.id.buttonGantiKataSandi);
        btnLogout = (MaterialButton) view.findViewById(R.id.buttonKeluar);
        namaProfil = view.findViewById(R.id.textNameProfile);
        noHpProfil = view.findViewById(R.id.textPhoneProfile);
        emailProfil = view.findViewById(R.id.textEmailProfile);

        //tampilan profil user dan guest
        if(token != null){
            namaProfil.setText(nama);
            emailProfil.setText(email);
            noHpProfil.setText(no_hp);

            view.findViewById(R.id.layoutProfilUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutProfilGuest).setVisibility(view.INVISIBLE);
        }

//        //Get response dari ubah profil dan ubah password
//        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            Intent intent = result.getData();
//                            // Handle the Intent
//                            String response = intent.getStringExtra("TOPROFIL");
//                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


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


        //Intent logout ke halaman login
        btnLogout.setOnClickListener(view1 -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.app_name)
                    .setMessage("Yakin mau keluar?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Config.API_BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            Route route = retrofit.create(Route.class);
                            String token = sharedPreferences.getString("TOKEN", null);

                            Call<MessageResponse> call = route.logout(token);
                            call.enqueue(new Callback<MessageResponse>() {
                                @Override
                                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                    MessageResponse messageResponse = response.body();
                                    if (response.code() == 200){
                                        if (response.isSuccessful()){
                                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                                            Toast.makeText(getActivity(), messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                            getActivity().finish();
                                            startActivity(intent);
                                            sharedPreferences.edit().clear().apply();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageResponse> call, Throwable t) {
                                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
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