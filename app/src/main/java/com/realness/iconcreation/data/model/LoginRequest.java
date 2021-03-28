package com.realness.iconcreation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @Expose
    @SerializedName("fcmToken")
    private String fcmToken;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("email")
    private String email;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.fcmToken = "123";
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
