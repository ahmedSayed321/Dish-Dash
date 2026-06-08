package com.example.dishdash.presentation.view.all_categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.MealCategory;
import com.example.dishdash.data.repo.meals.MealCategoryRepo;
import com.example.dishdash.presentation.presenter.meals.MealCategoryPresenterImpl;

import java.util.List;


public class AllCategoriesFragment extends Fragment implements MealCategoryView {

    RecyclerView recyclerView;
    AllCategoriesRecyclerViewAdapter adapter;
    MealCategoryPresenterImpl presenter;

    String key = "";
    String value = "";

    public AllCategoriesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoriesRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        presenter = new MealCategoryPresenterImpl(this, new MealCategoryRepo());
        AllCategoriesFragmentArgs args = AllCategoriesFragmentArgs.fromBundle(getArguments());
        key = args.getKey();
        value = args.getValue();
        presenter.getMealsByCategory(value);

        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMealsCategory(List<MealCategory> meals) {
        adapter = new AllCategoriesRecyclerViewAdapter(meals);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {

    }
}