package com.example.dishdash.presentation.presenter.meals;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.model.meals.Meal;

public interface MealDetailsPresenter {
    void getMealDetails(String mealId);

    void addToFav(Meal meal);

    void addCalenderMeal(CalenderMeal calenderMeal);

    void isFav(String mealId);

    void removeFromFav(Meal meal);
}
