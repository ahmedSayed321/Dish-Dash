package com.example.dishdash.data.repo.meals.local;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.dishdash.data.datasources.meals.local_data_source.FavouriteLocalDataSource;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.repo.meals.remote.FirebaseFavouriteRepository;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FavouriteRepository {

    private final FavouriteLocalDataSource localDataSource;
    private final FirebaseFavouriteRepository remoteDataSource;

    public FavouriteRepository(Context context) {
        localDataSource = new FavouriteLocalDataSource(context);
        remoteDataSource = new FirebaseFavouriteRepository();
    }

    public Completable addToFavorites(FavoriteMealEntity meal) {
        return localDataSource.insertFavorite(meal);
    }

    public Completable removeFromFavorites(FavoriteMealEntity meal) {
        return localDataSource.deleteFavorite(meal);
    }

    public Observable<List<FavoriteMealEntity>> getAllFavorites() {
        Log.i("Fav Repo", "getAllFavorites: ");
        return localDataSource.getAllFavorites();
    }


    public void isMealFavorite(String mealId, Consumer<Boolean> callback) {
        localDataSource.isMealFavorite(mealId, callback);
    }

    public Completable deleteAllFav() {
        return localDataSource.clearFavorites();

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
