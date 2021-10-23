package com.nongskuy.nongskuy.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Promo;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoViewHolder> {

    private ArrayList<Promo> listPromo;

    public PromoAdapter(ArrayList<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    @NonNull
    @Override
    public PromoAdapter.PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_promo, parent, false);

        return new PromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.PromoViewHolder holder, int position) {
        holder.textMakananPromo.setText(listPromo.get(position).getNamaMakanan());
        holder.textTokoPromo.setText(listPromo.get(position).getNamaToko());
        holder.textHargaSetelahPromo.setText(listPromo.get(position).getHargaPromo().toString());
        holder.textHargaSebelumPromo.setText(listPromo.get(position).getHargaAwal().toString());
        holder.keterangan.setText(listPromo.get(position).getKeterangan());

        holder.textHargaSebelumPromo.setPaintFlags(holder.textHargaSebelumPromo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return listPromo.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder{

        TextView textMakananPromo, textTokoPromo, textHargaSetelahPromo, textHargaSebelumPromo, keterangan;

        public PromoViewHolder(@NonNull View view){
            super(view);
            textMakananPromo = view.findViewById(R.id.textMakananPromo);
            textTokoPromo = view.findViewById(R.id.textTokoPromo);
            textHargaSetelahPromo = view.findViewById(R.id.textHargaSetelahPromo);
            textHargaSebelumPromo = view.findViewById(R.id.textHargaSebelumPromo);
            keterangan = view.findViewById(R.id.keteranganPromo);
        }

    }
}
