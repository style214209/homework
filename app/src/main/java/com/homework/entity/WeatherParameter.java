package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherParameter implements Serializable {
    @SerializedName("parameterName")
    private String parameterName;

    @SerializedName("parameterValue")
    private String parameterValue;

    @SerializedName("parameterUnit")
    private String parameterUnit;

    public String getParameterName() {
        return parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }
}
