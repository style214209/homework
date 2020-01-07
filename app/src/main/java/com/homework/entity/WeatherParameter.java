package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherParameter implements Serializable {
    @SerializedName("parameterName")
    String parameterName;

    @SerializedName("parameterValue")
    String parameterValue;

    @SerializedName("parameterUnit")
    String parameterUnit;

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
