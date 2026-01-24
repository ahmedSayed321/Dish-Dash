package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.MealCategoryRemoteDataSource;
import com.example.dishdash.data.datasources.meals.remote_data_source.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.MealCategory;

import java.util.List;

public class MealCategoryRepo {

    MealCategoryRemoteDataSource mealCategoryRemoteDataSource;

    public MealCategoryRepo() {
        mealCategoryRemoteDataSource = new MealCategoryRemoteDataSource();
    }

    public void getMealCategories(RandomMealRemoteDataSource.RandomMealNetworkResponse networkResponse, String category) {
        mealCategoryRemoteDataSource.getMealCategories(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<MealCategory>>() {

            @Override
            public void onSuccess(List<MealCategory> meals) {
                networkResponse.onSuccess(meals);

            }

            @Override
            public void onFailure(String message) {
                networkResponse.onFailure(message);
            }
        }, category);
    }
}
