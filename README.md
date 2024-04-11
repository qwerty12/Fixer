# Fixer

Fixer a suite of tweaks that works around some features I find annoying.

**Why Fixer?**
*    No ads or tracking
*    It's free and open; Tasker is paid
*    Setting up Tasker can be exhausting and non trivial
*    Rework and improvement of existing solutions for maximum efficiency
*    All-in-1: Integrating all tweaks to one app is more efficient, single running service
*    Opportunity for me to learn more and be creative in finding work arounds

## Setup

1. Run following command on your computer with Android device connected. This one time setup requires enabled Developer mode & USB debugging. <br> ``adb shell pm grant com.brouken.fixer android.permission.WRITE_SECURE_SETTINGS`` <br> ``adb shell pm grant com.brouken.fixer android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER``

2. Enable Accessibility

WARNING: There are no checks for required permissions or runtime applying of changes so some features may require restart of a service or a device.

## Features

### Hacker's Keyboard only in Termux and Microsoft Remote Desktop
Description: Use [HeliBoard](https://github.com/Helium314/HeliBoard) as default IME, switch to Hacker's Keyboard in Termux and Microsoft Remote Desktop<br>
Requirements: Accessibility and WRITE_SECURE_SETTINGS permission<br>
Notes: Based on Tasker guide https://www.xda-developers.com/how-to-automatically-change-your-keyboard-on-a-per-app-basis/
