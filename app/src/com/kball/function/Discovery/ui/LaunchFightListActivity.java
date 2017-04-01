package com.kball.function.Discovery.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.adapter.LaunchFightListAdapter;
import com.kball.function.Discovery.bean.DisBaseListBean;
import com.kball.function.Discovery.bean.LaunchFightListBean;
import com.kball.function.Discovery.impl.LaunchFightImpl;
import com.kball.function.Discovery.presenter.LaunchFightPresenter;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.custom.TitleView;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/22.
 */

public class LaunchFightListActivity extends BaseActivity implements View.OnClickListener, LaunchFightImpl {
    private TitleView title_view;
    private ListView list_view;
    private LaunchFightListAdapter adapter;
    private ArrayList<LaunchFightListBean> mData;
    private RelativeLayout back_button;
    private RelativeLayout right_button;
    private LaunchFightPresenter presenter;

    @Override
    protected int getContentViewResId() {
        return R.layout.launch_fight_list_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        back_button = (RelativeLayout) title_view.findViewById(R.id.back_button);
        right_button = (RelativeLayout) title_view.findViewById(R.id.right_button);
        title_view.setTitleText("约战广场");
        title_view.setRightButtonVis();
        title_view.setRightButtonImgVis();
        title_view.setRightButtonImg(R.drawable.add_icon);
        list_view = (ListView) findViewById(R.id.list_view);
        adapter = new LaunchFightListAdapter(this, mData);
        list_view.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter= new LaunchFightPresenter(this);
        presenter.getAboutGameList(this);
    }

    @Override
    protected void setListener() {
        right_button.setOnClickListener(this);
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_button:
                startActivity(new Intent(mContext, LaunchFightActivity.class));
                break;
            case R.id.back_button:
                finish();
                break;
        }
    }

    @Override
    public void setGetAboutGameListData(BaseBean<DisBaseListBean> result) {
        if ("1200".equals(result.getError_code())){
            mData = result.getData().getList();
            adapter.setDatas(mData);
        }
    }
}
