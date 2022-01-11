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
    ArrayList<Nongskuy> listFasilitas;

    public FasilitasAdapter(ArrayList<Nongskuy> listFasilitas) {
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

        switch (nongskuy.getFasilitasToko()) {
            case "Mushalla":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.mosque);
                break;
            case "Kamar Mandi":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.toilet);
                break;
            case "Free WiFi":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.wifi);
                break;
            case "24 Jam":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.ic_24hours);
                break;
            case "Smoking Area":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.smoking);
                break;
            case "VIP Room":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.vip);
                break;
            case "Area Bermain":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.playground);
                break;
            case "No Smoking Area":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.nosmoking);
                break;
            case "Live Music":
                holder.textFasilitas.setText(nongskuy.getFasilitasToko());
                holder.imgFasilitas.setImageResource(R.drawable.music);
                break;
        }

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
