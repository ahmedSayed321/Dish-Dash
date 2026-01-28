package com.example.dishdash.presentation.presenter.favourite;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.dishdash.presentation.view.home.fav.FavView;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.repo.meals.local.FavouriteRepository;

import java.util.List;

public class FavPresenter {

    private final FavView view;
    private final FavouriteRepository repository;

    public FavPresenter(FavView view, FavouriteRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getFavorites() {
        repository.getAllFavorites().observeForever(
                new Observer<List<FavoriteMealEntity>>() {
                    @Override
                    public void onChanged(List<FavoriteMealEntity> meals) {
                        Log.i("getFavorites", "onChanged: " + meals);
                        if (meals == null || meals.isEmpty()) {
                            view.showEmpty();
                        } else {
                            view.showFavorites(meals);
                        }
                    }
                }
        );
    }

    public void removeFromFavourite(FavoriteMealEntity favoriteMeal) {
        Log.i("removeFromFavourite", "removeFromFavourite: " + favoriteMeal);
        repository.removeFromFavorites(favoriteMeal);
    }

}
