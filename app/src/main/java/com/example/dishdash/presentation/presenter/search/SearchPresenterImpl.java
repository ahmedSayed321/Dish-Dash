package com.example.dishdash.presentation.presenter.search;

import com.example.dishdash.data.datasources.meals.remote_data_source.search.SearchNetworkResponse;
import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.repo.meals.SearchRepoImpl;
import com.example.dishdash.presentation.view.home.search.SearchFragment;
import com.example.dishdash.presentation.view.home.search.SearchView;

import java.util.List;

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

        searchRepo.searchMeals(query, type, new SearchNetworkResponse() {
            @Override
            public void onSuccess(List<MealCategory> meals) {
                searchView.hideLoading();
                searchView.showResults(meals);
            }

            @Override
            public void onFailure(String message) {
                searchView.hideLoading();
                searchView.showError(message);
            }
        });
    }
}
