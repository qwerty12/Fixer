package com.brouken.fixer;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class MonitorService extends AccessibilityService {

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
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        //log("onAccessibilityEvent()");
        //log(accessibilityEvent.toString());

        if (!mPrefs.isKeyboardSwitchingEnabled()) {
            setHk = false;
            return;
        }

        if (accessibilityEvent.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            return;

        final String rootPackageName = getRootInActiveWindow().getPackageName().toString();
        if (rootPackageName.equals("com.termux") || rootPackageName.equals("com.microsoft.rdc.androidx")) {
            if (!setHk)
                setHk = Utils.changeIME(getApplicationContext(), true);
            return;
        }

        if (setHk) {
            final String accessibilityEventPackageName = accessibilityEvent.getPackageName().toString();
            if (accessibilityEventPackageName.equals("org.pocketworkstation.pckeyboard")) {
                Utils.changeIME(getApplicationContext(), false);
                setHk = false;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

}
