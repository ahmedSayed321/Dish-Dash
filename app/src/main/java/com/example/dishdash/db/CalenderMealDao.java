package com.example.dishdash.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

@Dao
public interface CalenderMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCalenderMeal(CalenderMeal calenderMeal);

    @Delete
    void deleteCalenderMeal(CalenderMeal calenderMeal);

    @Query("SELECT * FROM calender_meal WHERE time BETWEEN :startDay AND :endDay")
    LiveData<List<CalenderMeal>> getAllCalenderMeal(long startDay, long endDay);


    @Query("DELETE FROM calender_meal")
    void deleteAllCalenderMeals();

    @Query("SELECT * FROM calender_meal")
    List<CalenderMeal> getAllCalenderMealsOnce();
}
