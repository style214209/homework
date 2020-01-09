package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherResult implements Serializable {
    @SerializedName("resource_id")
    private String resource_id;

    @SerializedName("fields")
    private List<WeatherField> fields;

    public String getResource_id() {
        return resource_id;
    }

    public List<WeatherField> getFields() {
        return fields;
    }
}
