package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.model.meals.Category;
import com.example.dishdash.data.datasources.meals.remote_data_source.category.CategoryNetworkResponse;
import com.example.dishdash.data.datasources.meals.remote_data_source.category.CategoryRemoteDataSource;

import java.util.List;

public class CategoryRepoImpl {

    CategoryRemoteDataSource categoryRemoteDataSource;


    public CategoryRepoImpl() {
        categoryRemoteDataSource = new CategoryRemoteDataSource();
    }

    public void getCategories(CategoryRepo callback) {
        categoryRemoteDataSource.getCategories(new CategoryNetworkResponse() {
            @Override
            public void onSuccess(List<Category> productList) {
                callback.onSuccess(productList);
            }

            @Override
            public void onFailure(String message) {
                callback.onFailure(message);
            }
        });
    }
}
