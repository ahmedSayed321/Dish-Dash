package com.example.dishdash.auth.presentation.view;

public interface AuthView {
    void showLoading();
    void hideLoading();

    void showEmailError(String message);
    void showPasswordError(String message);
    void showConfirmPasswordError(String message);

    void showFirstNameError(String message);
    void showLastNameError(String message);
    void onAuthSuccess();
    void onAuthError(String message);
}
