package com.homework.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherTime implements Parcelable {
    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("parameter")
    private WeatherParameter parameter;

    private WeatherTime(Parcel parcel) {
        startTime = parcel.readString();
        endTime = parcel.readString();
        parameter = (WeatherParameter) parcel.readSerializable();
    }

    public static final Parcelable.Creator<WeatherTime> CREATOR = new
            Parcelable.Creator<WeatherTime>() {
                public WeatherTime createFromParcel(Parcel in) {
                    return new WeatherTime(in);
                }

                public WeatherTime[] newArray(int size) {
                    return new WeatherTime[size];
                }
            };


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public WeatherParameter getParameter() {
        return parameter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeSerializable(parameter);
    }
}
