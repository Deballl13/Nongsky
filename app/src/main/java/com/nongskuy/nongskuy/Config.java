package com.nongskuy.nongskuy;

import com.nongskuy.nongskuy.route.Route;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    public static final String API_BASE_URL = "http://nongskuy.herokuapp.com/";
//    public static final String API_BASE_URL = "http://6390-114-125-6-152.ngrok.io/";

    public Route configRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Route route = retrofit.create(Route.class);
        return route;
    }

}
