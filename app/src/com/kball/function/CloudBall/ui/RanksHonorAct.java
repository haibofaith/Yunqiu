package com.kball.function.CloudBall.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.CloudBallHonorAdapter;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter;
import com.kball.function.CloudBall.bean.CloudBallHonorBean;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;
import com.kball.function.CloudBall.presenter.HonorPresenter;
import com.kball.function.CloudBall.view.HonorView;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class RanksHonorAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        HonorView {

    private ListView mlistView;
    private ArrayList<CloudBallHonorBean> mData;
    private CloudBallHonorAdapter mAdapter;
    private HonorPresenter mPrensent;
    private String team_id;

    private ImageView back_img;

    @Override
    protected int getContentViewResId() {
        return R.layout.cloud_ball_honor_act;
    }

    @Override
    protected void initView() {
        mlistView = (ListView) findViewById(R.id.mlistView);
        back_img = (ImageView) findViewById(R.id.back_img);
    }

    @Override
    protected void initData() {
        team_id = getIntent().getStringExtra("team_id");
        mData = new ArrayList<CloudBallHonorBean>();
        mAdapter = new CloudBallHonorAdapter(mContext, mData);
        mlistView.setAdapter(mAdapter);
        mAdapter.setDatas(mData);
        mPrensent = new HonorPresenter(this, this);
        mPrensent.getdata(team_id);
    }

    @Override
    protected void setListener() {
        back_img.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void setInfoData(ArrayList<CloudBallHonorBean> data) {
        mData = data;
        mAdapter.setDatas(mData);
    }
}