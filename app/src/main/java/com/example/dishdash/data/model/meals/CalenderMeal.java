package com.example.dishdash.data.model.meals;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calender_meal")
public class CalenderMeal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "idMeal")
    private String mealId;
    @ColumnInfo(name = "strMeal")
    private String mealName;
    @ColumnInfo(name = "strCategory")
    private String mealCategory;
    @ColumnInfo(name = "strArea")
    private String mealArea;
    @ColumnInfo(name = "strMealThumb")
    private String mealImage;
    @ColumnInfo(name = "time")
    private long timestamp;
    public CalenderMeal(String mealId, String mealName, String mealCategory, String mealArea, String mealImage, long timestamp) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealArea = mealArea;
        this.mealImage = mealImage;
        this.timestamp = timestamp;
    }

    public CalenderMeal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    public String getIdMeal() {
        return mealId;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.mealId = idMeal;
    }

    public String getStrMeal() {
        return mealName;
    }

    public void setStrMeal(String strMeal) {
        this.mealName = strMeal;
    }

    public String getStrCategory() {
        return mealCategory;
    }

    public void setStrCategory(String strCategory) {
        this.mealCategory = strCategory;
    }

    public String getStrArea() {
        return mealArea;
    }

    public void setStrArea(String strArea) {
        this.mealArea = strArea;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealArea() {
        return mealArea;
    }

    public void setMealArea(String mealArea) {
        this.mealArea = mealArea;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getStrMealThumb() {
        return mealImage;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.mealImage = strMealThumb;
    }
}
