package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherLocation implements Serializable {

    @SerializedName("locationName")
    String locationName;

    @SerializedName("weatherElement")
    List<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }
}
