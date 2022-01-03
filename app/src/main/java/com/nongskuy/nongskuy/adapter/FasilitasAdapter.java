package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.Nongskuy;

import java.util.ArrayList;

public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.FasilitasViewHolder>  {
    private Context context;

    ArrayList<Nongskuy> listFasilitas = new ArrayList<>();
    public void setListFasilitas(ArrayList<Nongskuy> listFasilitas){
        this.listFasilitas = listFasilitas;
    }

    @NonNull
    @Override
    public FasilitasAdapter.FasilitasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_fasilitas_horizontal, parent, false);
        context = view.getContext();
        return new FasilitasAdapter.FasilitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FasilitasAdapter.FasilitasViewHolder holder, int position) {
        Nongskuy nongskuy = listFasilitas.get(position);

        holder.imgFasilitas.setImageResource(R.drawable.wifi);
        holder.textFasilitas.setText(nongskuy.getFasilitasToko());
    }

    @Override
    public int getItemCount() {
        return listFasilitas.size();
    }

    public class FasilitasViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFasilitas;
        TextView textFasilitas;

        public FasilitasViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFasilitas = itemView.findViewById(R.id.imgFasilitas);
            textFasilitas = itemView.findViewById(R.id.textFasilitas);
        }
    }
}
