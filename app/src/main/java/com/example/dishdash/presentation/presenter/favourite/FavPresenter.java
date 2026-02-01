package com.example.dishdash.presentation.presenter.favourite;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.repo.meals.local.FavouriteRepository;
import com.example.dishdash.presentation.view.home.fav.FavView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter {

    private final FavView view;
    private final FavouriteRepository repository;

    public FavPresenter(FavView view, FavouriteRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getFavorites() {
        repository.getAllFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FavoriteMealEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<FavoriteMealEntity> favoriteMealEntities) {
                        view.showFavorites(favoriteMealEntities);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    public void getFavorites() {
//        repository.getAllFavorites().observeForever(
//                new Observer<List<FavoriteMealEntity>>() {
//                    @Override
//                    public void onChanged(List<FavoriteMealEntity> meals) {
//                        Log.i("getFavorites", "onChanged: " + meals);
//                        if (meals == null || meals.isEmpty()) {
//                            view.showEmpty();
//                        } else {
//                            view.showFavorites(meals);
//                        }
//                    }
//                }
//        );
//    }

    public void removeFromFavourite(FavoriteMealEntity favoriteMeal) {

        repository.removeFromFavorites(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
//        Log.i("removeFromFavourite", "removeFromFavourite: " + favoriteMeal);
//        repository.removeFromFavorites(favoriteMeal);
    }

    public void deleteAllFav() {
        repository.deleteAllFav()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
