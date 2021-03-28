package com.realness.iconcreation.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class SharedPrefsModule {
    private static final String SETTINGS_PREFS = "SETTINGS_PREFS";

    @Provides
    @Singleton
    public static SharedPreferences provideUserPrefs(Application application){
        return application.getApplicationContext().getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public static SharedPreferences.Editor provideUserPrefsEditor(Application application){
        return provideUserPrefs(application).edit();
    }

}
