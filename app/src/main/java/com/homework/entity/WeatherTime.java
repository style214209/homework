package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherTime implements Serializable {
    @SerializedName("startTime")
    String startTime;

    @SerializedName("endTime")
    String endTime;

    @SerializedName("parameter")
    WeatherParameter parameter;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public WeatherParameter getParameter() {
        return parameter;
    }
}
