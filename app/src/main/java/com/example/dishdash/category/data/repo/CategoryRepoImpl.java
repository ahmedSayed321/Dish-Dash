package com.example.dishdash.category.data.repo;

import com.example.dishdash.category.data.model.Category;
import com.example.dishdash.category.data.data_source.category_remote_data_source.CategoryNetworkResponse;
import com.example.dishdash.category.data.data_source.category_remote_data_source.CategoryRemoteDataSource;

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
