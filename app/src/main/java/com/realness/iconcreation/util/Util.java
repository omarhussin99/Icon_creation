package com.realness.iconcreation.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.gson.Gson;

public class Util {
    public static Handler makeDelay(){
        return new Handler(Looper.myLooper());
    }
    public static void disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    public static void changeStatusBarColor(Activity activity, String color){
        Window w = activity.getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.setStatusBarColor(Color.parseColor(color));
    }
    public static String FromObjectToJsonString(Object user){
        Gson gson = new Gson();
        return gson.toJson(user);
    }
}
