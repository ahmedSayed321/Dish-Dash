package com.example.dishdash.presentation.view.home.meals;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.model.meals.Ingredient;
import com.example.dishdash.data.model.meals.Meal;
import com.example.dishdash.presentation.presenter.meals.MealDetailsPresenter;
import com.example.dishdash.presentation.presenter.meals.MealDetailsPresenterImpl;
import com.example.dishdash.presentation.view.all_categories.AllCategoriesFragmentArgs;
import com.example.dishdash.utilites.MySnackBar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MealDetailsFragment extends Fragment implements MealDetailsView {

    RecyclerView rvIngredients;
    IngredientsAdapter ingredientsAdapter;
    List<Ingredient> ingredientList;
    MealDetailsPresenter presenter;
    TextView mealName, mealInstructions, mealArea, mealCategory;
    ImageView mealImage;
    ImageView favBtn, calenderBtn;
    boolean isFav = false;
    CalenderMeal calenderMealDto;

    YouTubePlayerView youTubePlayer;

    String key = "";
    String mealId = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meals_details_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mealName = view.findViewById(R.id.tvMealName);
        mealInstructions = view.findViewById(R.id.tvInstructions);
        mealArea = view.findViewById(R.id.tvArea);
        mealCategory = view.findViewById(R.id.tvCategory);
        mealImage = view.findViewById(R.id.imgMealDetail);
        rvIngredients = view.findViewById(R.id.rvIngredients);
        youTubePlayer = view.findViewById(R.id.youtubePlayerView);
        favBtn = view.findViewById(R.id.ic_fav);
        calenderBtn = view.findViewById(R.id.calenderImageView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvIngredients.setLayoutManager(layoutManager);

        AllCategoriesFragmentArgs args = AllCategoriesFragmentArgs.fromBundle(getArguments());
        key = args.getKey();
        mealId = args.getValue();

        presenter = new MealDetailsPresenterImpl(this, requireContext());
        presenter.getMealDetails(mealId);
        presenter.isFav(mealId);

        calenderBtn.setOnClickListener(v -> showDatePicker());

        getLifecycle().addObserver(youTubePlayer);
    }

    @Override
    public void showMealDetails(Meal meal) {

        mealName.setText(meal.name);
        mealInstructions.setText(meal.instructions);
        mealArea.setText("Area: " + meal.area);
        mealCategory.setText("Category: " + meal.category);
        Glide.with(this).load(meal.thumbnail).placeholder(R.raw.loading).into(mealImage);

        calenderMealDto = new CalenderMeal(
                meal.name + "_" + System.currentTimeMillis(), // idMeal فريد
                meal.name,
                meal.category,
                meal.area,
                meal.thumbnail,
                0
        );

        if (meal.youtubeLink != null && !meal.youtubeLink.isEmpty()) {
            String videoId = extractYoutubeVideoId(meal.youtubeLink);
            if (videoId != null) {
                youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }

        ingredientList = meal.getIngredients();
        ingredientsAdapter = new IngredientsAdapter(ingredientList);
        rvIngredients.setAdapter(ingredientsAdapter);

        favBtn.setOnClickListener(btnView -> {
            if (!isFav) {
                presenter.addToFav(meal);
                MySnackBar.showSuccess(getView(), "Meal Added Successfully");
                showFavoriteState(true);
            } else {
                presenter.removeFromFav(meal);
                MySnackBar.showSuccess(getView(), "Meal Removed Successfully");
                showFavoriteState(false);
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String selectedDate = dateFormat.format(selectedCalendar.getTime());

                    saveMealPlan(selectedDate, selectedCalendar.getTimeInMillis());
                },
                year, month, day
        );

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void saveMealPlan(String date, long timestamp) {
        if (calenderMealDto != null) {
            calenderMealDto.setTimestamp(timestamp);
            presenter.addCalenderMeal(calenderMealDto);

            Toast.makeText(getContext(), "Meal scheduled for: " + date, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error: Meal data not loaded yet!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFavoriteState(boolean isFavorite) {
        this.isFav = isFavorite;
        favBtn.setImageResource(isFavorite ? R.drawable.fav_selected : R.drawable.fav_unselected);
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
                return (ampersandPosition != -1) ? videoId.substring(0, ampersandPosition) : videoId;
            }
        }
        return null;
    }
}
