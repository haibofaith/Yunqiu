package com.kball.function.Match.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.CloudBall.ui.BallPlayerAct;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Login.presenter.MatchAboutPresenter;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.impls.MatchAboutViews;
import com.kball.function.home.view.DuiHuiView;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/24.
 */

public class MatchAboutView extends RelativeLayout implements MatchAboutViews, View.OnClickListener {
    private Context context;
    private LinearLayout lin;
    public static MatchAboutPresenter mPresenter;
    private TextView match_name, time_tv, didian_tv, saizhi_tv, benfang_tv, duifang_tv, feiyong, fuwu;
    private String game_id;
    private LinearLayout ybm_lin;
    private LinearLayout dd_lin;
    private LinearLayout qj_lin;
    private TextView qj_title, dd_title, bm_title;
    private DuiHuiView mDuiHuiView;

    private LinearLayout authority;//权限1：等待审核，管理员
    private TextView pass;//通过
    private TextView no_pass;//不通过

    private LinearLayout authority2;//权限2：等待迎战，对方管理员
    private TextView fight;//应战
    private TextView no_fight;//不应战

    private LinearLayout authority3;//权限3：报名中，所有人
    private LinearLayout baoming_detail_lin;//
    private TextView qingjia, daiding, baoming;

    private String type = "";
    private String cause;
    private String team_id;
    private String team_name;
    private String join_status;//1：报名 2：待定 3：请假

    private RelativeLayout duifang_rlin,qj_rel,daiding_rel;
    public static TextView duifang_df;
    public static String uniform_teamB;

    private RelativeLayout ybm_rlin;
    private RelativeLayout daiding_rlin;
    private RelativeLayout qingjia_rlin;

    private ArrayList<MemberBean> member;
    private String left_img;
    private String right_img;

    private TextView enter_score_tv;
    private LinearLayout authority4;//权限4：录入战报

    private MatchProgrammeAct activity;
    private RelativeLayout cancle_lin;//取消
    private TextView reason_tv;
    private TextView delete_match;
    private RelativeLayout refuse_sh_lin;//拒绝审核
    private RelativeLayout refuse_yz_lin;//拒绝审核
    private TextView refuse_sh_tv;//拒绝审核原因
    private TextView refuse_yz_tv;//拒绝审核原因


    private MatchAboutView(Context context, LinearLayout lin, String game_id, DuiHuiView v) {
        super(context);
        mPresenter = new MatchAboutPresenter(this);
        mDuiHuiView = v;
        init(context, lin, game_id);
    }

    private void init(Context context, LinearLayout lin, String game_id) {
        this.context = context;
        this.lin = lin;
        this.game_id = game_id;
        View v = LayoutInflater.from(context).inflate(R.layout.match_about_view, lin);
        match_name = (TextView) v.findViewById(R.id.match_name);
        time_tv = (TextView) v.findViewById(R.id.time_tv);
        didian_tv = (TextView) v.findViewById(R.id.didian_tv);
        saizhi_tv = (TextView) v.findViewById(R.id.saizhi_tv);
        benfang_tv = (TextView) v.findViewById(R.id.benfang_tv);
        duifang_tv = (TextView) v.findViewById(R.id.duifang_tv);
        qj_title = (TextView) v.findViewById(R.id.qj_title);
        dd_title = (TextView) v.findViewById(R.id.dd_title);
        bm_title = (TextView) v.findViewById(R.id.bm_title);
        feiyong = (TextView) v.findViewById(R.id.feiyong);
        qingjia = (TextView) v.findViewById(R.id.qingjia);
        daiding = (TextView) v.findViewById(R.id.daiding);
        baoming = (TextView) v.findViewById(R.id.baoming);
        fuwu = (TextView) v.findViewById(R.id.fuwu);
        ybm_lin = (LinearLayout) v.findViewById(R.id.ybm_lin);
        dd_lin = (LinearLayout) v.findViewById(R.id.dd_lin);
        qj_lin = (LinearLayout) v.findViewById(R.id.qj_lin);
        qj_rel = (RelativeLayout) v.findViewById(R.id.qj_rel);

        authority = (LinearLayout) v.findViewById(R.id.authority);
        pass = (TextView) v.findViewById(R.id.pass);
        no_pass = (TextView) v.findViewById(R.id.no_pass);

        authority2 = (LinearLayout) v.findViewById(R.id.authority2);
        fight = (TextView) v.findViewById(R.id.fight);
        no_fight = (TextView) v.findViewById(R.id.no_fight);

        authority3 = (LinearLayout) v.findViewById(R.id.authority3);
        baoming_detail_lin = (LinearLayout) v.findViewById(R.id.baoming_detail_lin);

        duifang_rlin = (RelativeLayout) v.findViewById(R.id.duifang_rlin);
        duifang_df = (TextView) v.findViewById(R.id.duifang_df);

        ybm_rlin = (RelativeLayout)v.findViewById(R.id.ybm_rlin);
        daiding_rlin = (RelativeLayout)v.findViewById(R.id.daiding_rlin);
        qingjia_rlin = (RelativeLayout)v.findViewById(R.id.qingjia_rlin);
        daiding_rel = (RelativeLayout)v.findViewById(R.id.daiding_rel);

        enter_score_tv = (TextView) v.findViewById(R.id.enter_score_tv);
        authority4 = (LinearLayout) v.findViewById(R.id.authority4);

        cancle_lin = (RelativeLayout) v.findViewById(R.id.cancle_lin);
        reason_tv = (TextView) v.findViewById(R.id.reason_tv);
        delete_match = (TextView) v.findViewById(R.id.delete_match);

        refuse_sh_lin = (RelativeLayout) v.findViewById(R.id.refuse_sh_lin);
        refuse_sh_tv = (TextView) v.findViewById(R.id.refuse_sh_tv);

        refuse_yz_lin = (RelativeLayout) v.findViewById(R.id.refuse_yz_lin);
        refuse_yz_tv = (TextView) v.findViewById(R.id.refuse_yz_tv);

        activity = (MatchProgrammeAct) context;
        setOnClick();
        initData();
    }

    private void setOnClick() {
        qingjia.setOnClickListener(this);
        daiding.setOnClickListener(this);
        baoming.setOnClickListener(this);
        pass.setOnClickListener(this);
        no_pass.setOnClickListener(this);
        fight.setOnClickListener(this);
        no_fight.setOnClickListener(this);
        duifang_rlin.setOnClickListener(this);
        ybm_rlin.setOnClickListener(this);
        qj_rel.setOnClickListener(this);
        daiding_rel.setOnClickListener(this);
        enter_score_tv.setOnClickListener(this);
        daiding_rlin.setOnClickListener(this);
        qingjia_rlin.setOnClickListener(this);
        delete_match.setOnClickListener(this);
    }


    private void initData() {
        mPresenter = new MatchAboutPresenter(this);
        mPresenter.selectGameInfo(context, game_id);
    }


    public static MatchAboutView matchAboutInit(Context context, LinearLayout lin, String game_id, DuiHuiView v) {
        return new MatchAboutView(context, lin, game_id, v);
    }

    @Override
    public void setData(BaseBean<GameInfoBean<MemberBean>> result) {
        member = result.getData().getMember();
        switch (result.getData().getIdentity()) {
            case "1"://A队成员
                team_id = result.getData().getGame_info().getEntry_teamA();
                team_name = result.getData().getGame_info().getTeamA_name();
                break;
            case "2"://B队成员
                team_id = result.getData().getGame_info().getEntry_teamB();
                team_name = result.getData().getGame_info().getTeamB_name();
                break;
            case "3"://AB队
                team_id = result.getData().getGame_info().getEntry_teamA();
                team_name = result.getData().getGame_info().getTeamA_name();
                break;
        }
        left_img = result.getData().getGame_info().getTeamA_badge();
        right_img = result.getData().getGame_info().getTeamB_badge();

        match_name.setText("比赛名称：" + result.getData().getGame_info().getGame_name());
        time_tv.setText("时间：" + timeChange(result.getData().getGame_info().getGame_time()));
        didian_tv.setText("地点：" + result.getData().getGame_info().getGame_site());
        saizhi_tv.setText("赛制：" + saizhi(result.getData().getGame_info().getGame_system()) + "制");
        benfang_tv.setText("本方队服：" + duifu(result.getData().getGame_info().getUniform_teamA()));
        duifang_tv.setText("对手队服：" + duifu(result.getData().getGame_info().getUniform_teamB()));
        feiyong.setText("比赛费用：" + result.getData().getGame_info().getGame_cost());
        fuwu.setText("视频/数据服务：" + fuwu(result.getData().getGame_info().getValue_added()));
        //取消原因
        reason_tv.setText(result.getData().getGame_info().getCancel_reason());
        refuse_sh_tv.setText(result.getData().getGame_info().getAudit_reason());
        refuse_yz_tv.setText(result.getData().getGame_info().getRefuse_reason());
        ybm_lin.removeAllViews();
        dd_lin.removeAllViews();
        qj_lin.removeAllViews();
        int ybm_num = 0;
        int dd_num = 0;
        int qj_num = 0;
        for (int i = 0; i < result.getData().getMember().size(); i++) {
            switch (result.getData().getMember().get(i).getStatus()) {
                case "1"://已报名
                    final View view = LayoutInflater.from(context).inflate(R.layout.head_img_view, null);
                    ImageView imageView = (ImageView) view.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView);
                    ybm_num++;
                    ybm_lin.addView(view);
                    break;
                case "2"://待定
                    final View view2 = LayoutInflater.from(context).inflate(R.layout.head_img_view, null);
                    ImageView imageView2 = (ImageView) view2.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView2);
                    dd_num++;
                    dd_lin.addView(view2);
                    break;
                case "3"://请假
                    final View view3 = LayoutInflater.from(context).inflate(R.layout.head_img_view, null);
                    ImageView imageView3 = (ImageView) view3.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView3);
                    qj_num++;
                    qj_lin.addView(view3);
                    break;

            }

        }
        qj_title.setText("请假 " + qj_num + "人");
        dd_title.setText("待定 " + dd_num + "人");
        bm_title.setText("已报名 " + ybm_num + "人");

        setUserStatus(result.getData().getJoin_status());
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
                                authority.setVisibility(View.VISIBLE);
                                break;
                            case "2"://2：普通管理；
                                authority.setVisibility(View.VISIBLE);
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
                                break;
                            case "1"://1：超级管理
                                authority2.setVisibility(View.VISIBLE);
                                break;
                            case "2"://2：普通管理；
                                authority2.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                        break;
                }
                break;
            case "3"://报名中
                authority3.setVisibility(View.VISIBLE);

                baoming_detail_lin.setVisibility(View.VISIBLE);
                break;
            case "4"://报名截止
                baoming_detail_lin.setVisibility(View.VISIBLE);
                break;
            case "7"://已取消
                baoming_detail_lin.setVisibility(View.VISIBLE);
                cancle_lin.setVisibility(View.VISIBLE);
                break;
            case "8"://8：拒绝比赛 9：拒绝应战
                refuse_sh_lin.setVisibility(View.VISIBLE);
                break;
            case "9"://8：拒绝比赛 9：拒绝应战
                refuse_yz_lin.setVisibility(View.VISIBLE);
                break;
            case "6"://已结束
                if ("1".equals(result.getData().getGame_info().getGame_status())) {
                    mDuiHuiView.setBf(result.getData().getGame_info().getScore_teamA(), result.getData().getGame_info
                            ().getScore_teamB());
                }
                authority3.setVisibility(View.GONE);
                baoming_detail_lin.setVisibility(View.VISIBLE);
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
                                            authority4.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "1"://1：超级管理
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "2"://2：普通管理；
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
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
                                            authority4.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "1"://1：超级管理
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "2"://2：普通管理；
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
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
                                            authority4.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "1"://1：超级管理
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "2"://2：普通管理；
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
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
                                            authority4.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "1"://1：超级管理
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                            //管理，未录入，手机端录入
                            case "2"://2：普通管理；
                                if ("0".equals(result.getData().getGame_info().getScore_status())) {
                                    if ("0".equals(result.getData().getGame_info().getEntering_status())) {
                                        authority4.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                }
        }
        mDuiHuiView.setDuihui(result.getData().getGame_info().getTeamA_badge(), result.getData().getGame_info()
                .getTeamB_badge(), result.getData().getGame_info().getEntry_teamA(), result.getData().getGame_info()
                .getEntry_teamB(),result.getData().getGame_info().getTeamA_name(),result.getData().getGame_info().getTeamB_name());
        if (result.getData().getGame_info().getClassify() != null && result.getData().getGame_info()
                .getUser_id() != null && result.getData().getGame_info().getJurisdictionA() != null && result.getData().getGame_info()
                .getIdentity() != null) {
            mDuiHuiView.setManager(result.getData().getGame_info().getClassify(), result.getData().getGame_info()
                    .getUser_id(), result.getData().getGame_info().getJurisdictionA(), result.getData().getGame_info()
                    .getIdentity());
        }
    }

    @Override
    public void setAuditOrFightGameData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (type) {
                case "1":
                    ToastAlone.show("审核比赛成功");
                    break;
                case "2":
                    ToastAlone.show("应战比赛成功");
                    break;
                default:
                    ToastAlone.show("审核/应战比赛成功");
                    break;
            }

        }
    }

    @Override
    public void setParticipationGameData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (join_status) {
                //1：报名 2：待定 3：请假
                case "1"://报名成功
                    baoming.setVisibility(View.GONE);
                    daiding.setVisibility(View.GONE);
                    qingjia.setVisibility(View.VISIBLE);
                    mPresenter.selectGameInfo(context, game_id);
                    break;
                case "2"://待定成功
                    baoming.setVisibility(View.VISIBLE);
                    daiding.setVisibility(View.GONE);
                    qingjia.setVisibility(View.VISIBLE);
                    mPresenter.selectGameInfo(context, game_id);
                    break;
                case "3":
                    baoming.setVisibility(View.VISIBLE);
                    daiding.setVisibility(View.GONE);
                    qingjia.setVisibility(View.GONE);
                    mPresenter.selectGameInfo(context, game_id);
                    break;
            }
        }
    }

    @Override
    public void cancleGame() {

    }

    public void setUserStatus(String userStatus) {

        if ("0".equals(userStatus)) {
            qingjia.setVisibility(View.VISIBLE);
            baoming.setVisibility(View.VISIBLE);
            daiding.setVisibility(View.VISIBLE);

        } else if ("1".equals(userStatus)) {
            qingjia.setVisibility(View.VISIBLE);
            baoming.setVisibility(View.GONE);
            daiding.setVisibility(View.GONE);

        } else if ("2".equals(userStatus)) {
            qingjia.setVisibility(View.VISIBLE);
            baoming.setVisibility(View.VISIBLE);
            daiding.setVisibility(View.GONE);

        } else if ("3".equals(userStatus)) {
            qingjia.setVisibility(View.GONE);
            baoming.setVisibility(View.VISIBLE);
            daiding.setVisibility(View.GONE);

        }
    }

    private String saizhi(String str) {
        if ("1".equals(str)) {
            return "3人";
        } else if ("2".equals(str)) {
            return "5人";
        } else if ("3".equals(str)) {
            return "7人";
        } else if ("4".equals(str)) {
            return "8人";
        } else if ("5".equals(str)) {
            return "9人";
        } else if ("6".equals(str)) {
            return "11人";
        } else {
            return "0人";
        }
    }

    private String fuwu(String str) {
        if ("0".equals(str)) {
            return "暂不选购";
        } else if ("1".equals(str)) {
            return "数据";
        } else if ("2".equals(str)) {
            return "视频";
        } else if ("3".equals(str)) {
            return "视频+数据";
        } else {
            return "";
        }
    }

    private String duifu(String str) {
        if ("1".equals(str)) {
            return "红色";
        } else if ("2".equals(str)) {
            return "蓝色";
        } else if ("3".equals(str)) {
            return "白色";
        } else if ("4".equals(str)) {
            return "紫色";
        } else if ("5".equals(str)) {
            return "橙色";
        } else if ("6".equals(str)) {
            return "黄色";
        } else if ("7".equals(str)) {
            return "绿色";
        } else if ("8".equals(str)) {
            return "灰色";
        } else if ("9".equals(str)) {
            return "黑色";
        } else if ("10".equals(str)) {
            return "粉色";
        }
        return "";
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long time = Long.parseLong(str);
        String d = format.format(time);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ybm_rlin:
                context.startActivity(new Intent().setClass(context, PeopleListAct.class).putExtra("type", "1").putExtra
                        ("member", member));
                break;
            case R.id.daiding_rlin:
                context.startActivity(new Intent().setClass(context, PeopleListAct.class).putExtra("type", "2").putExtra
                        ("member", member));
                break;
            case R.id.qingjia_rlin:
                context.startActivity(new Intent().setClass(context, PeopleListAct.class).putExtra("type", "3").putExtra
                        ("member", member));
                break;
            case R.id.baoming:
                //1：报名 2：待定 3：请假
                join_status = "1";
                mPresenter.participationGame(context, game_id, team_id, join_status);
                break;
            case R.id.qingjia:
                join_status = "3";
                mPresenter.participationGame(context, game_id, team_id, join_status);
                break;
            case R.id.daiding:
                join_status = "2";
                mPresenter.participationGame(context, game_id, team_id, join_status);
                break;
            case R.id.pass://通过
                type = "1";
                mPresenter.auditOrFightGame(context, game_id, type, "1", cause, uniform_teamB);
                break;
            case R.id.no_pass://不通过
                type = "1";
                mPresenter.auditOrFightGame(context, game_id, type, "2", cause, uniform_teamB);
                break;
            case R.id.fight://应战
                type = "2";
                activity.duifu_lin.setVisibility(View.VISIBLE);
                activity.zz_view.setVisibility(View.VISIBLE);
//                mPresenter.auditOrFightGame(context, game_id, type, "1", cause, uniform_teamB);
                break;
            case R.id.no_fight://不应战
                type = "2";
                mPresenter.auditOrFightGame(context, game_id, type, "2", cause, uniform_teamB);
                break;
            case R.id.duifang_rlin://对方
//                activity.duifu_lin.setVisibility(View.VISIBLE);
//                activity.zz_view.setVisibility(View.VISIBLE);
                break;
            case R.id.enter_score_tv:
                context.startActivity(new Intent(context, EntryScoreActivity.class).putExtra("team_id", team_id).putExtra("team_name", team_name).putExtra("left_img", left_img).putExtra("right_img", right_img).putExtra("game_id", game_id));
                break;
        }
    }


}
