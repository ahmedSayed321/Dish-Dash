package com.example.dishdash.presentation.view.home.meals;

import com.example.dishdash.data.model.meals.Meal;

public interface MealDetailsView {

    void showMealDetails(Meal meal);


    void showLoading();

    void hideLoading();

    void showError(String message);
}
