package com.homework;

import android.app.Application;

import com.homework.request.RequestEngine;

public class HomeworkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RequestEngine.init(getApplicationContext());
    }
}
