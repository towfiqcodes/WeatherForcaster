package com.example.jmirza.weatherforcaster.web_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getretrofit(){
        return new Retrofit.Builder()
                .baseUrl( "http://api.openweathermap.org/data/2.5/")
                .addConverterFactory( GsonConverterFactory.create())
                .build();
    }
}
