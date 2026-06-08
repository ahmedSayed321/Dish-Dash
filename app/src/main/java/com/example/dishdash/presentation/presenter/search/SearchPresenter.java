package com.example.dishdash.presentation.presenter.search;

import com.example.dishdash.presentation.view.home.search.SearchFragment;

public interface SearchPresenter {
    void searchMeals(String query, SearchFragment.SearchType type);
}
