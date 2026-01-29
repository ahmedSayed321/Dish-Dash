package com.example.dishdash.presentation.view.calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.presentation.presenter.calender.CalenderPresenter;
import com.example.dishdash.presentation.presenter.calender.CalenderPresenterImpl;

import java.util.Calendar;
import java.util.List;

public class CalenderMealFragment extends Fragment implements CalenderMealView {
    private RecyclerView recyclerView;
    private CalenderMealAdapter adapter;
    private CalenderPresenter presenter;
    private CalendarView calenderView3;

    public CalenderMealFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new CalenderPresenterImpl(requireContext());
        adapter = new CalenderMealAdapter(presenter);

        recyclerView = view.findViewById(R.id.mealsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        calenderView3 = view.findViewById(R.id.calendarView3);

        Calendar today = Calendar.getInstance();
        loadMealsForDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));

        calenderView3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                loadMealsForDate(year, month, dayOfMonth);
            }
        });
    }

    private void loadMealsForDate(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth, 0, 0, 0);
        long startDay = cal.getTimeInMillis();

        cal.set(year, month, dayOfMonth, 23, 59, 59);
        long endDay = cal.getTimeInMillis();

        presenter.getAllCalenderMeal(startDay, endDay).observe(getViewLifecycleOwner(), new Observer<List<CalenderMeal>>() {
            @Override
            public void onChanged(List<CalenderMeal> calenderMeals) {
                adapter.setCalenderMeals(calenderMeals);
            }
        });
    }

    @Override
    public void showCalenderMeals(List<CalenderMeal> calenderMeals) {
        adapter.setCalenderMeals(calenderMeals);
    }
}
