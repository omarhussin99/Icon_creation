package com.realness.iconcreation.data.model.homeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryHome {
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private String id;

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
