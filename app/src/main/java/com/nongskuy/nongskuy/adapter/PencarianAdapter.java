package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class PencarianAdapter extends RecyclerView.Adapter<PencarianAdapter.PencarianViewHolder>{

    private ArrayList<Store> listPencarian;

    public PencarianAdapter(ArrayList<Store> listPencarian) {
        this.listPencarian = listPencarian;
    }

    @NonNull
    @Override
    public PencarianAdapter.PencarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_pencarian, parent, false);

        return new PencarianAdapter.PencarianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PencarianAdapter.PencarianViewHolder holder, int position) {
//        holder.textNamaTokoPencarian.setText(listPencarian.get(position).getRating().toString());
//        holder.textAlamatTokoPencarian.setText(listPencarian.get(position).getNamaToko());
//        holder.textJarakToko.setText(listPencarian.get(position).getTipeToko());
//        holder.logoTokoPencarian.setImageResource(listPencarian.get(position).getJarakToko().toString());
//        holder.satuanJarak.setText(listPencarian.get(position).getSatuanJarak());
    }

    @Override
    public int getItemCount() {
        return listPencarian.size();
    }

    public class PencarianViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaTokoPencarian, textAlamatTokoPencarian, textJarakToko, satuanJarak;
        ShapeableImageView logoTokoPencarian;

        public PencarianViewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaTokoPencarian = itemView.findViewById(R.id.textNamaTokoPencarian);
            textAlamatTokoPencarian = itemView.findViewById(R.id.textAlamatTokoPencarian);
            textJarakToko = itemView.findViewById(R.id.textJarakToko);
            logoTokoPencarian = itemView.findViewById(R.id.logoTokoPencarian);
            satuanJarak = itemView.findViewById(R.id.textSatuanJarakPopulerBeranda);
        }
    }

}