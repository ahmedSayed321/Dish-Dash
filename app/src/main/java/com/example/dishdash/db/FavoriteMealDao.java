package com.example.dishdash.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface FavoriteMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(FavoriteMealEntity meal);

    @Delete
    Completable delete(FavoriteMealEntity meal);

    @Query("SELECT * FROM favorite_meals")
    Observable<List<FavoriteMealEntity>> getAllFavorites();

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_meals WHERE id = :mealId)")
    boolean isFavorite(String mealId);

    @Query("DELETE FROM favorite_meals")
    Completable deleteAllFavorites();

    @Query("SELECT * FROM favorite_meals")
    List<FavoriteMealEntity> getAllFavoritesOnce();


}
