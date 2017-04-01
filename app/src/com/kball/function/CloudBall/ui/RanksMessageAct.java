package com.kball.function.CloudBall.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.MessageAdapter;
import com.kball.function.CloudBall.adapter.MessageRanksAdapter;
import com.kball.function.CloudBall.bean.MessageBean;
import com.kball.function.CloudBall.bean.MessageRanksBean;
import com.kball.function.CloudBall.presenter.ChildMessagePresenter;
import com.kball.function.CloudBall.view.ChildMessageView;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class RanksMessageAct extends BaseActivity implements View.OnClickListener, ChildMessageView {

    private ListView mlistView;
    private ArrayList<MessageRanksBean> mData;
    private MessageRanksAdapter mAdapter;
    private String type, name, type_id;
    private ChildMessagePresenter mPre;
    private ImageView back_img;
    private TextView title_name;

    @Override
    protected int getContentViewResId() {
        return R.layout.ranks_message_act;
    }

    @Override
    protected void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        mlistView = (ListView) findViewById(R.id.mlistView);
        title_name = (TextView) findViewById(R.id.title_name);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");
        type_id = getIntent().getStringExtra("type_id");
        title_name.setText(name);
        mPre = new ChildMessagePresenter(this);
        mData = new ArrayList<MessageRanksBean>();
        mAdapter = new MessageRanksAdapter(mContext, mData);
        mlistView.setAdapter(mAdapter);
        mAdapter.setDatas(mData);
        mPre.getMessage(this, type, type_id);
    }

    @Override
    protected void setListener() {
        back_img.setOnClickListener(this);
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
    public void setdata(ArrayList<MessageRanksBean> list) {
        mData = list;
        mAdapter.setDatas(mData);
    }
}