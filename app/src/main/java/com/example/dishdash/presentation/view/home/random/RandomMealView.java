package com.example.dishdash.presentation.view.home.random;

import com.example.dishdash.data.model.meals.Meal;

import java.util.List;

public interface RandomMealView {
    void showLoading();

    void hideLoading();

    void showRandomMeals(List<Meal> meals);

    void showError(String message);
}
