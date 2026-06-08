package com.example.dishdash.presentation.presenter.category;

import androidx.annotation.NonNull;

import com.example.dishdash.data.model.meals.Category;
import com.example.dishdash.data.repo.meals.CategoryRepoImpl;
import com.example.dishdash.presentation.view.home.category.CategoryView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryPresenterImpl implements CategoryPresenter {

    CategoryRepoImpl categoryRepoImpl;
    CategoryView categoryView;

    public CategoryPresenterImpl(CategoryRepoImpl categoryRepoImpl, CategoryView categoryView) {
        this.categoryRepoImpl = categoryRepoImpl;
        this.categoryView = categoryView;
    }

    @Override
    public void getCategories() {
        categoryView.showCatLoading();

        categoryRepoImpl.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> response.getCategories())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Category> categoryList) {
                        categoryView.hideCatLoading();
                        categoryView.showCategories(categoryList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        categoryView.hideCatLoading();
                        categoryView.showError(e.getMessage());
                    }
                });

    }

}

//    @Override
//    public void getCategories() {
//        categoryView.showCatLoading();
//        categoryRepoImpl.getCategories(new CategoryRepo() {
//            @Override
//            public void onSuccess(List<Category> categoryList) {
//                categoryView.hideCatLoading();
//                categoryView.showCategories(categoryList);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                categoryView.hideCatLoading();
//                categoryView.showError(message);
//            }
//        });
//    }

