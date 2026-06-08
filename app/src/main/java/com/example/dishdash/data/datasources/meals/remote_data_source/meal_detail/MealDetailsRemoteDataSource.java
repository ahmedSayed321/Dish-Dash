package com.example.dishdash.data.datasources.meals.remote_data_source.meal_detail;

import com.example.dishdash.data.model.meals.MealResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;

import io.reactivex.rxjava3.core.Single;

public class MealDetailsRemoteDataSource {

    public Service service;

    public MealDetailsRemoteDataSource() {
        service = Network.getInstance().service;
    }


    public Single<MealResponse> getMealDetails(String mealId) {
        return service.getMealDetailById(mealId);
    }


//    public void getMealDetails(String mealId, RandomMealRemoteDataSource.RandomMealNetworkResponse randomMealNetworkResponse) {
//
//        service.getMealDetailById(mealId).enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                List<Meal> mealDetail = response.body().meals;
//                randomMealNetworkResponse.onSuccess(mealDetail);
//                Log.i("DataSource", "onResponse: " + mealDetail.get(0).name);
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                randomMealNetworkResponse.onFailure(t.getMessage());
//            }
//        });
//    }
}
