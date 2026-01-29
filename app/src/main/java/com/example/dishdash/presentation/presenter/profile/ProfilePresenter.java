package com.example.dishdash.presentation.presenter.profile;

import android.content.Context;

import com.example.dishdash.auth.data.data_source.remote_data_source.AuthDataSource;
import com.example.dishdash.auth.data.repo.AuthRepo;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.repo.meals.CalenderRepo;
import com.example.dishdash.data.repo.meals.remote.FirebaseCalenderRepository;
import com.example.dishdash.presentation.view.home.profile.ProfileView;

import java.util.List;

public class ProfilePresenter {

    private final AuthRepo authRepo;
    private final ProfileView view;
    private final FirebaseCalenderRepository firebaseCalenderRepo;
    private final CalenderRepo calenderRepo;

    public ProfilePresenter(ProfileView view, Context context) {
        this.view = view;
        authRepo = new AuthRepo(context);
        firebaseCalenderRepo = new FirebaseCalenderRepository();
        calenderRepo = new CalenderRepo(context);

    }

    public void loadUserProfile() {

        authRepo.getUserProfile(new AuthDataSource.UserProfileCallback() {
            @Override
            public void onSuccess(String email, String firstName, String lastName) {
                view.showUserName(firstName + " " + lastName);
                view.showUserEmail(email);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    public void uploadCalenderMeals(String userId, List<CalenderMeal> meals) {
        view.showLoading(true);

        firebaseCalenderRepo.uploadCalenderMeals(userId, meals,
                () -> {
                },
                error -> {
                }
//                () -> view.showSuccess("Calendar synced successfully"),
//                error -> view.showError("Failed to sync calendar: " + error)
        );
    }


    public void uploadLocalCalenderMeals(String userId) {
        view.showLoading(true);

        new Thread(() -> {
            List<CalenderMeal> meals = calenderRepo.getAllCalenderMealsOnce();

            firebaseCalenderRepo.uploadCalenderMeals(userId, meals,
                    () -> {
                        android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                        mainHandler.post(() -> {
                            view.showLoading(false);
                            //view.showSuccess("Calendar synced successfully");
                        });
                    },
                    error -> {
                        android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                        mainHandler.post(() -> {
                            view.showLoading(false);
                            view.showError("Failed to sync calendar: " + error);
                        });
                    }
            );
        }).start();
    }


}
