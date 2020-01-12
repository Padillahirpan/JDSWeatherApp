package com.example.weatherapp.page;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.base.BasePresenter;
import com.example.weatherapp.model.ForecastDailyWrapper;
import com.example.weatherapp.network.NetworkService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherDailyPresenter implements BasePresenter<WeatherDailyView> {

    private NetworkService networkService;
    private Context context;
    private WeatherDailyView weatherDailyViews;
    private ForecastDailyWrapper response;

    public WeatherDailyPresenter(Context context, NetworkService networkService) {
        this.context = context;
        this.networkService = networkService;
    }

    @Override
    public void onAttachPresenter(WeatherDailyView view) {
        this.weatherDailyViews = view;
    }

    public void getDataWeather(String query){
        weatherDailyViews.showLoading();

        networkService.getDaily(query+", ID", BuildConfig.APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForecastDailyWrapper>() {
                    @Override
                    public void onCompleted() {
                        weatherDailyViews.loadData(response);
                        Log.d("ini dia","ini oncomplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        weatherDailyViews.hideLoading();
                        weatherDailyViews.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ForecastDailyWrapper forecastDailyWrappers) {
//                        weatherDailyViews.loadData(forecastDailyWrappers);
                        Log.d("ini dia","ini onNext: "+forecastDailyWrappers.getCod());
                        weatherDailyViews.hideLoading();
                        if (forecastDailyWrappers.getCod().equals("200")) {
                            response = forecastDailyWrappers;
                        } else {
                            weatherDailyViews.showError("Terjadi kesalahan, silahkan coba lagi.");
                        }
                    }
                });
    }
}
