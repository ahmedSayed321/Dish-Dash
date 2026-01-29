package com.example.dishdash.auth.presentation.presenter;

import android.content.Context;

import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.auth.data.repo.AuthRepo;
import com.example.dishdash.auth.presentation.view.AuthView;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.repo.meals.CalenderRepo;
import com.example.dishdash.data.repo.meals.remote.FirebaseCalenderRepository;
import com.example.dishdash.utilites.ValidationUtils;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthPresenterImpl implements AuthPresenter {

    private final AuthView authView;
    private final AuthRepo authRepo;
    private final AuthLocalDataSource authLocalDataSource;
    private final FirebaseCalenderRepository firebaseCalenderRepo;
    private final CalenderRepo calenderRepo; // <-- ضفتها هنا


    public AuthPresenterImpl(AuthView authView, Context context) {
        this.authView = authView;
        authRepo = new AuthRepo(context);
        authLocalDataSource = new AuthLocalDataSource(context);
        firebaseCalenderRepo = new FirebaseCalenderRepository();
        calenderRepo = new CalenderRepo(context);
    }

    @Override
    public void signUp(String email, String password, String confirmPassword,
                       String firstName, String lastName) {

        if (firstName == null || firstName.trim().isEmpty()) {
            authView.showFirstNameError("First name is required");
            return;
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            authView.showLastNameError("Last name is required");
            return;
        }

        if (!ValidationUtils.isValidEmail(email)) {
            authView.showEmailError("Invalid Email");
            return;
        }

        if (!ValidationUtils.isValidPassword(password)) {
            authView.showPasswordError("Password too weak");
            return;
        }

        if (!ValidationUtils.isPasswordMatching(password, confirmPassword)) {
            authView.showConfirmPasswordError("Passwords do not match");
            return;
        }

        authView.showLoading();

        authRepo.signUp(email, password, firstName, lastName,
                new AuthRepo.AuthCallback() {
                    @Override
                    public void onSuccess(String uId) {
                        authLocalDataSource.saveUserUid(uId);
                        authView.hideLoading();
                        authView.onAuthSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        authView.hideLoading();
                        authView.onAuthError(message);
                    }
                });
    }

    @Override
    public void signIn(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            authView.onAuthError("Email and password required");
            return;
        }

        authView.showLoading();

        authRepo.signIn(email, password,
                new AuthRepo.AuthCallback() {
                    @Override
                    public void onSuccess(String uId) {
                        authLocalDataSource.saveUserUid(uId);
                        authView.hideLoading();
                        authView.onAuthSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        authView.hideLoading();
                        authView.onAuthError(message);
                    }
                });
    }

    @Override
    public void googleSignIn(String idToken) {

        if (idToken == null || idToken.isEmpty()) {
            authView.onAuthError("Google token is invalid");
            return;
        }

        authView.showLoading();

        AuthCredential credential =
                GoogleAuthProvider.getCredential(idToken, null);

        authRepo.signInWithGoogle(credential,
                new AuthRepo.AuthCallback() {
                    @Override
                    public void onSuccess(String uId) {
                        authLocalDataSource.saveUserUid(uId);
                        authView.hideLoading();
                        authView.onAuthSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        authView.hideLoading();
                        authView.onAuthError(message);
                    }
                });
    }

    public AuthLocalDataSource getAuthLocalDataSource() {
        return authLocalDataSource;
    }


    public void downloadAndSaveCalenderMeals(String userId) {
        authView.showLoading();

        firebaseCalenderRepo.downloadCalenderMeals(userId,
                meals -> {
                    new Thread(() -> {
                        for (CalenderMeal meal : meals) {
                            calenderRepo.insertCalenderMeal(meal);
                        }

                        android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                        mainHandler.post(() -> {
                            authView.showCalendarMeals(meals);
                            authView.hideLoading();
                        });

                    }).start();

                },
                error -> {
                    android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                    mainHandler.post(() -> {
                        authView.hideLoading();
                    });
                });
    }


}

