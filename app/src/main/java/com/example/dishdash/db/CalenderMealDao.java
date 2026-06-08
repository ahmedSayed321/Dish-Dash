package com.example.dishdash.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CalenderMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addCalenderMeal(CalenderMeal calenderMeal);

    @Delete
    Completable deleteCalenderMeal(CalenderMeal calenderMeal);

    @Query("SELECT * FROM calender_meal WHERE time BETWEEN :startDay AND :endDay")
    LiveData<List<CalenderMeal>> getAllCalenderMeal(long startDay, long endDay);


    @Query("DELETE FROM calender_meal")
    Completable deleteAllCalenderMeals();

    @Query("SELECT * FROM calender_meal")
    List<CalenderMeal> getAllCalenderMealsOnce();
}
