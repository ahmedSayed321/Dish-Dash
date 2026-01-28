package com.example.dishdash.presentation.presenter.profile;

import android.content.Context;

import com.example.dishdash.auth.data.data_source.remote_data_source.AuthDataSource;
import com.example.dishdash.auth.data.repo.AuthRepo;
import com.example.dishdash.presentation.view.home.profile.ProfileView;

public class ProfilePresenter {

    private final AuthRepo authRepo;
    private final ProfileView view;

    public ProfilePresenter(ProfileView view, Context context) {
        this.view = view;
        authRepo = new AuthRepo(context);
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
}
