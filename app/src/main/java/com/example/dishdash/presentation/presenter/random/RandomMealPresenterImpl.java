package com.example.dishdash.presentation.presenter.random;

import com.example.dishdash.data.datasources.meals.remote_data_source.random.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.repo.meals.RandomMealRepo;
import com.example.dishdash.presentation.view.home.random.RandomMealView;

import java.util.List;

public class RandomMealPresenterImpl implements RandomMealPresenter {

    public RandomMealRepo randomMealRepoImpl;
    public RandomMealView randomMealView;

    public RandomMealPresenterImpl(RandomMealRepo randomMealRepoImpl, RandomMealView randomMealView) {

        this.randomMealRepoImpl = randomMealRepoImpl;
        this.randomMealView = randomMealView;
    }

    @Override
    public void getRandomMeal() {
        randomMealView.showLoading();
        randomMealRepoImpl.getRandomMeal(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                randomMealView.hideLoading();
                randomMealView.showRandomMeals(meals);
            }

            @Override
            public void onFailure(String message) {
                randomMealView.hideLoading();
                randomMealView.showError(message);
            }
        });
    }
}
