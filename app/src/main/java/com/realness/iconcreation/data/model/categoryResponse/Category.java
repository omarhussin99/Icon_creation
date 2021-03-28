package com.realness.iconcreation.data.model.categoryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @Expose
    @SerializedName("hasProduct")
    private int hasProduct;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private String id;

    public int getHasProduct() {
        return hasProduct;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
