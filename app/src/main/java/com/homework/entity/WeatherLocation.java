package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherLocation implements Serializable {

    @SerializedName("locationName")
    private String locationName;

    @SerializedName("weatherElement")
    private List<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }
}
