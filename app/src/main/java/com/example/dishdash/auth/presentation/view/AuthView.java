package com.example.dishdash.auth.presentation.view;

import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

public interface AuthView {
    void showLoading();

    void hideLoading();

    void showEmailError(String message);

    void showPasswordError(String message);

    void showConfirmPasswordError(String message);

    void showFirstNameError(String message);

    void showLastNameError(String message);

    void onAuthSuccess();

    void onAuthError(String message);

    void showCalendarMeals(List<CalenderMeal> meals);


}
