package com.homework;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class BaseSharedPreference {

    private static final String IS_FIRST_TIME = "isFirstTime";
    private static SharedPreferences prefs = null;
    public static void init(Context context) {
        prefs = context.getSharedPreferences("global", Context.MODE_PRIVATE);
    }

    private static SharedPreferences getPrefs(Context context) {
        if ( prefs == null ) {
            init(context.getApplicationContext());
        }

        return prefs;
    }

    public static void put(Context context, String name, Object value){

        SharedPreferences.Editor editor = getPrefs(context).edit();

        if(value.getClass() == Boolean.class){
            editor.putBoolean(name, (Boolean)value);
        }
        if(value.getClass() == String.class){
            editor.putString(name, (String)value);
        }
        if(value.getClass() == Integer.class){
            editor.putInt(name, ((Integer)value).intValue());
        }
        editor.commit();
    }

    public static Object get(String name){

        Map<String, ?> table = prefs.getAll();
        return (table.get(name));
    }

    public static void setIsFirstTime(Context context, boolean state) {
        put(context, BaseSharedPreference.IS_FIRST_TIME, state);
    }

    public static boolean getIsFirstTime(Context context) {
        return getPrefs(context).getBoolean(BaseSharedPreference.IS_FIRST_TIME, false);
    }
}

