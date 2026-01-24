package com.example.dishdash.data.datasources.meals.remote_data_source;

import com.example.dishdash.data.model.meals.MealCategory;

import java.util.List;

public interface SearchNetworkResponse {
    void onSuccess(List<MealCategory> meals);

    void onFailure(String message);
}
