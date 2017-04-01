package com.kball.function.Match.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.impls.MatchProImpl;
import com.kball.function.Match.presenter.MatchProPresenter;
import com.kball.function.Match.presenter.MatchProgramPresenter;
import com.kball.function.Match.views.HeheView;
import com.kball.function.home.view.DuiHuiView;
import com.kball.neliveplayerdemo.util.NEVideoPlayerActivity2;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import com.kball.util.ShareUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by xiaole.wang on 17/2/24.
 */

public class MatchProgrammeAct extends BaseActivity implements View.OnClickListener, DuiHuiView, MatchProImpl,
        HeheView {


    private LinearLayout add_layout;
    private View view1, view2, view3, vv_z;
    private TextView tab_one, tab_two, tab_three;
    private String game_id;
    private ImageView back_button, user_img, user_img1, shoucang, share_img, more_img;
    private String teamAbg, teamBbg, teamAID, teamBID;

    private MatchProPresenter presenter;
    private String sc_status;//收藏状态
    private TextView edit_match, edit_train;

    public View zz_view;
    private String classify, jurisdictionA, identity;
    private LinearLayout edit_lin, cancle_lin;

    private TextView cancle_title, cancle_cancle, cancle_enter, bifen;
    private EditText cancle_edit;
    private String cancleStr;

    public ScrollView duifu_lin;//队服

    private TextView duifu_type1, duifu_type2, duifu_type3, duifu_type4, duifu_type5, duifu_type6, duifu_type7,
            duifu_type8, duifu_type9, duifu_type10;
    private String duifuStr = "";
    private MatchAboutView matchAboutView;
    private TextView teamB_name, teamA_name;


    private LinearLayout add_lin;
    private String share_url;
    private RelativeLayout rel_hehe;

    private String cause;

    @Override
    protected int getContentViewResId() {
        return R.layout.match_programme_act;
    }


    @Override
    public void setDuihui(String str1, String str2, String str3, String str4, String str5, String str6) {

        teamAbg = str1;
        teamBbg = str2;
        teamAID = str3;
        teamBID = str4;


        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + teamAbg, user_img);
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + teamBbg, user_img1);
        teamA_name.setText(str5);
        teamB_name.setText(str6);
    }

    @Override
    public void setManager(String classify, String user_id, String jurisdictionA, String str) {
        this.classify = classify;
        this.jurisdictionA = jurisdictionA;
        identity = str;
        if ("3".equals(classify)) {
            edit_train.setVisibility(View.VISIBLE);
            edit_match.setVisibility(View.GONE);
        } else {
            edit_train.setVisibility(View.GONE);
            edit_match.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void setBf(String score_teamA, String score_teamB) {
        bifen.setText(score_teamA + " : " + score_teamB);
    }

    @Override
    protected void initView() {
        add_layout = (LinearLayout) findViewById(R.id.add_layout);
        edit_lin = (LinearLayout) findViewById(R.id.edit_lin);
        cancle_lin = (LinearLayout) findViewById(R.id.cancle_lin);
        add_lin = (LinearLayout) findViewById(R.id.add_lin);
        rel_hehe = (RelativeLayout) findViewById(R.id.rel_hehe);
        zz_view = (View) findViewById(R.id.zz_view);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        view3 = (View) findViewById(R.id.view3);
        vv_z = (View) findViewById(R.id.vv_z);
        tab_one = (TextView) findViewById(R.id.tab_one);
        tab_two = (TextView) findViewById(R.id.tab_two);
        teamA_name = (TextView) findViewById(R.id.teamA_name);
        teamB_name = (TextView) findViewById(R.id.teamB_name);
        cancle_cancle = (TextView) findViewById(R.id.cancle_cancle);
        cancle_enter = (TextView) findViewById(R.id.cancle_enter);
        cancle_title = (TextView) findViewById(R.id.cancle_title);
        tab_three = (TextView) findViewById(R.id.tab_three);
        bifen = (TextView) findViewById(R.id.bifen);
        back_button = (ImageView) findViewById(R.id.back_button);
        user_img = (ImageView) findViewById(R.id.user_img);
        user_img1 = (ImageView) findViewById(R.id.user_img1);
        shoucang = (ImageView) findViewById(R.id.shoucang);
        share_img = (ImageView) findViewById(R.id.share_img);
        more_img = (ImageView) findViewById(R.id.more_img);
        edit_match = (TextView) findViewById(R.id.edit_match);
        cancle_edit = (EditText) findViewById(R.id.cancle_edit);

        duifu_lin = (ScrollView) findViewById(R.id.duifu_lin);
        duifu_type1 = (TextView) findViewById(R.id.duifu_type1);
        duifu_type2 = (TextView) findViewById(R.id.duifu_type2);
        duifu_type3 = (TextView) findViewById(R.id.duifu_type3);
        duifu_type4 = (TextView) findViewById(R.id.duifu_type4);
        duifu_type5 = (TextView) findViewById(R.id.duifu_type5);
        duifu_type6 = (TextView) findViewById(R.id.duifu_type6);
        duifu_type7 = (TextView) findViewById(R.id.duifu_type7);
        duifu_type8 = (TextView) findViewById(R.id.duifu_type8);
        duifu_type9 = (TextView) findViewById(R.id.duifu_type9);
        duifu_type10 = (TextView) findViewById(R.id.duifu_type10);
    }

    @Override
    protected void initData() {
        game_id = getIntent().getStringExtra("game_id");
        matchAboutView = MatchAboutView.matchAboutInit(mContext, add_layout, game_id, this);
        teamAbg = getIntent().getStringExtra("teamA");
        teamBbg = getIntent().getStringExtra("teamB");
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + teamAbg, user_img);
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + teamBbg, user_img1);

        presenter = new MatchProPresenter(this);
        presenter.selectGameInfo(this, game_id);
    }

    @Override
    protected void setListener() {
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        tab_three.setOnClickListener(this);
        back_button.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        share_img.setOnClickListener(this);
        more_img.setOnClickListener(this);
        user_img.setOnClickListener(this);
        user_img1.setOnClickListener(this);
        edit_match.setOnClickListener(this);
        cancle_enter.setOnClickListener(this);
        cancle_cancle.setOnClickListener(this);

        duifu_type1.setOnClickListener(this);
        duifu_type2.setOnClickListener(this);
        duifu_type3.setOnClickListener(this);
        duifu_type4.setOnClickListener(this);
        duifu_type5.setOnClickListener(this);
        duifu_type5.setOnClickListener(this);
        duifu_type6.setOnClickListener(this);
        duifu_type7.setOnClickListener(this);
        duifu_type8.setOnClickListener(this);
        duifu_type9.setOnClickListener(this);
        duifu_type10.setOnClickListener(this);
        vv_z.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vv_z:
                vv_z.setVisibility(View.GONE);
                edit_lin.setVisibility(View.GONE);
                break;
            case R.id.cancle_enter:

                cancleStr = cancle_edit.getText().toString().trim();
                if (cancleStr.length() == 0) {
                    ToastAlone.show("请输入取消的原因");
                    return;
                }
                presenter.cancelGame(mContext, game_id, cancleStr);

                break;
            case R.id.cancle_cancle:
                cancle_lin.setVisibility(View.VISIBLE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.cancle_match:
                cancle_lin.setVisibility(View.VISIBLE);
                edit_lin.setVisibility(View.GONE);
                cancle_title.setText("请输入取消比赛的原因");
                break;
            case R.id.edit_match:
                zz_view.setVisibility(View.GONE);
                edit_lin.setVisibility(View.GONE);
                startActivity(new Intent().setClass(mContext, UpdateMatchAct.class).putExtra("act_type", 2).putExtra
                        ("game_id", game_id));
                break;
            case R.id.user_img:
                startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", teamAID));
                break;
            case R.id.user_img1:
                startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", teamBID));
                break;
            case R.id.shoucang:
                if (null != game_id) {
                    if (sc_status != null) {
                        switch (sc_status) {
                            case "1"://未收藏
                                presenter.collect(this, game_id);
                                break;
                            case "2"://已收藏
                                presenter.cancelCollect(this, game_id);
                                break;
                        }
                    }
                }
                break;
            case R.id.more_img:
                vv_z.setVisibility(View.VISIBLE);
                edit_lin.setVisibility(View.VISIBLE);

                break;
            case R.id.share_img:
                ShareUtil.showShare(this, "云球", share_url);
                break;
            case R.id.tab_one:
                add_layout.removeAllViews();
                MatchAboutView.matchAboutInit(mContext, add_layout, game_id, this);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                tab_one.setTextColor(getResources().getColor(R.color.color_green));
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.tab_two:
                add_layout.removeAllViews();
                MatchAboutTwoView.matchAboutTwoInit(mContext, add_layout, imageLoader, game_id, this);
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                tab_two.setTextColor(getResources().getColor(R.color.color_green));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.tab_three:
                add_layout.removeAllViews();
                view2.setVisibility(View.INVISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                MatchAboutDataView.matchAboutDataInit(mContext, add_layout, imageLoader, game_id);
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_green));
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.duifu_type1:
                setDuifu("1", duifu_type1.getText().toString());
                break;
            case R.id.duifu_type2:
                setDuifu("2", duifu_type2.getText().toString());
                break;
            case R.id.duifu_type3:
                setDuifu("3", duifu_type3.getText().toString());
                break;
            case R.id.duifu_type4:
                setDuifu("4", duifu_type4.getText().toString());
                break;
            case R.id.duifu_type5:
                setDuifu("5", duifu_type5.getText().toString());
                break;
            case R.id.duifu_type6:
                setDuifu("6", duifu_type6.getText().toString());
                break;
            case R.id.duifu_type7:
                setDuifu("7", duifu_type7.getText().toString());
                break;
            case R.id.duifu_type8:
                setDuifu("8", duifu_type8.getText().toString());
                break;
            case R.id.duifu_type9:
                setDuifu("9", duifu_type9.getText().toString());
                break;
            case R.id.duifu_type10:
                setDuifu("10", duifu_type10.getText().toString());
                break;
        }
    }

    private void setDuifu(String s, String s1) {
        MatchAboutView.duifang_df.setText(s1);
        MatchAboutView.uniform_teamB = s;
        zz_view.setVisibility(View.GONE);
        duifu_lin.setVisibility(View.GONE);
        //应战
        MatchAboutView.mPresenter.auditOrFightGame(this, game_id, "2", "1", cause, s);
    }

    @Override
    public void setCollectData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("收藏比赛成功");
            shoucang.setImageResource(R.drawable.collect_press);
            presenter.selectGameInfo(this, game_id);
        }
    }

    @Override
    public void setSelectGameInfoData(BaseBean<GameInfoBean<MemberBean>> result) {
        share_url = result.getData().getShare_url();
        if ("1200".equals(result.getError_code())) {
            sc_status = result.getData().getIsCollection();
            switch (sc_status) {
                case "1":
                    //未收藏
                    shoucang.setImageResource(R.drawable.love_n);
                    break;
                case "2":
                    //已收藏
                    shoucang.setImageResource(R.drawable.collect_press);
                    break;
            }
            //以下为神一般的权限判断逻辑处理BUG：比赛详情页，待应战/报名中/报名截止状态下，对本方管理员和创建者缺少编辑和取消功能
            switch (result.getData().getGame_info().getGame_status()) {
                //比赛状态 0：不需要审核，不需要应战（赛事的比赛） 1 待审核 2：待应战 3：报名中 4：报名结束 5：进行中 6：已结束 7：已取消 8：拒绝比赛 9：拒绝应战
                case "1"://1 待审核
                    switch (result.getData().getIdentity()) {
                        //身份 0：游客，既不是A球队成员，也不是B球队成员； 1：A队成员；2：B队成员；3：AB两队共同成员； 注意：该值在比赛类型为训练时，不返回
                        case "1"://1：A队成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":

                                    break;
                                case "1"://1：超级管理
                                    break;
                                case "2"://2：普通管理；
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "3"://3：AB两队共同成员；
                            break;
                        case "0"://身份 0：游客，既不是A球队成员，也不是B球队成员；
                            break;
                        default:
                            break;
                    }
                    break;
                case "2"://待应战
                    switch (result.getData().getIdentity()) {
                        //身份 0：游客，既不是A球队成员，也不是B球队成员； 1：A队成员；2：B队成员；3：AB两队共同成员； 注意：该值在比赛类型为训练时，不返回
                        case "2"://2：B队成员
                            switch (result.getData().getJurisdictionB()) {
                                //在B球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客或者比赛类型为训练时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "1"://1：A队成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "3"://报名中
                    switch (result.getData().getIdentity()) {
                        //身份 0：游客，既不是A球队成员，也不是B球队成员； 1：A队成员；2：B队成员；3：AB两队共同成员； 注意：该值在比赛类型为训练时，不返回
                        case "2"://2：B队成员
                            switch (result.getData().getJurisdictionB()) {
                                //在B球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客或者比赛类型为训练时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "1"://1：A队成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "4"://报名截止
                    switch (result.getData().getIdentity()) {
                        //身份 0：游客，既不是A球队成员，也不是B球队成员； 1：A队成员；2：B队成员；3：AB两队共同成员； 注意：该值在比赛类型为训练时，不返回
                        case "2"://2：B队成员
                            switch (result.getData().getJurisdictionB()) {
                                //在B球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客或者比赛类型为训练时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "1"://1：A队成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        more_img.setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case "1"://1：超级管理
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                case "2"://2：普通管理；
                                    more_img.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "6"://已结束
                    if ("1".equals(result.getData().getGame_info().getGame_status())) {
                    }
                    //score_status	int	比分录入状态 0：未录入 1：已录入
                    //entering_status:0：前端录入比分 1：后台录入比分
                    switch (result.getData().getIdentity()) {
                        //身份 0：游客，既不是A球队成员，也不是B球队成员； 1：A队成员；2：B队成员；3：AB两队共同成员； 注意：该值在比赛类型为训练时，不返回
                        case "1"://1：A队成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":
                                    //创建者，未录入，手机端录入显示
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                            if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                            }
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "1"://1：超级管理
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "2"://2：普通管理；
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "2"://2：B队成员
                            switch (result.getData().getJurisdictionB()) {
                                //在B球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客或者比赛类型为训练时，该值不返回
                                case "0":
                                    //创建者，未录入，手机端录入显示
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                            if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                            }
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "1"://1：超级管理
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "2"://2：普通管理；
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "3"://3：AB两队共同成员；
                            switch (result.getData().getJurisdictionA()) {
                                //在A球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客时，该值不返回
                                case "0":
                                    //创建者，未录入，手机端录入显示
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                            if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                            }
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "1"://1：超级管理
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "2"://2：普通管理；
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            switch (result.getData().getJurisdictionB()) {
                                //在B球队的权限 0：无权限 1：超级管理 2：普通管理； 注意：当身份为游客或者比赛类型为训练时，该值不返回
                                case "0":
                                    //创建者，未录入，手机端录入显示
                                    if (result.getData().getGame_info().getUser_id().equals(C.SP.USER_ID)) {
                                        if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                            if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                            }
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "1"://1：超级管理
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                //管理，未录入，手机端录入
                                case "2"://2：普通管理；
                                    if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                        if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
            }
        }
    }

    @Override
    public void setCancelCollectData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("取消收藏比赛成功");
            shoucang.setImageResource(R.drawable.love_n);
            presenter.selectGameInfo(this, game_id);
        }
    }

    @Override
    public void cancleGame() {
        cancle_lin.setVisibility(View.GONE);
        vv_z.setVisibility(View.GONE);
        ToastAlone.show("取消成功");
        finish();
    }

    @Override
    public void setVideo(String software, String hardware, String url) {
        add_lin.removeAllViews();
        NEVideoPlayerActivity2 b = new NEVideoPlayerActivity2(this, "software", "hardware", url, this,1);
        add_lin.addView(b);
        add_lin.setVisibility(View.VISIBLE);
        rel_hehe.setVisibility(View.GONE);
    }

    @Override
    public void close() {
        add_lin.removeAllViews();
        add_lin.setVisibility(View.GONE);
        rel_hehe.setVisibility(View.VISIBLE);
    }
}
