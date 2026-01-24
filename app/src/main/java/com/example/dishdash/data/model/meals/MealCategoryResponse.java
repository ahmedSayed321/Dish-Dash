package com.example.dishdash.data.model.meals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealCategoryResponse {

    @SerializedName("meals")
    private List<MealCategory> meals;

    public MealCategoryResponse(List<MealCategory> meals) {
        this.meals = meals;
    }

    public List<MealCategory> getMeals() {
        return meals;
    }

    public void setMeals(List<MealCategory> meals) {
        this.meals = meals;
    }
}
