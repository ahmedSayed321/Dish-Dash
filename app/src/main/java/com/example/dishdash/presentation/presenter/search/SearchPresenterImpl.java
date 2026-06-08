package com.example.dishdash.presentation.presenter.search;

import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.repo.meals.SearchRepoImpl;
import com.example.dishdash.presentation.view.home.search.SearchFragment;
import com.example.dishdash.presentation.view.home.search.SearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImpl implements SearchPresenter {

    private final SearchView searchView;
    private final SearchRepoImpl searchRepo;

    public SearchPresenterImpl(SearchView searchView) {
        this.searchView = searchView;
        this.searchRepo = new SearchRepoImpl();
    }

    @Override
    public void searchMeals(String query, SearchFragment.SearchType type) {
        searchView.showLoading();

        searchRepo.searchMeals(query, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mealCategoryResponse -> mealCategoryResponse.getMeals())
                .subscribe(new SingleObserver<List<MealCategory>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<MealCategory> mealCategories) {
                        searchView.hideLoading();
                        searchView.showResults(mealCategories);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        searchView.hideLoading();
                        searchView.showError(e.getMessage());
                    }
                });


//        searchRepo.searchMeals(query, type, new SearchNetworkResponse() {
//            @Override
//            public void onSuccess(List<MealCategory> meals) {
//                searchView.hideLoading();
//                searchView.showResults(meals);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                searchView.hideLoading();
//                searchView.showError(message);
//            }
//        });
    }
}
