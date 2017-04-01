package com.kball.function.home.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kball.R;
import com.kball.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/23.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager view_pager;
    private View view1, view2, view3, view4;
    private ArrayList<View> viewList;
    private ImageView to_yunqiu;

    @Override
    protected int getContentViewResId() {
        return R.layout.guide_layout;
    }

    @Override
    protected void initView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.guide_item1, null);
        view2 = inflater.inflate(R.layout.guide_item2, null);
        view3 = inflater.inflate(R.layout.guide_item3, null);
        view4 = inflater.inflate(R.layout.guide_item4, null);
        to_yunqiu = (ImageView) view4.findViewById(R.id.to_yunqiu);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        PagerAdapter pagerAdapter = new PagerAdapter() {

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
        };

        view_pager.setAdapter(pagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        to_yunqiu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


}
