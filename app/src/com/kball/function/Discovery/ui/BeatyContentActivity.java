package com.kball.function.Discovery.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Discovery.adapter.BeautyMatchAdapter;
import com.kball.function.Discovery.bean.BeautyMatchBean;
import com.kball.function.Discovery.impl.BeatyContentView;
import com.kball.function.Discovery.presenter.BeatyContentPresenter;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Mine.custom.TitleView;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/22.
 */

public class BeatyContentActivity extends BaseActivity implements AdapterView.OnItemClickListener,BeatyContentView {
    private TitleView title_view;
    private ListView list_view;
    private BeautyMatchAdapter adapter;
    private ArrayList<VideoBean> mData;
    private BeatyContentPresenter mPresenter;


    @Override
    protected int getContentViewResId() {
        return R.layout.beauty_match_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("精彩内容");
        list_view = (ListView) findViewById(R.id.list_view);
        mData = new ArrayList<>();
//        {
//            mData = new ArrayList<>();
//            for (int i = 0; i < 15; i++) {
//                mData.add(new BeautyMatchBean());
//            }
//        }
        adapter = new BeautyMatchAdapter(this, mData);
        list_view.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        list_view.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, VideoDetailActivity.class));
    }
}
