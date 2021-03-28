package com.realness.iconcreation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.realness.iconcreation.R;
import com.realness.iconcreation.databinding.NewProductItemBinding;
import com.realness.iconcreation.data.model.homeResponse.NewProducts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {

    List<NewProducts> newProductsList = new ArrayList<>();
    NewProductItemBinding binding;
    public boolean validUser = true;

    @Inject
    public NewProductAdapter() {}

    @NonNull
    @Override
    public NewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.new_product_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.ViewHolder holder, int position) {
        if (!validUser){
            holder.binding.wishListImage.setVisibility(View.INVISIBLE);
        }
        NewProducts newProducts = newProductsList.get(position);
        holder.bindTrending(newProducts);
    }

    @Override
    public int getItemCount() {
        return newProductsList.size();
    }

    public void setNewProductsList(List<NewProducts> newProductsList){
        this.newProductsList = newProductsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NewProductItemBinding binding;
        public ViewHolder(@NonNull NewProductItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bindTrending(NewProducts newProducts){
            binding.setNewProducts(newProducts);
            binding.executePendingBindings();
        }
    }
}
