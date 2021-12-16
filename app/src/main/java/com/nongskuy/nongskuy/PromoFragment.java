package com.nongskuy.nongskuy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nongskuy.nongskuy.adapter.BerandaPromoAdapter;
import com.nongskuy.nongskuy.adapter.PromoAdapter;
import com.nongskuy.nongskuy.data.PromoData;
import com.nongskuy.nongskuy.model.Promo;
import com.nongskuy.nongskuy.model.PromoClass;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PromoFragment extends Fragment {

    private CarouselView carouselView;
    private Integer[] sampleImages = {R.drawable.gado, R.drawable.nuget, R.drawable.pempek, R.drawable.rempah, R.drawable.sushi};
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout refreshLayout;
    private Config config;

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

        config = new Config();
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);



        // cek login atau tidak
        if(token != null){
            view.findViewById(R.id.layoutPromoUser).setVisibility(view.VISIBLE);
            view.findViewById(R.id.layoutPromoGuest).setVisibility(view.GONE);
        }



        // refresh halaman
        refreshLayout = view.findViewById(R.id.refreshPromo);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPage(token);
            }
        });



        // recyclerview promo
        recyclerView = view.findViewById(R.id.rvPromo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadDataPromo(token);

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void refreshPage(String token){
        loadDataPromo(token);
        refreshLayout.setRefreshing(false);
    }

    public void loadDataPromo(String token){
        recyclerView.setAdapter(new PromoAdapter(null));
        Call<PromoClass> call = config.configRetrofit().promo(token);
        call.enqueue(new Callback<PromoClass>() {
            @Override
            public void onResponse(Call<PromoClass> call, Response<PromoClass> response) {
                if(response.code() == 200){
                    if (response.isSuccessful()){
                        PromoClass promoClass = response.body();
                        List<PromoData> listPromo = promoClass.getPromo();
                        ArrayList<Promo> arrayListPromo = new ArrayList<>();

                        for (PromoData promoData: listPromo) {
                            Promo promo = new Promo(
                                    promoData.getNamaToko(),
                                    promoData.getNamaMenu(),
                                    promoData.getHarga(),
                                    promoData.getGambar(),
                                    promoData.getPersentase(),
                                    promoData.getJenisPromo()
                            );

                            PromoAdapter recyclerViewPromoAdaper = new PromoAdapter(arrayListPromo);
                            arrayListPromo.add(promo);
                            recyclerViewPromoAdaper.setShimmer(false);
                            recyclerView.setAdapter(recyclerViewPromoAdaper);
                            recyclerViewPromoAdaper.notifyDataSetChanged();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PromoClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}