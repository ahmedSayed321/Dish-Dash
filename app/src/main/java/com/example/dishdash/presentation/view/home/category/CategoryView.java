package com.example.dishdash.presentation.view.home.category;

import com.example.dishdash.data.model.meals.Category;

import java.util.List;

public interface CategoryView {
    void showCatLoading();

    void hideCatLoading();

    void showCategories(List<Category> categories);

    void showError(String message);
}
