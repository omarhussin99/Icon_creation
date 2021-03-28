package com.realness.iconcreation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("code")
    private int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
