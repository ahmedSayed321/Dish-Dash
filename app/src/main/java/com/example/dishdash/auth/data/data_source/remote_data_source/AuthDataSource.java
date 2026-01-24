package com.example.dishdash.auth.data.data_source.remote_data_source;

import com.google.firebase.auth.AuthCredential;

public interface AuthDataSource {

    void signUp(String email, String password, String firstName, String lastName, AuthCallback callback);

    void signIn(String email, String password, AuthCallback callback);

    void signInWithGoogle(AuthCredential credential, AuthCallback callback);

    interface AuthCallback {
        void onSuccess();

        void onError(String message);
    }
}
