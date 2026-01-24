package com.example.dishdash.presentation.view.home.meals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.Ingredient;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.presentation.presenter.meals.MealDetailsPresenter;
import com.example.dishdash.presentation.presenter.meals.MealDetailsPresenterImpl;
import com.example.dishdash.presentation.view.all_categories.AllCategoriesFragmentArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class CategoriesDetailsFragment extends Fragment implements MealDetailsView {

    RecyclerView rvIngredients;
    IngredientsAdapter ingredientsAdapter;
    List<Ingredient> ingredientList;
    String key = "";
    String mealId = "";
    MealDetailsPresenter presenter;
    TextView mealName, mealInstructions, mealArea, mealCategory;
    ImageView mealImage;
    YouTubePlayerView youTubePlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meals_details_item, container, false);

        mealName = view.findViewById(R.id.tvMealName);
        mealInstructions = view.findViewById(R.id.tvInstructions);
        mealArea = view.findViewById(R.id.tvArea);
        mealCategory = view.findViewById(R.id.tvCategory);
        mealImage = view.findViewById(R.id.imgMealDetail);
        rvIngredients = view.findViewById(R.id.rvIngredients);
        youTubePlayer = view.findViewById(R.id.youtubePlayerView);

        getLifecycle().addObserver(youTubePlayer);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvIngredients.setLayoutManager(layoutManager);

        AllCategoriesFragmentArgs args = AllCategoriesFragmentArgs.fromBundle(getArguments());
        key = args.getKey();
        mealId = args.getValue();

        presenter = new MealDetailsPresenterImpl(this);
        presenter.getMealDetails(mealId);

        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {

        mealName.setText(meal.name);
        mealInstructions.setText(meal.instructions);
        mealArea.setText("Area: " + meal.area);
        mealCategory.setText("Category: " + meal.category);
        String imageurl = meal.thumbnail;
        Glide.with(this)
                .load(imageurl)
                .placeholder(R.raw.loading)
                .into(mealImage);

        if (meal.youtubeLink != null && !meal.youtubeLink.isEmpty()) {
            String videoId = extractYoutubeVideoId(meal.youtubeLink);
            if (videoId != null) {
                youTubePlayer.addYouTubePlayerListener(
                        new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                youTubePlayer.loadVideo(videoId, 0);
                            }
                        }
                );
            }
        }

        ingredientList = meal.getIngredients();
        ingredientsAdapter = new IngredientsAdapter(ingredientList);
        rvIngredients.setAdapter(ingredientsAdapter);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String message) {
    }

    private String extractYoutubeVideoId(String youtubeUrl) {
        if (youtubeUrl.contains("v=")) {
            String[] parts = youtubeUrl.split("v=");
            if (parts.length > 1) {
                String videoId = parts[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    return videoId.substring(0, ampersandPosition);
                }
                return videoId;
            }
        }
        return null;
    }
}
