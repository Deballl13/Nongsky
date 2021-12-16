package com.nongskuy.nongskuy.adapter;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RiwayatNongskuyAdapter extends RecyclerView.Adapter<RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder>{

    private Context context;
    private ArrayList<RiwayatNongskuy> listRiwayatNongskuy;

    //Constructor
    public RiwayatNongskuyAdapter (ArrayList<RiwayatNongskuy> listRiwayatNongskuy) {
        this.listRiwayatNongskuy = listRiwayatNongskuy;
    }

    //View Holder
    public class RiwayatNongskuyViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaToko, textStatusPesan, textTotalKursi, textTotalDeposit, textCaraBayar, textTglPesan, textWaktuPesan;
        ShapeableImageView imgTokoRiwayatNongskuy;

        public RiwayatNongskuyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaToko = itemView.findViewById(R.id.textNamaToko);
            imgTokoRiwayatNongskuy = itemView.findViewById(R.id.imgTokoRiwayatNongskuy);
            textStatusPesan = itemView.findViewById(R.id.textStatusPesan);
            textTotalKursi = itemView.findViewById(R.id.textTotalKursi);
            textTotalDeposit = itemView.findViewById(R.id.textTotalDeposit);
            textCaraBayar= itemView.findViewById(R.id.textCaraBayar);
            textTglPesan = itemView.findViewById(R.id.textTglPesan);
            textWaktuPesan = itemView.findViewById(R.id.textWaktuPesan);
        }
    }



    //Method adapter
    @NonNull
    @Override
    public RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_riwayat_pemesanan_tempat, parent,false);
        RiwayatNongskuyViewHolder viewHolder = new RiwayatNongskuyViewHolder(view);
        context = view.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder viewHolder, int position) {
        // format mata uang rupiah
        Helper helper = new Helper();

        RiwayatNongskuy riwayatNongskuy = listRiwayatNongskuy.get(position);
        viewHolder.textNamaToko.setText(riwayatNongskuy.getNamaToko());
        Glide.with(context)
                .load(Uri.parse(riwayatNongskuy.getGambarToko()))
                .apply(new RequestOptions()
                        .override(80, 80))
                .into(viewHolder.imgTokoRiwayatNongskuy);
        viewHolder.textTotalKursi.setText(riwayatNongskuy.getTotalKursi().toString());
        viewHolder.textTotalDeposit.setText(helper.mataUangRupiah(riwayatNongskuy.getTotalDeposit()));
        viewHolder.textCaraBayar.setText(riwayatNongskuy.getCaraBayar());
        viewHolder.textTglPesan.setText(riwayatNongskuy.getTglPesan());
        viewHolder.textWaktuPesan.setText(riwayatNongskuy.getWaktuPesan());

        if(riwayatNongskuy.getStatusPesan().equals(1)){
            viewHolder.textStatusPesan.setText("Dipesan");
            viewHolder.textStatusPesan.setTextColor(Color.GREEN);
        }
        else if(riwayatNongskuy.getStatusPesan().equals(0)){
            viewHolder.textStatusPesan.setText("Dibatalkan");
            viewHolder.textStatusPesan.setTextColor(Color.RED);
        }
        else if(riwayatNongskuy.getStatusPesan().equals(2)){
            viewHolder.textStatusPesan.setText("Proses Bayar");
            viewHolder.textStatusPesan.setTextColor(Color.BLUE);
        }
    }

    @Override
    public int getItemCount() {
        return listRiwayatNongskuy.size();
    }
}
