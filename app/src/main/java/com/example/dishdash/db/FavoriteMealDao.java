package com.example.dishdash.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;

import java.util.List;

@Dao
public interface FavoriteMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteMealEntity meal);

    @Delete
    void delete(FavoriteMealEntity meal);

    @Query("SELECT * FROM favorite_meals")
    LiveData<List<FavoriteMealEntity>> getAllFavorites();

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_meals WHERE id = :mealId)")
    boolean isFavorite(String mealId);

    @Query("DELETE FROM favorite_meals")
    void deleteAllFavorites();

    @Query("SELECT * FROM favorite_meals")
    List<FavoriteMealEntity> getAllFavoritesOnce();


}
