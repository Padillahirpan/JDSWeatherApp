package com.example.weatherapp.network;

import com.example.weatherapp.model.ForecastDailyWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {

    //GET JSON BEASISWA
    @GET("forecast/daily")
    Observable<ForecastDailyWrapper> getDaily(@Query("q") String query,
                                              @Query("appid") String appId);

}
