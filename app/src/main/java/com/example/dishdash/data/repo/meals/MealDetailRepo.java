package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.meal_detail.MealDetailsRemoteDataSource;
import com.example.dishdash.data.model.meals.MealResponse;

import io.reactivex.rxjava3.core.Single;

public class MealDetailRepo {

    public MealDetailsRemoteDataSource mealDetailsRemoteDataSource;

    public MealDetailRepo() {
        mealDetailsRemoteDataSource = new MealDetailsRemoteDataSource();
    }


    public Single<MealResponse> getMealDetailsById(String id) {
        return mealDetailsRemoteDataSource.getMealDetails(id);
    }

//    public void getMealDetailsById(String id, RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>> networkResponse) {
//
//        mealDetailsRemoteDataSource.getMealDetails(id, networkResponse);
//
//    }
}
