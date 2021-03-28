package com.realness.iconcreation.data.model.homeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("trending")
    private List<TrendingProducts> trendingProducts;
    @Expose
    @SerializedName("whats_new")
    private List<NewProducts> whats_new;
    @Expose
    @SerializedName("category")
    private List<CategoryHome> categoryHome;

    public List<TrendingProducts> getTrendingProducts() {
        return trendingProducts;
    }

    public List<NewProducts> getWhats_new() {
        return whats_new;
    }

    public List<CategoryHome> getCategoryHome() {
        return categoryHome;
    }
}
