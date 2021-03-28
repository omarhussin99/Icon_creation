package com.realness.iconcreation.data.local;

import android.content.SharedPreferences;

import com.realness.iconcreation.data.model.LoginResponse;
import com.realness.iconcreation.util.Util;

public class UserSharedPref {

    public static void setupUserPref(LoginResponse userResponse,SharedPreferences.Editor sharedPreferencesEditor){
        sharedPreferencesEditor.putString("user", Util.FromObjectToJsonString(userResponse));
        sharedPreferencesEditor.putString("userMsg",userResponse.getMsg());
        sharedPreferencesEditor.putInt("userCode",userResponse.getCode());
        sharedPreferencesEditor.apply();
        sharedPreferencesEditor.commit();
    }

    public static String  getUser(SharedPreferences sharedPreferences){
       return sharedPreferences.getString("user","");
    }

    public static boolean isExist(SharedPreferences sharedPreferences){
        return getUser(sharedPreferences).length() >= 1;
    }
}
