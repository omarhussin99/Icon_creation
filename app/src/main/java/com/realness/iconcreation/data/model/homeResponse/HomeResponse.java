package com.realness.iconcreation.data.model.homeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeResponse {

    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("item")
    private Item item;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public Item getItem() {
        return item;
    }
}
