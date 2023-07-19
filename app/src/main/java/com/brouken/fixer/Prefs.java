package com.brouken.fixer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences mSharedPreferences;

    private boolean pref_keyboard_switching = false;

    public Prefs(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        loadSavedPreferences();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    protected void dePrefs() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void loadSavedPreferences() {
        pref_keyboard_switching = mSharedPreferences.getBoolean("pref_keyboard_switching", pref_keyboard_switching);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals("pref_keyboard_switching")) {
            loadSavedPreferences();
        }
    }

    public boolean isKeyboardSwitchingEnabled() {
        return pref_keyboard_switching;
    }
}
