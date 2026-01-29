package com.example.dishdash.data.datasources.meals.local_data_source;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.db.CalenderDataBase;
import com.example.dishdash.db.CalenderMealDao;

import java.util.List;

public class CalenderLocalDataSource {

    private final CalenderMealDao calenderMealDao;

    public CalenderLocalDataSource(Context ctx) {
        this.calenderMealDao = CalenderDataBase.getInstance(ctx).calenderMealDao();
    }


    public void insertCalenderMeal(CalenderMeal calenderMeal) {
        new Thread(() -> {
            calenderMealDao.addCalenderMeal(calenderMeal);
        }).start();
    }

    public void deleteCalenderMeal(CalenderMeal calenderMeal) {
        new Thread(() -> {
            calenderMealDao.deleteCalenderMeal(calenderMeal);
        }).start();
    }

    public LiveData<List<CalenderMeal>> getAllMeals(long startDay, long endDay) {
        return calenderMealDao.getAllCalenderMeal(startDay, endDay);
    }

    public void deleteAllCalenderMeals() {
        new Thread(() -> {
            calenderMealDao.deleteAllCalenderMeals();

        }).start();
    }

    public List<CalenderMeal> getAllCalenderMealsOnce() {
        return calenderMealDao.getAllCalenderMealsOnce();
    }
}
