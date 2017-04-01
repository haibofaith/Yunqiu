package com.kball.util;

import android.app.Application;
import android.widget.Toast;

public class ToastAlone {
    /**
     * 唯一的toast
     */
    private static Toast mToast = null;

    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static void show(String text) {
        if (null == mToast) {
            mToast = Toast.makeText(mApplication, text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }

    public static void show(int textRid) {
        if (null == mToast) {
            mToast = Toast.makeText(mApplication, textRid, Toast.LENGTH_SHORT);
        }
        mToast.setText(textRid);
        mToast.show();
    }
}