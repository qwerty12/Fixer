package com.brouken.fixer;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

public class Utils {

    private static boolean hasWriteSecureSettingsPerm = false;

    public static void log(String text) {
        if (BuildConfig.DEBUG) {
            if (text != null)
                Log.d("Fixer", text);
        }
    }

    public static void changeIME(Context context, boolean temporaryIME) {
        // https://stackoverflow.com/questions/11036435/switch-keyboard-profile-programmatically
        // ime list -s
        // com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME
        // org.pocketworkstation.pckeyboard/.LatinIME

        // ime set com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME

        // settings get secure default_input_method
        // settings put secure default_input_method org.pocketworkstation.pckeyboard/.LatinIME

        //log("changeIME()");

        final String ime = !temporaryIME ? "com.touchtype.swiftkey/com.touchtype.KeyboardService" : "org.pocketworkstation.pckeyboard/.LatinIME";

        try {
            if (!hasWriteSecureSettingsPerm) {
                hasWriteSecureSettingsPerm = context.checkPermission(Manifest.permission.WRITE_SECURE_SETTINGS, android.os.Process.myPid(), android.os.Process.myUid()) == PackageManager.PERMISSION_GRANTED;
            }

            if (hasWriteSecureSettingsPerm) {
                Settings.Secure.putString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD, ime);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAccessibilitySettingsEnabled(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    context.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        final TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            final String settingValue = Settings.Secure.getString(
                    context.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    final String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.startsWith(context.getPackageName() + "/")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
