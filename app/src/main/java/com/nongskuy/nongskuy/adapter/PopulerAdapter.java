package com.nongskuy.nongskuy.adapter;

import android.content.Context;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Toko;
import java.util.ArrayList;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.PopulerViewHolder> {

    private ArrayList<Toko> listPopuler;
    private Context context;
    private boolean isShimmer = true;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    public PopulerAdapter(ArrayList<Toko> listPopuler) {
        this.listPopuler = listPopuler;
    }

    @NonNull
    @Override
    public PopulerAdapter.PopulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_populer, parent, false);
        context = view.getContext();
        return new PopulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopulerAdapter.PopulerViewHolder holder, int position) {
        if (isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            Toko toko = listPopuler.get(position);

            holder.imageTokoPopuler.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(toko.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(80, 80))
                    .into(holder.imageTokoPopuler);

            holder.textNamaTokoPopuler.setBackground(null);
            holder.textNamaTokoPopuler.setText(toko.getNamaToko());

            holder.textAlamatTokoPopuler.setBackground(null);
            holder.textAlamatTokoPopuler.setText(toko.getAlamatToko());

            holder.textTipeTongkrongan.setBackground(null);
            holder.textTipeTongkrongan.setText(toko.getTipeToko());

            holder.textJarakTongkrongan.setBackground(null);
            holder.textJarakTongkrongan.setText(toko.getJarakToko().toString() + " " + "Km");

            holder.ratingPopuler.setVisibility(View.VISIBLE);
            holder.ratingPopuler.setText(toko.getRatingToko());
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer ? 10 : listPopuler.size();
    }

    public class PopulerViewHolder extends RecyclerView.ViewHolder{
        TextView textNamaTokoPopuler, textAlamatTokoPopuler, textTipeTongkrongan, textJarakTongkrongan;
        ShapeableImageView imageTokoPopuler;
        MaterialButton ratingPopuler;
        ShimmerFrameLayout shimmerFrameLayout;

        public PopulerViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerTokoPopuler);
            imageTokoPopuler = itemView.findViewById(R.id.imageTokoPopuler);
            ratingPopuler = itemView.findViewById(R.id.ratingPopuler);
            textNamaTokoPopuler = itemView.findViewById(R.id.textNamaTokoPopuler);
            textAlamatTokoPopuler = itemView.findViewById(R.id.textAlamatTokoPopuler);
            textTipeTongkrongan = itemView.findViewById(R.id.textTipeTongkrongan);
            textJarakTongkrongan = itemView.findViewById(R.id.textJarakTongkrongan);
        }
    }
}
