package com.example.dishdash.data.datasources.meals.remote_data_source;


import android.util.Log;

import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;
import com.example.dishdash.presentation.view.home.search.SearchFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRemoteDataSource {

    public Service service;

    public SearchRemoteDataSource() {
        service = Network.getInstance().service;
    }

    public void searchMeals(
            String query,
            SearchFragment.SearchType searchType,
            SearchNetworkResponse searchNetworkResponse
    ) {

        Call<MealCategoryResponse> call = null;

        if (searchType == SearchFragment.SearchType.CATEGORY) {
            call = service.filterByCategory(query);
        } else if (searchType == SearchFragment.SearchType.AREA) {
            call = service.filterByArea(query);
        } else if (searchType == SearchFragment.SearchType.INGREDIENT) {
            call = service.filterByIngredient(query);
        }

        if (call == null) return;

        call.enqueue(new Callback<MealCategoryResponse>() {
            @Override
            public void onResponse(
                    Call<MealCategoryResponse> call,
                    Response<MealCategoryResponse> response
            ) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    List<MealCategory> meals = response.body().getMeals();
                    searchNetworkResponse.onSuccess(meals);

                    Log.i("SearchDataSource", "Meals count: " + meals.size());

                } else {
                    searchNetworkResponse.onFailure("No meals found");
                }
            }

            @Override
            public void onFailure(Call<MealCategoryResponse> call, Throwable t) {
                searchNetworkResponse.onFailure(t.getMessage());
            }
        });
    }
}

