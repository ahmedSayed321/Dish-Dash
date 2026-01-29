package com.example.dishdash.presentation.presenter.calender;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.model.meals.CalenderMeal;

import java.util.List;

public interface CalenderPresenter {

    void deleteCalenderMeal(CalenderMeal calenderMeal);

    LiveData<List<CalenderMeal>> getAllCalenderMeal(long start, long end);

}
