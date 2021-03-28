package com.realness.iconcreation.data.remote;

import com.realness.iconcreation.data.model.LoginRequest;
import com.realness.iconcreation.data.model.homeResponse.HomeResponse;
import com.realness.iconcreation.data.model.LoginResponse;
import com.realness.iconcreation.data.model.categoryResponse.CategoriesResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("getHome")
    Observable<HomeResponse> getHomeResponse();

    @POST("getCategory")
    Single<CategoriesResponse> getCategories();

    @POST("login/")
    Single<LoginResponse> login(@Body LoginRequest loginRequest);
}
