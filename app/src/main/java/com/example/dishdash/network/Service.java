package com.example.dishdash.network;

import com.example.dishdash.data.model.meals.CategoriesResponse;
import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.data.model.meals.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("categories.php")
    Single<CategoriesResponse> getCategories();

    @GET("random.php")
    Single<MealResponse> getRandomMeal();

    @GET("filter.php")
    Single<MealCategoryResponse> getAllMealsInCategory(@Query("c") String categoryName);

    @GET("lookup.php")
    Single<MealResponse> getMealDetailById(@Query("i") String mealId);

    @GET("filter.php")
    Single<MealCategoryResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<MealCategoryResponse> filterByArea(@Query("a") String area);

    @GET("filter.php")
    Single<MealCategoryResponse> filterByIngredient(@Query("i") String ingredient);

}
