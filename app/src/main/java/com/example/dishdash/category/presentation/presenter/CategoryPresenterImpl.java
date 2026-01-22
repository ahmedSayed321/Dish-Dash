package com.example.dishdash.category.presentation.presenter;

import com.example.dishdash.category.data.model.Category;
import com.example.dishdash.category.data.repo.CategoryRepo;
import com.example.dishdash.category.data.repo.CategoryRepoImpl;
import com.example.dishdash.category.presentation.view.CategoryView;

import java.util.List;

public class CategoryPresenterImpl implements CategoryPresenter{

    CategoryRepoImpl categoryRepoImpl;
    CategoryView categoryView;

    public CategoryPresenterImpl(CategoryRepoImpl categoryRepoImpl, CategoryView categoryView) {
        this.categoryRepoImpl = categoryRepoImpl;
        this.categoryView = categoryView;
    }

    @Override
    public void getCategories() {
        categoryView.showLoading();
        categoryRepoImpl.getCategories(new CategoryRepo() {
            @Override
            public void onSuccess(List<Category> categoryList) {
                categoryView.hideLoading();
                categoryView.showCategories(categoryList);
            }

            @Override
            public void onFailure(String message) {
                categoryView.hideLoading();
                categoryView.showError(message);
            }
        });
    }
}
