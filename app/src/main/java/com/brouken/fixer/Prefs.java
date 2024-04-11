package com.brouken.fixer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

final class Prefs implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences mSharedPreferences;

    boolean pref_keyboard_switching = false;

    Prefs(final Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        loadSavedPreferences();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    void dePrefs() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void loadSavedPreferences() {
        pref_keyboard_switching = mSharedPreferences.getBoolean("pref_keyboard_switching", pref_keyboard_switching);
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String s) {
        if ("pref_keyboard_switching".equals(s))
            loadSavedPreferences();
    }

}
