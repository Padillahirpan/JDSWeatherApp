package com.example.weatherapp.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static String convertTimestampToDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis * 1000);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        simpleDateFormat.setTimeZone(calendar.getTimeZone());

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String convertTimestampToDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis * 1000);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        simpleDateFormat.setTimeZone(calendar.getTimeZone());

        return simpleDateFormat.format(calendar.getTime());
    }

    @SuppressLint("DefaultLocale")
    public static String convertKelvinToCelcius(Double kelvinTemp) {

        Double celcius = kelvinTemp - 273.15;
        Log.d("ini dia","kelvin celcius: "+celcius);
        return String.format("%.1f", celcius);
    }

    public static String getCurrentTime() {

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String result = "";

        if(timeOfDay >= 0 && timeOfDay < 10){
            result = "Pagi";
        }else if(timeOfDay >= 10 && timeOfDay < 14){
            result = "Siang";
        }else if(timeOfDay >= 14 && timeOfDay < 18){
            result = "Sore";
        }else if(timeOfDay >=18 && timeOfDay < 24){
            result = "Malam";
        }

        return result;
    }

    public static int getCurrentTimeInt() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 10){
            return 1;
        }else if(timeOfDay >= 10 && timeOfDay < 14){
            return 2;
        }else if(timeOfDay >= 14 && timeOfDay < 18){
            return 3;
        }else if(timeOfDay >=18 && timeOfDay < 24){
            return 4;
        } else {
            return 1;
        }
    }
}
