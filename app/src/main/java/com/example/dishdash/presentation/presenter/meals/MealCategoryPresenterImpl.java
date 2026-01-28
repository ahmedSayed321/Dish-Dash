package com.example.dishdash.presentation.presenter.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.random.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.repo.meals.MealCategoryRepo;
import com.example.dishdash.presentation.view.all_categories.MealCategoryView;

import java.util.List;

public class MealCategoryPresenterImpl {

    MealCategoryView mealCategoryView;
    MealCategoryRepo mealCategoryRepo;


    public MealCategoryPresenterImpl(MealCategoryView mealCategoryView, MealCategoryRepo mealCategoryRepo) {
        this.mealCategoryView = mealCategoryView;
        this.mealCategoryRepo = mealCategoryRepo;
    }

    public void getMealsByCategory(String categoryName) {
        mealCategoryView.showLoading();
        mealCategoryRepo.getMealCategories(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<MealCategory>>() {

            @Override
            public void onSuccess(List<MealCategory> meals) {
                mealCategoryView.showMealsCategory(meals);
            }

            @Override
            public void onFailure(String message) {
                mealCategoryView.showError(message);
            }
        }, categoryName);
    }
}
