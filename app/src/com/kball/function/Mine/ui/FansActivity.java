package com.kball.function.Mine.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseToBean;
import com.kball.function.Mine.Views.FansView;
import com.kball.function.Mine.adapter.FansAdapter;
import com.kball.function.Mine.bean.FansBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.presenter.FansPresenter;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 * 粉丝
 */

public class FansActivity extends BaseActivity implements FansView,AdapterView.OnItemClickListener{
    private TitleView title_view;
    private ListView list_view;
    private FansAdapter adapter;
    private ArrayList<FansBean> mData;
    private FansPresenter mPresenter;
    @Override
    protected int getContentViewResId() {
        return R.layout.fans_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView)findViewById(R.id.title_view);
        title_view.setTitleText("粉丝");
        list_view = (ListView)findViewById(R.id.list_view);
        adapter = new FansAdapter(mContext,mData);
        list_view.setAdapter(adapter);
    }

    @Override
    protected void initData() {

        mPresenter = new FansPresenter(mContext,this);
        mPresenter.getData();
    }

    @Override
    protected void setListener() {
        list_view.setOnItemClickListener(this);
    }


    @Override
    public void setObjData(BaseToBean<FansBean> data) {
        mData = data.getFans();
        adapter.setDatas(mData);
    }

    @Override
    public void setqdData(BaseToBean<FansBean> data) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this,PersonInfoActivity.class).putExtra("userId",mData.get(position).getUser_id
                ()));
    }
}
