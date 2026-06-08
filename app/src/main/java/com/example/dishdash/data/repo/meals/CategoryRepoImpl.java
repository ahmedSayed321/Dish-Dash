package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.category.CategoryRemoteDataSource;
import com.example.dishdash.data.model.meals.CategoriesResponse;

import io.reactivex.rxjava3.core.Single;

public class CategoryRepoImpl {

    CategoryRemoteDataSource categoryRemoteDataSource;


    public CategoryRepoImpl() {
        categoryRemoteDataSource = new CategoryRemoteDataSource();
    }


    public Single<CategoriesResponse> getCategories() {
        return categoryRemoteDataSource.getCategories();
    }
//    public void getCategories(CategoryRepo callback) {
//        categoryRemoteDataSource.getCategories(new CategoryNetworkResponse() {
//            @Override
//            public void onSuccess(List<Category> productList) {
//                callback.onSuccess(productList);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                callback.onFailure(message);
//            }
//        });
//    }
}
