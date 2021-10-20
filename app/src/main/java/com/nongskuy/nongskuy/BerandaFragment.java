package com.nongskuy.nongskuy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BerandaFragment extends Fragment {

    RecyclerView recyclerViewBerandaPopuler;
    TextView namaUser;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);


        //Mengambil email login user
        namaUser = view.findViewById(R.id.textName);

        if(MainActivity.userEmail != null){
            namaUser.setText("William Wahyu");
        }


        return view;
    }

}