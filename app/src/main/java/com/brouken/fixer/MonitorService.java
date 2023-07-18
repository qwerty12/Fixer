package com.brouken.fixer;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.ArrayList;
import java.util.List;

import static com.brouken.fixer.Utils.log;

public class MonitorService extends AccessibilityService {

    private Prefs mPrefs;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        mPrefs = new Prefs(this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        //log("onAccessibilityEvent()");
        //log(accessibilityEvent.toString());

        if (mPrefs.isKeyboardSwitchingEnabled()) {
            if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                final String accessibilityEventPackageName = (String) accessibilityEvent.getPackageName();

                final List<String> visibleApps = new ArrayList<>();

                List<AccessibilityWindowInfo> accessibilityWindowInfos = getWindows();
                //log("windows=" + accessibilityWindowInfos.size());
                for (AccessibilityWindowInfo accessibilityWindowInfo : accessibilityWindowInfos) {
                    //log(accessibilityWindowInfo.toString());

                    AccessibilityNodeInfo accessibilityNodeInfo = accessibilityWindowInfo.getRoot();
                    if (accessibilityNodeInfo != null) {
                        visibleApps.add(accessibilityNodeInfo.getPackageName().toString());
                        accessibilityNodeInfo.recycle();
                    }
                }

                if (visibleApps.contains("com.termux") || visibleApps.contains("com.microsoft.rdc.androidx")) {
                    Utils.changeIME(getApplicationContext(), true);
                    return;
                }

                if (accessibilityEventPackageName.equals("org.pocketworkstation.pckeyboard")) {
                    Utils.changeIME(getApplicationContext(), false);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("onStartCommand");

        mPrefs = new Prefs(this);

        return super.onStartCommand(intent, flags, startId);
    }

}
