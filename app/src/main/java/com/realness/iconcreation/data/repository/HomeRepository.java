package com.realness.iconcreation.data.repository;

import com.realness.iconcreation.data.model.categoryResponse.CategoriesResponse;
import com.realness.iconcreation.data.model.homeResponse.HomeResponse;
import com.realness.iconcreation.data.remote.ApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class HomeRepository {
    private final ApiService apiService;

    @Inject
    public HomeRepository(ApiService apiService){
        this.apiService = apiService;
    }

    public Observable<HomeResponse> getHomeResponse(){
        return apiService.getHomeResponse();
    }

    public Single<CategoriesResponse> getCategoryResponse() {
        return apiService.getCategories();
    }

}
