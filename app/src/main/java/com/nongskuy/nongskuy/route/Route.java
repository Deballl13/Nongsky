package com.nongskuy.nongskuy.route;

import com.nongskuy.nongskuy.model.AuthClass;
import com.nongskuy.nongskuy.model.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Route {

    @FormUrlEncoded
    @POST("login")
    Call<AuthClass> login(@Field("email") String email, @Field("password") String password);

    @POST("logout")
    Call<MessageResponse> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("register")
    Call<MessageResponse> register(@Field("email") String email, @Field("nama") String nama,
                                   @Field("no_hp") String no_hp, @Field("password") String password);
}
