package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class PencarianAdapter extends RecyclerView.Adapter<PencarianAdapter.PencarianViewHolder>{

    //View Holder
    public class PencarianViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaTokoPencarian, textAlamatTokoPencarian, textJarakToko, satuanJarak;

        public PencarianViewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaTokoPencarian = itemView.findViewById(R.id.textNamaTokoPencarian);
            textAlamatTokoPencarian = itemView.findViewById(R.id.textAlamatTokoPencarian);
            textJarakToko = itemView.findViewById(R.id.textJarakToko);
            satuanJarak = itemView.findViewById(R.id.satuanJarak);
        }
    }

    //Array List menampung dan set data yang ditampilkan
    ArrayList<Store> listPencarian = new ArrayList<>();
    public void setListPencarian (ArrayList<Store> listPencarian) {
        this.listPencarian = listPencarian;
        notifyDataSetChanged();
    }

    //Method adapter
    @NonNull
    @Override
    public PencarianAdapter.PencarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_pencarian, parent,false);
        PencarianAdapter.PencarianViewHolder viewHolder = new PencarianAdapter.PencarianViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PencarianAdapter.PencarianViewHolder viewHolder, int position) {

        Store store = listPencarian.get(position);
        viewHolder.textNamaTokoPencarian.setText(store.getNamaToko());
        viewHolder.textAlamatTokoPencarian.setText(store.getAlamatToko());
        viewHolder.textJarakToko.setText(store.getJarakToko().toString());
        viewHolder.satuanJarak.setText(store.getSatuanJarak());
    }

    @Override
    public int getItemCount() {
        return listPencarian.size();
    }

}