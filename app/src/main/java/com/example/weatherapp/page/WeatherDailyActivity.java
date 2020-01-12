package com.example.weatherapp.page;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.base.BaseActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.model.City;
import com.example.weatherapp.model.ForecastDailyWrapper;
import com.example.weatherapp.model.Lists;
import com.example.weatherapp.model.Temp;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.utils.LocalPref;
import com.example.weatherapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDailyActivity extends BaseActivity implements WeatherDailyView{

    @BindView(R.id.title_id)
    TextView tvTitle;
    @BindView(R.id.subtitle_id)
    TextView tvSubTitle;
    @BindView(R.id.subtitle_weather)
    TextView tvSubTitleWeather;
    @BindView(R.id.temp_id)
    TextView tvTemperature;
    @BindView(R.id.welcome_text_id)
    TextView tvWelcomeText;
    @BindView(R.id.wind_id)
    TextView tvWind;
    @BindView(R.id.humidity_id)
    TextView tvHumidity;
    @BindView(R.id.pressure_id)
    TextView tvPressure;
    @BindView(R.id.icon_image)
    ImageView imageViewIcon;
    @BindView(R.id.linearError)
    LinearLayout linearError;
    @BindView(R.id.linear_daily)
    CardView linearDaily;
    @BindView(R.id.linear_information_weather)
    CardView linearInformationWeather;




    @BindView(R.id.day_id_1)
    TextView tvDay1;
    @BindView(R.id.day_id_2)
    TextView tvDay2;
    @BindView(R.id.day_id_3)
    TextView tvDay3;
    @BindView(R.id.day_id_4)
    TextView tvDay4;
    @BindView(R.id.day_id_5)
    TextView tvDay5;

    @BindView(R.id.temp_day_1)
    TextView tvTemp1;
    @BindView(R.id.temp_day_2)
    TextView tvTemp2;
    @BindView(R.id.temp_day_3)
    TextView tvTemp3;
    @BindView(R.id.temp_day_4)
    TextView tvTemp4;
    @BindView(R.id.temp_day_5)
    TextView tvTemp5;

    @BindView(R.id.image_icon_1)
    ImageView imageViewIcon1;
    @BindView(R.id.image_icon_2)
    ImageView imageViewIcon2;
    @BindView(R.id.image_icon_3)
    ImageView imageViewIcon3;
    @BindView(R.id.image_icon_4)
    ImageView imageViewIcon4;
    @BindView(R.id.image_icon_5)
    ImageView imageViewIcon5;


    WeatherDailyPresenter presenter;
    String username, postalcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_daily);
        ButterKnife.bind(this);

        presenter = new WeatherDailyPresenter(this, getNetworkService());
        presenter.onAttachPresenter(this);

        postalcode = getIntent().getExtras().getString("postcode");
        username = localPref.getString(LocalPref.Key.USERNAME, "");
//        username = getIntent().getExtras().getString("username");

        getWeatherDaily();
    }

    public void getWeatherDaily() {
        Log.d("ini dia","ini getWeatherDaily");
        presenter.getDataWeather(postalcode);
    }

    @Override
    public void showLoading() {
        showProgressDialog("loading...");
        Log.d("ini dia","ini showLoading");
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
        Log.d("ini dia","ini hideLoading");
    }

    @Override
    public void loadData(ForecastDailyWrapper forecastDailyWrapper) {
        City city = forecastDailyWrapper.getCity();
        List<Lists> lists = forecastDailyWrapper.getLists();
        Weather weather = forecastDailyWrapper.getLists().get(0).getWeather().get(0);

        linearDaily.setVisibility(View.VISIBLE);
        linearInformationWeather.setVisibility(View.VISIBLE);

        tvTitle.setText(city.getName());
        tvSubTitle.setText(Utils.convertTimestampToDate(lists.get(0).getDt()));
        tvSubTitleWeather.setText(getString(R.string.text_weather_description, weather.getMain(), weather.getDescription()));
        tvWelcomeText.setText(getString(R.string.text_welcome, Utils.getCurrentTime(), username.equals("") ? "User" : username));

        tvTemperature.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(0).getTemp()))));
        tvWind.setText(getString(R.string.text_wind, String.valueOf(lists.get(0).getSpeed())));
        tvHumidity.setText(getString(R.string.text_humidity, String.valueOf(lists.get(0).getHumidity())));
        tvPressure.setText(getString(R.string.text_pressure, String.valueOf(lists.get(0).getPressure())));

        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+weather.getIcon()+"@2x.png")
                .into(imageViewIcon);

        setDailyWeather(lists);
    }

    public Double setTemperature(Temp temperature) {
        Double result = temperature.getDay();

        switch (Utils.getCurrentTimeInt()) {
            case 1: result = temperature.getMorn();
                break;
            case 2: result = temperature.getDay();
                break;
            case 3: result = temperature.getEve();
                break;
            case 4: result = temperature.getNight();
                break;
        }

        return result;
    }

    public void setDailyWeather(List<Lists> lists) {

//        for (int i= 1;i <= 5; i++) {
//            Log.d("ini dia","ini temperatur: "+Utils.convertKelvinToCelcius(lists.get(i).getTemp().getDay()));
//        }

        tvDay1.setText(Utils.convertTimestampToDay(lists.get(1).getDt()));
        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+lists.get(1).getWeather().get(0).getIcon()+"@2x.png")
                .into(imageViewIcon1);
        tvTemp1.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(1).getTemp()))));

        tvDay2.setText(Utils.convertTimestampToDay(lists.get(2).getDt()));
        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+lists.get(2).getWeather().get(0).getIcon()+"@2x.png")
                .into(imageViewIcon2);
        tvTemp2.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(2).getTemp()))));

        tvDay3.setText(Utils.convertTimestampToDay(lists.get(3).getDt()));
        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+lists.get(3).getWeather().get(0).getIcon()+"@2x.png")
                .into(imageViewIcon3);
        tvTemp3.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(3).getTemp()))));

        tvDay4.setText(Utils.convertTimestampToDay(lists.get(4).getDt()));
        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+lists.get(4).getWeather().get(0).getIcon()+"@2x.png")
                .into(imageViewIcon4);
        tvTemp4.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(4).getTemp()))));

        tvDay5.setText(Utils.convertTimestampToDay(lists.get(5).getDt()));
        Glide.with(this)
                .asBitmap()
                .load("http://openweathermap.org/img/wn/"+lists.get(5).getWeather().get(0).getIcon()+"@2x.png")
                .into(imageViewIcon5);
        tvTemp5.setText(getString(R.string.text_temperature, Utils.convertKelvinToCelcius(setTemperature(lists.get(5).getTemp()))));
    }

    @Override
    public void showError(String message) {
        Log.d("ini dia","ini error: "+message);
        linearError.setVisibility(View.VISIBLE);
    }
}
