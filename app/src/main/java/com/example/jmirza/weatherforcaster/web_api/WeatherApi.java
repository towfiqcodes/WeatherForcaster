package com.example.jmirza.weatherforcaster.web_api;

import com.example.jmirza.weatherforcaster.models.Forcast;
import com.example.jmirza.weatherforcaster.weather_models.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    // @GET("weather?q=Dhaka,bd&mode=json&appid=cd8e16a66e62e5394e93dd4a40e58e20")

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String location, @Query("appid") String key);
    @GET("forecast")
    Call<Forcast> getForcastWeather(@Query("q") String location, @Query("appid") String key);
}
