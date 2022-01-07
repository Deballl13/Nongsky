package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Nongskuy;
import java.util.ArrayList;

public class BerandaTerdekatAdapter extends RecyclerView.Adapter<BerandaTerdekatAdapter.BerandaTerdekatViewHolder>{

    private ArrayList<Nongskuy> listTerdekatBeranda;
    private Context context;
    private boolean isShimmer = true;
    private OnTerdekatViewHolderClick terdekatClickObject;

    // constructor
    public BerandaTerdekatAdapter(ArrayList<Nongskuy> listTerdekatBeranda) {
        this.listTerdekatBeranda = listTerdekatBeranda;
    }

    // set shimmer
    public void setShimmer(boolean shimmer) {
        isShimmer = shimmer;
    }

    @NonNull
    @Override
    public BerandaTerdekatAdapter.BerandaTerdekatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_terdekat_horizontal, parent, false);
        context = view.getContext();
        return new BerandaTerdekatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaTerdekatAdapter.BerandaTerdekatViewHolder holder, int position) {
        if(isShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            Nongskuy nongskuy = listTerdekatBeranda.get(position);

            holder.imageTokoTerdekat.setBackground(null);
            Glide.with(context)
                    .load(Uri.parse(nongskuy.getGambarToko()))
                    .apply(new RequestOptions()
                            .override(148, 92))
                    .into(holder.imageTokoTerdekat);

            holder.namaToko.setBackground(null);
            holder.namaToko.setText(nongskuy.getNamaToko());

            holder.alamatToko.setBackground(null);
            holder.alamatToko.setText(nongskuy.getAlamatToko());

            holder.tipeToko.setBackground(null);
            holder.tipeToko.setText(nongskuy.getTipeToko());

            holder.jarakToko.setBackground(null);
            holder.jarakToko.setText(nongskuy.getJarakToko() + " Km");
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer || listTerdekatBeranda.size() > 10 ? 10 : listTerdekatBeranda.size();
    }

    // onClick item recyclerview
    public interface OnTerdekatViewHolderClick{
        void onTerdekatBerandaClick(Nongskuy nongskuy);
    }

    // set objek onClick item recyclerview
    public void setTerdekatClickObject(OnTerdekatViewHolderClick terdekatClickObject){
        this.terdekatClickObject = terdekatClickObject;
    }

    public class BerandaTerdekatViewHolder extends RecyclerView.ViewHolder{
        ShimmerFrameLayout shimmerFrameLayout;
        TextView namaToko, alamatToko, tipeToko, jarakToko;
        ShapeableImageView imageTokoTerdekat;

        public BerandaTerdekatViewHolder(@NonNull View itemView) {
            super(itemView);
            namaToko = itemView.findViewById(R.id.textNamaTokoTerdekatBeranda);
            alamatToko = itemView.findViewById(R.id.textAlamatTokoTerdekatBeranda);
            tipeToko = itemView.findViewById(R.id.textTipeTokoTerdekatBeranda);
            jarakToko = itemView.findViewById(R.id.textJarakTerdekatBeranda);
            imageTokoTerdekat = itemView.findViewById(R.id.imageTerdekatBeranda);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerTokoTerdekatBeranda);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isShimmer){
                        Nongskuy nongskuy = listTerdekatBeranda.get(getAdapterPosition());
                        terdekatClickObject.onTerdekatBerandaClick(nongskuy);
                    }
                }
            });
        }
    }
}
