package com.example.dishdash.network;

import com.example.dishdash.data.model.meals.CategoriesResponse;
import com.example.dishdash.data.model.meals.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
