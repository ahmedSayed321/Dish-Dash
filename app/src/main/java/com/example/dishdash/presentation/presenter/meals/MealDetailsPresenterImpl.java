package com.example.dishdash.presentation.presenter.meals;

import android.content.Context;

import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.model.meals.MealToFavMapper;
import com.example.dishdash.data.repo.meals.CalenderRepo;
import com.example.dishdash.data.repo.meals.MealDetailRepo;
import com.example.dishdash.data.repo.meals.local.FavouriteRepository;
import com.example.dishdash.presentation.view.home.meals.MealDetailsView;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImpl implements MealDetailsPresenter {

    MealDetailsView view;
    MealDetailRepo repo;
    FavouriteRepository favRepo;
    Context context;
    CalenderRepo calenderRepo;

    public MealDetailsPresenterImpl(MealDetailsView view, Context context) {
        this.view = view;
        this.context = context;
        repo = new MealDetailRepo();
        favRepo = new FavouriteRepository(context);
        calenderRepo = new CalenderRepo(context);
    }

    public void getMealDetails(String id) {

        view.showLoading();
        repo.getMealDetailsById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mealResponse -> mealResponse.getRandomMeals())
                .subscribe(new SingleObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Meal> meals) {
                        view.hideLoading();
                        view.showMealDetails(meals.get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }
//
//    @Override
//    public void getMealDetails(String mealId) {
//
//        view.showLoading();
//
//        repo.getMealDetailsById(mealId, new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<Meal>>() {
//
//                    @Override
//                    public void onSuccess(List<Meal> meals) {
//
//                        view.hideLoading();
//
//                        if (meals != null && !meals.isEmpty()) {
//                            Meal meal = meals.get(0);
//                            Log.i("App", "onSuccess: " + meal.name);
//                            view.showMealDetails(meal);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(String message) {
//                        view.hideLoading();
//                        view.showError(message);
//                    }
//                });
//    }

    @Override
    public void addToFav(Meal meal) {
        FavoriteMealEntity favMeal = MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid());
        favRepo.addToFavorites(favMeal)
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
        //favRepo.addToFavorites(MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid()));
    }

    @Override
    public void addCalenderMeal(CalenderMeal calenderMeal) {
        calenderRepo.insertCalenderMeal(calenderMeal)
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
    }

    @Override
    public void isFav(String mealId) {
        favRepo.isMealFavorite(mealId, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.showFavoriteState(value);
            }
        });

    }

    @Override
    public void removeFromFav(Meal meal) {
        FavoriteMealEntity favMeal = MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid());

        favRepo.removeFromFavorites(favMeal)
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
        //favRepo.removeFromFavorites(MealToFavMapper.converterMealToFav(meal, AuthLocalDataSource.getInstance(context).getUserUid()));
    }


}
