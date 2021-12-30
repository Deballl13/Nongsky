package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Nongskuy;
import java.util.ArrayList;

public class PencarianAdapter extends RecyclerView.Adapter<PencarianAdapter.PencarianViewHolder>{

    private ArrayList<Nongskuy> listPencarian;
    private Context context;
    private boolean isShimmer = true;

    //set shimmer
    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    //constructor
    public PencarianAdapter(ArrayList<Nongskuy> listPencarian) {
        this.listPencarian = listPencarian;
    }

    //View Holder
    public class PencarianViewHolder extends RecyclerView.ViewHolder {
        TextView textNamaTokoPencarian, textAlamatTokoPencarian, textJarakToko;
        ShapeableImageView imgTokoPencarian;
        ImageButton btnNext;
        ShimmerFrameLayout shimmerFrameLayout;

        public PencarianViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerPencarian);
            btnNext = itemView.findViewById(R.id.nextButton);

            imgTokoPencarian = itemView.findViewById(R.id.logoTokoPencarian);
            textNamaTokoPencarian = itemView.findViewById(R.id.textNamaTokoPencarian);
            textAlamatTokoPencarian = itemView.findViewById(R.id.textAlamatTokoPencarian);
            textJarakToko = itemView.findViewById(R.id.textJarakToko);
        }
    }

    //Method adapter
    @NonNull
    @Override
    public PencarianAdapter.PencarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_pencarian, parent,false);
        context = view.getContext();
        return new PencarianAdapter.PencarianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PencarianAdapter.PencarianViewHolder viewHolder, int position) {
        if (isShimmer){
            viewHolder.shimmerFrameLayout.startShimmer();
        }
        else{
            viewHolder.shimmerFrameLayout.stopShimmer();
            viewHolder.shimmerFrameLayout.setShimmer(null);

            Nongskuy nongskuy = listPencarian.get(position);

            viewHolder.imgTokoPencarian.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(nongskuy.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(80, 80))
                    .into(viewHolder.imgTokoPencarian);

            viewHolder.textNamaTokoPencarian.setBackground(null);
            viewHolder.textNamaTokoPencarian.setText(nongskuy.getNamaToko());

            viewHolder.textAlamatTokoPencarian.setBackground(null);
            viewHolder.textAlamatTokoPencarian.setText(nongskuy.getAlamatToko());

            viewHolder.textJarakToko.setBackground(null);
            viewHolder.textJarakToko.setText(nongskuy.getJarakToko().toString() + " Km");

            viewHolder.btnNext.setImageResource(R.drawable.ic_baseline_navigate_next);
            viewHolder.btnNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer ? 10 : listPencarian.size();
    }

}