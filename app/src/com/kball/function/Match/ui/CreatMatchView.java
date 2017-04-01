package com.kball.function.Match.ui;

/**
 * Created by xiaole.wang on 17/2/17.
 */


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.CloudBall.presenter.CreateTrainPresenter;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.CloudBall.ui.SearchPeopleAct;
import com.kball.function.CloudBall.ui.SelectTeamActivity;
import com.kball.function.CloudBall.view.CreateTrainImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.other.DatePickActivity;
import com.kball.util.ToastAlone;

import org.w3c.dom.Text;

import java.util.Calendar;


/**
 * Created by user on 2017/2/15.
 * 创建赛事日程
 */

public class CreatMatchView extends RelativeLayout implements View.OnClickListener, CreateTrainImpl {
    private Context context;
    private LinearLayout lin;
    View v, zz_view;
    private CreatScheduleAct activity;
    private static final int code = 12315;
    private static final int time = 10086;
    private static final int end_time = 10010;
    //比赛名称
    private EditText match_name;
    private String mMatchName;

    private RelativeLayout select_team_rlin;

    public static String team_id;
    public static TextView team_name;
    public static String teamB_id;
    public static TextView teamB_name;

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

    private CreatMatchView(Context context, LinearLayout lin) {
        super(context);
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        this.context = context;
        this.lin = lin;
        this.activity = (CreatScheduleAct) context;
        v = LayoutInflater.from(context).inflate(R.layout.match_view, lin);
        initView();
        setOnClick();
        initData();
    }

    private void initView() {
        match_name = (EditText) v.findViewById(R.id.match_name);
        pay_num = (EditText) v.findViewById(R.id.pay_num);
        team_name = (TextView) v.findViewById(R.id.team_name);
        teamB_name = (TextView) v.findViewById(R.id.teamB_name);
        place_tv = (TextView) v.findViewById(R.id.place_tv);
        time_tv = (TextView) v.findViewById(R.id.time_tv);
        create_tv = (TextView) v.findViewById(R.id.create_tv);
        bm_end_time_tv = (TextView) v.findViewById(R.id.bm_end_time_tv);
        time_rel = (RelativeLayout) v.findViewById(R.id.time_rel);
        select_team_rlin = (RelativeLayout) v.findViewById(R.id.select_team_rlin);
        bm_end_time_rel = (RelativeLayout) v.findViewById(R.id.bm_end_time_rel);
        duishou_rel = (RelativeLayout) v.findViewById(R.id.duishou_rel);
        place_rel = (RelativeLayout) v.findViewById(R.id.place_rel);
        zz_view = (View) v.findViewById(R.id.zz_view);

        chixu_lin = (LinearLayout) v.findViewById(R.id.chixu_lin);
        chixu_rel = (RelativeLayout) v.findViewById(R.id.chixu_rel);
        chixu_tv = (TextView) v.findViewById(R.id.chixu_tv);
        time_type1 = (TextView) v.findViewById(R.id.time_type1);
        time_type2 = (TextView) v.findViewById(R.id.time_type2);
        time_type3 = (TextView) v.findViewById(R.id.time_type3);
        time_type4 = (TextView) v.findViewById(R.id.time_type4);
        time_type5 = (TextView) v.findViewById(R.id.time_type5);
        time_type6 = (TextView) v.findViewById(R.id.time_type6);
        time_type7 = (TextView) v.findViewById(R.id.time_type7);
        time_type8 = (TextView) v.findViewById(R.id.time_type8);

        saizhi_lin = (LinearLayout) v.findViewById(R.id.saizhi_lin);
        saizhi_rel = (RelativeLayout) v.findViewById(R.id.saizhi_rel);
        saizhi_tv = (TextView) v.findViewById(R.id.saizhi_tv);
        saizhi_type1 = (TextView) v.findViewById(R.id.saizhi_type1);
        saizhi_type2 = (TextView) v.findViewById(R.id.saizhi_type2);
        saizhi_type3 = (TextView) v.findViewById(R.id.saizhi_type3);
        saizhi_type4 = (TextView) v.findViewById(R.id.saizhi_type4);
        saizhi_type5 = (TextView) v.findViewById(R.id.saizhi_type5);
        saizhi_type6 = (TextView) v.findViewById(R.id.saizhi_type6);

        duifu_lin = (ScrollView) v.findViewById(R.id.duifu_lin);
        duifu_rel = (RelativeLayout) v.findViewById(R.id.duifu_rel);
        duifu_tv = (TextView) v.findViewById(R.id.duifu_tv);
        duifu_type1 = (TextView) v.findViewById(R.id.duifu_type1);
        duifu_type2 = (TextView) v.findViewById(R.id.duifu_type2);
        duifu_type3 = (TextView) v.findViewById(R.id.duifu_type3);
        duifu_type4 = (TextView) v.findViewById(R.id.duifu_type4);
        duifu_type5 = (TextView) v.findViewById(R.id.duifu_type5);
        duifu_type6 = (TextView) v.findViewById(R.id.duifu_type6);
        duifu_type7 = (TextView) v.findViewById(R.id.duifu_type7);
        duifu_type8 = (TextView) v.findViewById(R.id.duifu_type8);
        duifu_type9 = (TextView) v.findViewById(R.id.duifu_type9);
        duifu_type10 = (TextView) v.findViewById(R.id.duifu_type10);

        shiping_lin = (LinearLayout) v.findViewById(R.id.shiping_lin);
        shiping_rel = (RelativeLayout) v.findViewById(R.id.shiping_rel);
        shiping_tv = (TextView) v.findViewById(R.id.shiping_tv);
        shiping_type1 = (TextView) v.findViewById(R.id.shiping_type1);
        shiping_type2 = (TextView) v.findViewById(R.id.shiping_type2);
        shiping_type3 = (TextView) v.findViewById(R.id.shiping_type3);
        shiping_type4 = (TextView) v.findViewById(R.id.shiping_type4);


    }

    private void setOnClick() {
        duishou_rel.setOnClickListener(this);
        shiping_rel.setOnClickListener(this);
        saizhi_rel.setOnClickListener(this);
        select_team_rlin.setOnClickListener(this);
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
    }


    private void initData() {
        presenter = new CreateTrainPresenter(this);
    }


    public static CreatMatchView matchInit(Context context, LinearLayout lin) {
        return new CreatMatchView(context, lin);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.duishou_rel:
                activity.startActivityForResult(new Intent(context, SearchPeopleAct.class), 114);
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
            case R.id.duifu_type1://参考： 1：红
                setDuifu("1", duifu_type1.getText().toString());
                break;
            case R.id.duifu_type2://橙 5：橙
                setDuifu("5", duifu_type2.getText().toString());
                break;
            case R.id.duifu_type3://黄 6：黄
                setDuifu("6", duifu_type3.getText().toString());
                break;
            case R.id.duifu_type4://绿 7：绿
                setDuifu("7", duifu_type4.getText().toString());
                break;
            case R.id.duifu_type5://蓝 2：蓝
                setDuifu("2", duifu_type5.getText().toString());
                break;
            case R.id.duifu_type6://紫 4：紫
                setDuifu("4", duifu_type6.getText().toString());
                break;
            case R.id.duifu_type7://黑 8：黑
                setDuifu("8", duifu_type7.getText().toString());
                break;
            case R.id.duifu_type8://白 3：白
                setDuifu("3", duifu_type8.getText().toString());
                break;
            case R.id.duifu_type9://灰 9：灰
                setDuifu("9", duifu_type9.getText().toString());
                break;
            case R.id.duifu_type10://粉 10：粉
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
                activity.startActivityForResult(new Intent(context, DatePickActivity.class), end_time);
                break;
            case R.id.select_team_rlin:
                activity.startActivityForResult(new Intent(context, SelectTeamActivity.class), code);
                break;
            case R.id.create_tv:
                submit();
                break;
            case R.id.time_rel:
                activity.startActivityForResult(new Intent(context, DatePickActivity.class), time);
                break;
            case R.id.place_rel:
                activity.startActivityForResult(new Intent(context, PlaceAct.class), 10000);
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
        } if (duifuStr.length() == 0) {
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
        String game_system =saizhiStr;
        String game_site =place_tv.getText().toString().trim();
        String game_cost =payNum;
        String value_added =shipingStr;

        presenter.createTournament1(context, classify, game_name, entry_teamA, entry_teamB, uniform_teamA, game_time,
                 continue_time, apply_end_time,game_system,game_site,game_cost,value_added);
    }


    @Override
    public void setCreateTournamentData(BaseBean result) {
        if ("1200".equals(result.getError_code())){
            ToastAlone.show("创建比赛成功");
            activity.finish();
        }
    }
}
