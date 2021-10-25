package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Populer;

import java.util.ArrayList;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.PopulerViewHolder> {

    private ArrayList<Populer> listPopuler;

    public PopulerAdapter(ArrayList<Populer> listPopuler) {
        this.listPopuler = listPopuler;
    }

    @NonNull
    @Override
    public PopulerAdapter.PopulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_populer, parent, false);

        return new PopulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopulerAdapter.PopulerViewHolder holder, int position) {
        holder.ratingPopuler.setText(listPopuler.get(position).getRating().toString());
        holder.textNamaTokoPopuler.setText(listPopuler.get(position).getNamaToko());
        holder.textAlamatTokoPopuler.setText(listPopuler.get(position).getAlamatToko());
        holder.textTipeTongkrongan.setText(listPopuler.get(position).getTipeToko());
        holder.textJarakTongkrongan.setText(listPopuler.get(position).getJarakToko().toString());
        holder.satuanJarak.setText(listPopuler.get(position).getSatuanJarak());
    }

    @Override
    public int getItemCount() {
        return listPopuler.size();
    }

    public class PopulerViewHolder extends RecyclerView.ViewHolder{

        MaterialButton ratingPopuler;
        TextView textNamaTokoPopuler, textAlamatTokoPopuler, textTipeTongkrongan, textJarakTongkrongan, satuanJarak;

        public PopulerViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingPopuler = itemView.findViewById(R.id.ratingPopuler);
            textNamaTokoPopuler = itemView.findViewById(R.id.textNamaTokoPopuler);
            textAlamatTokoPopuler = itemView.findViewById(R.id.textAlamatTokoPopuler);
            textTipeTongkrongan = itemView.findViewById(R.id.textTipeTongkrongan);
            textJarakTongkrongan = itemView.findViewById(R.id.textJarakTongkrongan);
            satuanJarak = itemView.findViewById(R.id.satuanJarak);
        }
    }
}
