package com.realness.iconcreation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.realness.iconcreation.R;
import com.realness.iconcreation.databinding.CategotyItemBinding;
import com.realness.iconcreation.data.model.categoryResponse.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categoryList = new ArrayList<>();
    CategotyItemBinding binding;

    @Inject
    public CategoryAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.categoty_item ,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bindCategory(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategories(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CategotyItemBinding binding;
        public ViewHolder(@NonNull CategotyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindCategory (Category category){
            binding.setCategory(category);
            binding.executePendingBindings();
        }
    }
}
