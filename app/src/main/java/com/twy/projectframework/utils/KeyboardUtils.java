package com.twy.projectframework.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS;
import static android.view.inputmethod.InputMethodManager.SHOW_FORCED;

public class KeyboardUtils {

    /**
     * Hide soft input method manager
     *
     * @param view
     * @return view
     */
    public static View hideSoftInput(final View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return view;
    }

    /**
     * <p>键盘隐藏/显示切换</p>
     */
    public static void hideSoftInputNotAlways(final View view) {
        InputMethodManager imm = (InputMethodManager) (view.getContext().getSystemService(INPUT_METHOD_SERVICE));
        imm.showSoftInput(view, HIDE_NOT_ALWAYS);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftInput(final Context context) {
        InputMethodManager inputMethodManager = ((InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE));
        final View currentFocusView = ((Activity)context).getCurrentFocus();
        if (currentFocusView != null) {
            final IBinder windowToken = currentFocusView.getWindowToken();
            if (inputMethodManager != null && windowToken != null) {
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    public static void toggleSoftInput(final Context context) {
        InputMethodManager inputMethodManager = ((InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(0, HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 软键盘的显示或隐藏
     * @param view
     * @param status 0为隐藏，1为显示
     */
    public static void softInputShowOrHide(final View view, final int status) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = ((InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE));
                if(status == 1){
                    inputMethodManager.showSoftInput(view,SHOW_FORCED);
                }else {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
            }
        },200);
    }


    public static void showSoftInput(final Context context, final View view) {
        InputMethodManager inputMethodManager = ((InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager != null) {
            if (!inputMethodManager.isActive()) {
                inputMethodManager.toggleSoftInput(0, HIDE_NOT_ALWAYS);
            }
            inputMethodManager.showSoftInput(view, 0);
        }

    }

    public static boolean isActive(final Activity activity){
        return activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
    }
}
