package com.example.dishdash.data.datasources.meals.remote_data_source.category;

import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;

import io.reactivex.rxjava3.core.Single;

public class MealCategoryRemoteDataSource {

    public Service service;

    public MealCategoryRemoteDataSource() {
        service = Network.getInstance().service;
    }


    public Single<MealCategoryResponse> getMealCategories(String categoryName) {
        return service.getAllMealsInCategory(categoryName);
    }
//    public void getMealCategories(RandomMealRemoteDataSource.RandomMealNetworkResponse randomMealNetworkResponse, String categoryName) {
//        service.getAllMealsInCategory(categoryName).enqueue(new Callback<MealCategoryResponse>() {
//            @Override
//            public void onResponse(Call<MealCategoryResponse> call, Response<MealCategoryResponse> response) {
//                List<MealCategory> meal = response.body().getMeals();
//                randomMealNetworkResponse.onSuccess(meal);
//            }
//
//            @Override
//            public void onFailure(Call<MealCategoryResponse> call, Throwable t) {
//                randomMealNetworkResponse.onFailure(t.getMessage());
//            }
//        });
//    }
}
