package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.random.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.Meal;

import java.util.List;

public class RandomMealRepo {

    public RandomMealRemoteDataSource randomMealRemoteDataSource;

    public RandomMealRepo() {
        randomMealRemoteDataSource = new RandomMealRemoteDataSource();
    }

    public void getRandomMeal(RandomMealRemoteDataSource.RandomMealNetworkResponse randomMealRepo) {

        randomMealRemoteDataSource.getRandomMeal(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                randomMealRepo.onSuccess(meals);
            }

            @Override
            public void onFailure(String message) {
                randomMealRepo.onFailure(message);
            }
        });
    }

}
