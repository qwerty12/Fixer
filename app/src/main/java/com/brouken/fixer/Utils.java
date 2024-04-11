package com.brouken.fixer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

public class Utils {

    private static boolean hasWriteSecureSettingsPerm = false;

    public static void log(final String text) {
        if (BuildConfig.DEBUG) {
            if (text != null)
                Log.d("Fixer", text);
        }
    }

    public static boolean changeIME(final Context context, final boolean temporaryIME) {
        // https://stackoverflow.com/questions/11036435/switch-keyboard-profile-programmatically
        // ime list -s
        // com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME
        // org.pocketworkstation.pckeyboard/.LatinIME

        // ime set com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME

        // settings get secure default_input_method
        // settings put secure default_input_method org.pocketworkstation.pckeyboard/.LatinIME

        //log("changeIME()");

        boolean ret = false;
        final String ime = !temporaryIME ? "helium314.keyboard/.latin.LatinIME" : "org.pocketworkstation.pckeyboard/.LatinIME";

        try {
            if (!hasWriteSecureSettingsPerm) {
                hasWriteSecureSettingsPerm = context.checkPermission(Manifest.permission.WRITE_SECURE_SETTINGS, android.os.Process.myPid(), android.os.Process.myUid()) == PackageManager.PERMISSION_GRANTED;
            }

            if (hasWriteSecureSettingsPerm) {
                ret = Settings.Secure.putString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD, ime);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
