package com.nongskuy.nongskuy.adapter;

import android.animation.LayoutTransition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;

import java.util.ArrayList;

public class RiwayatNongskuyAdapter extends RecyclerView.Adapter<RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder>{

    //View Holder
    public class RiwayatNongskuyViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaToko,textStatusPesan, textTotalKursi, textTotalDeposit, textCaraBayar, textTglPesan, textWaktuPesan;
        public RiwayatNongskuyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaToko = itemView.findViewById(R.id.textNamaToko);
            textStatusPesan = itemView.findViewById(R.id.textStatusPesan);
            textTotalKursi = itemView.findViewById(R.id.textTotalKursi);
            textTotalDeposit = itemView.findViewById(R.id.textTotalDeposit);
            textCaraBayar= itemView.findViewById(R.id.textCaraBayar);
            textTglPesan = itemView.findViewById(R.id.textTglPesan);
            textWaktuPesan = itemView.findViewById(R.id.textWaktuPesan);

        }
    }

    //Array List menampung dan set data yang ditampilkan
    ArrayList<RiwayatNongskuy> listRiwayatNongskuy = new ArrayList<>();
    public void setListRiwayatNongskuy(ArrayList<RiwayatNongskuy> listRiwayatNongskuy) {
        this.listRiwayatNongskuy = listRiwayatNongskuy;
        notifyDataSetChanged();
    }

    //Method adapter
    @NonNull
    @Override
    public RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_riwayat_pemesanan_tempat, parent,false);
        RiwayatNongskuyViewHolder viewHolder = new RiwayatNongskuyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder viewHolder, int position) {
        RiwayatNongskuy riwayatNongskuy = listRiwayatNongskuy.get(position);
        viewHolder.textNamaToko.setText(riwayatNongskuy.namaToko);
        viewHolder.textStatusPesan.setText(riwayatNongskuy.statusPesan);
        viewHolder.textTotalKursi.setText(riwayatNongskuy.totalKursi.toString());
        viewHolder.textTotalDeposit.setText(riwayatNongskuy.totalDeposit.toString());
        viewHolder.textCaraBayar.setText(riwayatNongskuy.caraBayar);
        viewHolder.textTglPesan.setText(riwayatNongskuy.tglPesan);
        viewHolder.textWaktuPesan.setText(riwayatNongskuy.waktuPesan);
    }

    @Override
    public int getItemCount() {
        return listRiwayatNongskuy.size();
    }
}
