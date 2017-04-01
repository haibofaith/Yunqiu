package com.kball.function.Match.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.adapter.MatchListAdapter;
import com.kball.function.CloudBall.adapter.MessageAdapter;
import com.kball.function.CloudBall.bean.MessageBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.adapter.MatchOtherAdapter;
import com.kball.function.Match.adapter.MatchTabTwoAdapter;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.bean.MatchOtherBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.impls.MatchListView;
import com.kball.function.Match.impls.MatchTabTwoViewImpl;
import com.kball.function.Match.presenter.MatchListPresenter;
import com.kball.function.Match.presenter.MatchTabTwoPresenter;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/25.
 */

public class MatchListAct extends BaseActivity implements View.OnClickListener, MatchTabTwoViewImpl, AdapterView
        .OnItemClickListener {

    private ListView mlistView;
    private MatchTabTwoPresenter presenter;
    private ArrayList<MatchTabTwoBean> mData;
    private static int pageNum = 1;
    private static int pageSize = 10;

    private static int pageTotal = 0;
    private String status;
    //    private String game_time;
    private String team_id;
    private ImageView message_tv;
    private MatchTabTwoAdapter mAdapter;


    @Override
    protected int getContentViewResId() {
        return R.layout.match_list_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView) findViewById(R.id.message_tv);
        mlistView = (ListView) findViewById(R.id.mlistView);
    }

    @Override
    protected void initData() {
        presenter = new MatchTabTwoPresenter(this);
        mData = new ArrayList<>();
        mAdapter = new MatchTabTwoAdapter(mContext, mData);
        mlistView.setAdapter(mAdapter);
        team_id = getIntent().getStringExtra("team_id");
        presenter.meTeamLeagueList(this, pageNum + "", pageSize + "", team_id, status);
    }

    @Override
    protected void setListener() {
        message_tv.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
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
    public void setListData(BaseBean<BaseListBean<MatchTabTwoBean>> result) {
        if ("1200".equals(result.getError_code())) {
//            ToastAlone.show("meTeamLeagueList调用成功！");
            mData = result.getData().getList();
            mAdapter.setDatas(mData);
        }
    }

    @Override
    public void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent().setClass(this, MatchDetailActivity.class).putExtra("league_id", mData.get
                (position).getLeague_id()).putExtra("match_name", mData.get(position).getLeague_abbreviation()));
    }
}