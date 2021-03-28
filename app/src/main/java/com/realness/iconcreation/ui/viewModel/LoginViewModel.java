package com.realness.iconcreation.ui.viewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.realness.iconcreation.data.model.LoginResponse;
import com.realness.iconcreation.data.repository.LoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
    LoginRepository loginRepository;
    MutableLiveData<LoginResponse> validUser = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();

    @ViewModelInject
    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void login(String email, String password) {
        Disposable loginResponse = loginRepository.login(email, password).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(responseBody -> validUser.setValue(responseBody),
                        error -> Log.d(TAG, "login: " + error));
        disposable.add(loginResponse);
    }

    public MutableLiveData<LoginResponse> getValidUser() {
        return validUser;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
