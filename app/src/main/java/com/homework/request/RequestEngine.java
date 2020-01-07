package com.homework.request;

import android.content.Context;

public class RequestEngine {
    private static RequestRunner mServiceRunner;
    private static Context mAppContext;

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
}
