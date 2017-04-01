package com.kball.function.CloudBall.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.CloudBall.adapter.MyPagerAdapter;
import com.kball.function.CloudBall.bean.DuihuiBean;
import com.kball.function.CloudBall.bean.DynamicBean;
import com.kball.function.CloudBall.bean.RankBaseBean;
import com.kball.function.CloudBall.presenter.CloudBallShowPresenter;
import com.kball.function.CloudBall.view.CloudBallShowActViews;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.ui.CreatScheduleAct;
import com.kball.function.Match.ui.MatchProgrammeAct;
import com.kball.function.Match.ui.XLDetailAct;
import com.kball.function.home.impl.DialogView;
import com.kball.function.other.CircleImageView;
import com.kball.function.other.cropper.Handle;
import com.kball.util.LoadingDialog;
import com.kball.util.PublicUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class CloudBallShowActView extends RelativeLayout implements CloudBallShowActViews {

    private CloudBallShowPresenter mPresenter;
    private Context mContext;
    private ViewPager vPager;
    private ArrayList<View> viewList;
    private MyPagerAdapter mAdapter;
    private RelativeLayout viewPagerContainer;
    private LinearLayout tuijian_lin;
    private TextView time_tv, ranks_name, teamA_name, teamB_name, bifen, shiping, date_tv, rank_name, add, game_name;
    private ImageView imgA, imgB, add_img, message_tv;
    private TextView week_tv, hour_tv, qingjia, daiding, baoming;//请假，报名，待定
    private LinearLayout match_detail_lin, rank_lin, qj_lin;
    private RelativeLayout end_rel, start_rel, huanyihuan;
    private int position;
    private ArrayList<DuihuiBean> info;
    private RelativeLayout rel_type;
    private TextView creat_btn, join_btn;
    private String type;
    String join_status = "";
    String team_id, game_id, game_id1;

    private TextView qiuyi_img, fast_creat_tv;
    private LinearLayout fast_creat;
    private LoadingDialog loadingDialog;
    private DialogView mh;

    private TextView gundong_tv;
    Animation top_to_bottom, bottom_to_up;

    private ArrayList<String> strs = new ArrayList<>();
    private static int countStrs = 0;

    private boolean flag =true;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 110:
                    if (flag) {
                        //中间向上动
                        gundong_tv.startAnimation(top_to_bottom);
                    } else {
                        gundong_tv.startAnimation(bottom_to_up);
                    }
                    break;

                default:
                    break;
            }
        }

    };

    private CloudBallShowActView(Context context, LinearLayout lin, DialogView mh) {
        super(context);
        this.mContext = context;
        mPresenter = new CloudBallShowPresenter(this);
        viewList = new ArrayList<View>();
        this.mh = mh;


        bottom_to_up = AnimationUtils
                .loadAnimation(mContext, R.anim.bottom_to_up);
        top_to_bottom = AnimationUtils.loadAnimation(mContext,
                R.anim.top_to_bottom);
        top_to_bottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //中间向上动
                flag =false;
                //改变文案在此处
                countStrs ++;
                gundong_tv.setText(strs.get(countStrs));
                mHandler.sendEmptyMessage(110);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bottom_to_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                flag = true;
                if (countStrs==strs.size()-1) {
                    return;
                }else{
                    mHandler.sendEmptyMessageDelayed(110,3000);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.cloud_ball_show_act, lin);
        vPager = (ViewPager) v.findViewById(R.id.vPager);
        tuijian_lin = (LinearLayout) v.findViewById(R.id.tuijian_lin);
        rank_lin = (LinearLayout) v.findViewById(R.id.rank_lin);
        match_detail_lin = (LinearLayout) v.findViewById(R.id.match_detail_lin);
        qj_lin = (LinearLayout) v.findViewById(R.id.qj_lin);
        fast_creat = (LinearLayout) v.findViewById(R.id.fast_creat);
        time_tv = (TextView) v.findViewById(R.id.time_tv);
        ranks_name = (TextView) v.findViewById(R.id.ranks_name);
        teamA_name = (TextView) v.findViewById(R.id.teamA_name);
        teamB_name = (TextView) v.findViewById(R.id.teamB_name);
        gundong_tv = (TextView) v.findViewById(R.id.gundong_tv);
        date_tv = (TextView) v.findViewById(R.id.date_tv);
        shiping = (TextView) v.findViewById(R.id.shiping);
        creat_btn = (TextView) v.findViewById(R.id.creat_btn);
        join_btn = (TextView) v.findViewById(R.id.join_btn);
        rank_name = (TextView) v.findViewById(R.id.rank_name);
        fast_creat_tv = (TextView) v.findViewById(R.id.fast_creat_tv);
        add = (TextView) v.findViewById(R.id.add);
        game_name = (TextView) v.findViewById(R.id.game_name);
        bifen = (TextView) v.findViewById(R.id.bifen);
        week_tv = (TextView) v.findViewById(R.id.week_tv);
        end_rel = (RelativeLayout) v.findViewById(R.id.end_rel);
        start_rel = (RelativeLayout) v.findViewById(R.id.start_rel);
        huanyihuan = (RelativeLayout) v.findViewById(R.id.huanyihuan);
        rel_type = (RelativeLayout) v.findViewById(R.id.rel_type);
        hour_tv = (TextView) v.findViewById(R.id.hour_tv);
        qingjia = (TextView) v.findViewById(R.id.qingjia);
        daiding = (TextView) v.findViewById(R.id.daiding);
        baoming = (TextView) v.findViewById(R.id.baoming);
        imgA = (ImageView) v.findViewById(R.id.imgA);
        imgB = (ImageView) v.findViewById(R.id.imgB);
        add_img = (ImageView) v.findViewById(R.id.add_img);
        message_tv = (ImageView) v.findViewById(R.id.message_tv);
        qiuyi_img = (TextView) v.findViewById(R.id.qiuyi_img);
        viewPagerContainer = (RelativeLayout) v.findViewById(R.id.viewPagerContainer);

        mPresenter.getDuihui(mContext);
//        mTjPresenter.getData();
        shiping.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastAlone.show("功能正在开发中");
            }
        });
        //1：报名 2：待定 3：请假
        qingjia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                join_status = "3";
                mPresenter.participationGame(mContext, game_id1, team_id, join_status);
            }
        });
        daiding.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                join_status = "2";
                mPresenter.participationGame(mContext, game_id1, team_id, join_status);
            }
        });
        baoming.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                join_status = "1";
                mPresenter.participationGame(mContext, game_id1, team_id, join_status);
            }
        });
        join_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastAlone.show("功能正在开发中");
            }
        });
        creat_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent().setClass(mContext, CreatRankAct.class).putExtra("act_type", C.SP
                        .CREATE_RANK));
            }
        });
        huanyihuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getRankInfo(mContext, info.get(position).getTeam_id(), position);
            }
        });
        add_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent().setClass(mContext, AddMatchOrPeopleAct.class));
            }
        });
        message_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent().setClass(mContext, MessageAct.class));
            }
        });

    }

    public void setData(final BaseBean<RankBaseBean> data) {

        if (null == data.getData().getStart_game()) {
            start_rel.setVisibility(View.GONE);
            qj_lin.setVisibility(View.GONE);
        } else {
            game_id1 = data.getData().getStart_game().getGame_id();
            if ("1".equals(type)) {
                start_rel.setVisibility(View.GONE);
                qj_lin.setVisibility(View.GONE);

            } else {
                start_rel.setVisibility(View.VISIBLE);
                qj_lin.setVisibility(View.VISIBLE);
            }
            match_detail_lin.removeAllViews();
            for (int i = 0; i < data.getData().getStart_game().getMatch().size(); i++) {
                View v = View.inflate(mContext, R.layout.img_item, null);
                CircleImageView hehe_img = (CircleImageView) v.findViewById(R.id.hehe_img);

                ImageLoader.getInstance().displayImage(C.SP.IMG_URL + data.getData().getStart_game().getMatch().get
                        (i).getPortrait(), hehe_img);
                match_detail_lin.addView(v);

            }


            if (!TextUtils.isEmpty(data.getData().getStart_game().getUniform_team())) {

                if ("1".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color1);
                } else if ("2".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color2);
                } else if ("3".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color3);
                } else if ("4".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color4);
                } else if ("5".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color5);
                } else if ("6".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color6);
                } else if ("7".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color7);
                } else if ("8".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color8);
                } else if ("9".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color9);
                } else if ("10".equals(data.getData().getStart_game().getUniform_team())) {
                    qiuyi_img.setBackgroundResource(R.drawable.qiuyi_color10);
                }

            }
            date_tv.setText(PublicUtil.getStrTime(data.getData().getStart_game().getGame_time(), "yyyy年MM月dd日"));
            rank_name.setText(data.getData().getStart_game().getName());
            add.setText(data.getData().getStart_game().getGame_site());
            game_name.setText(data.getData().getStart_game().getGame_name());
            week_tv.setText(PublicUtil.getWeekOfDate(data.getData().getStart_game().getGame_time()));
            hour_tv.setText(PublicUtil.getStrTime(data.getData().getStart_game().getGame_time(), "HH:mm"));

            start_rel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("3".equals(data.getData().getStart_game().getClassify())) {
                        mContext.startActivity(new Intent().setClass(mContext, XLDetailAct.class).putExtra("game_id",
                                data.getData().getStart_game().getGame_id()));
                    } else {
                        mContext.startActivity(new Intent().setClass(mContext, MatchProgrammeAct.class).putExtra
                                ("game_id", data.getData().getStart_game().getGame_id()));

                    }
                }
            });
            switch (data.getData().getStart_game().getRegistration_status()) {
//                当前报名状态 0:未登记 1：已报名 2：待定 3：请假
                case "0"://0:未登记
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    daiding.setTextColor(getResources().getColor(R.color.color_63));
                    daiding.setEnabled(true);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
                case "1"://1：已报名
                    baoming.setTextColor(getResources().getColor(R.color.color_6c));
                    baoming.setEnabled(false);
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
                case "2"://2：待定
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
                case "3"://3：请假
                    qingjia.setTextColor(getResources().getColor(R.color.color_6c));
                    qingjia.setEnabled(false);
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    break;
                default:
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    daiding.setTextColor(getResources().getColor(R.color.color_63));
                    daiding.setEnabled(true);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
            }
        }

        if (null == data.getData().getEnd_game()) {
            end_rel.setVisibility(View.GONE);
        } else {
            game_id = data.getData().getEnd_game().getGame_id();
            if ("1".equals(type)) {
                end_rel.setVisibility(View.GONE);
            } else {
                end_rel.setVisibility(View.VISIBLE);
            }
            if ("1".equals(data.getData().getEnd_game().getIsVideo())) {
                shiping.setVisibility(View.VISIBLE);
            } else if ("2".equals(data.getData().getEnd_game().getIsVideo())) {
                shiping.setVisibility(View.GONE);
            }

            time_tv.setText(PublicUtil.getStrTime(data.getData().getEnd_game().getGame_time(), "yyyy年MM月dd日") + " " +
                    PublicUtil.getWeekOfDate1(data.getData().getEnd_game().getGame_time()));
            ranks_name.setText(data.getData().getEnd_game().getGame_name());
            teamA_name.setText(data.getData().getEnd_game().getTeamA_name());
            teamB_name.setText(data.getData().getEnd_game().getTeamB_name());
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + data.getData().getEnd_game().getTeamA_badge(), imgA);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + data.getData().getEnd_game().getTeamB_badge(), imgB);
            bifen.setText(data.getData().getEnd_game().getScore_teamA() + ":" + data.getData().getEnd_game()
                    .getScore_teamB());
            if ("3".equals(data.getData().getEnd_game().getClassify())) {
                imgB.setVisibility(View.INVISIBLE);

            }

            rank_lin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("3".equals(data.getData().getEnd_game().getClassify())) {
                        mContext.startActivity(new Intent().setClass(mContext, XLDetailAct.class).putExtra("game_id",
                                data.getData().getEnd_game().getGame_id()));
                    } else {
                        mContext.startActivity(new Intent().setClass(mContext, MatchProgrammeAct.class).putExtra
                                ("game_id", data.getData().getEnd_game().getGame_id()).putExtra("teamA", data.getData
                                ().getEnd_game().getTeamA_badge()).putExtra("teamB", data.getData().getEnd_game()
                                .getTeamB_badge()));

                    }
                }
            });
        }


        if (null == data.getData().getEnd_game() && null == data.getData().getStart_game()) {
            fast_creat.setVisibility(View.VISIBLE);
            fast_creat_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, CreatScheduleAct.class));
                }
            });
        } else {
            fast_creat.setVisibility(View.GONE);
        }

        strs.clear();
        if (null== data.getData().getDynamic()||0==data.getData().getDynamic().size()){
            for (DynamicBean b : data.getData().getDynamic()){
                strs.add(b.getDynamic_desc());
            }
        }
        //第一次塞数据
        if (null == strs || 0 == strs.size()) {

        } else {
            gundong_tv.setText(strs.get(0));
            mHandler.sendEmptyMessageDelayed(110, 3000);
        }
    }

    public static CloudBallShowActView homeInit(Context context, LinearLayout lin, DialogView mh) {
        return new CloudBallShowActView(context, lin, mh);
    }


    @Override
    public void setInfoData(final ArrayList<DuihuiBean> info, String type) {
        this.type = type;
        if ("1".equals(type)) {
            rel_type.setVisibility(View.VISIBLE);
            vPager.setVisibility(View.GONE);
            start_rel.setVisibility(View.GONE);
            qj_lin.setVisibility(View.GONE);
            end_rel.setVisibility(View.GONE);

        } else {
            vPager.setVisibility(View.VISIBLE);
            start_rel.setVisibility(View.VISIBLE);
            qj_lin.setVisibility(View.VISIBLE);
            end_rel.setVisibility(View.VISIBLE);
            rel_type.setVisibility(View.GONE);
        }
        this.info = info;

        for (int i = 0; i < info.size(); i++) {
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.cloud_ball_dh_item, null);
            TextView name_tv = (TextView) view1.findViewById(R.id.name_tv);
            CircleImageView img = (CircleImageView) view1.findViewById(R.id.img);
            name_tv.setText(info.get(i).getTeam_name());
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + info.get(i).getBadge(), img);

            ImageLoader.getInstance().loadImage(C.SP.IMG_URL + info.get(i).getBackground(), new
                    SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    viewPagerContainer.setBackground(new BitmapDrawable(mContext.getResources(), loadedImage));
                }
            });

            final int pos = i;
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id",
                            info.get(pos).getTeam_id()));
                }
            });
            viewList.add(view1);
        }
        if (info.size() > 0) {
            mPresenter.getRankInfo(mContext, info.get(0).getTeam_id(), 0);
        }
        mAdapter = new MyPagerAdapter(viewList);
        vPager.setAdapter(mAdapter);
        vPager.setOffscreenPageLimit(2);


//        huanyihuan.
        vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                mPresenter.getRankInfo(mContext, info.get(position).getTeam_id(), position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // to refresh frameLayout
                if (viewPagerContainer != null) {
                    viewPagerContainer.invalidate();
                }

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        viewPagerContainer.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vPager.dispatchTouchEvent(event);
            }

        });
    }

    @Override
    public void setInfo(final BaseBean<RankBaseBean> result, int position) {
        this.position = position;
        team_id = info.get(position).getTeam_id();
        setData(result);
        for (int i = 0; i < result.getData().getRecommend_team().size(); i++) {
            View v = View.inflate(mContext, R.layout.cloud_ball_item1, null);
            TextView time_tv = (TextView) v.findViewById(R.id.time_tv);


        }
        tuijian_lin.removeAllViews();
        for (int i = 0; i < result.getData().getRecommend_team().size(); i++) {
            View v = View.inflate(mContext, R.layout.cloud_ball_item, null);
            CircleView view1 = (CircleView) v.findViewById(R.id.view1);
            ImageView img = (ImageView) v.findViewById(R.id.img);
            TextView name_tv = (TextView) v.findViewById(R.id.name_tv);
            TextView add = (TextView) v.findViewById(R.id.add);
            RelativeLayout rel_tj = (RelativeLayout) v.findViewById(R.id.rel_tj);

            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getRecommend_team().get(i)
                    .getBadge(), img);
            name_tv.setText(result.getData().getRecommend_team().get(i).getTeam_name());
            add.setText(result.getData().getRecommend_team().get(i).getProvince() + result.getData()
                    .getRecommend_team().get(i).getCity() + result.getData().getRecommend_team().get(i).getArea());
            double dd = Double.parseDouble(result.getData().getRecommend_team().get(i).getPower());
            view1.setNumText((int) dd + "");
            view1.setNumTextSize(10);
            //设置占比
            double b = Double.parseDouble(result.getData().getRecommend_team().get(i).getPower());
            view1.setPercent(Float.parseFloat((b / 100) + ""));
            //设置圆环厚度
            view1.setThickness(2);
            //设置上下文字间距
            view1.setPaddingtext(5);


            final int pos = i;
            rel_tj.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id",
                            result.getData().getRecommend_team().get(pos).getTeam_id()));
                }
            });

            tuijian_lin.addView(v);
        }
    }


    @Override
    public void setParticipationGameData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (join_status) {
                //1：报名 2：待定 3：请假
                case "1"://报名成功
                    ToastAlone.show("报名成功");
                    mPresenter.getRankInfo(mContext, info.get(position).getTeam_id(), position);
                    baoming.setTextColor(getResources().getColor(R.color.color_6c));
                    baoming.setEnabled(false);
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
                case "2"://待定成功
                    ToastAlone.show("待定成功");
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    qingjia.setTextColor(getResources().getColor(R.color.color_63));
                    qingjia.setEnabled(true);
                    break;
                case "3":
                    ToastAlone.show("请假成功");
                    mPresenter.getRankInfo(mContext, info.get(position).getTeam_id(), position);
                    qingjia.setTextColor(getResources().getColor(R.color.color_6c));
                    qingjia.setEnabled(false);
                    daiding.setTextColor(getResources().getColor(R.color.color_6c));
                    daiding.setEnabled(false);
                    baoming.setTextColor(getResources().getColor(R.color.color_63));
                    baoming.setEnabled(true);
                    break;
            }
        }
    }

    @Override
    public void showLoading() {
        mh.show();
    }

    @Override
    public void dismissLoading() {
        mh.dismiss();
    }
}
