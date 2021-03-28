package com.realness.iconcreation.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.realness.iconcreation.R;
import com.realness.iconcreation.data.local.UserSharedPref;
import com.realness.iconcreation.databinding.ActivityLoginBinding;
import com.realness.iconcreation.data.model.LoginResponse;
import com.realness.iconcreation.ui.viewModel.LoginViewModel;
import com.realness.iconcreation.util.Util;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.realness.iconcreation.util.Animation.showWithFadeAnimation;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    LoginActivityClickHandler clickHandler;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    SharedPreferences.Editor userPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        Util.changeStatusBarColor(this, "#000000");
        init();
    }

    private void init() {
        Util.makeDelay().postDelayed(this::startUi, 800);
        clickHandler = new LoginActivityClickHandler();
        binding.setClickHandler(clickHandler);
    }

    private void startUi() {
        showWithFadeAnimation(binding.numberInputField);
        showWithFadeAnimation(binding.numberInputField3);
        showWithFadeAnimation(binding.forgetPasswordText);
        showWithFadeAnimation(binding.signInButton);
        showWithFadeAnimation(binding.registerButton);
        showWithFadeAnimation(binding.skipButton);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class LoginActivityClickHandler {

        public void skipLogin(View view) {
            Intent intent = new Intent(view.getContext(), HomeActivity.class);
            view.getContext().startActivity(intent);
        }

        public void login(View view) {
            String userName = Objects.requireNonNull(binding.userName.getText()).toString();
            String password = Objects.requireNonNull(binding.password.getText()).toString();
            if (userName.length() == 0 || password.length() == 0) {
                Toast.makeText(LoginActivity.this, "Please enter a valid username and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            loginViewModel.login(userName, password);
            loginViewModel.getValidUser().observe(LoginActivity.this, LoginActivity.this::userValidation);
            binding.signInButton.setVisibility(View.GONE);
            binding.loading.setVisibility(View.VISIBLE);
        }
    }

    private void userValidation(LoginResponse userResponse) {
        if (userResponse != null && userResponse.getCode() == 200) {
            UserSharedPref.setupUserPref(userResponse,userPrefEditor);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, userResponse != null ? userResponse.getMsg() : getString(R.string.connection),
                    Toast.LENGTH_SHORT).show();
            binding.loading.setVisibility(View.GONE);
            binding.signInButton.setVisibility(View.VISIBLE);
        }
    }

}