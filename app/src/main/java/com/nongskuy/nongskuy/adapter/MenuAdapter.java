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
import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.Helper;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Menu;
import com.nongskuy.nongskuy.model.Nongskuy;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{
    private Context context;


    public class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView textNamaMenu, textHargaMenu;
        ShapeableImageView imageMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            textNamaMenu= itemView.findViewById(R.id.textMenuStore);
            textHargaMenu = itemView.findViewById(R.id.textHargaMenuStore);
//            imageMenu = itemView.findViewById(R.id.imageMenu);
        }
    }

    ArrayList<Menu> listMenu = new ArrayList<>();
    public void setListMenu(ArrayList<Menu> listMenu){
        this.listMenu = listMenu;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_menu_horizontal, parent, false);
        context = view.getContext();
        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        Helper helper = new Helper();
        Menu menu = listMenu.get(position);

//        Glide.with(context)
//                .load(Uri.parse(menu.getGambar()))
//                .apply(new RequestOptions()
//                        .override(148, 92))
//                .into(holder.imageMenu);
        holder.textNamaMenu.setText(menu.getNamaMenu());
        holder.textHargaMenu.setText(helper.mataUangRupiah(menu.getHarga()));
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

}
