package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.search.SearchRemoteDataSource;
import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.presentation.view.home.search.SearchFragment;

import io.reactivex.rxjava3.core.Single;

public class SearchRepoImpl {

    private final SearchRemoteDataSource remoteDataSource;

    public SearchRepoImpl() {
        remoteDataSource = new SearchRemoteDataSource();
    }

    public Single<MealCategoryResponse> searchMeals(String query, SearchFragment.SearchType type) {
        return remoteDataSource.searchMeals(query, type);
    }
//    public void searchMeals(
//            String query,
//            SearchFragment.SearchType type,
//            SearchNetworkResponse callback
//    ) {
//        remoteDataSource.searchMeals(query, type, callback);
//    }
}
