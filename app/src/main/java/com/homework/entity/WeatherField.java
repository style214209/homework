package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherField implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
