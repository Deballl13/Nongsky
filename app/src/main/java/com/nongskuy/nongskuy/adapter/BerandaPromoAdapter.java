package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Promo;

import java.util.ArrayList;

public class BerandaPromoAdapter extends RecyclerView.Adapter<BerandaPromoAdapter.BerandaPromoViewHolder>{

    ArrayList<Promo> listPromoBeranda;

    public BerandaPromoAdapter(ArrayList<Promo> listPromoBeranda) {
        this.listPromoBeranda = listPromoBeranda;
    }

    @NonNull
    @Override
    public BerandaPromoAdapter.BerandaPromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_promo_horizontal, parent, false);

        return new BerandaPromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaPromoAdapter.BerandaPromoViewHolder holder, int position) {
        holder.textMakananPromo.setText(listPromoBeranda.get(position).getNamaMakanan());
        holder.textTokoPromo.setText(listPromoBeranda.get(position).getNamaToko());
        holder.keterangan.setText(listPromoBeranda.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return listPromoBeranda.size();
    }

    public class BerandaPromoViewHolder extends RecyclerView.ViewHolder{

        TextView textMakananPromo, textTokoPromo, keterangan;

        public BerandaPromoViewHolder(@NonNull View itemView) {
            super(itemView);
            textMakananPromo = itemView.findViewById(R.id.textMakananPromoBeranda);
            textTokoPromo = itemView.findViewById(R.id.textTokoPromoBeranda);
            keterangan = itemView.findViewById(R.id.keteranganPromoBeranda);
        }
    }
}
