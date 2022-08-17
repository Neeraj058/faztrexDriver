package com.courier365cloud.faztrex.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static com.courier365cloud.faztrex.utils.AppConstant.PREFERENCE_FILE_NAME;

public class AppPreference {

    private static AppPreference appPreference;

    private SharedPreferences mSharedPreferences;

    public static AppPreference getInstance() {

        appPreference = new AppPreference();
        return appPreference;
    }

    public void clearPreferences(Activity activity) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setStringPreference(Activity activity, String key, String value) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(Activity activity, String key) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    public void setBooleanPreference(Activity activity, String key, boolean value) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.apply();
    }

    public boolean getBooleanPreference(Activity activity, String key) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(String.valueOf(key), false);
    }

    public void setIntegerPreference(Activity activity, String key, int value) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntegerPreference(Activity activity, String key) {

        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }

    public void setStringPreference(Context mContext, String key, String value) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(Context mContext, String key) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    public void setBooleanPreference(Context mContext, String key, boolean value) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.apply();
    }

    public boolean getBooleanPreference(Context mContext, String key) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(String.valueOf(key), false);
    }

    public void setIntegerPreference(Context mContext, String key, int value) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntegerPreference(Context mContext, String key) {

        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }
}
