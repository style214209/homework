package com.homework.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherElement implements Serializable {
    @SerializedName("elementName")
    private String elementName;

    @SerializedName("time")
    private List<WeatherTime> time;

    public String getElementName() {
        return elementName;
    }

    public List<WeatherTime> getTime() {
        return time;
    }
}
