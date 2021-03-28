package com.realness.iconcreation.data.repository;

import com.realness.iconcreation.data.model.LoginRequest;

import com.realness.iconcreation.data.model.LoginResponse;
import com.realness.iconcreation.data.remote.ApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class LoginRepository {
    private final ApiService apiService;

    @Inject
    public LoginRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<LoginResponse> login(String email, String password) {
        return apiService.login(new LoginRequest(email, password));
    }

}
