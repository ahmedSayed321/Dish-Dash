package com.example.dishdash.data.datasources.meals.local_data_source;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.db.AppDatabase;
import com.example.dishdash.db.FavoriteMealDao;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FavouriteLocalDataSource {

    private final FavoriteMealDao favDao;

    public FavouriteLocalDataSource(Context ctx) {
        favDao = AppDatabase.getInstance(ctx).favoriteMealDao();
    }

    public Completable insertFavorite(FavoriteMealEntity meal) {

        return favDao.insert(meal);

        //new Thread(() -> favDao.insert(meal)).start();
    }

    public Completable deleteFavorite(FavoriteMealEntity meal) {
        return favDao.delete(meal);
        //new Thread(() -> favDao.delete(meal)).start();
    }

    public Observable<List<FavoriteMealEntity>> getAllFavorites() {
        return favDao.getAllFavorites();
    }


    public void isMealFavorite(String mealId, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean result = favDao.isFavorite(mealId);
            new Handler(Looper.getMainLooper()).post(() -> callback.accept(result));
        }).start();

    }

    public Completable clearFavorites() {
        return favDao.deleteAllFavorites();
        //new Thread(favDao::deleteAllFavorites).start();
    }

    public List<FavoriteMealEntity> getAllFavoritesOnce() {
        return favDao.getAllFavoritesOnce();
    }


}
