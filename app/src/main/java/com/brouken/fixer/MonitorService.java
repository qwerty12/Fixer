package com.brouken.fixer;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public final class MonitorService extends AccessibilityService {

    private Prefs mPrefs = null;
    private boolean setHk;

    @Override
    public void onCreate() {
        super.onCreate();
        setHk = false;
        mPrefs = new Prefs(this);
    }

    @Override
    public void onDestroy() {
        if (mPrefs != null) {
            mPrefs.dePrefs();
            mPrefs = null;
        }
        super.onDestroy();
    }

    @Override
    public void onAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        //log("onAccessibilityEvent()");
        //log(accessibilityEvent.toString());

        if (!mPrefs.pref_keyboard_switching) {
            setHk = false;
            return;
        }

        if (accessibilityEvent == null || accessibilityEvent.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            return;

        final AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow != null) {
            final CharSequence rootPackageName = rootInActiveWindow.getPackageName();
            if (rootPackageName != null) {
                if ("com.termux".contentEquals(rootPackageName) || "com.microsoft.rdc.androidx".contentEquals(rootPackageName)) {
                    if (!setHk)
                        setHk = Utils.changeIME(getApplicationContext(), true);
                    return;
                }
            }
        }

        if (setHk) {
            final CharSequence packageName = accessibilityEvent.getPackageName();
            if (packageName != null) {
                if ("org.pocketworkstation.pckeyboard".contentEquals(packageName)) {
                    setHk = false;
                    Utils.changeIME(getApplicationContext(), false);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

}
