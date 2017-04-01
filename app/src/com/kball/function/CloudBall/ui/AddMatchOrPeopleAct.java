package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Match.ui.CreatScheduleAct;
import com.kball.util.ToastAlone;

/**
 * Created by xiaole.wang on 17/2/22.
 */

public class AddMatchOrPeopleAct extends BaseActivity implements View.OnClickListener {

    private ImageView back_tv, search_img;
    private RelativeLayout create_match_rlin, creat_rank;
    private TextView search_name;
    private String searchName;
    private RelativeLayout invite_player_rlin;

    @Override
    protected int getContentViewResId() {
        return R.layout.add_match_or_people_act;
    }

    @Override
    protected void initView() {
        back_tv = (ImageView) findViewById(R.id.back_tv);
        search_img = (ImageView) findViewById(R.id.search_img);
        create_match_rlin = (RelativeLayout) findViewById(R.id.create_match_rlin);
        creat_rank = (RelativeLayout) findViewById(R.id.creat_rank);
        search_name = (TextView) findViewById(R.id.search_name);
        invite_player_rlin = (RelativeLayout) findViewById(R.id.invite_player_rlin);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListener() {
        back_tv.setOnClickListener(this);
        create_match_rlin.setOnClickListener(this);
        creat_rank.setOnClickListener(this);
        invite_player_rlin.setOnClickListener(this);
        search_img.setOnClickListener(this);
        search_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_name:
                startActivity(new Intent().setClass(this, SearchBallAct.class).putExtra("searchName", ""));
                break;
            case R.id.search_img:

                startActivity(new Intent().setClass(this, SearchBallAct.class).putExtra("searchName", ""));
                break;
            case R.id.creat_rank:
                startActivity(new Intent().setClass(mContext, CreatRankAct.class).putExtra("act_type", C.SP
                        .CREATE_RANK));
                break;
            case R.id.back_tv:
                finish();
                break;
            case R.id.create_match_rlin:
                startActivity(new Intent(this, CreatScheduleAct.class));
                break;
            case R.id.invite_player_rlin:
                startActivity(new Intent(this, InvitePlayerActivity.class));
                break;
        }
    }
}
