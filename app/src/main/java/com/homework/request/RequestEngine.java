package com.homework.request;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.net.SocketFactory;

public class RequestEngine {
    private static RequestRunner mServiceRunner;
    private static Context mAppContext;

    public enum RunnerType {
        Volley,
        JavaSync,
        LocalFile
    }

    public RequestEngine() {
    }

    public static void init(Context context) {
        mAppContext = context;
        initServiceRunner();
    }

    private static void initServiceRunner() {
        mServiceRunner = new VolleyRequestRunner(mAppContext);
    }

    public static RequestRunner getServiceRunner() {
        return mServiceRunner;
    }

    public static void request(Request request) {
        mServiceRunner.run(request);
    }

    public static void request(Request request, Object tag) {
        mServiceRunner.run(request, tag);
    }

    public static void cancelRequest(Object tag) {
        mServiceRunner.cancelRequest(tag);
    }
}
