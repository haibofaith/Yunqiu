package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.presenter.BallPlayerPresenter;
import com.kball.function.CloudBall.view.BallPlayerImpl;
import com.kball.function.Mine.ui.PersonInfoActivity;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class BallPlayerAct extends BaseActivity implements View.OnClickListener ,
        BallPlayerImpl, AdapterView.OnItemClickListener {

    private ListView mlistView;
    private ArrayList<RankPeopleBean> mData;
    private CloudBallPeopleAdapter mAdapter;
    private View view1,view2;
    private TextView tab_one,tab_two;
    private ImageView back_img;
    private BallPlayerPresenter mPresenter;
    private String team_id;
    private TextView not_data;
    @Override
    protected int getContentViewResId() {
        return R.layout.cloud_ball_player_act;
    }

    @Override
    protected void initView() {
        mlistView = (ListView) findViewById(R.id.mlistView);
        view1 = (View)findViewById(R.id.view1);
        view2 = (View)findViewById(R.id.view2);
        tab_one = (TextView)findViewById(R.id.tab_one);
        tab_two = (TextView)findViewById(R.id.tab_two);
        not_data = (TextView)findViewById(R.id.not_data);
        back_img = (ImageView)findViewById(R.id.back_img);
    }

    @Override
    protected void initData() {
        team_id = getIntent().getStringExtra("team_id");
        mPresenter = new BallPlayerPresenter(mContext,this);
        mData = new ArrayList<RankPeopleBean>();
        mAdapter = new CloudBallPeopleAdapter(mContext, mData);
        mlistView.setAdapter(mAdapter);

        mPresenter.getdata(team_id);
    }

    @Override
    protected void setListener() {
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        back_img.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_one:
                mlistView.setAdapter(mAdapter);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                not_data.setVisibility(View.GONE);
                mlistView.setVisibility(View.VISIBLE);
                tab_one.setTextColor(getResources().getColor(R.color.color_green));
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.tab_two:
//                mlistView.setAdapter(mBestAdapter);
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                not_data.setVisibility(View.VISIBLE);
                mlistView.setVisibility(View.GONE);
                tab_two.setTextColor(getResources().getColor(R.color.color_green));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.back_img:
                finish();
                break;
        }

    }

    @Override
    public void setInfoData(ArrayList<RankPeopleBean> data) {
        mData = data;
        mAdapter.setDatas(mData);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext,PersonInfoActivity.class).putExtra("userId",mData.get(position).getUser_id()));
    }
}