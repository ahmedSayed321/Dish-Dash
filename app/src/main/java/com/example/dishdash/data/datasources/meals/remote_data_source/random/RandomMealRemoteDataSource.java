package com.example.dishdash.data.datasources.meals.remote_data_source.random;

import com.example.dishdash.data.model.meals.MealResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;

import io.reactivex.rxjava3.core.Single;

public class RandomMealRemoteDataSource {

    public Service service;

    public RandomMealRemoteDataSource() {
        service = Network.getInstance().service;
    }

    public Single<MealResponse> getRandomMeal() {
        return service.getRandomMeal();
    }
//    public void getRandomMeal(RandomMealNetworkResponse randomMealNetworkResponse) {
//
//        service.getRandomMeal().enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                List<Meal> meals = response.body().meals;
//                randomMealNetworkResponse.onSuccess(meals);
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                randomMealNetworkResponse.onFailure(t.getMessage());
//            }
//        });
//
//    }

    public interface RandomMealNetworkResponse<T> {
        void onSuccess(T randomMeals);

        void onFailure(String message);
    }
}
