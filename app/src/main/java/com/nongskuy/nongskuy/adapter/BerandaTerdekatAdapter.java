package com.nongskuy.nongskuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Store;

import java.util.ArrayList;

public class BerandaTerdekatAdapter extends RecyclerView.Adapter<BerandaTerdekatAdapter.BerandaTerdekatViewHolder>{

    ArrayList<Store> listTerdekatBeranda;

    public BerandaTerdekatAdapter(ArrayList<Store> listTerdekatBeranda) {
        this.listTerdekatBeranda = listTerdekatBeranda;
    }

    @NonNull
    @Override
    public BerandaTerdekatAdapter.BerandaTerdekatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_terdekat_horizontal, parent, false);

        return new BerandaTerdekatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaTerdekatAdapter.BerandaTerdekatViewHolder holder, int position) {
        holder.namaToko.setText(listTerdekatBeranda.get(position).getNamaToko());
        holder.alamatToko.setText(listTerdekatBeranda.get(position).getAlamatToko());
        holder.jarakToko.setText(listTerdekatBeranda.get(position).getJarakToko().toString());
        holder.satuanJarak.setText(listTerdekatBeranda.get(position).getSatuanJarak());
    }

    @Override
    public int getItemCount() {
        return listTerdekatBeranda.size();
    }

    public class BerandaTerdekatViewHolder extends RecyclerView.ViewHolder{

        TextView namaToko, alamatToko, jarakToko, satuanJarak;

        public BerandaTerdekatViewHolder(@NonNull View itemView) {
            super(itemView);
            namaToko = itemView.findViewById(R.id.textNamaTokoTerdekatBeranda);
            alamatToko = itemView.findViewById(R.id.textAlamatTokoTerdekatBeranda);
            jarakToko = itemView.findViewById(R.id.textJarakTerdekatBeranda);
            satuanJarak = itemView.findViewById(R.id.textSatuanJarakTerdekatBeranda);
        }
    }
}
