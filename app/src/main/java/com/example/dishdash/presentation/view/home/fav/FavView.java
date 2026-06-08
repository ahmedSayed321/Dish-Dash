package com.example.dishdash.presentation.view.home.fav;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;

import java.util.List;

public interface FavView {

    void showFavorites(List<FavoriteMealEntity> meals);

    void showEmpty();

    void showError(String message);

}
