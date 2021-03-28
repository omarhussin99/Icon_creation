package com.realness.iconcreation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.realness.iconcreation.R;
import com.realness.iconcreation.databinding.TrendingProductItemBinding;
import com.realness.iconcreation.data.model.homeResponse.TrendingProducts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TrendingProductAdapter extends RecyclerView.Adapter<TrendingProductAdapter.ViewHolder> {

    List<TrendingProducts> trendingProductsList = new ArrayList<>();
    TrendingProductItemBinding binding;
    public boolean validUser = true;


    @Inject
    public TrendingProductAdapter() {}

    @NonNull
    @Override
    public TrendingProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.trending_product_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingProductAdapter.ViewHolder holder, int position) {
        if (!validUser){
            holder.binding.wishListImage.setVisibility(View.INVISIBLE);
        }
        TrendingProducts trendingProducts = trendingProductsList.get(position);
        holder.bindTrending(trendingProducts);
    }

    @Override
    public int getItemCount() {
        return trendingProductsList.size();
    }

    public void setTrendingProductsList(List<TrendingProducts> trendingProductsList){
        this.trendingProductsList = trendingProductsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TrendingProductItemBinding binding;
        public ViewHolder(@NonNull TrendingProductItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bindTrending(TrendingProducts trendingProducts){
            binding.setTrendingProducts(trendingProducts);
            binding.executePendingBindings();
        }
    }
}
