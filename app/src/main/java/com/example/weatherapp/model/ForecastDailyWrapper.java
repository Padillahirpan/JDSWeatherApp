package com.example.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastDailyWrapper {
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<Lists> lists = null;

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public Double getMessage() {
        return message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public List<Lists> getLists() {
        return lists;
    }
}
