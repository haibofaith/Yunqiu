package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.SearchRanksAdapter;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.presenter.SearchRankPresenter;
import com.kball.function.CloudBall.view.SearchRankView;
import com.kball.function.home.bean.RanksTJBean;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public class SearchPeopleAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        SearchRankView {

    private ImageView back_tv;
    private ListView mlistView;
    private ArrayList<SearchRankBean> mData;
    private SearchRanksAdapter mAdapter;
    private SearchRankPresenter mPresenter;
    private ImageView search_img;
    private EditText search_edit;
    private String searchName;
    private TextView tv1;

    @Override
    protected int getContentViewResId() {
        return R.layout.search_people_act;
    }

    @Override
    protected void initView() {
        back_tv = (ImageView) findViewById(R.id.back_tv);
        search_img = (ImageView) findViewById(R.id.search_img);
        search_edit = (EditText) findViewById(R.id.search_edit);
        mlistView = (ListView) findViewById(R.id.mlistView);
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<SearchRankBean>();
        mAdapter = new SearchRanksAdapter(this, mData);
        mlistView.setAdapter(mAdapter);
        mPresenter = new SearchRankPresenter(mContext, this);
        mPresenter.getData();
    }

    @Override
    protected void setListener() {
        back_tv.setOnClickListener(this);
        search_img.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_tv:
                finish();
                break;
            case R.id.search_img:
                searchName = search_edit.getText().toString().trim();
                if (searchName.length() == 0) {
                    ToastAlone.show("请输入搜索球队的名称");
                    return;
                }


                mPresenter.getdata(searchName, "1");
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("team_id",mData.get(position).getTeam_id());
        intent.putExtra("team_name",mData.get(position).getTeam_name());
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void setInfoData(SearchBaseBean<SearchRankBean> data) {
        mData = data.getTeam();
        mAdapter.setDatas(mData);
        if (mData.size()>0){
            tv1.setVisibility(View.GONE);
        }
    }

    @Override
    public void setObjData(ArrayList<SearchRankBean> data) {
        mData = data;
        mAdapter.setDatas(mData);
        if (mData.size()>0){
            tv1.setVisibility(View.GONE);
        }
    }
}
