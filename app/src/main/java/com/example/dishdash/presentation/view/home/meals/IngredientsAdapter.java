package com.example.dishdash.presentation.view.home.meals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsHolder> {
    List<Ingredient> ingredients;

    public IngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grediant, parent, false);

        return new IngredientsHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder holder, int position) {

        final Ingredient currentIngredient = ingredients.get(position);
        holder.ingredientName.setText(currentIngredient.getName());
        holder.ingredientMeasure.setText(currentIngredient.getMeasure());
        String imageurl = "https://www.themealdb.com/images/ingredients/" + currentIngredient.getName() + "-Small.png";
        Glide.with(holder.itemView)
                .load(imageurl)
                .placeholder(R.raw.loading)
                .into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients != null ? ingredients.size() : 0;
    }
}


class IngredientsHolder extends RecyclerView.ViewHolder {
    public TextView ingredientName;
    public TextView ingredientMeasure;
    public ImageView imageView;
    public View layout;

    public IngredientsHolder(View v) {
        super(v);
        layout = v;
        ingredientName = v.findViewById(R.id.tvIngredientName);
        ingredientMeasure = v.findViewById(R.id.tvIngredientMeasure);
        imageView = v.findViewById(R.id.imgIngredient);
    }
}
