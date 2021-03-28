package com.realness.iconcreation.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.realness.iconcreation.R;
import com.realness.iconcreation.data.local.UserSharedPref;
import com.realness.iconcreation.databinding.ActivitySplashScreenBinding;
import com.realness.iconcreation.util.Util;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    @Inject
    SharedPreferences userSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        Util.changeStatusBarColor(this, "#000000");
        Util.disableDarkMode();
        init();
    }

    private void init() {
        YoYo.with(Techniques.FadeIn).duration(2000).playOn(binding.splashScreenLogo);
        binding.splashScreenLogo.setVisibility(View.VISIBLE);
        Util.makeDelay().postDelayed(this::startLoginScreen, 2000);
    }

    // Start Login Activity with element transition @binding.splashScreenLogo
    private void startLoginScreen() {
        if (UserSharedPref.isExist(userSharedPreferences)) {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            Pair<View, String> pairs = new Pair<>(binding.splashScreenLogo, "logo_transition");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pairs);
            startActivity(intent, options.toBundle());
            Util.makeDelay().postDelayed(this::finish, 1000);
        }
    }

    @Override
    public void onBackPressed() {
    }
}