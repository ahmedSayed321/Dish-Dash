package com.example.dishdash.presentation.view.all_categories;

import android.annotation.SuppressLint;
import android.util.Log;
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
import com.example.dishdash.data.model.meals.MealCategory;

import java.util.List;

public class AllCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<AllCategoriesRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerView";
    private final List<MealCategory> categories;

    public AllCategoriesRecyclerViewAdapter(List<MealCategory> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_horizontal, parent, false);
        Log.i(TAG, "oncreate");

        return new ViewHolder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MealCategory currentCategory = categories.get(position);
        holder.txtTitle.setText(currentCategory.getMealName());
        Glide.with(holder.itemView)
                .load(currentCategory.getMealImage())
                .placeholder(R.raw.loading)
                .into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(view);
                AllCategoriesFragmentDirections.ActionAllCategoriesFragmentToCategoriesDetailsFragment2 action = AllCategoriesFragmentDirections.actionAllCategoriesFragmentToCategoriesDetailsFragment2("i", currentCategory.getMealId());
                navController.navigate(action);

//                NavController navController = Navigation.findNavController(view);
//                navController.navigate(R.id.action_allCategoriesFragment_to_categoriesDetailsFragment2);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imageView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.tvAllCategoryName);
            imageView = v.findViewById(R.id.imgAllCategory);
        }
    }
}