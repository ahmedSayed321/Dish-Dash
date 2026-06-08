package com.example.dishdash.presentation.presenter.random;

import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.repo.meals.RandomMealRepo;
import com.example.dishdash.presentation.view.home.random.RandomMealView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomMealPresenterImpl implements RandomMealPresenter {

    public RandomMealRepo randomMealRepoImpl;
    public RandomMealView randomMealView;

    public RandomMealPresenterImpl(RandomMealRepo randomMealRepoImpl, RandomMealView randomMealView) {

        this.randomMealRepoImpl = randomMealRepoImpl;
        this.randomMealView = randomMealView;
    }

    public void getRandomMeal() {
        randomMealView.showLoading();

        randomMealRepoImpl.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mealResponse -> mealResponse.getRandomMeals())
                .subscribe(new SingleObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Meal> meals) {
                        randomMealView.hideLoading();
                        randomMealView.showRandomMeals(meals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        randomMealView.hideLoading();
                        randomMealView.showError(e.getMessage());
                    }
                });
    }

//    @Override
//    public void getRandomMeal() {
//        randomMealView.showLoading();
//        randomMealRepoImpl.getRandomMeal(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>>() {
//            @Override
//            public void onSuccess(List<Meal> meals) {
//                randomMealView.hideLoading();
//                randomMealView.showRandomMeals(meals);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                randomMealView.hideLoading();
//                randomMealView.showError(message);
//            }
//        });
//    }
}
