package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.MealDetailsRemoteDataSource;
import com.example.dishdash.data.datasources.meals.remote_data_source.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.Meal;

import java.util.List;

public class MealDetailRepo {

    public MealDetailsRemoteDataSource mealDetailsRemoteDataSource;

    public MealDetailRepo() {
        mealDetailsRemoteDataSource = new MealDetailsRemoteDataSource();
    }

    public void getMealDetailsById(String id, RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>> networkResponse) {

        mealDetailsRemoteDataSource.getMealDetails(id, networkResponse);

    }
}
