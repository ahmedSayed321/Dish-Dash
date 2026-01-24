package com.example.dishdash.presentation.presenter.category;

import com.example.dishdash.data.model.meals.Category;
import com.example.dishdash.data.repo.meals.CategoryRepo;
import com.example.dishdash.data.repo.meals.CategoryRepoImpl;
import com.example.dishdash.presentation.view.home.category.CategoryView;

import java.util.List;

public class CategoryPresenterImpl implements CategoryPresenter {

    CategoryRepoImpl categoryRepoImpl;
    CategoryView categoryView;

    public CategoryPresenterImpl(CategoryRepoImpl categoryRepoImpl, CategoryView categoryView) {
        this.categoryRepoImpl = categoryRepoImpl;
        this.categoryView = categoryView;
    }

    @Override
    public void getCategories() {
        categoryView.showCatLoading();
        categoryRepoImpl.getCategories(new CategoryRepo() {
            @Override
            public void onSuccess(List<Category> categoryList) {
                categoryView.hideCatLoading();
                categoryView.showCategories(categoryList);
            }

            @Override
            public void onFailure(String message) {
                categoryView.hideCatLoading();
                categoryView.showError(message);
            }
        });
    }
}
