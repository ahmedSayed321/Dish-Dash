package com.example.dishdash.data.datasources.meals.remote_data_source;

import com.example.dishdash.data.model.meals.Category;

import java.util.List;

public interface CategoryNetworkResponse {
    void onSuccess(List<Category> productList);
    void onFailure(String message);
}
