package com.example.dishdash.data.datasources.meals.remote_data_source.search;


import com.example.dishdash.data.model.meals.MealCategoryResponse;
import com.example.dishdash.network.Network;
import com.example.dishdash.network.Service;
import com.example.dishdash.presentation.view.home.search.SearchFragment;

import io.reactivex.rxjava3.core.Single;

public class SearchRemoteDataSource {

    public Service service;

    public SearchRemoteDataSource() {
        service = Network.getInstance().service;
    }


    public Single<MealCategoryResponse> searchMeals(
            String query,
            SearchFragment.SearchType searchType
    ) {

        if (searchType == SearchFragment.SearchType.CATEGORY) {
            return service.filterByCategory(query);

        } else if (searchType == SearchFragment.SearchType.AREA) {
            return service.filterByArea(query);

        } else if (searchType == SearchFragment.SearchType.INGREDIENT) {
            return service.filterByIngredient(query);
        }

        return Single.error(new Throwable("Invalid search type"));
    }
}
//    public void searchMeals(String query, SearchFragment.SearchType searchType, SearchNetworkResponse searchNetworkResponse) {
//
//        Call<MealCategoryResponse> call = null;
//
//        if (searchType == SearchFragment.SearchType.CATEGORY) {
//            call = service.filterByCategory(query);
//        } else if (searchType == SearchFragment.SearchType.AREA) {
//            call = service.filterByArea(query);
//        } else if (searchType == SearchFragment.SearchType.INGREDIENT) {
//            call = service.filterByIngredient(query);
//        }
//
//        if (call == null) return;
//
//        call.enqueue(new Callback<MealCategoryResponse>() {
//            @Override
//            public void onResponse(
//                    Call<MealCategoryResponse> call,
//                    Response<MealCategoryResponse> response
//            ) {
//
//                if (response.isSuccessful()
//                        && response.body() != null
//                        && response.body().getMeals() != null) {
//
//                    List<MealCategory> meals = response.body().getMeals();
//                    searchNetworkResponse.onSuccess(meals);
//
//                    Log.i("SearchDataSource", "Meals count: " + meals.size());
//
//                } else {
//                    searchNetworkResponse.onFailure("No meals found");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealCategoryResponse> call, Throwable t) {
//                searchNetworkResponse.onFailure(t.getMessage());
//            }
//        });
//    }


