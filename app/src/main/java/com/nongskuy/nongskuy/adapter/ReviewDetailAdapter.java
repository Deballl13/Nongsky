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
import com.nongskuy.nongskuy.model.Review;

import java.util.ArrayList;

public class ReviewDetailAdapter extends RecyclerView.Adapter<ReviewDetailAdapter.ReviewDetailViewHolder>{

    private Context context;

    ArrayList<Review> listReview = new ArrayList<>();
    public void setListReview(ArrayList<Review> listReview){
        this.listReview = listReview;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewDetailAdapter.ReviewDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_detail_review, parent, false);
        context = view.getContext();
        return new ReviewDetailAdapter.ReviewDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewDetailAdapter.ReviewDetailViewHolder holder, int position) {
        Review review = listReview.get(position);

        holder.imgStarReviewDetail.setImageResource(R.drawable.ic_star);
        holder.imgUserReviewDetail.setImageResource(R.drawable.avatar);
        holder.aksenKoma.setImageResource(R.drawable.ic_round_format_quote_24);

        holder.textNamaUserReviewDetail.setText(review.getIdUser().toString());
        holder.textNilaiReviewDetail.setText(review.getRating().toString());
        holder.textKomentarReviewDetail.setText(review.getKomentar());
        holder.textTanggalReviewDetail.setText(review.getTanggal());
    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

    public class ReviewDetailViewHolder extends RecyclerView.ViewHolder {
        TextView textNilaiReviewDetail, textKomentarReviewDetail, textNamaUserReviewDetail, textTanggalReviewDetail;
        ShapeableImageView imgUserReviewDetail;
        ImageView imgStarReviewDetail, aksenKoma;

        public ReviewDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            textNamaUserReviewDetail = itemView.findViewById(R.id.textNamaUserReviewDetail);
            textKomentarReviewDetail = itemView.findViewById(R.id.textKomentarReviewDetail);
            textNilaiReviewDetail = itemView.findViewById(R.id.textNilaiReviewDetail);
            textTanggalReviewDetail = itemView.findViewById(R.id.textTanggalReviewDetail);
            imgUserReviewDetail = itemView.findViewById(R.id.imgUserReviewDetail);
            imgStarReviewDetail = itemView.findViewById(R.id.imgStarReviewDetail);
            aksenKoma = itemView.findViewById(R.id.aksenKoma);

        }
    }
}
