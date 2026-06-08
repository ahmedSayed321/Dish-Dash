package com.example.dishdash.data.model.meals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    public List<Meal> meals;

    public List<Meal> getRandomMeals() {
        return meals;
    }

    public void setRandomMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
