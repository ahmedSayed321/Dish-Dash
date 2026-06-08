package com.example.dishdash.data.datasources.meals.remote_data_source.category;

import com.example.dishdash.data.model.meals.CategoriesResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;

import io.reactivex.rxjava3.core.Single;

public class CategoryRemoteDataSource {

    public Service service;

    public CategoryRemoteDataSource() {
        service = Network.getInstance().service;
    }

    public Single<CategoriesResponse> getCategories() {

        return service.getCategories();
    }

//    public void getCategories(CategoryNetworkResponse categoryNetworkResponse) {
//
//        service.getCategories().enqueue(new Callback<CategoriesResponse>() {
//            @Override
//            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Category> categoryList = response.body().categories;
//                    categoryNetworkResponse.onSuccess(categoryList);
//                } else {
//                    categoryNetworkResponse.onFailure("Failured to fetch categories");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
//                categoryNetworkResponse.onFailure(t.getMessage());
//            }
//        });
//
//
//    }

}
