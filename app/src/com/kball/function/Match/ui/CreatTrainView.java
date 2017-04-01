package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.CloudBall.presenter.CreateTrainPresenter;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.CloudBall.ui.SelectTeamActivity;
import com.kball.function.CloudBall.view.CreateTrainImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.other.DatePickActivity;
import com.kball.util.ToastAlone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CreatTrainView extends RelativeLayout implements View.OnClickListener, CreateTrainImpl {


    private Context context;
    private LinearLayout lin;
    private RelativeLayout select_team_rlin;
    private CreatScheduleAct activity;

    private RelativeLayout time_select;

    public static TextView time_tv;
    public static TextView time_keep_tv;
    public static TextView place_name_tv;
    public static EditText theme_select_edt;
    public static String team_id;
    public static TextView team_name;

    private RelativeLayout time_keep_rlin;
    private RelativeLayout place_select_rlin;

    private static int PLACE_CODE = 119;

    private TextView match_btn;//创建

    private CreateTrainPresenter presenter;

    private String classify= "3";
    private String game_name,entry_teamA,game_time,continue_time,game_site;

    private static final int time = 10087;


    private CreatTrainView(Context context, LinearLayout lin) {
        super(context);
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        this.context = context;
        this.lin = lin;
        this.activity = (CreatScheduleAct) context;
        View v = LayoutInflater.from(context).inflate(R.layout.train_view, lin);
        team_name = (TextView) v.findViewById(R.id.team_name);
        time_tv = (TextView) v.findViewById(R.id.time_tv);
        time_keep_tv = (TextView) v.findViewById(R.id.time_keep_tv);
        place_name_tv = (TextView) v.findViewById(R.id.place_name_tv);
        match_btn = (TextView) v.findViewById(R.id.match_btn);
        theme_select_edt = (EditText) v.findViewById(R.id.theme_select_edt);
        select_team_rlin = (RelativeLayout) v.findViewById(R.id.select_team_rlin);
        time_select = (RelativeLayout) v.findViewById(R.id.time_select);
        time_keep_rlin = (RelativeLayout) v.findViewById(R.id.time_keep_rlin);
        place_select_rlin = (RelativeLayout) v.findViewById(R.id.place_select_rlin);
        presenter = new CreateTrainPresenter(this);
        setOnClick();
        initData();
    }

    private void setOnClick() {
        select_team_rlin.setOnClickListener(this);
        time_select.setOnClickListener(this);
        time_keep_rlin.setOnClickListener(this);
        place_select_rlin.setOnClickListener(this);
        match_btn.setOnClickListener(this);
    }


    private void initData() {

    }


    public static CreatTrainView trainInit(Context context, LinearLayout lin) {
        return new CreatTrainView(context, lin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_team_rlin:
                activity.startActivityForResult(new Intent(context, SelectTeamActivity.class), C.SP.SelectTeamCode);
                break;
            case R.id.time_select:
                activity.startActivityForResult(new Intent(context, DatePickActivity.class), time);
                break;
            case R.id.time_keep_rlin:
                activity.showTimeListDialog();
                break;
            case R.id.place_select_rlin:
                activity.startActivityForResult(new Intent().setClass(context, PlaceAct.class), PLACE_CODE);
                break;
            case R.id.match_btn:
//                classify	是	int	创建类型 1：比赛 3：训练
//                * game_name	否	String	比赛名称/训练主题
//                    * entry_teamA	是	Sting	关联球队（A队/发起球队）
//                * game_time	是	Date	比赛/训练时间,yyyy-MM-dd HH:mm
//                    * continue_time	是	double	持续时间(单位：小时)
//                * game_site	是	String	比赛场地
                game_name = theme_select_edt.getText().toString();
                entry_teamA = team_id;
                game_time = time_tv.getText().toString();
                continue_time = time_keep_tv.getText().toString().replace("小时","");
                game_site = place_name_tv.getText().toString();
                presenter.createTournament(context, classify, game_name, entry_teamA,
                        game_time, continue_time,game_site);
                break;
        }
    }

    @Override
    public void setCreateTournamentData(BaseBean result) {
        if ("1200".equals(result.getError_code())){
            ToastAlone.show("创建训练成功");
            activity.finish();
        }
    }

    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getStrTime(re_time);
    }

    public static String getStrTime(String cc_time) {
        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
}
