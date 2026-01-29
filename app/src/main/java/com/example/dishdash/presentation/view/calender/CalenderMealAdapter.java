package com.example.dishdash.presentation.view.calender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.presentation.presenter.calender.CalenderPresenter;

import java.util.ArrayList;
import java.util.List;


public class CalenderMealAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final CalenderPresenter calenderPresenter;
    private List<CalenderMeal> calenderMeals = new ArrayList<>();

    public CalenderMealAdapter(CalenderPresenter calenderPresenter) {
        this.calenderPresenter = calenderPresenter;
    }

    public void setCalenderMeals(List<CalenderMeal> meals) {
        this.calenderMeals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalenderMeal calenderMealItem = calenderMeals.get(position);
        holder.txtTitle.setText(calenderMealItem.getMealName());
        Glide.with(holder.itemView.getContext())
                .load(calenderMealItem.getMealImage())
                .into(holder.imageView);

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calenderPresenter.deleteCalenderMeal(calenderMealItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calenderMeals.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder {
    View layout;
    TextView txtTitle;
    ImageView imageView, deleteIcon;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView;
        txtTitle = itemView.findViewById(R.id.tvMealNameCalender);
        imageView = itemView.findViewById(R.id.ivMealThumbCalender);
        deleteIcon = itemView.findViewById(R.id.ivDelete);

    }
}