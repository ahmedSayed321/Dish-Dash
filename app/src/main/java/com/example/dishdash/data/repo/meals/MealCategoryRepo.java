package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.category.MealCategoryRemoteDataSource;
import com.example.dishdash.data.model.meals.MealCategoryResponse;

import io.reactivex.rxjava3.core.Single;

public class MealCategoryRepo {

    MealCategoryRemoteDataSource mealCategoryRemoteDataSource;

    public MealCategoryRepo() {
        mealCategoryRemoteDataSource = new MealCategoryRemoteDataSource();
    }


    public Single<MealCategoryResponse> getMealCategories(String categoryName) {
        return mealCategoryRemoteDataSource.getMealCategories(categoryName);

    }
//    public void getMealCategories(RandomMealRemoteDataSource.RandomMealNetworkResponse networkResponse, String category) {
//        mealCategoryRemoteDataSource.getMealCategories(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<MealCategory>>() {
//
//            @Override
//            public void onSuccess(List<MealCategory> meals) {
//                networkResponse.onSuccess(meals);
//
//            }
//
//            @Override
//            public void onFailure(String message) {
//                networkResponse.onFailure(message);
//            }
//        }, category);
//    }
}
