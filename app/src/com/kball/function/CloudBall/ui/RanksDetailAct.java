package com.kball.function.CloudBall.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.bean.ExploitsListBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordChildBean;
import com.kball.function.CloudBall.presenter.ExploitsPresenter;
import com.kball.function.CloudBall.presenter.RanksDetailPresenter;
import com.kball.function.CloudBall.view.ExploitsImpl;
import com.kball.function.CloudBall.view.RanksDetailImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.impls.MatchTabTwoViewImpl;
import com.kball.function.Match.presenter.MatchTabTwoPresenter;
import com.kball.function.Match.ui.MatchListAct;
import com.kball.function.Match.ui.UpdateMatchAct;
import com.kball.function.Mine.ui.HomeAbilityView;
import com.kball.function.home.bean.MemberBean;
import com.kball.function.home.bean.RanksBaseBean;
import com.kball.function.home.bean.TeamInfoBean;
import com.kball.function.other.CircleImageView;
import com.kball.util.ShareUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/22.
 */

public class RanksDetailAct extends BaseActivity implements View.OnClickListener, RanksDetailImpl,
        MatchTabTwoViewImpl, ExploitsImpl {

    private LinearLayout home_mine_lin, home_ability_lin;
    private RanksMilitaryView mRanksMilitaryView;
    private RelativeLayout zhanji_rel, saishi_rel, qiuyuan_rel, rongyu_rel;
    private RanksDetailPresenter mPresenter;
    private String team_id, team_name;
    private TextView fans_num, name_tv, address_tv, address_tv1, type_tv, age_tv, zhanli, zhanli_tv;
    private ImageView back_button, zhanfu_img;
    private HomeAbilityView homeAbilityView;
    private LinearLayout member_lin;
    private LinearLayout labels_lin;
    private MemberBean memberbean;
    private TextView person_name;
    private TeamInfoBean teamInfoBean;
    private String[] labels;
    private RelativeLayout list_first_item;
    //    private MatchListPresenter listPresenter;
    private MatchTabTwoPresenter presenter;
    private static int pageNum = 1;
    private static int pageSize = 10;
    private static int pageTotal = 0;
    private ArrayList<MatchTabTwoBean> mData;
    private ArrayList<ExploitsListRecordBean<ExploitsListRecordChildBean>> exploitsData;
    private ImageView img_bg;
    private ImageView title_img;
    private TextView match_name;
    private TextView time_tv1;
    private TextView time_tv2;
    private TextView address1_tv;
    private TextView match_type;
    private TextView ssxq_tv;

    private CircleImageView user_img;
    private String status;

    private ExploitsPresenter exploitsPresenter;
    private TextView zhanji_name, right_tv;

    private String shenfen = "";
    private String guanli = "";
    private String guanzhu = "";
    private TextView shenqing_tv, faqi_tv, guanli_tv, yaoqing_tv;
    private View zz_view;

    //管理
    private LinearLayout manager_lin;
    private TextView guanli_bianji, guanli_yijiao, guanli_qiuyuan, guanli_jiesan, guanli_xiugaiyaoqing, guanli_quxiao;
    private LinearLayout xgyq_lin, jiesan_lin;
    private TextView yaoqing_cancle, xiugai_enter, jiesan_tv, jiesan_cancle;
    private EditText xiugai_edit;
    private String yaoqingma = "";


    //申请加入
    private LinearLayout join_lin, join_lin_lin;
    private TextView join_tv, join_yq, join_qx, join_cancle, join_enter, join_tvtv;
    private EditText join_edit;
    private String joinStr;
    private int joinType = 1;

    BaseBean<RanksBaseBean> resultData;

    private RelativeLayout leida_rlin;
    private RelativeLayout cloud_home_bg;

    //邀请
    private LinearLayout yq_lin;
    private TextView jujueyq_tv, jieshiyq_tv;
    private String isYq = "";
    private String yq_str;

    @Override
    protected int getContentViewResId() {
        return R.layout.ranks_detail_act;
    }

    @Override
    protected void initView() {
        join_cancle = (TextView) findViewById(R.id.join_cancle);
        join_qx = (TextView) findViewById(R.id.join_qx);
        join_yq = (TextView) findViewById(R.id.join_yq);
        join_tv = (TextView) findViewById(R.id.join_tv);
        join_tvtv = (TextView) findViewById(R.id.join_tvtv);
        jujueyq_tv = (TextView) findViewById(R.id.jujueyq_tv);
        jieshiyq_tv = (TextView) findViewById(R.id.jieshiyq_tv);
        guanli_bianji = (TextView) findViewById(R.id.guanli_bianji);
        guanli_yijiao = (TextView) findViewById(R.id.guanli_yijiao);
        guanli_qiuyuan = (TextView) findViewById(R.id.guanli_qiuyuan);
        guanli_jiesan = (TextView) findViewById(R.id.guanli_jiesan);
        join_enter = (TextView) findViewById(R.id.join_enter);
        jiesan_tv = (TextView) findViewById(R.id.jiesan_tv);
        jiesan_cancle = (TextView) findViewById(R.id.jiesan_cancle);
        guanli_xiugaiyaoqing = (TextView) findViewById(R.id.guanli_xiugaiyaoqing);
        guanli_quxiao = (TextView) findViewById(R.id.guanli_quxiao);
        xgyq_lin = (LinearLayout) findViewById(R.id.xgyq_lin);
        jiesan_lin = (LinearLayout) findViewById(R.id.jiesan_lin);
        join_lin = (LinearLayout) findViewById(R.id.join_lin);
        join_lin_lin = (LinearLayout) findViewById(R.id.join_lin_lin);
        yq_lin = (LinearLayout) findViewById(R.id.yq_lin);
        xiugai_edit = (EditText) findViewById(R.id.xiugai_edit);
        join_edit = (EditText) findViewById(R.id.join_edit);
        zhanji_rel = (RelativeLayout) findViewById(R.id.zhanji_rel);
        saishi_rel = (RelativeLayout) findViewById(R.id.saishi_rel);
        qiuyuan_rel = (RelativeLayout) findViewById(R.id.qiuyuan_rel);
        rongyu_rel = (RelativeLayout) findViewById(R.id.rongyu_rel);
        fans_num = (TextView) findViewById(R.id.fans_num);
        name_tv = (TextView) findViewById(R.id.name_tv);
        address_tv = (TextView) findViewById(R.id.address_tv);
        address_tv1 = (TextView) findViewById(R.id.address_tv1);
        type_tv = (TextView) findViewById(R.id.type_tv);
        right_tv = (TextView) findViewById(R.id.right_tv);
        xiugai_enter = (TextView) findViewById(R.id.xiugai_enter);
        yaoqing_cancle = (TextView) findViewById(R.id.yaoqing_cancle);
        guanli_tv = (TextView) findViewById(R.id.guanli_tv);
        age_tv = (TextView) findViewById(R.id.age_tv);
        zhanli = (TextView) findViewById(R.id.zhanli);
        yaoqing_tv = (TextView) findViewById(R.id.yaoqing_tv);
        shenqing_tv = (TextView) findViewById(R.id.shenqing_tv);
        faqi_tv = (TextView) findViewById(R.id.faqi_tv);
        zhanli_tv = (TextView) findViewById(R.id.zhanli_tv);
        back_button = (ImageView) findViewById(R.id.back_button);
        user_img = (CircleImageView) findViewById(R.id.user_img);
        zhanfu_img = (ImageView) findViewById(R.id.zhanfu_img);
        home_ability_lin = (LinearLayout) findViewById(R.id.home_ability_lin);
        home_mine_lin = (LinearLayout) findViewById(R.id.home_mine_lin);
        manager_lin = (LinearLayout) findViewById(R.id.manager_lin);
        homeAbilityView = HomeAbilityView.homeAbilityInit(this, home_ability_lin);
        mRanksMilitaryView = RanksMilitaryView.RanksMilitaryInit(mContext, home_mine_lin);
        member_lin = (LinearLayout) findViewById(R.id.member_lin);
        labels_lin = (LinearLayout) findViewById(R.id.labels_lin);
        list_first_item = (RelativeLayout) findViewById(R.id.list_first_item);
        img_bg = (ImageView) findViewById(R.id.img_bg);
        title_img = (ImageView) findViewById(R.id.title_img);
        match_name = (TextView) findViewById(R.id.match_name);
        time_tv1 = (TextView) findViewById(R.id.time_tv1);
        time_tv2 = (TextView) findViewById(R.id.time_tv2);
        address1_tv = (TextView) findViewById(R.id.address1_tv);
        match_type = (TextView) findViewById(R.id.match_type);
        ssxq_tv = (TextView) findViewById(R.id.ssxq_tv);
        zhanji_name = (TextView) findViewById(R.id.zhanji_name);
        person_name = (TextView) findViewById(R.id.person_name);
        zz_view = (View) findViewById(R.id.zz_view);
        leida_rlin = (RelativeLayout) findViewById(R.id.leida_rlin);
        cloud_home_bg = (RelativeLayout) findViewById(R.id.cloud_home_bg);
    }

    @Override
    public void setListInfoData(BaseBean<RanksBaseBean> result) {
        isYq = result.getData().getSubjoin_info().getInvitationStatus();

        resultData = result;
        shenfen = result.getData().getSubjoin_info().getIdentity();
        guanli = result.getData().getSubjoin_info().getJurisdiction();
        guanzhu = result.getData().getSubjoin_info().getFocus_status();
        yaoqing_tv.setText("邀请码：" + result.getData().getTeam_info().getInvite());
        //身份处理 为零时 展示加入 、约战、关注
        if (isYq.equals("1")) {
            yq_lin.setVisibility(View.VISIBLE);
            yq_str = result.getData().getSubjoin_info().getInvitation_id();
        } else {
            yq_lin.setVisibility(View.GONE);
            if ("0".equals(shenfen)) {
                shenqing_tv.setVisibility(View.VISIBLE);

                //管理展示约战
                faqi_tv.setVisibility(View.VISIBLE);
                right_tv.setVisibility(View.VISIBLE);
                guanli_tv.setVisibility(View.GONE);
                fans_num.setVisibility(View.GONE);
                if ("1".equals(guanzhu)) {
                    right_tv.setText("关注");
                } else {
                    right_tv.setText("已关注");
                }
            } else {
                right_tv.setVisibility(View.GONE);
                guanli_tv.setVisibility(View.VISIBLE);
                shenqing_tv.setVisibility(View.GONE);
                faqi_tv.setVisibility(View.GONE);
                guanli_tv.setVisibility(View.VISIBLE);
                yaoqing_tv.setVisibility(View.VISIBLE);
                fans_num.setVisibility(View.VISIBLE);
                //管理展示约战
                if ("0".equals(guanli)) {
                    guanli_tv.setVisibility(View.GONE);
                } else {
                    guanli_tv.setVisibility(View.VISIBLE);
                }
            }
        }
        fans_num.setText("粉丝值：" + result.getData().getTeam_info().getFans_number());
        name_tv.setText(result.getData().getTeam_info().getTeam_name());
        team_name = result.getData().getTeam_info().getTeam_name();
        address_tv.setText("地区：" + result.getData().getTeam_info().getArea());
        address_tv1.setText("主场：" + result.getData().getTeam_info().getHome());
        type_tv.setText("类型：" + leixing(result.getData().getTeam_info().getTeam_type()));
        age_tv.setText("平均年龄：" + result.getData().getSubjoin_info().getMean_age());
        if (result.getData().getPower() == null) {
            zhanli.setText("综合评分：");
        } else {
            zhanli.setText("综合评分：" + result.getData().getPower().getMean_power());
        }
        if (result.getData().getPower() == null) {

        } else {
            zhanli_tv.setText(result.getData().getPower().getGains());
        }
        if (result.getData().getTeam_info() == null) {

        } else {
            imageLoader.displayImage(C.SP.IMG_URL + result.getData().getTeam_info().getBadge(), user_img);
        }

        if (result.getData().getTeam_info() == null) {

        } else {
            ImageLoader.getInstance().loadImage(C.SP.IMG_URL + result.getData().getTeam_info().getBackground(), new
                    SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    cloud_home_bg.setBackground(new BitmapDrawable(getApplicationContext().getResources(),
                            loadedImage));
                }
            });
        }

        if (result.getData().getPower() == null) {

        } else {
            zhangfu(Integer.parseInt(result.getData().getPower().getGains()));
        }
        mRanksMilitaryView.setData(result.getData().getGrade());
        homeAbilityView.setData1(result.getData().getPower());
        memberbean = result.getData().getMember();
        teamInfoBean = result.getData().getTeam_info();
        setMembersView(memberbean);
        setTeamInfo(teamInfoBean);
    }

    @Override
    public void setInvite() {
        ToastAlone.show("邀请码修改成功");
        mPresenter.indexTeamInfo(this, team_id);
        zz_view.setVisibility(View.GONE);
        xgyq_lin.setVisibility(View.GONE);
    }

    @Override
    public void applyJoinTeam() {
        join_lin_lin.setVisibility(View.GONE);
        zz_view.setVisibility(View.GONE);
        mPresenter.indexTeamInfo(this, team_id);
        ToastAlone.show("申请成功");
    }

    @Override
    public void dissolveTeam() {
        jiesan_lin.setVisibility(View.GONE);
        zz_view.setVisibility(View.GONE);
        ToastAlone.show("球队解散成功");
        finish();
    }

    @Override
    public void attentionTeam() {
        ToastAlone.show("球队关注成功");
        right_tv.setText("已关注");
        guanzhu = "2";
    }

    @Override
    public void cancelAttentionTeam() {
        ToastAlone.show("球队已取消关注");
        right_tv.setText("关注");
        guanzhu = "1";

    }

    @Override
    public void applyJoinTeamByInvite() {
        join_lin_lin.setVisibility(View.GONE);
        zz_view.setVisibility(View.GONE);
        mPresenter.indexTeamInfo(this, team_id);
        ToastAlone.show("申请成功");
    }

    @Override
    public void auditInvitation() {
        mPresenter.indexTeamInfo(this, team_id);
    }

    private void setTeamInfo(TeamInfoBean teamInfoBean) {
        if (teamInfoBean == null) {
            return;
        }
        if (teamInfoBean.getLabel() != null && teamInfoBean.getLabel().length() > 0) {
            String str = teamInfoBean.getLabel().replace("null", "");
            labels = str.split(",");
            if (labels.length > 0) {
                for (int i = 0; i < labels.length; i++) {
                    final View v = LayoutInflater.from(this).inflate(R.layout.labels_item_view, null);
                    TextView label_item = (TextView) v.findViewById(R.id.label_item);
                    label_item.setText(labels[i]);
                    labels_lin.addView(v);
                }
            }
        }

    }

    private void setMembersView(MemberBean memberbean) {
        if (memberbean == null || memberbean.getMember_list() == null || memberbean.getMember_list().size() == 0) {
            person_name.setText("球员（ 0 ）");
            return;
        }
        person_name.setText("球员（" + memberbean.getMember_list().size() + "）");
        for (int i = 0; i < memberbean.getMember_list().size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.head_img_view, null);
            CircleImageView imageView = (CircleImageView) view.findViewById(R.id.head_img_item);
            imageLoader.displayImage(C.SP.IMG_URL + memberbean.getMember_list().get(i).getPortrait(), imageView);
            member_lin.addView(view);
        }
    }

    private void zhangfu(int i) {
        if (i > 0) {
            zhanfu_img.setBackgroundResource(R.drawable.mine_up_icon);
        } else if (i < 0) {
            zhanfu_img.setBackgroundResource(R.drawable.mine_down_icon);
        } else if (i == 0) {
            zhanfu_img.setBackgroundResource(R.drawable.mine_keep_icon);
        }
    }

    private String leixing(String team_type) {
        if ("1".equals(team_type)) {
            return "校园球队";
        } else if ("2".equals(team_type)) {
            return "业余爱好";
        } else if ("3".equals(team_type)) {
            return "公司球队";
        } else if ("4".equals(team_type)) {
            return "青少年球队";
        } else return "";
    }

    @Override
    protected void initData() {
        team_id = getIntent().getStringExtra("team_id");
        mPresenter = new RanksDetailPresenter(this);
        mPresenter.indexTeamInfo(this, team_id);
        presenter = new MatchTabTwoPresenter(this);
        presenter.meTeamLeagueList(this, pageNum + "", pageSize + "", team_id, status);

        exploitsPresenter = new ExploitsPresenter(this);
        exploitsPresenter.teamRecord(this, pageNum + "", pageSize + "", team_id);
    }

    @Override
    protected void setListener() {
        join_tv.setOnClickListener(this);
        join_yq.setOnClickListener(this);
        join_qx.setOnClickListener(this);
        join_cancle.setOnClickListener(this);
        join_enter.setOnClickListener(this);
        jujueyq_tv.setOnClickListener(this);
        jieshiyq_tv.setOnClickListener(this);

        guanli_bianji.setOnClickListener(this);
        guanli_yijiao.setOnClickListener(this);
        guanli_qiuyuan.setOnClickListener(this);
        guanli_jiesan.setOnClickListener(this);
        guanli_xiugaiyaoqing.setOnClickListener(this);
        guanli_quxiao.setOnClickListener(this);
        yaoqing_cancle.setOnClickListener(this);
        xiugai_enter.setOnClickListener(this);
        jiesan_cancle.setOnClickListener(this);
        jiesan_tv.setOnClickListener(this);

        zhanji_rel.setOnClickListener(this);
        saishi_rel.setOnClickListener(this);
        qiuyuan_rel.setOnClickListener(this);
        rongyu_rel.setOnClickListener(this);
        back_button.setOnClickListener(this);
        right_tv.setOnClickListener(this);
        shenqing_tv.setOnClickListener(this);
        faqi_tv.setOnClickListener(this);
        yaoqing_tv.setOnClickListener(this);
        guanli_tv.setOnClickListener(this);
        zz_view.setOnClickListener(this);
        leida_rlin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jujueyq_tv:
                //TODO 拒绝邀请
                mPresenter.auditInvitation(mContext, yq_str, "2");
                break;
            case R.id.jieshiyq_tv:
                //TODO 接受
                mPresenter.auditInvitation(mContext, yq_str, "3");
                break;
            case R.id.join_enter:

                join_lin_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);


                joinStr = join_edit.getText().toString().trim();

                if (1 == joinType) {
                    mPresenter.applyJoinTeam(this, team_id, joinStr);
                } else {
                    mPresenter.applyJoinTeamByInvite(this, team_id, joinStr);
                }
                break;
            case R.id.join_cancle:
                join_lin_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.join_tv:
                join_lin.setVisibility(View.GONE);
                join_tvtv.setText("给管理员打个招呼");
                joinType = 1;
                join_lin_lin.setVisibility(View.VISIBLE);

                break;
            case R.id.join_yq:
                join_lin.setVisibility(View.GONE);
                join_lin_lin.setVisibility(View.VISIBLE);
                join_tvtv.setText("请输入邀请码");
                joinType = 2;

//
                break;
            case R.id.join_qx:
                join_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.jiesan_cancle:
                jiesan_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.jiesan_tv:

                mPresenter.dissolveTeam(this, team_id);
                break;
            case R.id.guanli_quxiao:
                zz_view.setVisibility(View.GONE);
                manager_lin.setVisibility(View.GONE);
                break;
            case R.id.guanli_bianji:
                zz_view.setVisibility(View.GONE);
                manager_lin.setVisibility(View.GONE);
                startActivityForResult(new Intent().setClass(mContext, CreatRankAct.class).putExtra("act_type", C.SP
                        .EDIT_RANK).putExtra("teamDetail", resultData.getData().getTeam_info()), 110);
                break;
            case R.id.guanli_yijiao:
                zz_view.setVisibility(View.GONE);
                manager_lin.setVisibility(View.GONE);

                startActivity(new Intent().setClass(mContext, MatchPeopleAct.class).putExtra("team_id", teamInfoBean
                        .getTeam_id()).putExtra("type", "yijiao"));
                break;
            case R.id.guanli_qiuyuan:
                zz_view.setVisibility(View.GONE);
                manager_lin.setVisibility(View.GONE);
                startActivity(new Intent().setClass(mContext, MatchPeopleAct.class).putExtra("team_id", teamInfoBean
                        .getTeam_id()).putExtra("type", "guanli"));
                break;
            case R.id.guanli_jiesan:
                manager_lin.setVisibility(View.GONE);
                jiesan_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.guanli_xiugaiyaoqing:
                manager_lin.setVisibility(View.GONE);
                xgyq_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.yaoqing_cancle:
                zz_view.setVisibility(View.GONE);
                xgyq_lin.setVisibility(View.GONE);
                break;
            case R.id.xiugai_enter:

                yaoqingma = xiugai_edit.getText().toString().trim();
                if (yaoqingma.length() < 4) {
                    ToastAlone.show("请输入4-6位数字或字母");
                    return;
                }
                //TODO
                mPresenter.changeTeamInvite(this, yaoqingma, team_id);
                break;

            case R.id.yaoqing_tv:
                ShareUtil.showShare(this, "云球", yaoqing_tv.getText().toString());
                break;
            case R.id.guanli_tv:
                zz_view.setVisibility(View.VISIBLE);
                manager_lin.setVisibility(View.VISIBLE);
                if ("1".equals(guanli)) {
                    guanli_yijiao.setVisibility(View.VISIBLE);
                    guanli_jiesan.setVisibility(View.VISIBLE);
                } else if ("2".equals(guanli)) {
                    guanli_yijiao.setVisibility(View.GONE);
                    guanli_jiesan.setVisibility(View.GONE);
                }

                break;
            case R.id.shenqing_tv:
                join_lin.setVisibility(View.VISIBLE);
                zz_view.setVisibility(View.VISIBLE);
                break;
            case R.id.faqi_tv:

                startActivity(new Intent().setClass(mContext, UpdateMatchAct.class).putExtra("act_type", 1).putExtra
                        ("teamId", team_id).putExtra("teamName", team_name));
                break;
            case R.id.right_tv:
                if ("1".equals(guanzhu)) {
                    mPresenter.attentionTeam(this, team_id);
                } else {
                    mPresenter.cancelAttentionTeam(this, team_id);
                }
                break;
            case R.id.zhanji_rel:
                if (teamInfoBean != null && teamInfoBean.getTeam_id() != null) {
                    startActivity(new Intent().setClass(mContext, ExploitsAct.class).putExtra("team_id", teamInfoBean
                            .getTeam_id()));
                }
                break;
            case R.id.saishi_rel:
                if (teamInfoBean != null && teamInfoBean.getTeam_id() != null) {
                    startActivity(new Intent().setClass(mContext, MatchListAct.class).putExtra("team_id",
                            teamInfoBean.getTeam_id()));
                }
                break;
            case R.id.qiuyuan_rel:
                startActivity(new Intent().setClass(mContext, BallPlayerAct.class).putExtra("team_id", teamInfoBean
                        .getTeam_id()));
                break;
            case R.id.rongyu_rel:
                startActivity(new Intent().setClass(mContext, RanksHonorAct.class).putExtra("team_id", teamInfoBean
                        .getTeam_id()));
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.leida_rlin:
                startActivity(new Intent(this, TeamCapabilityActivity.class).putExtra("team_id", teamInfoBean
                        .getTeam_id()));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 110:
                    mPresenter.indexTeamInfo(this, team_id);
                    break;
            }
        }
    }

    @Override
    public void setListData(BaseBean<BaseListBean<MatchTabTwoBean>> result) {
        if ("1200".equals(result.getError_code())) {
            mData = result.getData().getList();

            if (mData == null || mData.size() == 0) {
                list_first_item.setVisibility(View.GONE);
                ssxq_tv.setText("赛事详情（ 0 ）");
                return;
            }

            ssxq_tv.setText("赛事详情(" + mData.size() + ")");
            list_first_item.setVisibility(View.VISIBLE);

            imageLoader.displayImage(C.SP.IMG_URL + mData.get(0).getLeague_icon(), img_bg);

            match_name.setText(mData.get(0).getLeague_abbreviation() + "");
            time_tv1.setText("报名时间段：" + timeChange(mData.get(0).getApply_start_time()) + "-" + timeChange(mData.get
                    (0).getApply_end_time()) + "");
            time_tv2.setText("比赛时间段：" + timeChange(mData.get(0).getStart_time()) + "-" + timeChange(mData.get(0)
                    .getEnd_time()));
            address1_tv.setText("地点：" + mData.get(0).getProvince() + " " + mData.get(0).getCity() + " " + mData.get
                    (0).getArea() + "");
            match_type.setText("赛制：" + saizhi(mData.get(0).getGame_system()) + "");
            switch (mData.get(0).getStatus()) {
                case "1"://报名中
                    title_img.setImageResource(R.drawable.bmz_img_tab);
                    time_tv1.setVisibility(View.VISIBLE);
                    time_tv2.setVisibility(View.GONE);
                    break;
                case "2":
                    title_img.setVisibility(View.INVISIBLE);
                    time_tv1.setVisibility(View.GONE);
                    time_tv2.setVisibility(View.GONE);
                    break;
                case "3"://进行中
                    title_img.setImageResource(R.drawable.jxz_img_tab);
                    time_tv1.setVisibility(View.GONE);
                    time_tv2.setVisibility(View.VISIBLE);
                    break;
                case "4"://已结束
                    title_img.setImageResource(R.drawable.yjs_img_tab);
                    time_tv1.setVisibility(View.GONE);
                    time_tv2.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;

            }
        }
    }

    @Override
    public void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result) {

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

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
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

    //赛绩
    @Override
    public void setTeamRecordData(BaseBean<ExploitsListBean<ExploitsListRecordBean<ExploitsListRecordChildBean>>>
                                              result) {
        if ("1200".equals(result.getError_code())) {
            exploitsData = result.getData().getRecord();
            if (exploitsData == null || exploitsData.size() == 0) {
                zhanji_name.setText("球队战绩( 0 )");
                return;
            } else {
                zhanji_name.setText("球队战绩(" + exploitsData.size() + ")");
            }
        }
    }
}
