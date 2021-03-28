package com.realness.iconcreation.ui.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.realness.iconcreation.R;
import com.realness.iconcreation.data.local.UserSharedPref;
import com.realness.iconcreation.data.model.homeResponse.NewProducts;
import com.realness.iconcreation.databinding.NewProductItemBinding;
import com.realness.iconcreation.databinding.TrendingProductItemBinding;
import com.realness.iconcreation.ui.adapters.CategoryAdapter;
import com.realness.iconcreation.ui.adapters.NewProductAdapter;
import com.realness.iconcreation.ui.adapters.TrendingProductAdapter;
import com.realness.iconcreation.databinding.ActivityHomeBinding;
import com.realness.iconcreation.ui.viewModel.HomeViewModel;
import com.realness.iconcreation.util.Util;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.realness.iconcreation.util.Animation.*;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel homeViewModel;
    @Inject
    CategoryAdapter categoryAdapter;
    @Inject
    TrendingProductAdapter trendingProductAdapter;
    @Inject
    NewProductAdapter newProductAdapter;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        init();
    }

    private void init() {
        homeViewModel.fetchDataFromServer();
        setupCategoryAdapter();
        setupNewProductsAdapter();
        setupTrendingProductsAdapter();
    }

    private void setupCategoryAdapter() {
        binding.categoriesList.setHasFixedSize(true);
        binding.categoriesList.setAdapter(categoryAdapter);
        binding.categoriesList.setItemTransitionTimeMillis(240);
        binding.categoriesList.setOverScrollEnabled(true);
        binding.categoriesList.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(.7f)
                .build());
        homeViewModel.getCategoryItems().observe(this, categories -> {
                    categoryAdapter.setCategories(categories);
                    startUi();
                }
        );
    }

    private void setupNewProductsAdapter() {
        binding.newProductsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.newProductsList.setHasFixedSize(true);
        binding.newProductsList.setAdapter(newProductAdapter);
        homeViewModel.getNewProducts().observe(this, newProducts -> newProductAdapter.setNewProductsList(newProducts));
    }

    private void setupTrendingProductsAdapter() {
        binding.trendingProductsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.trendingProductsList.setHasFixedSize(true);
        binding.trendingProductsList.setAdapter(trendingProductAdapter);
        homeViewModel.getTrendingItems().observe(this, trendingList -> trendingProductAdapter.setTrendingProductsList(trendingList));
    }

    private void startUi(){
        checkUser();
        binding.loading.setVisibility(View.GONE);
        showWithFadeAnimation(binding.discoverText);
        showWithFadeAnimation(binding.categoriesList);
        showWithFadeAnimation(binding.whatsNewText);
        showWithFadeAnimation(binding.newProductsList);
        showWithFadeAnimation(binding.trendingText);
        showWithFadeAnimation(binding.trendingProductsList);
        showWithSlideUpAnimation(binding.chipNavigationBar);
        Util.makeDelay().postDelayed(()->binding.chipNavigationBar.setItemSelected(R.id.homeNav,true),1300);
    }

    private void checkUser(){
        if (!UserSharedPref.isExist(sharedPreferences)){
            binding.wishListImage.setVisibility(View.INVISIBLE);
            newProductAdapter.validUser = false;
            trendingProductAdapter.validUser = false;

        }
    }

    @Override
    public void onBackPressed() {
        if (UserSharedPref.isExist(sharedPreferences)){
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.colored_logo)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout !")
                    .setPositiveButton("Yes", (dialog, which) -> logout())
                    .setNegativeButton("No", null)
                    .show();
        }
        else {
            super.onBackPressed();
        }
    }

    public void logout(){
        sharedPreferences.edit().clear().apply();
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}