# Fixer

Fixer a suite of tweaks that works around some features I find annoying.

**Why Fixer**?
*    No ads or tracking
*    It's free and open; Tasker is paid
*    Setting up Tasker can be exhausting and non trivial
*    Rework of existing solutions for maximum efficiency
*    All-in-1: Integrating all tweaks to one app is more efficient, single running service
*    Opportunity for me to learn more and be creative in finding work arounds

##Setup

Root access (or Xposed) is not required, it's just an alternative.

1. ``adb shell pm grant com.brouken.fixer android.permission.WRITE_SECURE_SETTINGS`` (or allow root access)
2. Enable Accessibility

##Features

###No safe volume warning
Description: <br>
Requirements: WRITE_SECURE_SETTINGS permission or root<br>
Notes: Based on Tasker guide https://www.xda-developers.com/how-to-automatically-disable-the-high-volume-warning-without-root/

###Media volume by default
Description: <br>
Requirements: Accessibility<br>
Notes: Based on https://github.com/KrongKrongPadakPadak/mvo; Unsure Android P compatibility because of the use of non-public API however P already has media as default stream

###Hacker's Keyboard only in Termux
Description: Use Gboard as default IME, switch to Hacker's Keyboard in Termux<br>
Requirements: Accessibility and (WRITE_SECURE_SETTINGS permission or root)<br>
Notes: Based on Tasker guide https://www.xda-developers.com/how-to-automatically-change-your-keyboard-on-a-per-app-basis/

###No location dialog in GM
Description: No more "To continue, let your device turn on location using Google's Location Service" in Google Maps<br>
Requirements: Accessibility<br>
Notes: Simulates tapping Cancel button. It uses resource id instead of text so it doesn't depend on interface language.

###Samsung: No LED in DnD
Description: Disable LED in Do not disturb mode<br>
Requirements: None (APK is already set to ``targetSdkVersion 22`` to allow system automatically granting WRITE_SETTINGS)<br>
Notes: Based on Tasker plugin: https://www.apkmonk.com/app/com.tinyroar.galaxys3ledcontroller/

###Samsung: No BT/WiFi popups
Description: <br>
Requirements: Accessibility<br>
Notes:

###Shortcut: SIP settings
Description: Brings up system SIP/VoIP configuration screen that is often hidden but functional.<br>
Requirements: None<br>
Notes:

###Shortcut: Radio (Device info)
Description: Set often unavailable network modes like "LTE only" or "LTE/WCDMA" (4G/3G without 2G)<br>
Requirements: None<br>
Notes: Because Samsung blocks secret codes (``*#*#4646#*#*``)...
