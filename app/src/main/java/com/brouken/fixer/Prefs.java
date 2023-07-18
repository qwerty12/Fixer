package com.brouken.fixer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

    SharedPreferences mSharedPreferences;

    private boolean pref_keyboard_switching = false;

    public Prefs(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        pref_keyboard_switching = mSharedPreferences.getBoolean("pref_keyboard_switching", pref_keyboard_switching);
    }

    public boolean isKeyboardSwitchingEnabled() {
        return pref_keyboard_switching;
    }
}
