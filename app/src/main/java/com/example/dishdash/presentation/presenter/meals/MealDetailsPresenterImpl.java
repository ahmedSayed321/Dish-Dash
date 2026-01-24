package com.example.dishdash.presentation.presenter.meals;

import android.util.Log;

import com.example.dishdash.data.datasources.meals.remote_data_source.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.repo.meals.MealDetailRepo;
import com.example.dishdash.presentation.view.home.meals.MealDetailsView;

import java.util.List;

public class MealDetailsPresenterImpl implements MealDetailsPresenter {

    MealDetailsView view;
    MealDetailRepo repo;

    public MealDetailsPresenterImpl(MealDetailsView view) {
        this.view = view;
        repo = new MealDetailRepo();
    }

    @Override
    public void getMealDetails(String mealId) {

        view.showLoading();

        repo.getMealDetailsById(mealId,
                new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>>() {

                    @Override
                    public void onSuccess(List<Meal> meals) {

                        view.hideLoading();

                        if (meals != null && !meals.isEmpty()) {
                            Meal meal = meals.get(0);
                            Log.i("App", "onSuccess: " + meal.name);
                            view.showMealDetails(meal);
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        view.hideLoading();
                        view.showError(message);
                    }
                });
    }

}
