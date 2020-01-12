package com.example.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LocalPref {
    private static final String SETTINGS_NAME = "id.example.weatherapp.pref";

    private static LocalPref sSharedPrefs;
    protected SharedPreferences mPref;
    protected SharedPreferences.Editor mEditor;
    protected boolean mBulkUpdate = false;

    public enum Key {
        USERNAME
    }

    protected LocalPref(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static LocalPref getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new LocalPref(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public void put(Key key, String val) {
        Log.d("ini put ","ini put: "+key+" value: "+val);
        doEdit();
        mEditor.putString(key.name(), val);
        doCommit();
    }

    public String getString(Key key, String defaultValue) {
        return mPref.getString(key.name(), defaultValue);
    }

    protected void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    protected void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
