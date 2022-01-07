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
import com.nongskuy.nongskuy.model.Nongskuy;
import java.util.ArrayList;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.PopulerViewHolder> {

    private ArrayList<Nongskuy> listPopuler;
    private Context context;
    private boolean isShimmer = true;
    private PopulerAdapter.OnPopulerViewHolderClick populerClickObject;

    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    public PopulerAdapter(ArrayList<Nongskuy> listPopuler) {
        this.listPopuler = listPopuler;
    }

    //Click Listener Item RV
    public interface OnPopulerViewHolderClick{
        void onPopulerClick(Nongskuy nongskuy);
    }

    public void setPopulerClickObject(PopulerAdapter.OnPopulerViewHolderClick populerClickObject){
        this.populerClickObject = populerClickObject;
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

            Nongskuy nongskuy = listPopuler.get(position);

            holder.imageTokoPopuler.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(nongskuy.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(80, 80))
                    .into(holder.imageTokoPopuler);

            holder.textNamaTokoPopuler.setBackground(null);
            holder.textNamaTokoPopuler.setText(nongskuy.getNamaToko());

            holder.textAlamatTokoPopuler.setBackground(null);
            holder.textAlamatTokoPopuler.setText(nongskuy.getAlamatToko());

            holder.textTipeTongkrongan.setBackground(null);
            holder.textTipeTongkrongan.setText(nongskuy.getTipeToko());

            holder.textJarakTongkrongan.setBackground(null);
            holder.textJarakTongkrongan.setText(nongskuy.getJarakToko().toString() + " Km");

            holder.ratingPopuler.setVisibility(View.VISIBLE);
            holder.ratingPopuler.setText(nongskuy.getRatingToko());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isShimmer){
                        Nongskuy nongskuy = listPopuler.get(getAdapterPosition());
                        populerClickObject.onPopulerClick(nongskuy);
                    }
                }
            });
        }
    }
}
