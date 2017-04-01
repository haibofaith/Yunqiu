package com.kball.function.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/24.
 */

public class FxPagerAdapter extends PagerAdapter {
    private Context context;

    private ArrayList<View> viewList;

    public void setViewList(ArrayList<View> viewList) {
        this.viewList = viewList;
        notifyDataSetChanged();
    }

    public FxPagerAdapter(Context context, ArrayList<View> viewList) {
        this.context = context;
        this.viewList = viewList;
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

}
