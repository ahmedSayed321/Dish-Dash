package com.example.dishdash.auth.data.data_source.local_data_source;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthLocalDataSource {

    private static final String PREF_NAME = "auth_pref";
    private static final String KEY_EMAIL = "user_email";

    private final SharedPreferences sharedPreferences;

    public AuthLocalDataSource(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserEmail(String email) {
        sharedPreferences.edit()
                .putString(KEY_EMAIL, email)
                .apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public boolean isUserLoggedIn() {
        return getUserEmail() != null;
    }

    public void clearUser() {
        sharedPreferences.edit().clear().apply();
    }
}
