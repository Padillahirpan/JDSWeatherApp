package com.example.weatherapp.page;

import com.example.weatherapp.model.ForecastDailyWrapper;

public interface WeatherDailyView {

    void showLoading();
    void hideLoading();
    void loadData(ForecastDailyWrapper forecastDailyWrapper);
    void showError(String message);
}
