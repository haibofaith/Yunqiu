package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchAboutDataBean;
import com.kball.function.Match.bean.MatchDataAssistsBean;
import com.kball.function.Match.bean.MatchDataBean;
import com.kball.function.Match.bean.NewEntireBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Match.impls.MatchAboutTwoViews;
import com.kball.function.Match.presenter.MatchAboutDataPresenter;
import com.kball.function.Match.presenter.MatchAboutTwoPresenter;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.neliveplayerdemo.NEVideoPlayerActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchAboutDataView extends RelativeLayout implements MatchAboutDataViews, View.OnClickListener {
    private Context context;
    private LinearLayout lin;
    private MatchAboutDataPresenter mPresenter;
    private ImageLoader imageLoader;
    private String url;

    private LinearLayout all_data_1;
    private View v;
    private String game_id;

    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entireBean;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> goalBean;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> yellowBean;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> redBean;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> dqBean;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> wlqBean;

    private LinearLayout my_team;//进球
    private LinearLayout he_team;//

    private LinearLayout my_team_yellow;//
    private LinearLayout he_team_yellow;//

    private LinearLayout my_team_red;//
    private LinearLayout he_team_red;//

    private LinearLayout my_team_dq;//
    private LinearLayout he_team_dq;//

    private LinearLayout my_team_wlq;//
    private LinearLayout he_team_wlq;//

    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire1;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire2;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire3;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire4;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire5;
    private ArrayList<MatchDataBean<MatchDataAssistsBean>> entire6;
    private Double entireTotalNum1;
    private Double entireTotalNum2;
    private Double entireTotalNum3;
    private Double entireTotalNum4;
    private Double entireTotalNum5;
    private Double entireTotalNum6;

    private TextView goal_total;
    private TextView yellow_total;
    private TextView red_total;
    private TextView dq_total;
    private TextView wlq_total;
    private ArrayList<NewEntireBean> mEntireData;


    private MatchAboutDataView(Context context, LinearLayout lin, ImageLoader imageLoader, String game_id) {
        super(context);
        mPresenter = new MatchAboutDataPresenter(this);
        this.imageLoader = imageLoader;
        this.game_id = game_id;
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        this.context = context;
        this.lin = lin;
        v = LayoutInflater.from(context).inflate(R.layout.match_about_data_view, lin);
        my_team = (LinearLayout) v.findViewById(R.id.my_team);
        he_team = (LinearLayout) v.findViewById(R.id.he_team);
        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);

        my_team_yellow = (LinearLayout) v.findViewById(R.id.my_team_yellow);
        he_team_yellow = (LinearLayout) v.findViewById(R.id.he_team_yellow);

        my_team_red = (LinearLayout) v.findViewById(R.id.my_team_red);
        he_team_red = (LinearLayout) v.findViewById(R.id.he_team_red);

        my_team_dq = (LinearLayout) v.findViewById(R.id.my_team_dq);
        he_team_dq = (LinearLayout) v.findViewById(R.id.he_team_dq);

        my_team_wlq = (LinearLayout) v.findViewById(R.id.my_team_wlq);
        he_team_wlq = (LinearLayout) v.findViewById(R.id.he_team_wlq);

        goal_total = (TextView) v.findViewById(R.id.goal_total);
        yellow_total = (TextView) v.findViewById(R.id.yellow_total);
        red_total = (TextView) v.findViewById(R.id.red_total);
        dq_total = (TextView) v.findViewById(R.id.dq_total);
        wlq_total = (TextView) v.findViewById(R.id.wlq_total);

        setOnClick();
        initData();
    }

    private void setOnClick() {
    }


    private void initData() {
        mPresenter.selectGrand(context, game_id);
    }


    public static MatchAboutDataView matchAboutDataInit(Context context, LinearLayout lin, ImageLoader imageLoader,
                                                        String game_id) {
        return new MatchAboutDataView(context, lin, imageLoader, game_id);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void setMatchAboutData(BaseBean<MatchAboutDataBean<MatchDataBean<MatchDataAssistsBean>>> result) {
        if ("1200".equals(result.getError_code())) {
            entireBean = result.getData().getEntire();
            goalBean = result.getData().getGoal();
            yellowBean = result.getData().getYellow();
            redBean = result.getData().getRed();
            dqBean = result.getData().getPenalty();
            wlqBean = result.getData().getOwn();
            mEntireData = result.getData().getEntire_subsumption();
            setData();
        }
    }

    private void setData() {
        if (goalBean != null) {
            goal_total.setText(goalBean.size() + "");
        }
        if (yellow_total != null) {
            yellow_total.setText(yellowBean.size() + "");
        }
        if (red_total != null) {
            red_total.setText(redBean.size() + "");
        }
        if (dq_total != null) {
            dq_total.setText(dqBean.size() + "");
        }
        if (wlq_total != null) {
            wlq_total.setText(wlqBean.size() + "");
        }

//        setAllDataViews();
        setAllData();
        setGoalBeanView();
        setYellowView();
        setRedView();
        setDqView();
        setWlqView();
    }

    private void setAllData() {
//        mEntireData
        all_data_1.removeAllViews();
        for (int i = 0; i < mEntireData.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
            ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
            TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
            TextView right_tv = (TextView) view.findViewById(R.id.right_tv);

            try {
                double TotalNum = Double.parseDouble(mEntireData.get(i).getTotal_numberA()) + Double.parseDouble
                        (mEntireData.get(i).getTotal_numberB());

                left_tv.setText(mEntireData.get(i).getTotal_numberA());
                right_tv.setText(mEntireData.get(i).getTotal_numberB());

                if(TotalNum == 0){
                    TotalNum =1;
                }

                Double entirePercent1 = Double.parseDouble(mEntireData.get(i).getTotal_numberA()) /
                        TotalNum;
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                left_green.setLayoutParams(lp);


                Double entirePercent2 = Double.parseDouble(mEntireData.get(i).getTotal_numberB()) /
                        TotalNum;
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                right_blue.setLayoutParams(lp2);

                name.setText(mEntireData.get(i).getTotal_name());
                //TODO 算比例时 小心除数为零
            } catch (Exception e) {

            }
            all_data_1.addView(view);
        }
    }

    private void setWlqView() {
        if (wlqBean == null || wlqBean.size() == 0) {
            return;
        }
        my_team_wlq.removeAllViews();
        he_team_wlq.removeAllViews();
        for (int i = 0; i < wlqBean.size(); i++) {
            switch (wlqBean.get(i).getAttribution()) {
                case "1":
                    final View view_goal = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv = (TextView) view_goal.findViewById(R.id.name_tv);
                    ImageView img_right = (ImageView) view_goal.findViewById(R.id.img_right);
                    TextView name_zg_tv = (TextView) view_goal.findViewById(R.id.name_zg_tv);
                    name_tv.setText(wlqBean.get(i).getNickname());
                    img_right.setImageResource(R.drawable.test_head_img);
                    if (wlqBean.get(i).getAssists() != null) {
                        name_zg_tv.setText(wlqBean.get(i).getAssists().getNickname());
                    }
                    my_team_wlq.addView(view_goal);
                    break;
                case "2":
                    final View view_goal2 = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv2 = (TextView) view_goal2.findViewById(R.id.name_tv);
                    ImageView img_right2 = (ImageView) view_goal2.findViewById(R.id.img_right);
                    TextView name_zg_tv2 = (TextView) view_goal2.findViewById(R.id.name_zg_tv);
                    name_tv2.setText(wlqBean.get(i).getNickname());
                    img_right2.setImageResource(R.drawable.test_head_img);
                    if (wlqBean.get(i).getAssists() != null) {
                        name_zg_tv2.setText(wlqBean.get(i).getAssists().getNickname());
                    }
                    he_team_wlq.addView(view_goal2);
                    break;
            }
        }
    }

    private void setDqView() {
        if (dqBean == null || dqBean.size() == 0) {
            return;
        }
        my_team_dq.removeAllViews();
        he_team_dq.removeAllViews();
        for (int i = 0; i < dqBean.size(); i++) {
            switch (dqBean.get(i).getAttribution()) {
                case "1":
                    final View view_goal = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv = (TextView) view_goal.findViewById(R.id.name_tv);
                    ImageView img_right = (ImageView) view_goal.findViewById(R.id.img_right);
                    TextView name_zg_tv = (TextView) view_goal.findViewById(R.id.name_zg_tv);
                    name_tv.setText(dqBean.get(i).getNickname());
                    img_right.setImageResource(R.drawable.test_head_img);
                    if (dqBean.get(i).getAssists() != null) {
                        name_zg_tv.setText(dqBean.get(i).getAssists().getNickname());
                    }
                    my_team_dq.addView(view_goal);
                    break;
                case "2":
                    final View view_goal2 = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv2 = (TextView) view_goal2.findViewById(R.id.name_tv);
                    ImageView img_right2 = (ImageView) view_goal2.findViewById(R.id.img_right);
                    TextView name_zg_tv2 = (TextView) view_goal2.findViewById(R.id.name_zg_tv);
                    name_tv2.setText(dqBean.get(i).getNickname());
                    img_right2.setImageResource(R.drawable.test_head_img);
                    if (dqBean.get(i).getAssists() != null) {
                        name_zg_tv2.setText(dqBean.get(i).getAssists().getNickname());
                    }
                    he_team_dq.addView(view_goal2);
                    break;
            }
        }
    }

    private void setRedView() {
        if (redBean == null || redBean.size() == 0) {
            return;
        }
        my_team_red.removeAllViews();
        he_team_red.removeAllViews();
        for (int i = 0; i < redBean.size(); i++) {
            switch (redBean.get(i).getAttribution()) {
                case "1":
                    final View view_goal = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv = (TextView) view_goal.findViewById(R.id.name_tv);
                    ImageView img_right = (ImageView) view_goal.findViewById(R.id.img_right);
                    TextView name_zg_tv = (TextView) view_goal.findViewById(R.id.name_zg_tv);
                    name_tv.setText(redBean.get(i).getNickname());
                    img_right.setImageResource(R.drawable.test_head_img);
                    if (redBean.get(i).getAssists() != null) {
                        name_zg_tv.setText(redBean.get(i).getAssists().getNickname());
                    }
                    my_team_red.addView(view_goal);
                    break;
                case "2":
                    final View view_goal2 = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv2 = (TextView) view_goal2.findViewById(R.id.name_tv);
                    ImageView img_right2 = (ImageView) view_goal2.findViewById(R.id.img_right);
                    TextView name_zg_tv2 = (TextView) view_goal2.findViewById(R.id.name_zg_tv);
                    name_tv2.setText(redBean.get(i).getNickname());
                    img_right2.setImageResource(R.drawable.test_head_img);
                    if (redBean.get(i).getAssists() != null) {
                        name_zg_tv2.setText(redBean.get(i).getAssists().getNickname());
                    }
                    he_team_red.addView(view_goal2);
                    break;
            }
        }
    }

    private void setYellowView() {
        if (yellowBean == null || yellowBean.size() == 0) {
            return;
        }
        my_team_yellow.removeAllViews();
        he_team_yellow.removeAllViews();
        for (int i = 0; i < yellowBean.size(); i++) {
            switch (yellowBean.get(i).getAttribution()) {
                case "1":
                    final View view_goal = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv = (TextView) view_goal.findViewById(R.id.name_tv);
                    ImageView img_right = (ImageView) view_goal.findViewById(R.id.img_right);
                    TextView name_zg_tv = (TextView) view_goal.findViewById(R.id.name_zg_tv);
                    name_tv.setText(yellowBean.get(i).getNickname());
                    img_right.setImageResource(R.drawable.test_head_img);
                    if (yellowBean.get(i).getAssists() != null) {
                        name_zg_tv.setText(yellowBean.get(i).getAssists().getNickname());
                    }
                    my_team_yellow.addView(view_goal);
                    break;
                case "2":
                    final View view_goal2 = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv2 = (TextView) view_goal2.findViewById(R.id.name_tv);
                    ImageView img_right2 = (ImageView) view_goal2.findViewById(R.id.img_right);
                    TextView name_zg_tv2 = (TextView) view_goal2.findViewById(R.id.name_zg_tv);
                    name_tv2.setText(yellowBean.get(i).getNickname());
                    img_right2.setImageResource(R.drawable.test_head_img);
                    if (yellowBean.get(i).getAssists() != null) {
                        name_zg_tv2.setText(yellowBean.get(i).getAssists().getNickname());
                    }
                    he_team_yellow.addView(view_goal2);
                    break;
            }
        }

    }


    //进球
    private void setGoalBeanView() {
        if (goalBean == null || goalBean.size() == 0) {
            return;
        }
        he_team.removeAllViews();
        my_team.removeAllViews();
        for (int i = 0; i < goalBean.size(); i++) {
            switch (goalBean.get(i).getAttribution()) {
                case "1":
                    final View view_goal = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv = (TextView) view_goal.findViewById(R.id.name_tv);
                    ImageView img_right = (ImageView) view_goal.findViewById(R.id.img_right);
                    TextView name_zg_tv = (TextView) view_goal.findViewById(R.id.name_zg_tv);
                    name_tv.setText(goalBean.get(i).getNickname());
                    img_right.setImageResource(R.drawable.test_head_img);
                    if (goalBean.get(i).getAssists() != null) {
                        name_zg_tv.setText(goalBean.get(i).getAssists().getNickname());
                    }
                    my_team.addView(view_goal);
                    break;
                case "2":
                    final View view_goal2 = LayoutInflater.from(context).inflate(R.layout.person_item_view, null);
                    TextView name_tv2 = (TextView) view_goal2.findViewById(R.id.name_tv);
                    ImageView img_right2 = (ImageView) view_goal2.findViewById(R.id.img_right);
                    TextView name_zg_tv2 = (TextView) view_goal2.findViewById(R.id.name_zg_tv);
                    name_tv2.setText(goalBean.get(i).getNickname());
                    img_right2.setImageResource(R.drawable.test_head_img);
                    if (goalBean.get(i).getAssists() != null) {
                        name_zg_tv2.setText(goalBean.get(i).getAssists().getNickname());
                    }
                    he_team.addView(view_goal2);
                    break;
            }
        }
    }


    //全部数据
    private void setAllDataViews() {
        if (entireBean == null || entireBean.size() == 0) {
            return;
        }
        entire1 = new ArrayList<>();
        entire1.clear();
        entireTotalNum1 = 0.0;

        entire2 = new ArrayList<>();
        entire2.clear();
        entireTotalNum2 = 0.0;

        entire3 = new ArrayList<>();
        entire3.clear();
        entireTotalNum3 = 0.0;

        entire4 = new ArrayList<>();
        entire4.clear();
        entireTotalNum4 = 0.0;

        entire5 = new ArrayList<>();
        entire5.clear();
        entireTotalNum5 = 0.0;

        entire6 = new ArrayList<>();
        entire6.clear();
        entireTotalNum6 = 0.0;

        for (int i = 0; i < entireBean.size(); i++) {

            //类型 1：进球 2：点球 3：红牌 4：黄牌 5：乌龙球 6：助攻
            switch (entireBean.get(i).getType()) {
                case "1":
                    entire1.add(entireBean.get(i));
                    if (entire1.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire1.size(); k++) {
                            entireTotalNum1 = entireTotalNum1 + Double.parseDouble(entire1.get(k).getTotal_number());
                        }
                        if (entireTotalNum1 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire1.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire1.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire1.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire1.get(j).getTotal_number()) /
                                            entireTotalNum1;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire1.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire1.get(j).getTotal_number()) /
                                            entireTotalNum1;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("进球");
                    }
                    break;
                case "2":
                    entire2.add(entireBean.get(i));
                    if (entire2.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire2.size(); k++) {
                            entireTotalNum2 = entireTotalNum2 + Double.parseDouble(entire2.get(k).getTotal_number());
                        }
                        if (entireTotalNum2 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire2.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire2.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire2.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire2.get(j).getTotal_number()) /
                                            entireTotalNum2;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire2.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire2.get(j).getTotal_number()) /
                                            entireTotalNum2;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("点球");
                    }
                    break;
                case "3":
                    entire3.add(entireBean.get(i));
                    if (entire3.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire3.size(); k++) {
                            entireTotalNum3 = entireTotalNum3 + Double.parseDouble(entire3.get(k).getTotal_number());
                        }
                        if (entireTotalNum3 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire3.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire3.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire3.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire3.get(j).getTotal_number()) /
                                            entireTotalNum3;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire3.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire3.get(j).getTotal_number()) /
                                            entireTotalNum3;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("红牌");
                    }
                    break;
                case "4":
                    entire4.add(entireBean.get(i));
                    if (entire4.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire4.size(); k++) {
                            entireTotalNum4 = entireTotalNum4 + Double.parseDouble(entire4.get(k).getTotal_number());
                        }
                        if (entireTotalNum4 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire4.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire4.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire4.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire4.get(j).getTotal_number()) /
                                            entireTotalNum4;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire4.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire4.get(j).getTotal_number()) /
                                            entireTotalNum4;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("黄牌");
                    }
                    break;
                case "5":
                    entire5.add(entireBean.get(i));
                    if (entire5.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire5.size(); k++) {
                            entireTotalNum5 = entireTotalNum5 + Double.parseDouble(entire5.get(k).getTotal_number());
                        }
                        if (entireTotalNum5 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire5.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire5.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire5.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire5.get(j).getTotal_number()) /
                                            entireTotalNum5;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire5.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire5.get(j).getTotal_number()) /
                                            entireTotalNum5;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("乌龙球");
                    }
                    break;
                case "6":
                    entire6.add(entireBean.get(i));
                    if (entire6.size() == 2) {
                        all_data_1 = (LinearLayout) v.findViewById(R.id.all_data_1);
                        final View view = LayoutInflater.from(context).inflate(R.layout.round_rect_item_view, null);
                        all_data_1.addView(view);
                        TextView name = (TextView) view.findViewById(R.id.name);
                        ImageView left_green = (ImageView) view.findViewById(R.id.left_green);
                        ImageView right_blue = (ImageView) view.findViewById(R.id.right_blue);
                        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
                        TextView right_tv = (TextView) view.findViewById(R.id.right_tv);
                        for (int k = 0; k < entire6.size(); k++) {
                            entireTotalNum6 = entireTotalNum6 + Double.parseDouble(entire6.get(k).getTotal_number());
                        }
                        if (entireTotalNum6 == 0) {
                            return;
                        }
                        for (int j = 0; j < entire6.size(); j++) {
                            //所属球队 1：发起球队/A队 2：对手球队/B队
                            switch (entire6.get(j).getAttribution()) {
                                case "1":
                                    left_tv.setText(entire6.get(j).getTotal_number());
                                    Double entirePercent1 = Double.parseDouble(entire6.get(j).getTotal_number()) /
                                            entireTotalNum6;
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp.leftMargin = (int) (dp2px(150) * (1 - entirePercent1));
                                    left_green.setLayoutParams(lp);
                                    break;
                                case "2":
                                    right_tv.setText(entire6.get(j).getTotal_number());
                                    Double entirePercent2 = Double.parseDouble(entire6.get(j).getTotal_number()) /
                                            entireTotalNum6;
                                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup
                                            .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    lp2.rightMargin = (int) (dp2px(150) * (1 - entirePercent2));
                                    right_blue.setLayoutParams(lp2);
                                    break;
                            }
                        }
                        name.setText("助攻");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void setLinearLayoutHeightBasedOnChildren(LinearLayout lin) {
        int totalHeight = 0;
        for (int i = 0, len = lin.getChildCount(); i < len; i++) {
            View view = lin.getChildAt(i);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lin.getLayoutParams();
        params.height = totalHeight;
        lin.setLayoutParams(params);
    }

    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }
}
