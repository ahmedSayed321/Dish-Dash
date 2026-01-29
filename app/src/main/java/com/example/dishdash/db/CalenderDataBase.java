package com.example.dishdash.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;

@Database(entities = {FavoriteMealEntity.class, CalenderMeal.class}, version = 1)
public abstract class CalenderDataBase extends RoomDatabase {

    private static final String DB_NAME = "meals_db";
    private static CalenderDataBase instance = null;

    public static CalenderDataBase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx, CalenderDataBase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
            return instance;
        }
        return instance;
    }


    public abstract CalenderMealDao calenderMealDao();

}
