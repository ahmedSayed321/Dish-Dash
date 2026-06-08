package com.example.dishdash.auth.presentation.presenter;

public interface AuthPresenter {
    void signUp(String email, String password, String confirmPassword, String firstName, String lastName);

    void signIn(String email, String password);

    void googleSignIn(String Token);


}
