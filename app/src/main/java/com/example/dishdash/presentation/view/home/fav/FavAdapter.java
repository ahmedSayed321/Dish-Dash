package com.example.dishdash.presentation.view.home.fav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.FavFragmentDirections;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.presentation.presenter.favourite.FavPresenter;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    private final FavPresenter favPresenter;
    private List<FavoriteMealEntity> meals = new ArrayList<>();

    public FavAdapter(FavPresenter presenter) {
        this.favPresenter = presenter;
    }

    public void setMeals(List<FavoriteMealEntity> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        FavoriteMealEntity meal = meals.get(position);
        holder.tvMealName.setText(meal.name);
        Glide.with(holder.itemView.getContext())
                .load(meal.thumbnail)
                .into(holder.ivMealThumb);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                FavFragmentDirections.ActionFavFragmentToCategoriesDetailsFragment2 action = FavFragmentDirections.actionFavFragmentToCategoriesDetailsFragment2("i", meal.id);
                navController.navigate(action);
            }
        });
        holder.ivDelete.setOnClickListener(v -> {
            favPresenter.removeFromFavourite(meal);

        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {

        TextView tvMealName;
        ImageView ivMealThumb, ivDelete;
        View v;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            tvMealName = itemView.findViewById(R.id.tvMealName);
            ivMealThumb = itemView.findViewById(R.id.ivMealThumb);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
