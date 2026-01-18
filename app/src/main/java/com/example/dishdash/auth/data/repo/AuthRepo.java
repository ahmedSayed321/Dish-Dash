
package com.example.dishdash.auth.data.repo;

import com.example.dishdash.auth.data.data_source.AuthDataSource;
import com.example.dishdash.auth.data.data_source.AuthRemoteDataSource;
import com.google.firebase.auth.AuthCredential;

public class AuthRepo {

    private AuthDataSource remoteDataSource;

    public AuthRepo() {
        remoteDataSource = new AuthRemoteDataSource();
    }

    public void signUp(String email, String password,
                       String firstName, String lastName,
                       AuthCallback callback) {

        remoteDataSource.signUp(email, password, firstName, lastName,
                new AuthDataSource.AuthCallback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        callback.onError(message);
                    }
                });
    }

    public void signIn(String email, String password, AuthCallback callback) {
        remoteDataSource.signIn(email, password,
                new AuthDataSource.AuthCallback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        callback.onError(message);
                    }
                });
    }

    public void signInWithGoogle(AuthCredential credential, AuthCallback callback) {
        remoteDataSource.signInWithGoogle(credential,
                new AuthDataSource.AuthCallback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError(String message) {
                        callback.onError(message);
                    }
                });
    }

    public interface AuthCallback {
        void onSuccess();
        void onError(String message);
    }
}

