package com.example.dishdash.presentation.view.home.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.MealCategory;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<MealCategory> meals = new ArrayList<>();

    public void setMeals(List<MealCategory> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_horizontal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealCategory meal = meals.get(position);
        holder.tvMealName.setText(meal.getMealName());

        Glide.with(holder.itemView.getContext())
                .load(meal.getMealImage())
                .into(holder.imgMeal);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.tvMealName.toString(), Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(view);
                SearchFragmentDirections.ActionSearchFragmentToCategoriesDetailsFragment2 action = SearchFragmentDirections.actionSearchFragmentToCategoriesDetailsFragment2("i", meal.getMealId());
                navController.navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMeal;
        TextView tvMealName;
        View layout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            imgMeal = itemView.findViewById(R.id.imgAllCategory);
            tvMealName = itemView.findViewById(R.id.tvAllCategoryName);
        }
    }
}
