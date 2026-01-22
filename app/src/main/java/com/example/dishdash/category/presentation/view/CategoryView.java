package com.example.dishdash.category.presentation.view;

import com.example.dishdash.category.data.model.Category;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void showCategories(List<Category> categories);
    void showError(String message);
}
