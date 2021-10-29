package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class BerandaPopulerAdapter extends RecyclerView.Adapter<BerandaPopulerAdapter.BerandaPopulerViewHolder> {

    private ArrayList<Store> listPopulerBeranda;

    public BerandaPopulerAdapter(ArrayList<Store> listPopulerBeranda) {
        this.listPopulerBeranda = listPopulerBeranda;
    }

    @NonNull
    @Override
    public BerandaPopulerAdapter.BerandaPopulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_populer_horizontal, parent, false);

        return new BerandaPopulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaPopulerAdapter.BerandaPopulerViewHolder holder, int position) {
        holder.ratingPopuler.setText(listPopulerBeranda.get(position).getRating().toString());
        holder.textNamaTokoPopuler.setText(listPopulerBeranda.get(position).getNamaToko());
        holder.textTipeTongkrongan.setText(listPopulerBeranda.get(position).getTipeToko());
        holder.textJarakTongkrongan.setText(listPopulerBeranda.get(position).getJarakToko().toString());
        holder.satuanJarak.setText(listPopulerBeranda.get(position).getSatuanJarak());
    }

    @Override
    public int getItemCount() {
        return listPopulerBeranda.size();
    }

    public class BerandaPopulerViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaTokoPopuler, textTipeTongkrongan, textJarakTongkrongan, satuanJarak, ratingPopuler;

        public BerandaPopulerViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingPopuler = itemView.findViewById(R.id.ratingPopulerBeranda);
            textNamaTokoPopuler = itemView.findViewById(R.id.textNamaTokoPopulerBeranda);
            textTipeTongkrongan = itemView.findViewById(R.id.textTipeTokoPopulerBeranda);
            textJarakTongkrongan = itemView.findViewById(R.id.textJarakPopulerBeranda);
            satuanJarak = itemView.findViewById(R.id.textSatuanJarakPopulerBeranda);
        }
    }
}
