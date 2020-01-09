package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherField implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
