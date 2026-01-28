package com.example.dishdash.utilites;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MySnackBar {

    private MySnackBar() {
    }

    public static void showSuccess(View view, String msg) {
        if (view == null) return;

        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.parseColor("#20DFBF"));
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void showError(View view, String msg) {
        if (view == null) return;

        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.RED);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void showGeneral(View view, String msg) {
        if (view == null) return;
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}