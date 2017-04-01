package com.kball.function.Mine.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseToBean;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.Mine.Views.FansView;
import com.kball.function.Mine.adapter.FansAdapter;
import com.kball.function.Mine.adapter.TeamAdapter;
import com.kball.function.Mine.bean.FansBean;
import com.kball.function.Mine.bean.TeamBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.presenter.ConcernPresenter;
import com.kball.library.PullToRefreshBase;
import com.kball.library.PullToRefreshListView;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class ConcernActivity extends BaseActivity implements View.OnClickListener,FansView ,AdapterView
        .OnItemClickListener{
    private TitleView title_view;
    private TextView qd_tv;
    private TextView qy_tv;
    private PullToRefreshListView qyRefreshList;
    private PullToRefreshListView qdRefreshList;
    private View qd_view;
    private View qy_view;
    private int GreenColor = Color.parseColor("#59be5e");
    private int BlackColor = Color.parseColor("#333333");
    private ListView concern_list;
    private TeamAdapter adapter;
    private ArrayList<FansBean> mData;
    private ListView concern_qy_list;
    private FansAdapter qy_adapter;
    private ArrayList<FansBean> qy_mData;
    private ConcernPresenter mPresenter;
    private String type = "1";
    private int no = 1;
    private int size = 20;
    private boolean refreshFlag = true;
    private int total =0;

    @Override
    protected int getContentViewResId() {
        return R.layout.concern_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("关注");
        qd_tv = (TextView) findViewById(R.id.qd_tv);
        qy_tv = (TextView) findViewById(R.id.qy_tv);
        qd_view = (View) findViewById(R.id.qd_view);
        qy_view = (View) findViewById(R.id.qy_view);
        qd_tv.setTextColor(BlackColor);
        qd_view.setVisibility(View.VISIBLE);
        qy_tv.setTextColor(GreenColor);
        qy_view.setVisibility(View.GONE);

        qyRefreshList = (PullToRefreshListView) findViewById(R.id.concern_qy_list);
        qdRefreshList = (PullToRefreshListView) findViewById(R.id.concern_list);
        concern_qy_list =qyRefreshList.getRefreshableView();
        concern_list =qdRefreshList.getRefreshableView();
        //默认显示球员
        qyRefreshList.setVisibility(View.VISIBLE);
        qdRefreshList.setVisibility(View.GONE);

        qy_mData = new ArrayList<FansBean>();
        mData = new ArrayList<FansBean>();
        adapter = new TeamAdapter(this, mData);
        concern_list.setAdapter(adapter);
        qy_adapter = new FansAdapter(this, qy_mData);
        concern_qy_list.setAdapter(qy_adapter);
    }

    @Override
    protected void initData() {
        mPresenter = new ConcernPresenter(mContext,this);
        mPresenter.getData(no+"" ,size+"");
    }

    @Override
    protected void setListener() {
        qd_tv.setOnClickListener(this);
        qy_tv.setOnClickListener(this);
        concern_list.setOnItemClickListener(this);
        concern_qy_list.setOnItemClickListener(this);
        qyRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshFlag = true;
                mPresenter.getData(no+"" ,size+"");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                completePullWidget();
                refreshFlag = false;
                no++;
                if (no>total){
                    ToastAlone.show("已经是最后一页了");
                    return;
                }
                mPresenter.getData(no+"" ,size+"");
            }
        });
        qdRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshFlag = true;
                mPresenter.getQdData(no+"" ,size+"");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                completePullWidget();
                refreshFlag = false;
                no++;
                if (no>total){
                    ToastAlone.show("已经是最后一页了");
                    return;
                }
                mPresenter.getQdData(no+"" ,size+"");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qy_tv:
                type = "1";
                no = 1;
                qy_tv.setTextColor(GreenColor);
                qd_view.setVisibility(View.VISIBLE);
                qd_tv.setTextColor(BlackColor);
                qy_view.setVisibility(View.GONE);
                qyRefreshList.setVisibility(View.VISIBLE);
                qdRefreshList.setVisibility(View.GONE);

                mPresenter.getData(no+"" ,size+"");
                break;

            case R.id.qd_tv:
                type = "2";
                no = 1;
                qy_tv.setTextColor(BlackColor);
                qd_view.setVisibility(View.GONE);
                qd_tv.setTextColor(GreenColor);
                qy_view.setVisibility(View.VISIBLE);
                qyRefreshList.setVisibility(View.GONE);
                qdRefreshList.setVisibility(View.VISIBLE);
                mPresenter.getQdData(no+"",size+"");
                break;
        }
    }

    @Override
    public void setObjData(BaseToBean<FansBean> data) {
        completePullWidget();
        total = Integer.parseInt(data.getTotal());
        if (refreshFlag){
            qy_mData = data.getFocus();
            qy_adapter.setDatas(qy_mData);
        }else{
            qy_mData.addAll(data.getFocus());
            qy_adapter.setDatas(qy_mData);
        }



    }

    @Override
    public void setqdData(BaseToBean<FansBean> data) {
        qdRefreshList.postDelayed(new Runnable() {

            @Override
            public void run() {
                qdRefreshList.onRefreshComplete();

            }
        }, 100);
        total = Integer.parseInt(data.getTotal());
        if (refreshFlag){
            mData = data.getFocus();
            adapter.setDatas(mData);
        }else{
            mData.addAll(data.getFocus());
            adapter.setDatas(mData);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if ("1".equals(type)){
            startActivity(new Intent(this,PersonInfoActivity.class).putExtra("userId",qy_mData.get(position-1)
                    .getUser_id
                    ()));
        }else if("2".equals(type)){
            startActivity(new Intent().setClass(this, RanksDetailAct.class).putExtra("team_id", mData.get
                    (position-1).getTeam_id()));
        }
    }


    private void completePullWidget() {
        qyRefreshList.postDelayed(new Runnable() {

            @Override
            public void run() {
                qyRefreshList.onRefreshComplete();

            }
        }, 100);
    }

}
