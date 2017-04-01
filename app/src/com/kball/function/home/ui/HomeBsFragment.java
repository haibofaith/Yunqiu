package com.kball.function.home.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseFragment;
import com.kball.function.CloudBall.ui.AddMatchOrPeopleAct;
import com.kball.function.CloudBall.ui.MessageAct;
import com.kball.function.Login.ui.PassLoginActivity;
import com.kball.function.Match.adapter.MatchOtherAdapter;
import com.kball.function.Match.bean.MatchOtherBean;
import com.kball.function.Match.ui.CreatScheduleAct;
import com.kball.function.Match.ui.MatchDetailActivity;
import com.kball.function.Match.ui.MatchProgrammeAct;
import com.kball.function.Match.ui.MatchProgrammeView;
import com.kball.function.Match.ui.MatchTabTwoView;
import com.kball.function.home.impl.DialogView;
import com.kball.util.SPUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/1/16.
 */
public class HomeBsFragment extends BaseFragment implements View.OnClickListener, DialogView {


    private View rootView;
    private LinearLayout lin_add_view;
    private TextView tab_one, tab_two;
    private ImageView add_img;

    public HomeBsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_sj, null, false);
        return rootView;
    }

    @Override
    protected void initView() {
        lin_add_view = (LinearLayout) findViewById(R.id.lin_add_view);
        tab_one = (TextView) findViewById(R.id.tab_one);
        tab_two = (TextView) findViewById(R.id.tab_two);
        add_img = (ImageView) findViewById(R.id.add_img);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        setTabOne();
    }

    @Override
    protected void setListener() {
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        add_img.setOnClickListener(this);

    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                mContext.startActivity(new Intent().setClass(mContext, PassLoginActivity.class));
            } else {
            }
        } else {
        }
    }

    private void setTabOne() {
        lin_add_view.removeAllViews();

        MatchProgrammeView.MatchProgrammeInit(mContext, lin_add_view, this);
    }


    private void setTabTwo() {
        lin_add_view.removeAllViews();
        MatchTabTwoView.homeInit(mContext, lin_add_view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_one:
                setTabOne();
                tab_one.setTextColor(getResources().getColor(R.color.color_green));
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.tab_two:
                setTabTwo();
                tab_one.setTextColor(Color.parseColor("#666666"));
                tab_two.setTextColor(getResources().getColor(R.color.color_green));
                break;
            case R.id.add_img:
                startActivity(new Intent().setClass(mContext, AddMatchOrPeopleAct.class));
                break;
        }
    }

    @Override
    public void show() {
        showLoading();
    }

    @Override
    public void dismiss() {
        dismissLoading();
    }
}
