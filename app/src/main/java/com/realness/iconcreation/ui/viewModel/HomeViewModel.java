package com.realness.iconcreation.ui.viewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.realness.iconcreation.data.model.categoryResponse.Category;
import com.realness.iconcreation.data.model.homeResponse.NewProducts;
import com.realness.iconcreation.data.model.homeResponse.TrendingProducts;
import com.realness.iconcreation.data.repository.HomeRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";
    HomeRepository homeRepository;
    MutableLiveData<List<TrendingProducts>> trendingItems = new MutableLiveData<>();
    MutableLiveData<List<NewProducts>> newProducts = new MutableLiveData<>();
    MutableLiveData<List<Category>> categoryItems = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();

    @ViewModelInject
    public HomeViewModel(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public void fetchDataFromServer() {
        Disposable subscribeHomeResponse =
                homeRepository.getHomeResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(homeResponse -> {
                                    trendingItems.setValue(homeResponse.getItem().getTrendingProducts());
                                    newProducts.setValue(homeResponse.getItem().getWhats_new());
                                },
                                error -> Log.e(TAG, "Can't get Home Response" + error));

        Disposable subscribeCategoryResponse =
                homeRepository.getCategoryResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(categoriesResponse -> categoryItems.setValue(categoriesResponse.getItem().getList()),
                                error -> Log.e(TAG, "Can't get Categories" + error));

        disposable.addAll(subscribeHomeResponse, subscribeCategoryResponse);
    }

    public MutableLiveData<List<TrendingProducts>> getTrendingItems() {
        return trendingItems;
    }

    public MutableLiveData<List<NewProducts>> getNewProducts() {
        return newProducts;
    }

    public MutableLiveData<List<Category>> getCategoryItems() {
        return categoryItems;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
