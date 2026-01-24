//package com.example.dishdash;
//
//import static android.view.View.GONE;
//import static android.view.View.VISIBLE;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//
//import com.example.dishdash.data.repo.meals.CategoryRepo;
//import com.example.dishdash.data.repo.meals.CategoryRepoImpl;
//import com.example.dishdash.presentation.presenter.category.CategoryPresenter;
//import com.example.dishdash.presentation.presenter.category.CategoryPresenterImpl;
//import com.example.dishdash.presentation.view.home.category.CategoryView;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.List;
//
//
//public class HomeFragment extends Fragment implements CategoryView {
//
//
//    RecyclerView recyclerView ;
//    CategoryRepoImpl categoryRepo;
//    ProgressBar progressBar;
//    CategoryPresenter presenter;
//
//
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        recyclerView = view.findViewById(R.id.horizontalRecycler);
//
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//
//        recyclerView.setLayoutManager(layoutManager);
//        categoryRepo = new CategoryRepoImpl();
//        progressBar = view.findViewById(R.id.loadingAnimation2);
//        presenter = new CategoryPresenterImpl(categoryRepo,this);
//
//        Category [] category ={
//                new Category("1","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("2","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("3","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("4","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("5","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("6","Pizza","https://www.themealdb.com/images/category/beef.png"),
//                new Category("7","Pizza","https://www.themealdb.com/images/category/beef.png"),
//        };
//
//        CategoriesRecyclerViewAdapter adapter = new CategoriesRecyclerViewAdapter(getContext(),category);
//        recyclerView.setAdapter(adapter);
//
//        presenter.getCategories();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//
//    @Override
//    public void showLoading() {
//        progressBar.setVisibility(VISIBLE);
//    }
//
//    @Override
//    public void hideLoading() {
//       progressBar.setVisibility(GONE);
//    }
//
//    @Override
//    public void showCategories(List<Category> categories) {
//        Category[] categoriesArray = new Category[categories.size()];
//        categoriesArray = categories.toArray(categoriesArray);
//
//        adapter = new CategoriesRecyclerViewAdapter(getContext(), categoriesArray);
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void showError(String message) {
//        Snackbar.make(recyclerView,"Sorry Something went wrong",Snackbar.LENGTH_LONG).show();
//    }
//}

package com.example.dishdash.presentation.view.home;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.Category;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.data.repo.meals.CategoryRepoImpl;
import com.example.dishdash.data.repo.meals.RandomMealRepo;
import com.example.dishdash.presentation.presenter.category.CategoryPresenterImpl;
import com.example.dishdash.presentation.presenter.random.RandomMealPresenterImpl;
import com.example.dishdash.presentation.view.home.category.CategoriesRecyclerViewAdapter;
import com.example.dishdash.presentation.view.home.category.CategoryView;
import com.example.dishdash.presentation.view.home.random.RandomMealView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HomeFragment extends Fragment implements CategoryView, RandomMealView {

    CardView cardView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CategoriesRecyclerViewAdapter adapter;
    private CategoryPresenterImpl presenter;
    private RandomMealPresenterImpl randomMealPresenter;
    private LottieAnimationView loadingAnimation;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.horizontalRecycler);
        loadingAnimation = view.findViewById(R.id.loadingAnimation2);
        cardView = view.findViewById(R.id.imageCardView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        presenter = new CategoryPresenterImpl(new CategoryRepoImpl(), this);

        randomMealPresenter = new RandomMealPresenterImpl(new RandomMealRepo(), this);
        presenter.getCategories();
        randomMealPresenter.getRandomMeal();
    }

    @Override
    public void showLoading() {
        loadingAnimation.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingAnimation.setVisibility(View.GONE);
    }

    @Override
    public void showRandomMeals(List<Meal> meals) {
        Meal meal = meals.get(0);
        TextView text = cardView.findViewById(R.id.cardText);
        text.setText(meal.name);
        ImageView imageView = cardView.findViewById(R.id.cardImage);
        Glide.with(getContext())
                .load(meal.thumbnail)
                .into(imageView);
    }

    @Override
    public void showCatLoading() {
        loadingAnimation.setVisibility(VISIBLE);

    }

    @Override
    public void hideCatLoading() {
        loadingAnimation.setVisibility(View.GONE);

    }

    @Override
    public void showCategories(List<Category> categories) {
        hideLoading();

        if (categories == null || categories.isEmpty()) return;

        adapter = new CategoriesRecyclerViewAdapter(getContext(), categories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        hideLoading();
        Snackbar.make(recyclerView, message != null ? message : "Sorry, Something went wrong", Snackbar.LENGTH_LONG).show();
    }

}
