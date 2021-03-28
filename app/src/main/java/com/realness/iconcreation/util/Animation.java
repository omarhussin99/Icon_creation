package com.realness.iconcreation.util;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Animation {
    public static void showWithFadeAnimation(View view){
        YoYo.with(Techniques.FadeIn).duration(2000).playOn(view);
        view.setVisibility(View.VISIBLE);
    }

    public static void showWithSlideUpAnimation(View view){
        YoYo.with(Techniques.SlideInUp).duration(1000).playOn(view);
        view.setVisibility(View.VISIBLE);
    }
}
