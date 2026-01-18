package com.example.dishdash.auth.data.data_source;

import com.google.firebase.auth.AuthCredential;

public interface AuthDataSource {

    interface AuthCallback {
        void onSuccess();
        void onError(String message);
    }

    void signUp(String email, String password, String firstName, String lastName, AuthCallback callback);

    void signIn(String email, String password, AuthCallback callback);

    void signInWithGoogle(AuthCredential credential, AuthCallback callback);
}
