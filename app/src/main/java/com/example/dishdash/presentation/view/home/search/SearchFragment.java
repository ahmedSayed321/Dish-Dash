package com.example.dishdash.presentation.view.home.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.OfflineFragment;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.presentation.presenter.search.SearchPresenterImpl;
import com.example.dishdash.utilites.NetworkMonitor;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SearchFragment extends Fragment implements SearchView {

    private EditText etSearch;
    private ChipGroup chipGroupFilters;
    private RecyclerView rvSearchResults;
    private SearchAdapter searchAdapter;
    private SearchPresenterImpl searchPresenter;
    private LottieAnimationView lottieLoading;
    private FrameLayout frameLayout;
    private NetworkMonitor networkMonitor;

    private SearchType currentSearchType = SearchType.CATEGORY;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch = view.findViewById(R.id.etSearch);
        chipGroupFilters = view.findViewById(R.id.chipGroupFilters);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);
        lottieLoading = view.findViewById(R.id.lottieLoading);

        networkMonitor = new NetworkMonitor(requireContext());
        frameLayout = view.findViewById(R.id.errorFragmentSearch);

        searchAdapter = new SearchAdapter();
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSearchResults.setAdapter(searchAdapter);

        searchPresenter = new SearchPresenterImpl(this);
        networkMonitor = new NetworkMonitor(requireContext());
        networkMonitor.observe(getViewLifecycleOwner(), isConnected -> {
            if (isConnected) {
                frameLayout.setVisibility(View.GONE);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.errorFragmentSearch, new OfflineFragment())
                        .commitAllowingStateLoss();
            }
        });
        chipGroupFilters.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chipCategory) {
                currentSearchType = SearchType.CATEGORY;
                etSearch.setHint("Search by category ");
            } else if (checkedId == R.id.chipCountry) {
                currentSearchType = SearchType.AREA;
                etSearch.setHint("Search by area ");
            } else if (checkedId == R.id.chipIngredient) {
                currentSearchType = SearchType.INGREDIENT;
                etSearch.setHint("Search by ingredient ");
            }

            String query = etSearch.getText().toString().trim();
            if (!query.isEmpty()) {
                searchPresenter.searchMeals(query, currentSearchType);
            }
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            performSearch();
            return true;
        });

    }

    private void performSearch() {
        String query = etSearch.getText().toString().trim();
        if (!query.isEmpty()) {
            searchPresenter.searchMeals(query, currentSearchType);
        } else {
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        lottieLoading.setVisibility(View.VISIBLE);
        lottieLoading.playAnimation();
        rvSearchResults.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        lottieLoading.pauseAnimation();
        lottieLoading.setVisibility(View.GONE);
        rvSearchResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults(List<MealCategory> meals) {
        if (meals != null && !meals.isEmpty()) {
            searchAdapter.setMeals(meals);
        } else {
            showError("No results found for this search.");
        }
    }

    @Override
    public void showError(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public enum SearchType {
        CATEGORY,
        AREA,
        INGREDIENT
    }
}



