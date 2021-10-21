package com.nongskuy.nongskuy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class PromoFragment extends Fragment {

    CarouselView carouselView;
    Integer[] sampleImages = {R.drawable.gado, R.drawable.nuget, R.drawable.pempek, R.drawable.rempah, R.drawable.sushi};


    public PromoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promo, container, false);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        if(MainActivity.userEmail != null){
            view.findViewById(R.id.layoutPromoUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutPromoGuest).setVisibility(view.INVISIBLE);
        }

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}