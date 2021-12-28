package com.nongskuy.nongskuy.adapter;

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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.RiwayatNongskuy;
import java.util.ArrayList;

public class RiwayatNongskuyAdapter extends RecyclerView.Adapter<RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder>{

    private Context context;
    private ArrayList<RiwayatNongskuy> listRiwayatNongskuy;
    private boolean isShimmer = true;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    //Constructor
    public RiwayatNongskuyAdapter (ArrayList<RiwayatNongskuy> listRiwayatNongskuy) {
        this.listRiwayatNongskuy = listRiwayatNongskuy;
    }

    //View Holder
    public class RiwayatNongskuyViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaToko, textStatusPesan, textTotalKursi, textTotalDeposit, textCaraBayar, textTglPesan, textWaktuPesan;
        TextView totalKursi, totalDeposit, caraBayar, tanggalPesan, waktuPesan;
        ShapeableImageView imgTokoRiwayatNongskuy;
        ShimmerFrameLayout shimmerFrameLayout;

        public RiwayatNongskuyViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerRiwayat);

            textNamaToko = itemView.findViewById(R.id.textNamaToko);
            imgTokoRiwayatNongskuy = itemView.findViewById(R.id.imgTokoRiwayatNongskuy);
            textStatusPesan = itemView.findViewById(R.id.textStatusPesan);
            textTotalKursi = itemView.findViewById(R.id.textTotalKursi);
            textTotalDeposit = itemView.findViewById(R.id.textTotalDeposit);
            textCaraBayar= itemView.findViewById(R.id.textCaraBayar);
            textTglPesan = itemView.findViewById(R.id.textTglPesan);
            textWaktuPesan = itemView.findViewById(R.id.textWaktuPesan);

            totalKursi = itemView.findViewById(R.id.TotalKursi);
            totalDeposit = itemView.findViewById(R.id.TotalDeposit);
            caraBayar = itemView.findViewById(R.id.CaraBayar);
            tanggalPesan = itemView.findViewById(R.id.TanggalPesan);
            waktuPesan = itemView.findViewById(R.id.WaktuPesan);
        }
    }


    //Method adapter
    @NonNull
    @Override
    public RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_riwayat_pemesanan_tempat, parent,false);
        context = view.getContext();
        return new RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatNongskuyAdapter.RiwayatNongskuyViewHolder viewHolder, int position) {
        if (isShimmer){
            viewHolder.shimmerFrameLayout.startShimmer();
        }
        else{
            viewHolder.shimmerFrameLayout.stopShimmer();
            viewHolder.shimmerFrameLayout.setShimmer(null);

            Helper helper = new Helper();

            RiwayatNongskuy riwayatNongskuy = listRiwayatNongskuy.get(position);

            //set data
            viewHolder.imgTokoRiwayatNongskuy.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(riwayatNongskuy.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(80, 80))
                    .into(viewHolder.imgTokoRiwayatNongskuy);

            viewHolder.textNamaToko.setBackground(null);
            viewHolder.textNamaToko.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_store, 0, 0, 0);
            viewHolder.textNamaToko.setCompoundDrawablePadding(10);
            viewHolder.textNamaToko.setText(riwayatNongskuy.getNamaToko());

            viewHolder.textTotalKursi.setBackground(null);
            viewHolder.textTotalKursi.setText(":  " + riwayatNongskuy.getTotalKursi().toString() + " Kursi");

            viewHolder.textTotalDeposit.setBackground(null);
            viewHolder.textTotalDeposit.setText(":  " + helper.mataUangRupiah(riwayatNongskuy.getTotalDeposit()));

            viewHolder.textCaraBayar.setBackground(null);
            viewHolder.textCaraBayar.setText(":  " + riwayatNongskuy.getCaraBayar());

            viewHolder.textTglPesan.setBackground(null);
            viewHolder.textTglPesan.setText(":  " + riwayatNongskuy.getTglPesan());

            viewHolder.textWaktuPesan.setBackground(null);
            viewHolder.textWaktuPesan.setText(":  " + riwayatNongskuy.getWaktuPesan());

            viewHolder.textStatusPesan.setBackground(null);
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

            //set judul data
            viewHolder.totalKursi.setBackground(null);
            viewHolder.totalKursi.setText("Total Kursi");

            viewHolder.totalDeposit.setBackground(null);
            viewHolder.totalDeposit.setText("Total Deposit");

            viewHolder.caraBayar.setBackground(null);
            viewHolder.caraBayar.setText("Metode Bayar");

            viewHolder.tanggalPesan.setBackground(null);
            viewHolder.tanggalPesan.setText("Tanggal Pesan");

            viewHolder.waktuPesan.setBackground(null);
            viewHolder.waktuPesan.setText("Waktu Pesan");
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer ? 10 : listRiwayatNongskuy.size();
    }
}
