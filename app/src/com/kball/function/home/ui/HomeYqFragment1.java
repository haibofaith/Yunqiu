package com.kball.function.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseFragment;
import com.kball.function.CloudBall.adapter.CloudBallAdapter;
import com.kball.function.CloudBall.bean.CloudBallBean;
import com.kball.function.CloudBall.ui.AddMatchOrPeopleAct;
import com.kball.function.CloudBall.ui.BallPlayerAct;
import com.kball.function.CloudBall.ui.CloudBallAct;
import com.kball.function.CloudBall.ui.CloudBallShowAct;
import com.kball.function.CloudBall.ui.CloudBallShowActView;
import com.kball.function.CloudBall.ui.CreatRankAct;
import com.kball.function.CloudBall.ui.ExploitsAct;
import com.kball.function.CloudBall.ui.MatchPeopleAct;
import com.kball.function.CloudBall.ui.MessageAct;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.CloudBall.ui.RanksHonorAct;
import com.kball.function.CloudBall.ui.RanksInviteAct;
import com.kball.function.CloudBall.ui.RanksMessageAct;
import com.kball.function.CloudBall.ui.SearchBallAct;
import com.kball.function.Login.ui.PassLoginActivity;
import com.kball.function.home.bean.RanksTJBean;
import com.kball.function.home.impl.DialogView;
import com.kball.function.home.presenter.BallRanksPresenter;
import com.kball.function.home.view.BallRanksView;
import com.kball.neliveplayerdemo.NEMainActivity;
import com.kball.neliveplayerdemo.NEVideoPlayerActivity;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.netease.neliveplayer.NELivePlayer;
import com.netease.neliveplayer.NEMediaPlayer;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/1/16.
 */
public class HomeYqFragment1 extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener,
        BallRanksView ,DialogView{
    private View rootView;
    private ListView mlistView;
    private ArrayList<RanksTJBean> mData;
    private CloudBallAdapter mAdapter;
    private TextView creat_btn, join_btn;
    private ImageView creat_tv, message_tv;
    private BallRanksPresenter mPresenter;
    private RelativeLayout huanyihuan;
    private LinearLayout not_login,add_lin;
    private CloudBallShowActView mView;

    public HomeYqFragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.cloud_ball_act, null, false);
        return rootView;
    }

    @Override
    protected void initView() {
        mlistView = (ListView) rootView.findViewById(R.id.mlistView);
        creat_btn = (TextView) rootView.findViewById(R.id.creat_btn);
        join_btn = (TextView) rootView.findViewById(R.id.join_btn);
        creat_tv = (ImageView) rootView.findViewById(R.id.creat_tv);
        message_tv = (ImageView) rootView.findViewById(R.id.message_tv);
        huanyihuan = (RelativeLayout) rootView.findViewById(R.id.huanyihuan);
        not_login = (LinearLayout) rootView.findViewById(R.id.not_login);
        add_lin = (LinearLayout) rootView.findViewById(R.id.add_lin);
    }

    @Override
    protected void initData() {
        mPresenter = new BallRanksPresenter(mContext, this, imageLoader);
        mData = new ArrayList<RanksTJBean>();
        mAdapter = new CloudBallAdapter(mContext, mData, imageLoader);
        mlistView.setAdapter(mAdapter);
        mPresenter.getData();



    }

    @Override
    public void onResume() {
        super.onResume();
        add_lin.removeAllViews();
        if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID,""))){
            not_login.setVisibility(View.VISIBLE);
        }else {
            not_login.setVisibility(View.GONE);
            mView.homeInit(mContext,add_lin,this);
        }

    }

    @Override
    protected void setListener() {
        creat_btn.setOnClickListener(this);
        message_tv.setOnClickListener(this);
        creat_tv.setOnClickListener(this);
        join_btn.setOnClickListener(this);
        huanyihuan.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.creat_btn:

                if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                    startActivityForResult(new Intent(mContext, PassLoginActivity.class), C.SP.LoginCode);
                } else {
//                startActivity(new Intent().setClass(mContext, CreatRankAct.class));
//                startActivity(new Intent().setClass(mContext, NEMainActivity.class));
                    Intent intent = new Intent(mContext, NEVideoPlayerActivity.class);
                    //把多个参数传给NEVideoPlayerActivity
                    intent.putExtra("media_type", "software");
                    intent.putExtra("decode_type", "hardware");
                    intent.putExtra("videoPath", "http://mvvideo2.meitudata.com/5785a7e3e6a1b824.mp4");
                    startActivity(intent);
                }
                break;
            case R.id.message_tv:
                startActivity(new Intent().setClass(mContext, MessageAct.class));
                break;
            case R.id.creat_tv:
                if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                    startActivityForResult(new Intent(mContext, PassLoginActivity.class), C.SP.LoginCode);
                } else {
                    startActivity(new Intent().setClass(mContext, AddMatchOrPeopleAct.class));
                }

                break;
            case R.id.join_btn:
                if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                    startActivityForResult(new Intent(mContext, PassLoginActivity.class), C.SP.LoginCode);
                } else {
                    startActivity(new Intent().setClass(mContext, SearchBallAct.class));
                }

                break;
            case R.id.huanyihuan:
                mPresenter.getData();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
            startActivityForResult(new Intent(mContext, PassLoginActivity.class), C.SP.LoginCode);
        } else {
            startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", mData.get
                    (position).getTeam_id()));
        }
    }

    @Override
    public void setObjData(ArrayList<RanksTJBean> info) {
        this.mData = info;
        mAdapter.setDatas(info);
    }

    @Override
    public void showLoading1() {

    }

    @Override
    public void dismissLoading1() {

    }

    @Override
    public void show() {
        showLoading();
    }

    @Override
    public void dismiss() {
dismissLoading();
    }
}
