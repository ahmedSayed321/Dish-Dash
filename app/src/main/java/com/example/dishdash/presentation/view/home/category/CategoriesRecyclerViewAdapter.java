package com.example.dishdash.presentation.view.home.category;

import android.content.Context;
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
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.Category;
import com.example.dishdash.presentation.view.home.HomeFragmentDirections;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewHolder> {

    private final Context context;
    private final List<Category> categories;

    public CategoriesRecyclerViewAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        CategoriesRecyclerViewHolder viewHolder = new CategoriesRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRecyclerViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryTextView.setText(category.getCategoryName());
        Glide.with(context)
                .load(category.getCategoryImage())
                .into(holder.categoryImageView);
        holder.converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, category.getCategoryId(), Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(view);
                HomeFragmentDirections.ActionHomeFragmentToAllCategoriesFragment action =
                        HomeFragmentDirections.actionHomeFragmentToAllCategoriesFragment("c", category.getCategoryName());
                navController.navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}

class CategoriesRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView categoryImageView;
    TextView categoryTextView;
    MaterialCardView converter;

    public CategoriesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        converter = (MaterialCardView) itemView;
        categoryImageView = itemView.findViewById(R.id.categoryImage);
        categoryTextView = itemView.findViewById(R.id.categoryName);


    }
}
