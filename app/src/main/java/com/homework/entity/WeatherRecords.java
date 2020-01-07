package com.homework.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherRecords implements Serializable {
    @SerializedName("datasetDescription")
    String datasetDescription;

    @SerializedName("location")
    List<WeatherLocation> location;

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public List<WeatherLocation> getLocation() {
        return location;
    }
}
