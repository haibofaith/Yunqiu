package com.kball.function.Match.ui;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.EnterGrandListBean;
import com.kball.function.Match.bean.GoalBean;
import com.kball.function.Match.bean.GoalPlayerBean;
import com.kball.function.Match.bean.PenaltyBean;
import com.kball.function.Match.bean.PenaltyNumBean;
import com.kball.function.Match.bean.RedCardBean;
import com.kball.function.Match.impls.EntryScoreImpl;
import com.kball.function.Match.presenter.EntryScorePresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/25.
 */

public class EntryScoreActivity extends BaseActivity implements View.OnClickListener, EntryScoreImpl {
    private TitleView title_view;
    private ImageView goal_min, goal_plus;
    private LinearLayout team_select_lin, goal_select_lin, help_select_lin;

    private EntryScorePresenter presenter;
    private ArrayList<RankPeopleBean> datas;
    private String team_id, team_name;

    private TextView add_cancle_tv, add_enter_tv;
    private TextView dq_add_cancle_tv, dq_add_enter_tv;
    private RelativeLayout select_rlin;//进球
    private RelativeLayout dq_rlin;//点球

    private GoalPlayerBean goalPlayerBean = new GoalPlayerBean();//选择进球及助攻队员的结果
    //接口从此处拿数据去处理
    private ArrayList<GoalPlayerBean> goalPlayerlists = new ArrayList<>();//所有进球及助攻的结果记录
    private ArrayList<GoalPlayerBean> redPlayerlists = new ArrayList<>();//所有红牌的结果记录
    private ArrayList<GoalPlayerBean> yellowPlayerlists = new ArrayList<>();//所有黄牌的结果记录
    private ArrayList<GoalPlayerBean> wlqPlayerlists = new ArrayList<>();//所有乌龙球的结果记录
    private int penaltyNumber = 0; //记录点球数量

    private EnterGrandListBean enterGrandListBean;

    private boolean no_help_select = false;

    private ImageView dq_plus_icon;
    private ImageView dq_min_icon;

    private LinearLayout dq_select_team_lin;
    private LinearLayout dq_select_player_lin;

    private ArrayList<PenaltyNumBean> dqNums = new ArrayList<>();

    private LinearLayout goal_lin;//显示已选择的进球
    private LinearLayout dq_lin;//显示已选择的点球

    private TextView goal_num_tv;
    private TextView dq_num_tv;

    private ImageView red_plus_icon;
    private ImageView red_min_icon;

    private RelativeLayout hp_rlin;
    private TextView hp_add_cancle_tv;
    private TextView hp_add_enter_tv;
    private LinearLayout hp_select_team_lin;
    private LinearLayout hp_select_player_lin;
    private LinearLayout hongpai_lin;
    private TextView red_num_tv;

    private RelativeLayout yellow_rlin;
    private TextView yellow_add_cancle_tv;
    private TextView yellow_add_enter_tv;
    private LinearLayout yellow_select_team_lin;
    private LinearLayout yellow_select_player_lin;
    private LinearLayout yellow_lin;
    private TextView yellow_num_tv;
    private ImageView yellow_min_icon;
    private ImageView yellow_plus_icon;

    private RelativeLayout wlq_rlin;
    private TextView wlq_add_cancle_tv;
    private TextView wlq_add_enter_tv;
    private LinearLayout wlq_select_team_lin;
    private LinearLayout wlq_select_player_lin;
    private LinearLayout wlq_lin;
    private TextView wlq_num_tv;
    private ImageView wlq_min_icon;
    private ImageView wlq_plus_icon;

    private ImageView left_img;
    private ImageView right_img;

    private String left_img_str;
    private String right_img_str;

    private EditText team_a_score;
    private EditText team_b_score;
    String game_id;
    String score_teamA;
    String score_teamB;

    private String data_json;

    @Override
    protected int getContentViewResId() {
        return R.layout.entry_score_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("录入战报");
        title_view.setRightButtonVis();
        title_view.setRightButtonText("保存");

        goal_plus = (ImageView) findViewById(R.id.goal_plus);
        goal_min = (ImageView) findViewById(R.id.goal_min);

        dq_plus_icon = (ImageView) findViewById(R.id.dq_plus_icon);
        dq_min_icon = (ImageView) findViewById(R.id.dq_min_icon);

        team_select_lin = (LinearLayout) findViewById(R.id.team_select_lin);
        goal_select_lin = (LinearLayout) findViewById(R.id.goal_select_lin);
        help_select_lin = (LinearLayout) findViewById(R.id.help_select_lin);

        add_cancle_tv = (TextView) findViewById(R.id.add_cancle_tv);
        add_enter_tv = (TextView) findViewById(R.id.add_enter_tv);

        dq_add_cancle_tv = (TextView) findViewById(R.id.dq_add_cancle_tv);
        dq_add_enter_tv = (TextView) findViewById(R.id.dq_add_enter_tv);

        select_rlin = (RelativeLayout) findViewById(R.id.select_rlin);
        dq_rlin = (RelativeLayout) findViewById(R.id.dq_rlin);

        dq_select_team_lin = (LinearLayout) findViewById(R.id.dq_select_team_lin);
        dq_select_player_lin = (LinearLayout) findViewById(R.id.dq_select_player_lin);

        goal_lin = (LinearLayout) findViewById(R.id.goal_lin);

        dq_lin = (LinearLayout) findViewById(R.id.dq_lin);

        goal_num_tv = (TextView) findViewById(R.id.goal_num_tv);
        dq_num_tv = (TextView) findViewById(R.id.dq_num_tv);

        red_plus_icon = (ImageView) findViewById(R.id.red_plus_icon);
        red_min_icon = (ImageView) findViewById(R.id.red_min_icon);

        hp_rlin = (RelativeLayout) findViewById(R.id.hp_rlin);
        hp_add_cancle_tv = (TextView) findViewById(R.id.hp_add_cancle_tv);
        hp_add_enter_tv = (TextView) findViewById(R.id.hp_add_enter_tv);

        hp_select_team_lin = (LinearLayout) findViewById(R.id.hp_select_team_lin);
        hp_select_player_lin = (LinearLayout) findViewById(R.id.hp_select_player_lin);
        hongpai_lin = (LinearLayout) findViewById(R.id.hongpai_lin);
        red_num_tv = (TextView) findViewById(R.id.red_num_tv);

        yellow_rlin = (RelativeLayout) findViewById(R.id.yellow_rlin);
        yellow_add_cancle_tv = (TextView) findViewById(R.id.yellow_add_cancle_tv);
        yellow_add_enter_tv = (TextView) findViewById(R.id.yellow_add_enter_tv);
        yellow_select_team_lin = (LinearLayout) findViewById(R.id.yellow_select_team_lin);
        yellow_select_player_lin = (LinearLayout) findViewById(R.id.yellow_select_player_lin);
        yellow_lin = (LinearLayout) findViewById(R.id.yellow_lin);
        yellow_num_tv = (TextView) findViewById(R.id.yellow_num_tv);

        yellow_min_icon = (ImageView) findViewById(R.id.yellow_min_icon);
        yellow_plus_icon = (ImageView) findViewById(R.id.yellow_plus_icon);

        wlq_rlin = (RelativeLayout) findViewById(R.id.wlq_rlin);
        wlq_add_cancle_tv = (TextView) findViewById(R.id.wlq_add_cancle_tv);
        wlq_add_enter_tv = (TextView) findViewById(R.id.wlq_add_enter_tv);
        wlq_select_team_lin = (LinearLayout) findViewById(R.id.wlq_select_team_lin);
        wlq_select_player_lin = (LinearLayout) findViewById(R.id.wlq_select_player_lin);
        wlq_lin = (LinearLayout) findViewById(R.id.wlq_lin);
        wlq_num_tv = (TextView) findViewById(R.id.wlq_num_tv);

        wlq_min_icon = (ImageView) findViewById(R.id.wlq_min_icon);
        wlq_plus_icon = (ImageView) findViewById(R.id.wlq_plus_icon);

        left_img = (ImageView) findViewById(R.id.left_img);
        right_img = (ImageView) findViewById(R.id.right_img);

        team_a_score = (EditText) findViewById(R.id.team_a_score);
        team_b_score = (EditText) findViewById(R.id.team_b_score);
    }

    @Override
    protected void initData() {
        presenter = new EntryScorePresenter(this);
        team_id = getIntent().getStringExtra("team_id");
        team_name = getIntent().getStringExtra("team_name");
        left_img_str = getIntent().getStringExtra("left_img");
        right_img_str = getIntent().getStringExtra("right_img");
        game_id = getIntent().getStringExtra("game_id");
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL_YPY + left_img_str, left_img);
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL_YPY + right_img_str, right_img);
        presenter.selectTeamMember(this, team_id);
        for (int i = 0; i < 10; i++) {
            dqNums.add(new PenaltyNumBean(i + 1, false));
        }
    }

    @Override
    protected void setListener() {
        title_view.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbit();
            }
        });
        goal_plus.setOnClickListener(this);
        goal_min.setOnClickListener(this);
        add_enter_tv.setOnClickListener(this);
        add_cancle_tv.setOnClickListener(this);

        dq_plus_icon.setOnClickListener(this);
        dq_min_icon.setOnClickListener(this);
        dq_add_cancle_tv.setOnClickListener(this);
        dq_add_enter_tv.setOnClickListener(this);

        red_plus_icon.setOnClickListener(this);
        red_min_icon.setOnClickListener(this);
        hp_add_cancle_tv.setOnClickListener(this);
        hp_add_enter_tv.setOnClickListener(this);

        yellow_min_icon.setOnClickListener(this);
        yellow_plus_icon.setOnClickListener(this);
        yellow_add_cancle_tv.setOnClickListener(this);
        yellow_add_enter_tv.setOnClickListener(this);

        wlq_min_icon.setOnClickListener(this);
        wlq_plus_icon.setOnClickListener(this);
        wlq_add_cancle_tv.setOnClickListener(this);
        wlq_add_enter_tv.setOnClickListener(this);
    }

    private void sumbit() {
//        private ArrayList<GoalPlayerBean> goalPlayerlists = new ArrayList<>();//所有进球及助攻的结果记录
//        private ArrayList<GoalPlayerBean> redPlayerlists = new ArrayList<>();//所有红牌的结果记录
//        private ArrayList<GoalPlayerBean> yellowPlayerlists = new ArrayList<>();//所有黄牌的结果记录
//        private ArrayList<GoalPlayerBean> wlqPlayerlists = new ArrayList<>();//所有乌龙球的结果记录
//        private int penaltyNumber = 0; //记录点球数量
        enterGrandListBean = new EnterGrandListBean();
        ArrayList<GoalBean> goals = new ArrayList<>();
        enterGrandListBean.setGoal(goals);
        ArrayList<PenaltyBean> penaltys = new ArrayList<>();
        enterGrandListBean.setPenalty(penaltys);
        ArrayList<RedCardBean> reds = new ArrayList<>();
        enterGrandListBean.setRed_card(reds);
        ArrayList<RedCardBean> yellows = new ArrayList<>();
        enterGrandListBean.setYellow_card(yellows);
        ArrayList<RedCardBean> wlqs = new ArrayList<>();
        enterGrandListBean.setOwn_goal(wlqs);

        score_teamA = team_a_score.getText().toString();
        score_teamB = team_b_score.getText().toString();

        enterGrandListBean.setGame_id(game_id);
        enterGrandListBean.setScore_teamA(score_teamA);
        enterGrandListBean.setScore_teamB(score_teamB);
        for (int i = 0; i < goalPlayerlists.size(); i++) {
            String user_id;
            if (null != goalPlayerlists.get(i).getHelpPlayer() && !("".equals(goalPlayerlists.get(i).getHelpPlayer()))) {
                user_id = goalPlayerlists.get(i).getGoalPlayer() + "," + goalPlayerlists.get(i).getHelpPlayer();
            }else{
                user_id = goalPlayerlists.get(i).getGoalPlayer();
            }
            enterGrandListBean.getGoal().add(new GoalBean(team_id,user_id));
        }
        enterGrandListBean.getPenalty().add(new PenaltyBean(team_id,penaltyNumber+""));
        for (int i=0;i<redPlayerlists.size();i++){
            enterGrandListBean.getRed_card().add(new RedCardBean(team_id,redPlayerlists.get(i).getGoalPlayer()));
        }
        for (int i=0;i<yellowPlayerlists.size();i++){
            enterGrandListBean.getYellow_card().add(new RedCardBean(team_id,yellowPlayerlists.get(i).getGoalPlayer()));
        }
        for (int i=0;i<wlqPlayerlists.size();i++){
            enterGrandListBean.getOwn_goal().add(new RedCardBean(team_id,wlqPlayerlists.get(i).getGoalPlayer()));
        }
        Gson gson =new Gson();
        data_json = gson.toJson(enterGrandListBean);
        Log.e("TAG",data_json);
        presenter.enterGrand(this,data_json);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goal_plus://进球增加按钮
                select_rlin.setVisibility(View.VISIBLE);
                break;
            case R.id.goal_min://进球减去按钮
                if (goalPlayerlists.size() >= 1) {
                    goalPlayerlists.remove((goalPlayerlists.size() - 1));
                    addGoalLin(goalPlayerlists);
                    goal_num_tv.setText(goalPlayerlists.size() + "");
                }
                break;
            case R.id.dq_plus_icon://点球增加按钮
                dq_rlin.setVisibility(View.VISIBLE);
                break;
            case R.id.dq_min_icon://点球减去按钮
                penaltyNumber = 0;
                dq_num_tv.setText(penaltyNumber + "");
                addDqLin();
                break;
            case R.id.add_enter_tv://进球确定按钮
                if (null==goalPlayerBean.getGoalPlayer()){
                    ToastAlone.show("必须选择进球球员");
                    return;
                }
                select_rlin.setVisibility(View.GONE);
                //必须新建对象，否则会造成引用数据被修改
                if (Integer.parseInt(team_a_score.getText().toString())<=goal_lin.getChildCount()){
                    ToastAlone.show("进球数量必须小于比分，请修改！");
                    return;
                }
                GoalPlayerBean goalPlayerListBean = new GoalPlayerBean();
                goalPlayerListBean.setGoalPlayer(goalPlayerBean.getGoalPlayer());
                goalPlayerListBean.setGoalPlayerName(goalPlayerBean.getGoalPlayerName());
                goalPlayerListBean.setHelpPlayer(goalPlayerBean.getHelpPlayer());
                goalPlayerListBean.setHelpPlayerName(goalPlayerBean.getHelpPlayerName());
                goalPlayerlists.add(goalPlayerListBean);
                addGoalLin(goalPlayerlists);
                goal_num_tv.setText(goalPlayerlists.size() + "");
                break;
            case R.id.add_cancle_tv:
                select_rlin.setVisibility(View.GONE);
                break;
            case R.id.dq_add_enter_tv://点球确定按钮
                dq_rlin.setVisibility(View.GONE);
                addDqLin();
                dq_num_tv.setText(penaltyNumber + "");
                break;
            case R.id.dq_add_cancle_tv:
                dq_rlin.setVisibility(View.GONE);
                break;
            case R.id.red_plus_icon://红牌增加按钮
                hp_rlin.setVisibility(View.VISIBLE);
                break;
            case R.id.red_min_icon://红牌减少按钮
                if (redPlayerlists.size() >= 1) {
                    redPlayerlists.remove((redPlayerlists.size() - 1));
                    addRedLin(redPlayerlists);
                    red_num_tv.setText(redPlayerlists.size() + "");
                }
                break;
            case R.id.hp_add_cancle_tv:
                hp_rlin.setVisibility(View.GONE);

                break;
            case R.id.hp_add_enter_tv://红牌确认按钮
                hp_rlin.setVisibility(View.GONE);
                //必须新建对象，否则会造成引用数据被修改
                GoalPlayerBean hpPlayerListBean = new GoalPlayerBean();
                hpPlayerListBean.setGoalPlayer(goalPlayerBean.getGoalPlayer());
                hpPlayerListBean.setGoalPlayerName(goalPlayerBean.getGoalPlayerName());
                hpPlayerListBean.setHelpPlayer(goalPlayerBean.getHelpPlayer());
                hpPlayerListBean.setHelpPlayerName(goalPlayerBean.getHelpPlayerName());
                redPlayerlists.add(hpPlayerListBean);
                addRedLin(redPlayerlists);
                red_num_tv.setText(redPlayerlists.size() + "");
                break;
            case R.id.yellow_min_icon:
                if (yellowPlayerlists.size() >= 1) {
                    yellowPlayerlists.remove((yellowPlayerlists.size() - 1));
                    addYellowLin(yellowPlayerlists);
                    yellow_num_tv.setText(yellowPlayerlists.size() + "");
                }
                break;
            case R.id.yellow_plus_icon://黄牌增加按钮
                yellow_rlin.setVisibility(View.VISIBLE);
                break;
            case R.id.yellow_add_enter_tv:
                yellow_rlin.setVisibility(View.GONE);
                //必须新建对象，否则会造成引用数据被修改
                GoalPlayerBean yellowPlayerListBean = new GoalPlayerBean();
                yellowPlayerListBean.setGoalPlayer(goalPlayerBean.getGoalPlayer());
                yellowPlayerListBean.setGoalPlayerName(goalPlayerBean.getGoalPlayerName());
                yellowPlayerListBean.setHelpPlayer(goalPlayerBean.getHelpPlayer());
                yellowPlayerListBean.setHelpPlayerName(goalPlayerBean.getHelpPlayerName());
                yellowPlayerlists.add(yellowPlayerListBean);
                addYellowLin(yellowPlayerlists);
                yellow_num_tv.setText(yellowPlayerlists.size() + "");
                break;
            case R.id.yellow_add_cancle_tv:
                yellow_rlin.setVisibility(View.GONE);
                break;
            case R.id.wlq_min_icon:
                if (wlqPlayerlists.size() >= 1) {
                    wlqPlayerlists.remove((wlqPlayerlists.size() - 1));
                    addWlqLin(wlqPlayerlists);
                    wlq_num_tv.setText(wlqPlayerlists.size() + "");
                }
                break;
            case R.id.wlq_plus_icon:
                wlq_rlin.setVisibility(View.VISIBLE);
                break;
            case R.id.wlq_add_enter_tv:
                wlq_rlin.setVisibility(View.GONE);
                //必须新建对象，否则会造成引用数据被修改
                GoalPlayerBean wlqPlayerListBean = new GoalPlayerBean();
                wlqPlayerListBean.setGoalPlayer(goalPlayerBean.getGoalPlayer());
                wlqPlayerListBean.setGoalPlayerName(goalPlayerBean.getGoalPlayerName());
                wlqPlayerListBean.setHelpPlayer(goalPlayerBean.getHelpPlayer());
                wlqPlayerListBean.setHelpPlayerName(goalPlayerBean.getHelpPlayerName());
                wlqPlayerlists.add(wlqPlayerListBean);
                addWlqLin(wlqPlayerlists);
                wlq_num_tv.setText(wlqPlayerlists.size() + "");
                break;
            case R.id.wlq_add_cancle_tv:
                wlq_rlin.setVisibility(View.GONE);
                break;
        }
    }


    //乌龙球球队
    public void setWlqTeamSelectItems() {
        wlq_select_team_lin.removeAllViews();
        final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
        player_tv.setText(team_name);
        wlq_select_team_lin.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.color_6c));
    }

    //乌龙球球员
    public void setWlqSelectItems(final ArrayList<RankPeopleBean> datas) {
        wlq_select_player_lin.removeAllViews();
        if (datas == null) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(datas.get(i).getNickname());
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (RankPeopleBean data : datas) {
                        if (data.isSelected()) {
                            data.setSelected(false);
                        }
                    }
                    datas.get(position).setSelected(true);
                    goalPlayerBean.setGoalPlayer(datas.get(position).getUser_id());
                    goalPlayerBean.setGoalPlayerName(datas.get(position).getNickname());
                    setWlqSelectItems(datas);
                }
            });
            if (datas.get(position).isSelected()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            wlq_select_player_lin.addView(view);
        }
    }

    //乌龙球确定后显示动态添加view
    private void addWlqLin(ArrayList<GoalPlayerBean> wlqPlayerlists) {
        wlq_lin.removeAllViews();
        for (int i = 0; i < wlqPlayerlists.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_text_item, null);
            TextView team_tv = (TextView) view.findViewById(R.id.team_tv);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            team_tv.setText(team_name);
            player_tv.setText(wlqPlayerlists.get(i).getGoalPlayerName() + "乌龙球");
            wlq_lin.addView(view);
        }
    }

    //黄牌球队
    public void setYellowTeamSelectItems() {
        yellow_select_team_lin.removeAllViews();
        final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
        player_tv.setText(team_name);
        yellow_select_team_lin.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.color_6c));
    }

    //黄牌球员
    public void setYellowSelectItems(final ArrayList<RankPeopleBean> datas) {
        yellow_select_player_lin.removeAllViews();
        if (datas == null) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(datas.get(i).getNickname());
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (RankPeopleBean data : datas) {
                        if (data.isSelected()) {
                            data.setSelected(false);
                        }
                    }
                    datas.get(position).setSelected(true);
                    goalPlayerBean.setGoalPlayer(datas.get(position).getUser_id());
                    goalPlayerBean.setGoalPlayerName(datas.get(position).getNickname());
                    setYellowSelectItems(datas);
                }
            });
            if (datas.get(position).isSelected()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            yellow_select_player_lin.addView(view);
        }
    }

    //黄牌确定后显示动态添加view
    private void addYellowLin(ArrayList<GoalPlayerBean> yellowPlayerlists) {
        yellow_lin.removeAllViews();
        for (int i = 0; i < yellowPlayerlists.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_text_item, null);
            TextView team_tv = (TextView) view.findViewById(R.id.team_tv);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            team_tv.setText(team_name);
            player_tv.setText(yellowPlayerlists.get(i).getGoalPlayerName() + "黄牌");
            yellow_lin.addView(view);
        }
    }


    //红牌球队
    public void setHpTeamSelectItems() {
        hp_select_team_lin.removeAllViews();
        final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
        player_tv.setText(team_name);
        hp_select_team_lin.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.color_6c));
    }

    //红牌球员
    public void setHpSelectItems(final ArrayList<RankPeopleBean> datas) {
        hp_select_player_lin.removeAllViews();
        if (datas == null) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(datas.get(i).getNickname());
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (RankPeopleBean data : datas) {
                        if (data.isSelected()) {
                            data.setSelected(false);
                        }
                    }
                    datas.get(position).setSelected(true);
                    goalPlayerBean.setGoalPlayer(datas.get(position).getUser_id());
                    goalPlayerBean.setGoalPlayerName(datas.get(position).getNickname());
                    setHpSelectItems(datas);
                }
            });
            if (datas.get(position).isSelected()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            hp_select_player_lin.addView(view);
        }
    }

    //红牌确定后显示动态添加view
    private void addRedLin(ArrayList<GoalPlayerBean> redPlayerlists) {
        hongpai_lin.removeAllViews();
        for (int i = 0; i < redPlayerlists.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_text_item, null);
            TextView team_tv = (TextView) view.findViewById(R.id.team_tv);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            team_tv.setText(team_name);
            player_tv.setText(redPlayerlists.get(i).getGoalPlayerName() + "红牌");
            hongpai_lin.addView(view);
        }
    }

    //进球确定后显示动态添加view
    private void addGoalLin(ArrayList<GoalPlayerBean> goalPlayerlists) {
        goal_lin.removeAllViews();
        for (int i = 0; i < goalPlayerlists.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_text_item, null);
            TextView team_tv = (TextView) view.findViewById(R.id.team_tv);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            TextView help_tv = (TextView) view.findViewById(R.id.help_tv);
            team_tv.setText(team_name);
            player_tv.setText(goalPlayerlists.get(i).getGoalPlayerName() + "进球");
            if (null != goalPlayerlists.get(i).getHelpPlayerName() && "" != goalPlayerlists.get(i).getHelpPlayerName()) {
                help_tv.setText(goalPlayerlists.get(i).getHelpPlayerName() + "助攻");
            }
            goal_lin.addView(view);
        }
    }

    //点球确定后显示动态添加view
    private void addDqLin() {
        dq_lin.removeAllViews();
        if (penaltyNumber > 0) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_text_item2, null);
            TextView team_tv = (TextView) view.findViewById(R.id.team_tv);
            TextView num_tv = (TextView) view.findViewById(R.id.num_tv);
            team_tv.setText(team_name);
            num_tv.setText(penaltyNumber + "个点球");
            dq_lin.addView(view);
        }
    }

    //点球球队
    public void setDqTeamSelectItems() {
        dq_select_team_lin.removeAllViews();
        final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
        player_tv.setText(team_name);
        dq_select_team_lin.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.color_6c));
    }

    //点球数量
    public void setDqGoalSelectItems(final ArrayList<PenaltyNumBean> dqNums) {
        dq_select_player_lin.removeAllViews();
        for (int i = 0; i < dqNums.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(dqNums.get(i).getNum() + "");
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (PenaltyNumBean data : dqNums) {
                        data.setSelect(false);
                    }
                    dqNums.get(position).setSelect(true);
                    penaltyNumber = dqNums.get(position).getNum();
                    setDqGoalSelectItems(dqNums);
                }
            });
            if (dqNums.get(position).isSelect()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            dq_select_player_lin.addView(view);
        }
    }

    //进球球队
    public void setTeamSelectItems() {
        team_select_lin.removeAllViews();
        final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
        player_tv.setText(team_name);
        team_select_lin.addView(view);
        view.setBackgroundColor(getResources().getColor(R.color.color_6c));
    }

    //进球射门
    public void setGoalSelectItems(final ArrayList<RankPeopleBean> datas) {
        goal_select_lin.removeAllViews();
        if (datas == null) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(datas.get(i).getNickname());
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (RankPeopleBean data : datas) {
                        if (data.isSelected()) {
                            data.setSelected(false);
                        }
                    }
                    datas.get(position).setSelected(true);
                    goalPlayerBean.setGoalPlayer(datas.get(position).getUser_id());
                    goalPlayerBean.setGoalPlayerName(datas.get(position).getNickname());
                    setGoalSelectItems(datas);
                }
            });
            if (datas.get(position).isSelected()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            goal_select_lin.addView(view);
        }
    }

    //进球助攻
    public void setHelpSelectItems(final ArrayList<RankPeopleBean> datas) {
        help_select_lin.removeAllViews();
        if (datas == null) {
            return;
        }
        final View no_view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
        TextView no_player_tv = (TextView) no_view.findViewById(R.id.player_tv);
        no_player_tv.setText("无");
        RelativeLayout no_player_rlin = (RelativeLayout) no_view.findViewById(R.id.player_rlin);
        no_player_rlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (RankPeopleBean data : datas) {
                    if (data.isSelected()) {
                        data.setSelected(false);
                    }
                    goalPlayerBean.setHelpPlayer("");
                    goalPlayerBean.setHelpPlayerName("");
                    no_help_select = true;
                    setHelpSelectItems(datas);
                }
            }
        });
        if (no_help_select) {
            no_view.setBackgroundColor(getResources().getColor(R.color.color_6c));
        }
        help_select_lin.addView(no_view);

        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.simple_player_item, null);
            TextView player_tv = (TextView) view.findViewById(R.id.player_tv);
            player_tv.setText(datas.get(i).getNickname());
            RelativeLayout player_rlin = (RelativeLayout) view.findViewById(R.id.player_rlin);
            final int position = i;
            player_rlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (RankPeopleBean data : datas) {
                        if (data.isSelected()) {
                            data.setSelected(false);
                        }
                    }
                    no_help_select = false;
                    datas.get(position).setSelected(true);
                    goalPlayerBean.setHelpPlayer(datas.get(position).getUser_id());
                    goalPlayerBean.setHelpPlayerName(datas.get(position).getNickname());
                    setHelpSelectItems(datas);
                }
            });
            if (datas.get(position).isSelected()) {
                view.setBackgroundColor(getResources().getColor(R.color.color_6c));
            }
            help_select_lin.addView(view);
        }
    }

    //查询球队成员
    @Override
    public void setSelectTeamMemberData(ListBaseBean<RankPeopleBean> result) {
        if ("1200".equals(result.getError_code())) {
            datas = result.getData();
            //进球
            setTeamSelectItems();
            setGoalSelectItems(datas);
            setHelpSelectItems(datas);
            //点球
            setDqTeamSelectItems();
            setDqGoalSelectItems(dqNums);
            //红牌
            setHpTeamSelectItems();
            setHpSelectItems(datas);
            //黄牌
            setYellowTeamSelectItems();
            setYellowSelectItems(datas);
            //乌龙球
            setWlqTeamSelectItems();
            setWlqSelectItems(datas);
        }
    }

    @Override
    public void setEnterGrandData(BaseBean result) {
        if ("1200".equals(result.getError_code())){
            ToastAlone.show("录入比赛比分成功");
            finish();
        }
    }
}
