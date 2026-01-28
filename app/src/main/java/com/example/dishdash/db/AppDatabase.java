package com.example.dishdash.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.model.meals.IngredientConverter;

@Database(entities = {FavoriteMealEntity.class}, version = 1)
@TypeConverters(IngredientConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "meals_db";
    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx, AppDatabase.class, DB_NAME).build();
            return instance;
        }
        return instance;
    }

    public abstract FavoriteMealDao favoriteMealDao();

}
