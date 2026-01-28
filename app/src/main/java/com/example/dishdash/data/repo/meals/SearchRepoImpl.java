package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.datasources.meals.remote_data_source.search.SearchNetworkResponse;
import com.example.dishdash.data.datasources.meals.remote_data_source.search.SearchRemoteDataSource;
import com.example.dishdash.presentation.view.home.search.SearchFragment;

public class SearchRepoImpl {

    private final SearchRemoteDataSource remoteDataSource;

    public SearchRepoImpl() {
        remoteDataSource = new SearchRemoteDataSource();
    }

    public void searchMeals(
            String query,
            SearchFragment.SearchType type,
            SearchNetworkResponse callback
    ) {
        remoteDataSource.searchMeals(query, type, callback);
    }
}
