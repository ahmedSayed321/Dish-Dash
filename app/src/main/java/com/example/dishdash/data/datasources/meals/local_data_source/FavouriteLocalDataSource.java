package com.example.dishdash.data.datasources.meals.local_data_source;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.db.AppDatabase;
import com.example.dishdash.db.FavoriteMealDao;

import java.util.List;
import java.util.function.Consumer;

public class FavouriteLocalDataSource {

    private final FavoriteMealDao favDao;

    public FavouriteLocalDataSource(Context ctx) {
        favDao = AppDatabase.getInstance(ctx).favoriteMealDao();
    }

    public void insertFavorite(FavoriteMealEntity meal) {
        new Thread(() -> favDao.insert(meal)).start();
    }

    public void deleteFavorite(FavoriteMealEntity meal) {
        new Thread(() -> favDao.delete(meal)).start();
    }

    public LiveData<List<FavoriteMealEntity>> getAllFavorites() {
        return favDao.getAllFavorites();
    }


    public void isMealFavorite(String mealId, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean result = favDao.isFavorite(mealId);
            new Handler(Looper.getMainLooper()).post(() -> callback.accept(result));
        }).start();

    }

    public void clearFavorites() {
        new Thread(favDao::deleteAllFavorites).start();
    }

    public List<FavoriteMealEntity> getAllFavoritesOnce() {
        return favDao.getAllFavoritesOnce();
    }


}
