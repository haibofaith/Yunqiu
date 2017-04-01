package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.CloudBallAdapter;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter1;
import com.kball.function.CloudBall.bean.CloudBallBean;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.presenter.ManagerRankPresenter;
import com.kball.function.CloudBall.view.ManagerPeopleView;
import com.kball.function.CloudBall.view.ManagerRankView;
import com.kball.function.Login.ui.ScorePositionActivity;
import com.kball.util.ToastAlone;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class MatchPeopleAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        ManagerRankView, ManagerPeopleView {

    private ListView mlistView;
    private ArrayList<RankPeopleBean> mData;
    private CloudBallPeopleAdapter1 mAdapter;
    private ManagerRankPresenter mPresenter;
    private String team_id;
    private ImageView back_img;
    private View zz_view;
    private TextView right_btn, title_name;

    //移除
    private LinearLayout yichu_lin;
    private TextView yc_name, yc_cancel, yc_enter;
    private String ycUserId;

    //修改球衣号码
    private LinearLayout xg_lin;
    private TextView xg_cancle, xg_enter;
    private EditText xg_edit;
    private String xg_num;

    //审核
    private LinearLayout sh_lin;
    private TextView sh_yes, sh_no, sh_cancle;
    private String join_id;
    private String status;


    private String act_type;

    //移交
    private LinearLayout yj_lin;
    private TextView yj_tv, yj_cancle, yj_enter;
    private String user_id;

    private String returnStr;

    //身份和权限
    private RelativeLayout shenfen_rel;
    private TextView cancle_tv, enter_tv;
    private String sf_str = "", qx_str = "";
    private TextView sf_type1, sf_type2, sf_type3, sf_type4, sf_type5;
    private TextView qx_type1, qx_type2;

    private boolean isEdit;

    @Override
    protected int getContentViewResId() {
        return R.layout.cloud_ball_people_act;
    }

    @Override
    protected void initView() {
        mlistView = (ListView) findViewById(R.id.mlistView);
        back_img = (ImageView) findViewById(R.id.back_img);
        zz_view = (View) findViewById(R.id.zz_view);
        yichu_lin = (LinearLayout) findViewById(R.id.yichu_lin);
        yj_cancle = (TextView) findViewById(R.id.yj_cancle);
        yj_enter = (TextView) findViewById(R.id.yj_enter);
        yj_tv = (TextView) findViewById(R.id.yj_tv);
        yj_lin = (LinearLayout) findViewById(R.id.yj_lin);
        xg_lin = (LinearLayout) findViewById(R.id.xg_lin);
        sh_lin = (LinearLayout) findViewById(R.id.sh_lin);
        yc_name = (TextView) findViewById(R.id.yc_name);
        yc_cancel = (TextView) findViewById(R.id.yc_cancle);
        yc_enter = (TextView) findViewById(R.id.yc_enter);
        xg_cancle = (TextView) findViewById(R.id.xg_cancle);
        xg_enter = (TextView) findViewById(R.id.xg_enter);
        sh_yes = (TextView) findViewById(R.id.sh_yes);
        sh_no = (TextView) findViewById(R.id.sh_no);
        sh_cancle = (TextView) findViewById(R.id.sh_cancle);
        right_btn = (TextView) findViewById(R.id.right_btn);
        title_name = (TextView) findViewById(R.id.title_name);
        cancle_tv = (TextView) findViewById(R.id.cancle_tv);
        enter_tv = (TextView) findViewById(R.id.enter_tv);
        xg_edit = (EditText) findViewById(R.id.xg_edit);
        shenfen_rel = (RelativeLayout) findViewById(R.id.shenfen_rel);
        sf_type1 = (TextView) findViewById(R.id.sf_type1);
        sf_type2 = (TextView) findViewById(R.id.sf_type2);
        sf_type3 = (TextView) findViewById(R.id.sf_type3);
        sf_type4 = (TextView) findViewById(R.id.sf_type4);
        sf_type5 = (TextView) findViewById(R.id.sf_type5);
        qx_type1 = (TextView) findViewById(R.id.qx_type1);
        qx_type2 = (TextView) findViewById(R.id.qx_type2);

    }

    @Override
    protected void initData() {
        team_id = getIntent().getStringExtra("team_id");
        act_type = getIntent().getStringExtra("type");
        if (act_type.equals("yijiao")) {
            right_btn.setVisibility(View.GONE);
            title_name.setText("移交球队");
        } else if (act_type.equals("guanli")) {
            right_btn.setVisibility(View.VISIBLE);
            title_name.setText("球员");
        }
        mPresenter = new ManagerRankPresenter(this, this);
        mData = new ArrayList<RankPeopleBean>();
        mAdapter = new CloudBallPeopleAdapter1(mContext, mData, this);
        mAdapter.setDatas(mData);
        mlistView.setAdapter(mAdapter);
        mPresenter.getdata(team_id);

    }


    @Override
    protected void setListener() {
        mlistView.setOnItemClickListener(this);
        back_img.setOnClickListener(this);
        yc_cancel.setOnClickListener(this);
        yc_enter.setOnClickListener(this);
        xg_cancle.setOnClickListener(this);
        xg_enter.setOnClickListener(this);
        sh_yes.setOnClickListener(this);
        sh_no.setOnClickListener(this);
        sh_cancle.setOnClickListener(this);
        right_btn.setOnClickListener(this);
        yj_cancle.setOnClickListener(this);
        yj_enter.setOnClickListener(this);
        cancle_tv.setOnClickListener(this);
        enter_tv.setOnClickListener(this);
        sf_type1.setOnClickListener(this);
        sf_type2.setOnClickListener(this);
        sf_type3.setOnClickListener(this);
        sf_type4.setOnClickListener(this);
        sf_type5.setOnClickListener(this);
        qx_type1.setOnClickListener(this);
        qx_type2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sf_type1:
                sf_str = "1";
                sf_type1.setBackgroundColor(Color.parseColor("#cccccc"));
                sf_type2.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type3.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type4.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type5.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.sf_type2:
                sf_str = "2";
                sf_type1.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type2.setBackgroundColor(Color.parseColor("#cccccc"));
                sf_type3.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type4.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type5.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.sf_type3:
                sf_str = "3";
                sf_type1.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type2.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type3.setBackgroundColor(Color.parseColor("#cccccc"));
                sf_type4.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type5.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.sf_type4:
                sf_str = "4";
                sf_type1.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type2.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type3.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type4.setBackgroundColor(Color.parseColor("#cccccc"));
                sf_type5.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.sf_type5:
                sf_str = "5";
                sf_type1.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type2.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type3.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type4.setBackgroundColor(Color.parseColor("#ffffff"));
                sf_type5.setBackgroundColor(Color.parseColor("#cccccc"));
                break;
            case R.id.qx_type1:
                qx_str = "0";
                qx_type1.setBackgroundColor(Color.parseColor("#cccccc"));
                qx_type2.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.qx_type2:
                qx_str = "2";
                qx_type2.setBackgroundColor(Color.parseColor("#cccccc"));
                qx_type1.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.yj_enter:
                mPresenter.handoverManagement(team_id, user_id);
                break;
            case R.id.yj_cancle:
                yj_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.right_btn:
                if (!isEdit){
                    isEdit = !isEdit;
                    mAdapter.setEdit(isEdit);
                    right_btn.setText("保存");

                }else{
                    isEdit = !isEdit;
                    mAdapter.setEdit(isEdit);
                    right_btn.setText("管理球队");
                    mPresenter.getdata(team_id);
                }

                break;
            case R.id.enter_tv:
                if (sf_str.equals("")) {
                    ToastAlone.show("请选择身份");
                    return;
                }
                if (qx_str.equals("")) {
                    ToastAlone.show("请选择权限");
                    return;
                }
                mPresenter.settingIdentity(team_id, ycUserId, sf_str, qx_str);
                break;
            case R.id.cancle_tv:
                shenfen_rel.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.sh_yes:

                status = "1";
                mPresenter.auditJoin("1", join_id);
                break;
            case R.id.sh_no:
                status = "2";
                mPresenter.auditJoin("2", join_id);
                break;
            case R.id.sh_cancle:
                zz_view.setVisibility(View.GONE);
                sh_lin.setVisibility(View.GONE);
                break;
            case R.id.xg_enter:
                xg_num = xg_edit.getText().toString().trim();
                if (xg_num.length() == 0) {
                    ToastAlone.show("请输入修改的号码");
                    return;
                }
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).getJersey_no().equals(xg_num)){
                        ToastAlone.show("球员号码有重复");
                        return ;
                    }
                }
                mPresenter.updateJerseyNo(team_id, ycUserId, xg_num);
                break;
            case R.id.xg_cancle:
                zz_view.setVisibility(View.GONE);
                xg_lin.setVisibility(View.GONE);
                break;
            case R.id.yc_enter:
                mPresenter.removeMember(team_id, ycUserId);
                break;
            case R.id.yc_cancle:
                zz_view.setVisibility(View.GONE);
                yichu_lin.setVisibility(View.GONE);
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (act_type.equals("yijiao")) {
            yj_lin.setVisibility(View.VISIBLE);
            zz_view.setVisibility(View.VISIBLE);
            yj_tv.setText("确定要传位于" + mData.get(position).getNickname() + "?");
        } else if (act_type.equals("guanli")) {
            if (isEdit) {
                if (!mData.get(position).isShow()) {
                    mData.get(position).setShow(true);
                } else {
                    mData.get(position).setShow(false);
                }
                mAdapter.setDatas(mData);
            }
        }

    }

    @Override
    public void setInfoData(ArrayList<RankPeopleBean> data) {
        mData = data;
        mAdapter.setDatas(mData);
    }

    @Override
    public void removeMember() {
        ToastAlone.show("移除球员成功");
        mPresenter.getdata(team_id);
    }

    @Override
    public void updateJerseyNo() {
        ToastAlone.show("修改成功");
        mPresenter.getdata(team_id);
        zz_view.setVisibility(View.GONE);
        xg_lin.setVisibility(View.GONE);
    }

    @Override
    public void auditJoin() {
        if ("1".equals(status)) {
            ToastAlone.show("审核通过成功");
        } else if ("2".equals(status)) {
            ToastAlone.show("已拒绝入队");
        }
        mPresenter.getdata(team_id);
    }

    @Override
    public void handoverManagement() {
        ToastAlone.show("球队移交成功");
    }

    @Override
    public void updatePlace() {
        ToastAlone.show("位置调整成功");
        mPresenter.getdata(team_id);
    }

    @Override
    public void settingIdentity() {
        ToastAlone.show("身份和权限设置成功");
        mPresenter.getdata(team_id);
        shenfen_rel.setVisibility(View.GONE);
        zz_view.setVisibility(View.GONE);
    }

    @Override
    public void yichu(int position) {
        ycUserId = mData.get(position).getUser_id();
        yc_name.setText("确认将" + mData.get(position).getNickname() + "移出球队？");
        zz_view.setVisibility(View.VISIBLE);
        yichu_lin.setVisibility(View.VISIBLE);
    }

    @Override
    public void xiugai(int position) {
        zz_view.setVisibility(View.VISIBLE);
        xg_lin.setVisibility(View.VISIBLE);
        ycUserId = mData.get(position).getUser_id();
    }

    @Override
    public void shenhe(int position) {
        join_id = mData.get(position).getJoin_id();
        zz_view.setVisibility(View.VISIBLE);
        sh_lin.setVisibility(View.VISIBLE);
    }

    @Override
    public void weizhi(int position) {
        Intent intent = new Intent(this, ScorePositionActivity.class);
        startActivityForResult(intent, 10018);
        ycUserId = mData.get(position).getUser_id();
    }

    @Override
    public void setShenfen(int position) {
        //TODO
        ycUserId = mData.get(position).getUser_id();

        shenfen_rel.setVisibility(View.VISIBLE);
        zz_view.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 10018:
                    returnStr = data.getStringExtra("returnStr");
                    Log.e("TAG", returnStr);
                    mPresenter.updatePlace(team_id, ycUserId, returnStr);
                    break;
            }
        }
    }
}
