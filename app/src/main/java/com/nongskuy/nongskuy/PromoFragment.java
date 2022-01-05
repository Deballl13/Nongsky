package com.nongskuy.nongskuy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
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

public class PromoFragment extends Fragment implements PromoAdapter.OnPromoViewHolderClick {

    private CarouselView carouselView;
    private Integer[] sampleImages = {R.drawable.gado, R.drawable.nuget, R.drawable.pempek, R.drawable.rempah, R.drawable.sushi};
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
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
        carouselView = view.findViewById(R.id.carouselViewPromo);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        recyclerView = view.findViewById(R.id.rvPromo);

        // cek login atau tidak
        if(token != null){
            // menampilkan tampilan halaman untuk user
            view.findViewById(R.id.toolbarPromo).setVisibility(View.VISIBLE);
            view.findViewById(R.id.textPromo).setVisibility(View.VISIBLE);
            view.findViewById(R.id.cardViewCarouselPromo).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            // menghilangkan tampilan halaman untuk guest
            view.findViewById(R.id.promoGuest).setVisibility(View.GONE);
            view.findViewById(R.id.textUtamaGuestPromo).setVisibility(View.GONE);
            view.findViewById(R.id.textPelengkapGuestPromo).setVisibility(View.GONE);
        }

        // recyclerview promo
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
                        PromoAdapter recyclerViewPromoAdaper = new PromoAdapter(arrayListPromo);

                        for (PromoData promoData: listPromo) {
                            Promo promo = new Promo(
                                    promoData.getIdToko(),
                                    promoData.getNamaToko(),
                                    promoData.getNamaMenu(),
                                    promoData.getHarga(),
                                    promoData.getGambar(),
                                    promoData.getPersentase(),
                                    promoData.getJenisPromo()
                            );

                            arrayListPromo.add(promo);
                            recyclerViewPromoAdaper.setShimmer(false);
                            recyclerView.setAdapter(recyclerViewPromoAdaper);
                            recyclerViewPromoAdaper.notifyDataSetChanged();
                        }
                        recyclerViewPromoAdaper.setPromoClickObject(PromoFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<PromoClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPromoClick(Promo promo) {
        Intent intent = new Intent(getActivity(), DetailNongskuy.class);
//        intent.putExtra("IdToko", promo.getIdToko());
//        intent.putExtra("NamaToko", promo.getNamaToko());
//        intent.putExtra("GambarToko", nongskuy.getGambarToko());
//        intent.putExtra("AlamatToko", nongskuy.getAlamatToko());
//        intent.putExtra("LatToko", nongskuy.getLatToko());
//        intent.putExtra("LongToko", nongskuy.getLongToko());
        startActivity(intent);
    }
}