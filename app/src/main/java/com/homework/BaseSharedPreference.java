package com.homework;

import android.content.Context;
import android.content.SharedPreferences;

public class BaseSharedPreference {

    private static final String IS_FIRST_TIME = "isFirstTime";
    private static SharedPreferences prefs = null;

    private static void init(Context context) {
        prefs = context.getSharedPreferences("global", Context.MODE_PRIVATE);
    }

    private static SharedPreferences getPrefs(Context context) {
        if ( prefs == null ) {
            init(context.getApplicationContext());
        }

        return prefs;
    }

    public static void setIsFirstTime(Context context, boolean state) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(BaseSharedPreference.IS_FIRST_TIME, state);
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context) {
        return getPrefs(context).getBoolean(BaseSharedPreference.IS_FIRST_TIME, true);
    }
}

