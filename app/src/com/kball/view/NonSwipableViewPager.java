package com.kball.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonSwipableViewPager extends ViewPager {
    public NonSwipableViewPager(Context context) {
        super(context);
    }

    public NonSwipableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // do not allow swiping to switch page
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // do not allow swiping to switch page
        return false;
    }
}