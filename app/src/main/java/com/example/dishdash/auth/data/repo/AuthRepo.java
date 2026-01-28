package com.example.dishdash.auth.data.repo;

import android.content.Context;

import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.auth.data.data_source.remote_data_source.AuthDataSource;
import com.example.dishdash.auth.data.data_source.remote_data_source.AuthRemoteDataSource;
import com.google.firebase.auth.AuthCredential;

public class AuthRepo {

    private final AuthDataSource remoteDataSource;
    private final AuthRemoteDataSource authRemoteDataSource;
    private final AuthLocalDataSource authLocalDataSource;

    public AuthRepo(Context context) {
        remoteDataSource = new AuthRemoteDataSource();
        authRemoteDataSource = new AuthRemoteDataSource();
        authLocalDataSource = new AuthLocalDataSource(context);

    }

    public void signUp(String email, String password,
                       String firstName, String lastName,
                       AuthCallback callback) {

        remoteDataSource.signUp(email, password, firstName, lastName,
                new AuthDataSource.AuthCallback() {
                    @Override
                    public void onSuccess(String uId) {
                        callback.onSuccess(uId);
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
                    public void onSuccess(String uId) {
                        callback.onSuccess(uId);
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
                    public void onSuccess(String uId) {
                        callback.onSuccess(uId);
                    }

                    @Override
                    public void onError(String message) {
                        callback.onError(message);
                    }
                });
    }

    public void getUserProfile(AuthDataSource.UserProfileCallback callback) {
        String usId = authLocalDataSource.getUserUid();

        authRemoteDataSource.getUserProfile(usId,
                new AuthDataSource.UserProfileCallback() {
                    @Override
                    public void onSuccess(String email, String firstName, String lastName) {
                        callback.onSuccess(email, firstName, lastName);
                    }

                    @Override
                    public void onError(String message) {
                        callback.onError(message);
                    }
                });
    }


    public interface AuthCallback {
        void onSuccess(String uId);

        void onError(String message);
    }
}

