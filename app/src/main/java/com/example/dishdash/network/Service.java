package com.example.dishdash.network;

import com.example.dishdash.data.model.meals.CategoriesResponse;
import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.data.model.meals.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("filter.php")
    Call<MealCategoryResponse> getAllMealsInCategory(@Query("c") String categoryName);

    @GET("lookup.php")
    Call<MealResponse> getMealDetailById(@Query("i") String mealId);

    @GET("filter.php")
    Call<MealCategoryResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealCategoryResponse> filterByArea(@Query("a") String area);

    @GET("filter.php")
    Call<MealCategoryResponse> filterByIngredient(@Query("i") String ingredient);

}
