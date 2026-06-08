package com.example.dishdash.data.repo.meals;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.datasources.meals.local_data_source.CalenderLocalDataSource;
import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderRepo {

    private final CalenderLocalDataSource calenderLocalDataSource;

    public CalenderRepo(Context context) {
        calenderLocalDataSource = new CalenderLocalDataSource(context);
    }


    public Completable deleteCalenderMeal(CalenderMeal calenderMeal) {
        return calenderLocalDataSource.deleteCalenderMeal(calenderMeal);
    }

    public Completable insertCalenderMeal(CalenderMeal calenderMeal) {
        return calenderLocalDataSource.insertCalenderMeal(calenderMeal);
    }

    public LiveData<List<CalenderMeal>> getAllCalenderMeals(long start, long end) {
        return calenderLocalDataSource.getAllMeals(start, end);
    }

    public void deleteAllCalenderMeals() {
        calenderLocalDataSource.deleteAllCalenderMeals()
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

    public List<CalenderMeal> getAllCalenderMealsOnce() {
        return calenderLocalDataSource.getAllCalenderMealsOnce();
    }

}
