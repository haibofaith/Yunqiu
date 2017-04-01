package com.kball.function.CloudBall.ui;

import android.view.View;
import android.widget.LinearLayout;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.ui.HomeMineView;

/**
 * Created by xiaole.wang on 17/2/21.
 */

public class RanksInviteAct extends BaseActivity implements View.OnClickListener {

    private LinearLayout home_mine_lin;
    private RanksMilitaryView mRanksMilitaryView;
    @Override
    protected int getContentViewResId() {
        return R.layout.ranks_invite_act;
    }

    @Override
    protected void initView() {
        home_mine_lin = (LinearLayout)findViewById(R.id.home_mine_lin);
    }

    @Override
    protected void initData() {
        mRanksMilitaryView = RanksMilitaryView.RanksMilitaryInit(mContext, home_mine_lin);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}