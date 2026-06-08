package com.example.dishdash.presentation.presenter.calender;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.repo.meals.CalenderRepo;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPresenterImpl implements CalenderPresenter {

    private final CalenderRepo calenderRepo;

    public CalenderPresenterImpl(Context context) {
        calenderRepo = new CalenderRepo(context);
    }

    @Override
    public void deleteCalenderMeal(CalenderMeal calenderMeal) {
        calenderRepo.deleteCalenderMeal(calenderMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


//    public @NonNull Disposable getAllCalenderMeal(long start, long end) {
//        return  calenderRepo.getAllCalenderMeals(start, end)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//    }

    @Override
    public LiveData<List<CalenderMeal>> getAllCalenderMeal(long start, long end) {
        return calenderRepo.getAllCalenderMeals(start, end);
    }
}
