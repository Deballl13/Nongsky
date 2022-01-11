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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.Nongskuy;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    ArrayList<Menu> listMenu;
    private Context context;
    private boolean isShimmer = true;
    private Integer numberShimmer = 10;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    public MenuAdapter(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView textMenu, textHargaSebelumPromo, textHargaSetelahPromo, textTokoPromo, keterangan;
        ShapeableImageView imageMenu;
        ShimmerFrameLayout shimmerFrameLayout;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            shimmerFrameLayout = itemView.findViewById(R.id.shimmerPromoBeranda);
            imageMenu = itemView.findViewById(R.id.imagePromoBeranda);
            textTokoPromo = itemView.findViewById(R.id.textTokoPromoBeranda);
            textTokoPromo.setVisibility(View.GONE);

            textMenu = itemView.findViewById(R.id.textMenuPromoBeranda);
            textHargaSebelumPromo = itemView.findViewById(R.id.textHargaAwalPromoBeranda);
            textHargaSetelahPromo = itemView.findViewById(R.id.textHargaSetelahPromoBeranda);
            keterangan = itemView.findViewById(R.id.keteranganPromoBeranda);
        }
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_promo_horizontal, parent, false);
        context = view.getContext();
        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        if(isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            Helper helper = new Helper();
            Menu menu = listMenu.get(position);

            holder.imageMenu.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(menu.getGambar()))
                    .apply(new RequestOptions()
                            .override(148, 92))
                    .into(holder.imageMenu);

            holder.textMenu.setBackground(null);
            holder.textMenu.setText(menu.getNamaMenu());

            holder.textHargaSebelumPromo.setBackground(null);
            holder.textHargaSebelumPromo.setText(helper.mataUangRupiah(menu.getHarga()));

            if(menu.getJenisPromo() != null && menu.getPersentase() != null){

                holder.textHargaSebelumPromo.setPaintFlags(holder.textHargaSebelumPromo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                holder.textHargaSetelahPromo.setBackground(null);
                holder.textHargaSetelahPromo.setText(
                        helper.mataUangRupiah((100 - menu.getPersentase()) * (menu.getHarga()/100))
                );

                holder.keterangan.setText(menu.getJenisPromo() + " " + menu.getPersentase().toString() + "%");

                if(menu.getJenisPromo().equals("diskon")){
                    holder.keterangan.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
                    holder.keterangan.setBackgroundResource(R.drawable.background_ket_diskon);

                }
                else if(menu.getJenisPromo().equals("cashback")){
                    holder.keterangan.setTextColor(Color.WHITE);
                    holder.keterangan.setBackgroundResource(R.drawable.background_ket_cashback);
                }
            }
            else
            {
                holder.textHargaSetelahPromo.setVisibility(View.GONE);
                holder.keterangan.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return isShimmer || listMenu.size() > 10 ? 10 : listMenu.size();
    }

}
