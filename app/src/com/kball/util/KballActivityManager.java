package com.kball.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity管理
 * <p/>
 * lizheng -- 15/5/21
 * <p/>
 * weici
 */
public class KballActivityManager {
    private static KballActivityManager instance;
    private Stack<Activity> mActivityStack = new Stack<Activity>();

    private KballActivityManager() {

    }

    public static KballActivityManager getInstance() {
        if (null == instance) {
            instance = new KballActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity act) {
        mActivityStack.push(act);
    }

    public void removeActivity(Activity act) {
        mActivityStack.remove(act);
    }

    public void killMyProcess() {
        int nCount = mActivityStack.size();
        for (int i = nCount - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            activity.finish();
        }
        mActivityStack.clear();
        // android.os.Process.killProcess(android.os.Process.myPid());
    }
}
