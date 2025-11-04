package com.example.notesstorageapp;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManager {

    private static final String STORAGE_PREF = "storage_pref";
    private static final String KEY_TYPE = "storage_type";
    private static final String TYPE_SHARED = "shared";
    private static final String TYPE_FILE = "file";

    public static void setStorageType(Context context, String type) {
        SharedPreferences preferences = context.getSharedPreferences(STORAGE_PREF, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_TYPE, type).apply();
    }

    public static String getStorageType(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(STORAGE_PREF, Context.MODE_PRIVATE);
        return preferences.getString(KEY_TYPE, TYPE_SHARED); // default is SharedPreferences
    }

    public static IStorage getStorage(Context context) {
        String type = getStorageType(context);
        if (TYPE_FILE.equals(type)) {
            return new FileStorage(context);
        } else {
            return new SharedPreferencesStorage(context);
        }
    }
}
