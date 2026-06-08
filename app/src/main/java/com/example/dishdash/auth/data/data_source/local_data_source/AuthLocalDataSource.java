package com.example.dishdash.auth.data.data_source.local_data_source;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthLocalDataSource {

    private static final String PREF_NAME = "auth_pref";
    private static final String KEY_UID = "user_uid";

    private static AuthLocalDataSource instance;

    private final SharedPreferences sharedPreferences;

    public AuthLocalDataSource(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized AuthLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new AuthLocalDataSource(context.getApplicationContext());
        }
        return instance;
    }

    public void saveUserUid(String uid) {
        sharedPreferences.edit()
                .putString(KEY_UID, uid)
                .apply();
    }

    public String getUserUid() {
        return sharedPreferences.getString(KEY_UID, null);
    }

    public boolean isUserLoggedIn() {
        return getUserUid() != null;
    }

    public void clearUser() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean isGuest() {
        return getUserUid() == null || getUserUid().isEmpty();
    }


}
