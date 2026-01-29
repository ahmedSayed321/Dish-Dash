package com.example.dishdash.presentation.presenter.meals;

import android.content.Context;
import android.util.Log;

import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.data.datasources.meals.remote_data_source.random.RandomMealRemoteDataSource;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.model.meals.MealToFavMapper;
import com.example.dishdash.data.repo.meals.CalenderRepo;
import com.example.dishdash.data.repo.meals.MealDetailRepo;
import com.example.dishdash.data.repo.meals.local.FavouriteRepository;
import com.example.dishdash.presentation.view.home.meals.MealDetailsView;

import java.util.List;
import java.util.function.Consumer;

public class MealDetailsPresenterImpl implements MealDetailsPresenter {

    MealDetailsView view;
    MealDetailRepo repo;
    FavouriteRepository favRepo;
    Context context;
    CalenderRepo calenderRepo;

    public MealDetailsPresenterImpl(MealDetailsView view, Context context) {
        this.view = view;
        this.context = context;
        repo = new MealDetailRepo();
        favRepo = new FavouriteRepository(context);
        calenderRepo = new CalenderRepo(context);
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

    @Override
    public void addToFav(Meal meal) {

        favRepo.addToFavorites(MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid()));
    }

    @Override
    public void addCalenderMeal(CalenderMeal calenderMeal) {
        calenderRepo.insertCalenderMeal(calenderMeal);
    }

    @Override
    public void isFav(String mealId) {
        favRepo.isMealFavorite(mealId, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.showFavoriteState(value);
            }
        });

    }

    @Override
    public void removeFromFav(Meal meal) {
        favRepo.removeFromFavorites(MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid()));
    }


}
