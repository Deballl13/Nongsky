package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Promo;
import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoViewHolder> {

    private ArrayList<Promo> listPromo;
    private Context context;
    private boolean isShimmer = true;
    Integer numberShimmer = 10;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    public PromoAdapter(ArrayList<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    @NonNull
    @Override
    public PromoAdapter.PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_promo, parent, false);
        context = view.getContext();
        return new PromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.PromoViewHolder holder, int position) {
        if(isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            Promo promo = listPromo.get(position);

            // format mata uang rupiah
            Helper helper = new Helper();

            holder.imagePromo.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(promo.getGambar()))
                    .apply(new RequestOptions()
                            .override(82, 84))
                    .into(holder.imagePromo);

            holder.textMenuPromo.setBackground(null);
            holder.textMenuPromo.setText(promo.getNamaMenu());

            holder.textTokoPromo.setBackground(null);
            holder.textTokoPromo.setText(promo.getNamaToko());

            holder.textHargaSebelumPromo.setBackground(null);
            holder.textHargaSebelumPromo.setText(helper.mataUangRupiah(promo.getHargaAwal()));
            holder.textHargaSebelumPromo.setPaintFlags(holder.textHargaSebelumPromo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.textHargaSetelahPromo.setBackground(null);
            holder.textHargaSetelahPromo.setText(
                    helper.mataUangRupiah((100 - promo.getPersentase()) * (promo.getHargaAwal()/100))
            );

            holder.keterangan.setBackground(null);
            holder.keterangan.setText(promo.getJenis_promo() + " " + promo.getPersentase().toString() + "%");

            if(promo.getJenis_promo().equals("diskon")){
                holder.keterangan.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
                holder.keterangan.setBackgroundResource(R.drawable.background_ket_diskon);

            }
            else if(promo.getJenis_promo().equals("cashback")){
                holder.keterangan.setTextColor(Color.WHITE);
                holder.keterangan.setBackgroundResource(R.drawable.background_ket_cashback);
            }
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer?numberShimmer:listPromo.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder{

        TextView textMenuPromo, textTokoPromo, textHargaSetelahPromo, textHargaSebelumPromo, keterangan;
        ShapeableImageView imagePromo;
        ShimmerFrameLayout shimmerFrameLayout;

        public PromoViewHolder(@NonNull View view){
            super(view);
            shimmerFrameLayout = view.findViewById(R.id.shimmerPromo);
            imagePromo = view.findViewById(R.id.imagePromo);
            textMenuPromo = view.findViewById(R.id.textMenuPromo);
            textTokoPromo = view.findViewById(R.id.textTokoPromo);
            textHargaSetelahPromo = view.findViewById(R.id.textHargaSetelahPromo);
            textHargaSebelumPromo = view.findViewById(R.id.textHargaSebelumPromo);
            keterangan = view.findViewById(R.id.keteranganPromo);
        }

    }
}
