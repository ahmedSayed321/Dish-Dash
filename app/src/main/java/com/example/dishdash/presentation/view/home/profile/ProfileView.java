package com.example.dishdash.presentation.view.home.profile;

import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

public interface ProfileView {

    void showUserName(String fullName);

    void showUserEmail(String email);

    void showError(String message);

    void showCalendarMeals(List<CalenderMeal> meals);

    void showLoading(boolean isLoading);


}
