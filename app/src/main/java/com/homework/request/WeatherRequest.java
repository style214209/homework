package com.homework.request;

import com.google.gson.Gson;
import com.homework.entity.WeatherEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WeatherRequest implements Request {

    private RequestListener<WeatherEntity> listener;

    public WeatherRequest(RequestListener<WeatherEntity> listener) {
        this.listener = listener;
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public int getTimeoutMs() {
        return 10 * 1000;
    }

    @Override
    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder(ServicePaths.WEATHER);

        try {
            String locationName = URLEncoder.encode("臺北市", "UTF-8");
            stringBuilder.append("locationName=").append(locationName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "CWB-A3B99CCF-B140-4383-BA90-18140C4578E3");
        return header;
    }

    @Override
    public String getBody() {
        return "";
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public void onRequestSuccess(String response) {
        Gson gson = new Gson();
        WeatherEntity weatherEntity = gson.fromJson(response, WeatherEntity.class);
        listener.onRequestSuccess(weatherEntity);
    }

    @Override
    public void onRequestFail(RequestException exception) {
        exception.printStackTrace();
        listener.onRequestFail(exception);
    }

    public void request() {
        RequestEngine.request(this);
    }
}
