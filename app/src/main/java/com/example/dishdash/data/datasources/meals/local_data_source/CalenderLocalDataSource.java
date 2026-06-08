package com.example.dishdash.data.datasources.meals.local_data_source;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.db.CalenderDataBase;
import com.example.dishdash.db.CalenderMealDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class CalenderLocalDataSource {

    private final CalenderMealDao calenderMealDao;

    public CalenderLocalDataSource(Context ctx) {
        this.calenderMealDao = CalenderDataBase.getInstance(ctx).calenderMealDao();
    }


    public Completable insertCalenderMeal(CalenderMeal calenderMeal) {
        return calenderMealDao.addCalenderMeal(calenderMeal);
//        new Thread(() -> {
//            calenderMealDao.addCalenderMeal(calenderMeal);
//        }).start();
    }

    public Completable deleteCalenderMeal(CalenderMeal calenderMeal) {
        return calenderMealDao.deleteCalenderMeal(calenderMeal);
//        new Thread(() -> {
//            calenderMealDao.deleteCalenderMeal(calenderMeal);
//        }).start();
    }

    public LiveData<List<CalenderMeal>> getAllMeals(long startDay, long endDay) {
        return calenderMealDao.getAllCalenderMeal(startDay, endDay);
    }

    public Completable deleteAllCalenderMeals() {
        return calenderMealDao.deleteAllCalenderMeals();
//        new Thread(() -> {
//            calenderMealDao.deleteAllCalenderMeals();
//
//        }).start();
    }

    public List<CalenderMeal> getAllCalenderMealsOnce() {
        return calenderMealDao.getAllCalenderMealsOnce();
    }
}
