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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.model.MessageClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {
    private MaterialButton btnUbahProfil;
    private MaterialButton btnGantiKataSandi;
    private MaterialButton btnLogout;
    private TextView namaProfil, emailProfil, noHpProfil;
    private SharedPreferences sharedPreferences;
    private Config config;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", null);
        String nama = sharedPreferences.getString("Nama", null);
        String email = sharedPreferences.getString("Email", null);
        String no_hp = sharedPreferences.getString("NoHp", null);

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
            view.findViewById(R.id.layoutProfilGuest).setVisibility(view.GONE);
        }

//        //Get response dari ubah profil dan ubah password
        ActivityResultLauncher<Intent> intentResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            String response_nama = intent.getStringExtra("Nama");
                            String response_no_hp = intent.getStringExtra("NoHp");
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Nama", response_nama);
                            editor.putString("NoHp", response_no_hp);
                            editor.apply();

                            namaProfil.setText(response_nama);
                            noHpProfil.setText(response_no_hp);
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
                            config = new Config();
                            String token = sharedPreferences.getString("Token", null);

                            Call<MessageClass> call = config.configRetrofit().logout(token);
                            toggleViewProgressBar();
                            call.enqueue(new Callback<MessageClass>() {
                                @Override
                                public void onResponse(Call<MessageClass> call, Response<MessageClass> response) {
                                    if (response.code() == 200){
                                        if (response.isSuccessful()){
                                            Intent intent = new Intent(getActivity(), GetStartedActivity.class);
                                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            getActivity().finish();
                                            startActivity(intent);
                                            sharedPreferences.edit().clear().apply();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageClass> call, Throwable t) {
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

    public void toggleViewProgressBar(){
        btnUbahProfil.setEnabled(false);
        btnGantiKataSandi.setEnabled(false);
        btnLogout.setEnabled(false);
    }
}