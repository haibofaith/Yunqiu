package com.kball.function.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseFragment;
import com.kball.function.CloudBall.ui.BallPlayerAct;
import com.kball.function.CloudBall.ui.CloudBallAct;
import com.kball.function.CloudBall.ui.CloudBallShowAct;
import com.kball.function.CloudBall.ui.ExploitsAct;
import com.kball.function.CloudBall.ui.MatchPeopleAct;
import com.kball.function.CloudBall.ui.MessageAct;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.CloudBall.ui.RanksHonorAct;
import com.kball.function.CloudBall.ui.RanksInviteAct;
import com.kball.function.CloudBall.ui.RanksMessageAct;
import com.kball.function.CloudBall.ui.SearchBallAct;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by xiaole.wang on 17/1/16.
 */
public class HomeYqFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;

    private TextView sy_btn, qy_btn, ry_btn, qy_btn2,message_btn,search_btn,zhanji,qd_message,yq_sy,create_ranks,changdi,
            join_or_no;

    public HomeYqFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_sy, null, false);
        return rootView;
    }

    @Override
    protected void initView() {
        sy_btn = (TextView) findViewById(R.id.sy_btn);
        qy_btn = (TextView) findViewById(R.id.qy_btn);
        ry_btn = (TextView) findViewById(R.id.ry_btn);
        qy_btn2 = (TextView) findViewById(R.id.qy_btn2);
        message_btn = (TextView) findViewById(R.id.message_btn);
        search_btn = (TextView) findViewById(R.id.search_btn);
        zhanji = (TextView) findViewById(R.id.zhanji);
        qd_message = (TextView) findViewById(R.id.qd_message);
        yq_sy = (TextView) findViewById(R.id.yq_sy);
        create_ranks = (TextView) findViewById(R.id.create_ranks);
        join_or_no = (TextView) findViewById(R.id.join_or_no);
        changdi = (TextView) findViewById(R.id.changdi);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        sy_btn.setOnClickListener(this);
        qy_btn.setOnClickListener(this);
        ry_btn.setOnClickListener(this);
        qy_btn2.setOnClickListener(this);
        message_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        zhanji.setOnClickListener(this);
        qd_message.setOnClickListener(this);
        yq_sy.setOnClickListener(this);
        create_ranks.setOnClickListener(this);
        join_or_no.setOnClickListener(this);
        changdi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sy_btn:
                startActivity(new Intent().setClass(mContext, CloudBallAct.class));
                break;
            case R.id.qy_btn:
                break;
            case R.id.ry_btn:
                startActivity(new Intent().setClass(mContext, RanksHonorAct.class));
                break;
            case R.id.qy_btn2:
                startActivity(new Intent().setClass(mContext, BallPlayerAct.class));
                break;
            case R.id.message_btn:
                startActivity(new Intent().setClass(mContext, MessageAct.class));
                break;
            case R.id.search_btn:
                startActivity(new Intent().setClass(mContext, SearchBallAct.class));
                break;
            case R.id.zhanji:
                startActivity(new Intent().setClass(mContext, ExploitsAct.class));
                break;
            case R.id.qd_message:
                startActivity(new Intent().setClass(mContext, RanksMessageAct.class));
                break;
            case R.id.yq_sy:
                startActivity(new Intent().setClass(mContext, CloudBallShowAct.class));
                break;
            case R.id.create_ranks:
                break;
            case R.id.join_or_no:
                startActivity(new Intent().setClass(mContext, RanksInviteAct.class));
                break;
            case R.id.changdi:
                startActivity(new Intent().setClass(mContext, PlaceAct.class));
                break;
        }
    }
}
