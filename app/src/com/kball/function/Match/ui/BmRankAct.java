package com.kball.function.Match.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.MyPagerAdapter;
import com.kball.function.CloudBall.adapter.SelectTeamAdapter;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.presenter.BmRankPresenter;
import com.kball.function.Mine.Views.SelectTeamImpl;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.presenter.SelectTeamPresenter;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/25.
 */

public class BmRankAct extends BaseActivity implements  AdapterView.OnItemClickListener ,BmRankView{
    private TitleView title_view;
    private ListView team_list;
    private SelectTeamAdapter adapter;
    private ArrayList<selectTeamListBean> datas;
    private String  id;
    private BmRankPresenter mPresenter;
    @Override
    protected int getContentViewResId() {
        return R.layout.select_team_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView)findViewById(R.id.title_view);
        title_view.setTitleText("报名球队");
        title_view.setRightButtonImg(R.drawable.more_img);
        title_view.setRightButtonImgVis();
        title_view.setRightButtonVis();
        team_list = (ListView) findViewById(R.id.team_list);
        datas = new ArrayList<>();
        adapter = new SelectTeamAdapter(this,datas);
        team_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        mPresenter = new BmRankPresenter(this);
        mPresenter.select(this,id);
    }

    @Override
    protected void setListener() {
        team_list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", datas.get
                (position).getTeam_id()));
    }

    @Override
    public void setSelectTeamListData(ArrayList<selectTeamListBean> list) {
        datas = list;
        adapter.setDatas(datas);
    }
}