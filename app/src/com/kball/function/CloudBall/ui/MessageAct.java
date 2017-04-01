package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.MessageAdapter;
import com.kball.function.CloudBall.bean.MessageBean;
import com.kball.function.CloudBall.presenter.MessagePresenter;
import com.kball.function.CloudBall.view.MessageView;
import com.kball.function.home.bean.ListBaseBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class MessageAct extends BaseActivity implements View.OnClickListener, MessageView, AdapterView
        .OnItemClickListener {

    private ListView mlistView;
    private ArrayList<MessageBean> mData;
    private MessageAdapter mAdapter;
    private MessagePresenter mPresenter;
    private ImageView message_tv;

    @Override
    protected int getContentViewResId() {
        return R.layout.message_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView) findViewById(R.id.message_tv);
        mlistView = (ListView) findViewById(R.id.mlistView);
    }

    @Override
    protected void initData() {
        mPresenter = new MessagePresenter(this);
        mData = new ArrayList<MessageBean>();
        mAdapter = new MessageAdapter(mContext, mData);
        mlistView.setAdapter(mAdapter);
        mAdapter.setDatas(mData);
        mPresenter.getMessageList(this);
    }

    @Override
    protected void setListener() {
        mlistView.setOnItemClickListener(this);
        message_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_tv:
                finish();
                break;
        }
    }

    @Override
    public void setdata(ListBaseBean<MessageBean> result) {
        mData = result.getData();
        mAdapter.setDatas(mData);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent().setClass(this, RanksMessageAct.class).putExtra("type", mData.get(position)
                .getMessage_type()).putExtra("name", mData.get(position).getMessage_name()).putExtra("type_id", mData
                .get(position).getType_id()));
    }
}
