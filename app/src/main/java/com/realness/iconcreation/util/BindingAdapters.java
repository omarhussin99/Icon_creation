package com.realness.iconcreation.util;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.realness.iconcreation.R;

public class BindingAdapters {

    @BindingAdapter("android:imageUrl")
    public static void setImageUrl(ImageView imageView, String Url) {
        Glide.with(imageView.getContext()).load(Url)
                .placeholder(ContextCompat.getDrawable(imageView.getContext(),R.drawable.colored_logo))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

}
