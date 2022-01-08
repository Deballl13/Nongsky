package com.nongskuy.nongskuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.nongskuy.nongskuy.R;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private Context context;
    private ArrayList<Review> listReview;

    public ReviewAdapter(ArrayList<Review> listReview) {
        this.listReview = listReview;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView textNilaiReview, textKomentarReview, textNamaUserReview, textTanggalReview;
        ShapeableImageView imgUserReview;
        ImageView imgStarReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStarReview = itemView.findViewById(R.id.imgStarReview);
            imgUserReview = itemView.findViewById(R.id.imgUserReview);
            textNilaiReview = itemView.findViewById(R.id.textNilaiReview);
            textKomentarReview = itemView.findViewById(R.id.textKomentarReview);
            textNamaUserReview= itemView.findViewById(R.id.textNamaUserReview);
            textTanggalReview = itemView.findViewById(R.id.textTanggalReview);
        }
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_review_horizontal, parent, false);
        context = view.getContext();
        return new ReviewAdapter.ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        Review review = listReview.get(position);

        holder.imgStarReview.setImageResource(R.drawable.ic_star);
        holder.imgUserReview.setImageResource(R.drawable.avatar);
        holder.textNamaUserReview.setText(review.getNamaUser());
        holder.textNilaiReview.setText(review.getRating());
        holder.textKomentarReview.setText(review.getKomentar());
        holder.textTanggalReview.setText(review.getTanggal());
    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

}
