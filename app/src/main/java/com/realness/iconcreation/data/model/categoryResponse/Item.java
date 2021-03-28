package com.realness.iconcreation.data.model.categoryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("list")
    private List<Category> list;

    public List<Category> getList() {
        return list;
    }
}
