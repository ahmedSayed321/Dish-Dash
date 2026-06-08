package com.example.dishdash.data.model.meals;

import com.google.gson.annotations.SerializedName;

public class MealCategory {

    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strMealThumb")
    private String mealImage;

    @SerializedName("idMeal")
    private String mealId;

    public MealCategory(String mealName, String mealImage, String mealId) {
        this.mealName = mealName;
        this.mealImage = mealImage;
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
