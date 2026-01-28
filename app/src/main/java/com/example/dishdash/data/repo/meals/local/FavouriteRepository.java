package com.example.dishdash.data.repo.meals.local;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.datasources.meals.local_data_source.FavouriteLocalDataSource;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.repo.meals.remote.FirebaseFavouriteRepository;

import java.util.List;
import java.util.function.Consumer;

public class FavouriteRepository {

    private final FavouriteLocalDataSource localDataSource;
    private final FirebaseFavouriteRepository remoteDataSource;

    public FavouriteRepository(Context context) {
        localDataSource = new FavouriteLocalDataSource(context);
        remoteDataSource = new FirebaseFavouriteRepository();
    }

    public void addToFavorites(FavoriteMealEntity meal) {
        localDataSource.insertFavorite(meal);
    }

    public void removeFromFavorites(FavoriteMealEntity meal) {
        localDataSource.deleteFavorite(meal);
    }

    public LiveData<List<FavoriteMealEntity>> getAllFavorites() {
        return localDataSource.getAllFavorites();
    }


    public void isMealFavorite(String mealId, Consumer<Boolean> callback) {
        localDataSource.isMealFavorite(mealId, callback);
    }

    public void deleteAllFav() {
        localDataSource.clearFavorites();

    }

    public void syncFavoritesToFirebase(
            String userId,
            Runnable onSuccess,
            Consumer<String> onError
    ) {

        new Thread(() -> {

            List<FavoriteMealEntity> meals =
                    localDataSource.getAllFavoritesOnce();

            new Handler(Looper.getMainLooper()).post(() ->
                    remoteDataSource.uploadFavorites(
                            userId,
                            meals,
                            onSuccess,
                            onError
                    )
            );

        }).start();
    }

    public void downloadFavoritesFromFirebase(
            String userId,
            Runnable onComplete,
            Consumer<String> onError
    ) {
        remoteDataSource.downloadFavorites(userId,
                meals -> {
                    for (FavoriteMealEntity meal : meals) {
                        localDataSource.insertFavorite(meal);
                    }
                    onComplete.run();
                },
                error -> onError.accept(error)
        );
    }
}
