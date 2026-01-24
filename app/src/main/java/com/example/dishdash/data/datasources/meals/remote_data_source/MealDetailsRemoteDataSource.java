package com.example.dishdash.data.datasources.meals.remote_data_source;

import android.util.Log;

import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.model.meals.MealResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailsRemoteDataSource {

    public Service service;

    public MealDetailsRemoteDataSource() {
        service = Network.getInstance().service;
    }

    public void getMealDetails(String mealId, RandomMealRemoteDataSource.RandomMealNetworkResponse randomMealNetworkResponse) {

        service.getMealDetailById(mealId).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                List<Meal> mealDetail = response.body().meals;
                randomMealNetworkResponse.onSuccess(mealDetail);
                Log.i("DataSource", "onResponse: " + mealDetail.get(0).name);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                randomMealNetworkResponse.onFailure(t.getMessage());
            }
        });
    }
}
