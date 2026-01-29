package com.example.dishdash.data.repo.meals;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.datasources.meals.local_data_source.CalenderLocalDataSource;
import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

public class CalenderRepo {

    private final CalenderLocalDataSource calenderLocalDataSource;

    public CalenderRepo(Context context) {
        calenderLocalDataSource = new CalenderLocalDataSource(context);
    }


    public void deleteCalenderMeal(CalenderMeal calenderMeal) {
        calenderLocalDataSource.deleteCalenderMeal(calenderMeal);
    }

    public void insertCalenderMeal(CalenderMeal calenderMeal) {
        calenderLocalDataSource.insertCalenderMeal(calenderMeal);
    }

    public LiveData<List<CalenderMeal>> getAllCalenderMeals(long start, long end) {
        return calenderLocalDataSource.getAllMeals(start, end);
    }

    public void deleteAllCalenderMeals() {
        calenderLocalDataSource.deleteAllCalenderMeals();
    }

    public List<CalenderMeal> getAllCalenderMealsOnce() {
        return calenderLocalDataSource.getAllCalenderMealsOnce();
    }

}
