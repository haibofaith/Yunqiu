package com.kball.function.CloudBall.ui;

import android.view.View;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.CloudBallAdapter;
import com.kball.function.CloudBall.bean.CloudBallBean;
import com.kball.function.home.bean.RanksTJBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CloudBallAct extends BaseActivity implements View.OnClickListener {

    private ListView mlistView;
    private ArrayList<RanksTJBean> mData;
    private CloudBallAdapter mAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.cloud_ball_act;
    }

    @Override
    protected void initView() {

        mlistView = (ListView) findViewById(R.id.mlistView);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<RanksTJBean>();
        mAdapter = new CloudBallAdapter(mContext, mData,imageLoader);
        mlistView.setAdapter(mAdapter);
        for (int i = 0; i < 10; i++) {
            RanksTJBean b = new RanksTJBean();
            mData.add(b);
        }
        mAdapter.setDatas(mData);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
