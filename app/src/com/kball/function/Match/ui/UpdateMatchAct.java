package com.kball.function.Match.ui;

import android.content.Context;
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
import com.kball.function.CloudBall.presenter.CreateTrainPresenter;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.CloudBall.ui.SearchPeopleAct;
import com.kball.function.CloudBall.ui.SelectTeamActivity;
import com.kball.function.CloudBall.view.CreateTrainImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Login.bean.GameInfoOneBean;
import com.kball.function.Login.presenter.MatchAboutPresenter;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.impls.MatchAboutViews;
import com.kball.function.other.DatePickActivity;
import com.kball.util.PublicUtil;
import com.kball.util.ToastAlone;

/**
 * Created by xiaole.wang on 17/3/17.
 */

public class UpdateMatchAct extends BaseActivity implements View.OnClickListener, CreateTrainImpl, MatchAboutViews {
    String teamId, teamName;
    private ImageView back_button_img;
    private LinearLayout lin;
    View zz_view;
    private static final int code = 12315;
    private static final int time = 10086;
    private static final int end_time = 10010;
    //比赛名称
    private EditText match_name;
    private String mMatchName;

    private RelativeLayout select_team_rlin;

    public String team_id;
    public TextView team_name;
    public String teamB_id;
    public TextView teamB_name;

    //创建
    private TextView create_tv;

    //比赛时间
    private RelativeLayout time_rel;
    public static TextView time_tv;

    //报名结束时间
    private RelativeLayout bm_end_time_rel;
    public static TextView bm_end_time_tv;

    //场地
    private RelativeLayout place_rel;
    public static TextView place_tv;

    //持续时间
    private LinearLayout chixu_lin;
    private RelativeLayout chixu_rel;
    private TextView chixu_tv;
    private String chixuStr = "2";
    private TextView time_type1, time_type2, time_type3, time_type4, time_type5, time_type6, time_type8, time_type7;
    // 赛制
    private LinearLayout saizhi_lin;
    private RelativeLayout saizhi_rel;
    private TextView saizhi_tv;
    private String saizhiStr = "";
    private TextView saizhi_type1, saizhi_type2, saizhi_type3, saizhi_type4, saizhi_type5, saizhi_type6;
    // 队服
    private ScrollView duifu_lin;
    private RelativeLayout duifu_rel;
    private TextView duifu_tv;
    private String duifuStr = "";
    private TextView duifu_type1, duifu_type2, duifu_type3, duifu_type4, duifu_type5, duifu_type6, duifu_type7,
            duifu_type8, duifu_type9, duifu_type10;

    //比赛费用
    private String payNum;
    private EditText pay_num;

    //视频选购
    private LinearLayout shiping_lin;
    private RelativeLayout shiping_rel;
    private TextView shiping_tv;
    private String shipingStr = "0";
    private TextView shiping_type1, shiping_type2, shiping_type3, shiping_type4;

    //对手
    private RelativeLayout duishou_rel;

    private CreateTrainPresenter presenter;
    private int act_type = 0;
    private String game_id;
    private MatchAboutPresenter mPresenter;

    @Override
    protected int getContentViewResId() {
        return R.layout.update_match;
    }

    @Override
    public void setData(BaseBean<GameInfoBean<MemberBean>> result) {
        GameInfoOneBean game_info = result.getData().getGame_info();
        match_name.setText(game_info.getGame_name());
        team_name.setText(game_info.getTeamA_name());
        teamB_name.setText(game_info.getTeamB_name());
        team_id = game_info.getEntry_teamA();
        teamB_id = game_info.getEntry_teamB();
        time_tv.setText(PublicUtil.getStrTime(game_info.getGame_time(),"yyyy-MM-dd HH:mm"));
        chixu_tv.setText(game_info.getContinue_time() + "小时");
        saizhi_tv.setText(saizhi(game_info.getGame_system()));
        place_tv.setText(game_info.getGame_site());
        duifu_tv.setText(duifu(game_info.getGame_site()));
        bm_end_time_tv.setText(PublicUtil.getStrTime(game_info.getApply_end_time(),"yyyy-MM-dd HH:mm"));
        pay_num.setText(game_info.getGame_cost());
        shiping_tv.setText(xuangou(game_info.getValue_added()));
        duifuStr = game_info.getUniform_teamA();
        saizhiStr = game_info.getGame_system();
        shipingStr = game_info.getValue_added();
    }

    private String xuangou(String str) {
        if ("0".equals(str)) {
            return "暂不选购";
        } else if ("1".equals(str)) {
            return "数据";
        } else if ("2".equals(str)) {
            return "视频";
        } else if ("3".equals(str)) {
            return "视频+数据";
        }
        return "";
    }

    private String duifu(String str) {
        if ("1".equals(str)) {
            return "红";
        } else if ("2".equals(str)) {
            return "蓝";
        } else if ("3".equals(str)) {
            return "白";
        } else if ("4".equals(str)) {
            return "紫";
        } else if ("5".equals(str)) {
            return "橙";
        } else if ("6".equals(str)) {
            return "黄";
        } else if ("7".equals(str)) {
            return "绿";
        } else if ("8".equals(str)) {
            return "灰";
        } else if ("9".equals(str)) {
            return "黑";
        } else if ("10".equals(str)) {
            return "粉";
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_img:
                finish();
                break;
            case R.id.duishou_rel:
                startActivityForResult(new Intent(this, SearchPeopleAct.class), 114);
                break;
            case R.id.shiping_type1:
                setShiping("0", shiping_type1.getText().toString());
                break;
            case R.id.shiping_type2:
                setShiping("1", shiping_type2.getText().toString());
                break;
            case R.id.shiping_type3:
                setShiping("2", shiping_type3.getText().toString());
                break;
            case R.id.shiping_type4:
                setShiping("3", shiping_type4.getText().toString());
                break;
            case R.id.shiping_rel:
                zz_view.setVisibility(View.VISIBLE);
                shiping_lin.setVisibility(View.VISIBLE);
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
            case R.id.duifu_rel:
                zz_view.setVisibility(View.VISIBLE);
                duifu_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.saizhi_type1:
                setSaizhi("1", saizhi_type1.getText().toString());
                break;
            case R.id.saizhi_type2:
                setSaizhi("2", saizhi_type2.getText().toString());
                break;
            case R.id.saizhi_type3:
                setSaizhi("3", saizhi_type3.getText().toString());
                break;
            case R.id.saizhi_type4:
                setSaizhi("4", saizhi_type4.getText().toString());
                break;
            case R.id.saizhi_type5:
                setSaizhi("5", saizhi_type5.getText().toString());
                break;
            case R.id.saizhi_type6:
                setSaizhi("6", saizhi_type6.getText().toString());
                break;
            case R.id.saizhi_rel:
                zz_view.setVisibility(View.VISIBLE);
                saizhi_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.time_type1:
                setChixu(time_type1);
                break;
            case R.id.time_type2:
                setChixu(time_type2);
                break;
            case R.id.time_type3:
                setChixu(time_type3);
                break;
            case R.id.time_type4:
                setChixu(time_type4);
                break;
            case R.id.time_type5:
                setChixu(time_type5);
                break;
            case R.id.time_type6:
                setChixu(time_type6);
                break;
            case R.id.time_type7:
                setChixu(time_type7);
                break;
            case R.id.time_type8:
                setChixu(time_type1);
                break;
            case R.id.chixu_rel:
                zz_view.setVisibility(View.VISIBLE);
                chixu_lin.setVisibility(View.VISIBLE);
                break;

            case R.id.bm_end_time_rel:
                startActivityForResult(new Intent(this, DatePickActivity.class), end_time);
                break;
            case R.id.select_team_rlin:
                if (1 == act_type) {
                    startActivityForResult(new Intent(this, SelectTeamActivity.class), code);
                }
                break;
            case R.id.create_tv:
                submit();
                break;
            case R.id.time_rel:
                startActivityForResult(new Intent(this, DatePickActivity.class), time);
                break;
            case R.id.place_rel:
                startActivityForResult(new Intent(this, PlaceAct.class), 10000);
                break;
        }

    }

    private void submit() {
        mMatchName = match_name.getText().toString().trim();
        if (mMatchName.length() == 0) {
            ToastAlone.show("请输入比赛名称");
            return;
        }
        if (saizhiStr.length() == 0) {
            ToastAlone.show("请选择赛制");
            return;
        }
        if (duifuStr.length() == 0) {
            ToastAlone.show("请选择队服");
            return;
        }
        if (time_tv.getText().toString().trim().length() == 0) {
            ToastAlone.show("请选择比赛时间");
            return;
        }
        if (place_tv.getText().toString().trim().length() == 0) {
            ToastAlone.show("请选择场地");
            return;
        }

        payNum = pay_num.getText().toString().trim();

        String classify = "1";
        String game_name = mMatchName;
        String entry_teamA = team_id;
        String entry_teamB = teamB_id;
        String uniform_teamA = duifuStr;
        String game_time = time_tv.getText().toString().trim();
        String continue_time = chixuStr;
        String apply_end_time = bm_end_time_tv.getText().toString().trim();
        String game_system = saizhiStr;
        String game_site = place_tv.getText().toString().trim();
        String game_cost = payNum;
        String value_added = shipingStr;

        if (1 == act_type) {
            presenter.createTournament1(this, classify, game_name, entry_teamA, entry_teamB, uniform_teamA,
                    game_time, continue_time, apply_end_time, game_system, game_site, game_cost, value_added);

        } else if (2 == act_type) {
            presenter.updateMatch(this, classify, game_name, entry_teamA, entry_teamB, uniform_teamA, game_time,
                    continue_time, apply_end_time, game_system, game_site, game_cost, value_added);

        }
    }


    @Override
    protected void initView() {
        back_button_img = (ImageView) findViewById(R.id.back_button_img);
        match_name = (EditText) findViewById(R.id.match_name);
        pay_num = (EditText) findViewById(R.id.pay_num);
        team_name = (TextView) findViewById(R.id.team_name);
        teamB_name = (TextView) findViewById(R.id.teamB_name);
        place_tv = (TextView) findViewById(R.id.place_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        create_tv = (TextView) findViewById(R.id.create_tv);
        bm_end_time_tv = (TextView) findViewById(R.id.bm_end_time_tv);
        time_rel = (RelativeLayout) findViewById(R.id.time_rel);
        select_team_rlin = (RelativeLayout) findViewById(R.id.select_team_rlin);
        bm_end_time_rel = (RelativeLayout) findViewById(R.id.bm_end_time_rel);
        duishou_rel = (RelativeLayout) findViewById(R.id.duishou_rel);
        place_rel = (RelativeLayout) findViewById(R.id.place_rel);
        zz_view = (View) findViewById(R.id.zz_view);

        chixu_lin = (LinearLayout) findViewById(R.id.chixu_lin);
        chixu_rel = (RelativeLayout) findViewById(R.id.chixu_rel);
        chixu_tv = (TextView) findViewById(R.id.chixu_tv);
        time_type1 = (TextView) findViewById(R.id.time_type1);
        time_type2 = (TextView) findViewById(R.id.time_type2);
        time_type3 = (TextView) findViewById(R.id.time_type3);
        time_type4 = (TextView) findViewById(R.id.time_type4);
        time_type5 = (TextView) findViewById(R.id.time_type5);
        time_type6 = (TextView) findViewById(R.id.time_type6);
        time_type7 = (TextView) findViewById(R.id.time_type7);
        time_type8 = (TextView) findViewById(R.id.time_type8);

        saizhi_lin = (LinearLayout) findViewById(R.id.saizhi_lin);
        saizhi_rel = (RelativeLayout) findViewById(R.id.saizhi_rel);
        saizhi_tv = (TextView) findViewById(R.id.saizhi_tv);
        saizhi_type1 = (TextView) findViewById(R.id.saizhi_type1);
        saizhi_type2 = (TextView) findViewById(R.id.saizhi_type2);
        saizhi_type3 = (TextView) findViewById(R.id.saizhi_type3);
        saizhi_type4 = (TextView) findViewById(R.id.saizhi_type4);
        saizhi_type5 = (TextView) findViewById(R.id.saizhi_type5);
        saizhi_type6 = (TextView) findViewById(R.id.saizhi_type6);

        duifu_lin = (ScrollView) findViewById(R.id.duifu_lin);
        duifu_rel = (RelativeLayout) findViewById(R.id.duifu_rel);
        duifu_tv = (TextView) findViewById(R.id.duifu_tv);
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

        shiping_lin = (LinearLayout) findViewById(R.id.shiping_lin);
        shiping_rel = (RelativeLayout) findViewById(R.id.shiping_rel);
        shiping_tv = (TextView) findViewById(R.id.shiping_tv);
        shiping_type1 = (TextView) findViewById(R.id.shiping_type1);
        shiping_type2 = (TextView) findViewById(R.id.shiping_type2);
        shiping_type3 = (TextView) findViewById(R.id.shiping_type3);
        shiping_type4 = (TextView) findViewById(R.id.shiping_type4);
    }

    @Override
    protected void initData() {
        presenter = new CreateTrainPresenter(this);
        act_type = getIntent().getIntExtra("act_type", 0);
        mPresenter = new MatchAboutPresenter(this);
        if (1 == act_type) {
            teamB_id = getIntent().getStringExtra("team_id");
            teamB_name.setText(getIntent().getStringExtra("teamName"));
        } else if (2 == act_type) {
            game_id = getIntent().getStringExtra("game_id");
            mPresenter.selectGameInfo(this, game_id);
        }

    }

    @Override
    protected void setListener() {
        back_button_img.setOnClickListener(this);
        shiping_rel.setOnClickListener(this);
        saizhi_rel.setOnClickListener(this);
        duifu_rel.setOnClickListener(this);
        bm_end_time_rel.setOnClickListener(this);
        create_tv.setOnClickListener(this);
        time_rel.setOnClickListener(this);
        place_rel.setOnClickListener(this);
        zz_view.setOnClickListener(this);
        chixu_rel.setOnClickListener(this);
        time_type1.setOnClickListener(this);
        time_type2.setOnClickListener(this);
        time_type3.setOnClickListener(this);
        time_type4.setOnClickListener(this);
        time_type5.setOnClickListener(this);
        time_type6.setOnClickListener(this);
        time_type7.setOnClickListener(this);
        time_type8.setOnClickListener(this);
        saizhi_type1.setOnClickListener(this);
        saizhi_type2.setOnClickListener(this);
        saizhi_type3.setOnClickListener(this);
        saizhi_type4.setOnClickListener(this);
        saizhi_type5.setOnClickListener(this);
        saizhi_type6.setOnClickListener(this);
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
        shiping_type1.setOnClickListener(this);
        shiping_type2.setOnClickListener(this);
        shiping_type3.setOnClickListener(this);
        shiping_type4.setOnClickListener(this);
        select_team_rlin.setOnClickListener(this);

    }


    private void setChixu(TextView time_type) {
        zz_view.setVisibility(View.GONE);
        chixu_lin.setVisibility(View.GONE);
        chixu_tv.setText(time_type.getText());
        chixuStr = chixu_tv.getText().toString().replace("小时", "");
    }

    public void setSaizhi(String str, String st) {
        saizhiStr = str;
        saizhi_tv.setText(st);
        zz_view.setVisibility(View.GONE);
        saizhi_lin.setVisibility(View.GONE);
    }

    private void setDuifu(String s, String s1) {
        duifu_tv.setText(s1);
        duifuStr = s;
        zz_view.setVisibility(View.GONE);
        duifu_lin.setVisibility(View.GONE);
    }

    private void setShiping(String s, String s1) {
        shiping_tv.setText(s1);
        shipingStr = s;
        zz_view.setVisibility(View.GONE);
        shiping_lin.setVisibility(View.GONE);
    }

    @Override
    public void setCreateTournamentData(BaseBean result) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 12315:

                    teamId = data.getStringExtra("team_id");
                    teamName = data.getStringExtra("team_name");
                    team_name.setText(teamName);
                    team_id = teamId;
                    break;
                case 114:

                    teamId = data.getStringExtra("team_id");
                    teamName = data.getStringExtra("team_name");
                    teamB_name.setText(teamName);
                    teamB_id = teamId;
                    break;
                case 10086:
                    String date = data.getStringExtra("date");
                    time_tv.setText(date);
                    break;
                case 10010:
                    String date1 = data.getStringExtra("date");
                    bm_end_time_tv.setText(date1);
                    break;

                case 10000:
                    if (null != data) {
                        place_tv.setText(data.getStringExtra("place_name"));
                    }
            }
        }
    }

    @Override
    public void setAuditOrFightGameData(BaseBean result) {

    }

    @Override
    public void setParticipationGameData(BaseBean result) {

    }

    @Override
    public void cancleGame() {

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
}
