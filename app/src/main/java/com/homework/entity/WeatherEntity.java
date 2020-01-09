package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherEntity implements Serializable {
    @SerializedName("success")
    private String success;

    @SerializedName("result")
    private WeatherResult result;

    @SerializedName("records")
    private WeatherRecords records;

    public String getSuccess() {
        return success;
    }

    public WeatherResult getResult() {
        return result;
    }

    public WeatherRecords getRecords() {
        return records;
    }
}
