package com.example.dishdash.presentation.view.home.search;

import com.example.dishdash.data.model.meals.MealCategory;

import java.util.List;

public interface SearchView {
    void showLoading();

    void hideLoading();

    void showResults(List<MealCategory> meals);

    void showError(String message);

}
