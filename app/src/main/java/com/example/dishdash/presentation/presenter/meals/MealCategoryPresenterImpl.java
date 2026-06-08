package com.example.dishdash.presentation.presenter.meals;

import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.repo.meals.MealCategoryRepo;
import com.example.dishdash.presentation.view.all_categories.MealCategoryView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealCategoryPresenterImpl {

    MealCategoryView mealCategoryView;
    MealCategoryRepo mealCategoryRepo;


    public MealCategoryPresenterImpl(MealCategoryView mealCategoryView, MealCategoryRepo mealCategoryRepo) {
        this.mealCategoryView = mealCategoryView;
        this.mealCategoryRepo = mealCategoryRepo;
    }

    public void getMealsByCategory(String categoryName) {
        mealCategoryView.showLoading();

        mealCategoryRepo.getMealCategories(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mealCategoryResponse -> mealCategoryResponse.getMeals())
                .subscribe(new SingleObserver<List<MealCategory>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<MealCategory> mealCategories) {
                        mealCategoryView.hideLoading();
                        mealCategoryView.showMealsCategory(mealCategories);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mealCategoryView.showLoading();
                        mealCategoryView.showError(e.getMessage());
                    }
                });
    }

//    public void getMealsByCategory(String categoryName) {
//        mealCategoryView.showLoading();
//        mealCategoryRepo.getMealCategories(new RandomMealRemoteDataSource.RandomMealNetworkResponse<List<MealCategory>>() {
//
//            @Override
//            public void onSuccess(List<MealCategory> meals) {
//                mealCategoryView.showMealsCategory(meals);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                mealCategoryView.showError(message);
//            }
//        }, categoryName);
//    }
}
