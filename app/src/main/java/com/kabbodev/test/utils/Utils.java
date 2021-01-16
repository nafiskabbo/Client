package com.kabbodev.test.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Ok", v -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

}