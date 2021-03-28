package com.realness.iconcreation.data.model.homeResponse;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewProducts {
    @Expose
    @SerializedName("isWishList")
    private int isWishList;
    @Expose
    @SerializedName("shop_id")
    private String shop_id;
    @Expose
    @SerializedName("cat_id")
    private String cat_id;
    @Expose
    @SerializedName("rate")
    private String rate;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("shop_name")
    private String shop_name;
    @Expose
    @SerializedName("currency")
    private String currency;
    @Expose
    @SerializedName("price")
    private String price;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private String id;

    public int getIsWishList() {
        return isWishList;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getRate() {
        return rate;
    }

    public String getImage() {
        return image;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
