package com.kball.function.Match.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.ui.PlaceAct;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.custom.EditListDialogView;
import com.kball.function.Match.impls.EditTrainImpl;
import com.kball.function.Match.presenter.EditTrainPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.other.DatePickActivity;
import com.kball.util.ToastAlone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/3/17.
 */

public class EditTrainActivity extends BaseActivity implements View.OnClickListener, EditTrainImpl {
    private TitleView title_view;

    public TextView time_tv;
    public static TextView time_keep_tv;
    public TextView place_name_tv;
    public EditText theme_select_edt;
    public TextView team_name;

    private TextView match_btn;//创建

    private RelativeLayout time_keep_rlin;
    private RelativeLayout place_select_rlin;

    private RelativeLayout select_team_rlin;
    private RelativeLayout time_select;

    public String game_id;

    private EditTrainPresenter presenter;

    private String game_name, entry_teamA, game_time, continue_time, game_site,classify;

    public final static int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;

    private String team_id;

    private LinearLayout time_list_lin;
    private EditListDialogView editListDialogView;

    private Calendar ca;

    private final static int PLACE_CODE = 119;
    private static final int time = 10087;

    @Override
    protected int getContentViewResId() {
        return R.layout.edit_train_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("修改训练详情");

        team_name = (TextView) findViewById(R.id.team_name);
        time_tv = (TextView) findViewById(R.id.time_tv);

        time_keep_tv = (TextView) findViewById(R.id.time_keep_tv);
        place_name_tv = (TextView) findViewById(R.id.place_name_tv);
        match_btn = (TextView) findViewById(R.id.match_btn);
        theme_select_edt = (EditText) findViewById(R.id.theme_select_edt);
        select_team_rlin = (RelativeLayout) findViewById(R.id.select_team_rlin);
        time_select = (RelativeLayout) findViewById(R.id.time_select);
        time_keep_rlin = (RelativeLayout) findViewById(R.id.time_keep_rlin);
        place_select_rlin = (RelativeLayout) findViewById(R.id.place_select_rlin);

        time_list_lin = (LinearLayout) findViewById(R.id.time_list_lin);
        editListDialogView = new EditListDialogView(this, time_list_lin);
        time_list_lin.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        game_id = getIntent().getStringExtra("game_id");
        presenter = new EditTrainPresenter(this);
        presenter.selectGameInfo(this, game_id);
        ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void setListener() {
        select_team_rlin.setOnClickListener(this);
        time_select.setOnClickListener(this);
        time_keep_rlin.setOnClickListener(this);
        place_select_rlin.setOnClickListener(this);
        match_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_team_rlin:
                break;
            case R.id.time_select:
                startActivityForResult(new Intent(this, DatePickActivity.class), time);
                break;
            case R.id.time_keep_rlin:
                showTimeListDialog();
                break;
            case R.id.place_select_rlin:
                startActivityForResult(new Intent().setClass(this, PlaceAct.class), PLACE_CODE);
                break;
            case R.id.match_btn:
//                int	创建类型 1：比赛 3：训练
//                * game_name	否	String	比赛名称/训练主题
//                    * entry_teamA	是	Sting	关联球队（A队/发起球队）
//                * game_time	是	Date	比赛/训练时间,yyyy-MM-dd HH:mm
//                    * continue_time	是	double	持续时间(单位：小时)
//                * game_site	是	String	比赛场地
                game_name = theme_select_edt.getText().toString();
                entry_teamA = team_id;
                game_time = getTime(time_tv.getText().toString());
                continue_time = time_keep_tv.getText().toString().replace("小时", "");
                game_site = place_name_tv.getText().toString();
                classify = "3";
                if (entry_teamA != null) {
                    presenter.updateGame(this,classify, game_name, entry_teamA,
                            game_time, continue_time, game_site,game_id);
                }
                break;
        }
    }

    public void showTimeListDialog() {
        time_list_lin.setVisibility(View.VISIBLE);
    }

    public void dismissTimeListDialog() {
        time_list_lin.setVisibility(View.GONE);
    }


    @Override
    public void setSelectGameInfoData(BaseBean<GameInfoBean<MemberBean>> result) {
        if ("1200".equals(result.getError_code())) {
            team_id = result.getData().getGame_info().getEntry_teamA();
            team_name.setText(result.getData().getGame_info().getTeamA_name());//训练球队
            theme_select_edt.setText(result.getData().getGame_info().getGame_name());//训练名称
            time_tv.setText(timeChange(result.getData().getGame_info().getGame_time()));//训练开始时间
            time_keep_tv.setText(result.getData().getGame_info().getContinue_time() + "小时");//训练持续时常
            place_name_tv.setText(result.getData().getGame_info().getGame_site());//训练场地
        }
    }

    @Override
    public void setUpdateGameData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("修改训练成功");
            setResult(RESULT_OK);
            finish();
        }
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }


    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        time_tv.setText(mYear + "-" + (int) (mMonth + 1) + "-" + mDay);
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PLACE_CODE:
                    if (null != data) {
                        place_name_tv.setText(data.getStringExtra("place_name"));
                    }
                    break;
                case 10087:
                    String date2 = data.getStringExtra("date");
                    time_tv.setText(date2);
                    break;
                default:
                    break;
            }
        }
    }
}
