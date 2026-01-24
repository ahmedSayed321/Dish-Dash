package com.example.dishdash.presentation.view.all_categories;

import com.example.dishdash.data.model.meals.MealCategory;

import java.util.List;

public interface MealCategoryView {
    void showLoading();

    void hideLoading();

    void showMealsCategory(List<MealCategory> meals);

    void showError(String message);
}
