package com.nongskuy.nongskuy.route;

import com.nongskuy.nongskuy.model.AuthClass;
import com.nongskuy.nongskuy.model.MessageClass;
import com.nongskuy.nongskuy.model.PromoClass;
import com.nongskuy.nongskuy.model.RiwayatNongskuyClass;
import com.nongskuy.nongskuy.model.PencarianClass;
import com.nongskuy.nongskuy.model.NongskuyPopulerClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Route {

    @FormUrlEncoded
    @POST("login")
    Call<AuthClass> login(@Field("email") String email, @Field("password") String password);

    @POST("logout")
    Call<MessageClass> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("register")
    Call<MessageClass> register(@Field("email") String email, @Field("nama") String nama,
                                @Field("no_hp") String no_hp, @Field("password") String password);

    @FormUrlEncoded
    @PUT("ubahpassword")
    Call<MessageClass> ubahPassword(@Header("Authorization") String token, @Field("password") String password);

    @FormUrlEncoded
    @PUT("ubahprofil")
    Call<MessageClass> ubahProfil(@Header("Authorization") String token,
                                  @Field("nama") String nama,
                                  @Field("no_hp") String no_hp);

    @GET("promo")
    Call<PromoClass> promo(@Header("Authorization") String token);

    @GET("pesan/riwayat")
    Call<RiwayatNongskuyClass> riwayat(@Header("Authorization") String token);

    @GET("toko/populer")
    Call<NongskuyPopulerClass> tokoPopuler();

    @FormUrlEncoded
    @POST("toko/search")
    Call<PencarianClass> search(@Field("keyword") String keyword);
}
