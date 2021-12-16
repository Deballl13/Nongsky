package com.nongskuy.nongskuy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class PromoFragment extends Fragment {

    private CarouselView carouselView;
    private Integer[] sampleImages = {R.drawable.gado, R.drawable.nuget, R.drawable.pempek, R.drawable.rempah, R.drawable.sushi};
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    public PromoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_promo, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", null);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        recyclerView = view.findViewById(R.id.rvPromo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new PromoAdapter(getData()));

        if(token != null){
            view.findViewById(R.id.layoutPromoUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutPromoGuest).setVisibility(view.GONE);
        }

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

//    public ArrayList<Promo> getData(){
//        ArrayList<Promo> listPromo = new ArrayList<>();
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//        listPromo.add(new Promo(
//                "Sate Taichan",
//                "Taichan Mimi Peri",
//                25000,
//                30000,
//                "Diskon 5%"
//        ));
//
//        return listPromo;
//    }
}