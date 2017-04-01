package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.SelectTeamAdapter;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.Views.SelectTeamImpl;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.presenter.SelectTeamPresenter;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public class SelectTeamActivity extends BaseActivity implements SelectTeamImpl, AdapterView.OnItemClickListener {
    private TitleView title_view;
    private ListView team_list;
    private SelectTeamAdapter adapter;
    private ArrayList<selectTeamListBean> datas;
    private SelectTeamPresenter presenter;
    private Intent intent;
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
        presenter = new SelectTeamPresenter(this);
        presenter.selectTeamList(this);
        intent = getIntent();
    }

    @Override
    protected void setListener() {
        team_list.setOnItemClickListener(this);
    }

    @Override
    public void setSelectTeamListData(BaseBean<ArrayList<selectTeamListBean>> result) {
        datas= result.getData();
        adapter.setDatas(datas);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent.putExtra("team_id",datas.get(position).getTeam_id());
        intent.putExtra("team_name",datas.get(position).getTeam_name());
        setResult(RESULT_OK,intent);
        finish();
    }
}
