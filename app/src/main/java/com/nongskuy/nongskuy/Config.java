package com.nongskuy.nongskuy;

import com.nongskuy.nongskuy.route.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    private String API_BASE_URL = "http://nongskuy.herokuapp.com/";
    private static final String PACKAGE_NAME = "com.nongskuy.nongskuy.services";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + "LOCATION_DATA_EXTRA";
    public static final Integer SUCCESS_RESULT = 1;
    public static final Integer FAILURE_RESULT = 0;

    public Route configRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Route route = retrofit.create(Route.class);
        return route;
    }

}
