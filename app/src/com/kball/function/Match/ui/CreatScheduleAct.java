package com.kball.function.Match.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Match.custom.ListDialogView;

import java.util.Calendar;

/**
 * Created by xiaole.wang on 17/2/16.
 */

public class CreatScheduleAct extends BaseActivity implements View.OnClickListener {
    private LinearLayout child_layout;

    private static CreatMatchView mCreatMatchView;
    public static CreatTrainView mCreatTrainView;
    private View view1, view2;
    private TextView tab_one, tab_two;
    private String teamId, teamName;

    public final static int DATE_DIALOG = 1;
    private int mYear, mMonth, mDay;

    private Calendar ca;

    private TextView team_name;

    private LinearLayout time_list_lin;
    private ListDialogView listDialogView;

    private final static int PLACE_CODE = 119;

    private ImageView back_button_img;


    @Override
    protected int getContentViewResId() {
        return R.layout.creat_schedule_layout;
    }

    @Override
    protected void initView() {
        child_layout = (LinearLayout) findViewById(R.id.child_layout);
        tab_one = (TextView) findViewById(R.id.tab_one);
        tab_two = (TextView) findViewById(R.id.tab_two);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        time_list_lin = (LinearLayout) findViewById(R.id.time_list_lin);
        listDialogView = new ListDialogView(this, time_list_lin);
        time_list_lin.setVisibility(View.GONE);
        back_button_img = (ImageView) findViewById(R.id.back_button_img);
    }

    @Override
    protected void initData() {
        mCreatMatchView = CreatMatchView.matchInit(mContext, child_layout);
        mCreatTrainView = CreatTrainView.trainInit(mContext, child_layout);
        ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    public void showTimeListDialog() {
        time_list_lin.setVisibility(View.VISIBLE);
    }

    public void dismissTimeListDialog() {
        time_list_lin.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        back_button_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_one:
                child_layout.removeAllViews();
                CreatMatchView.matchInit(mContext, child_layout);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                tab_one.setTextColor(getResources().getColor(R.color.color_green));
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.tab_two:
                child_layout.removeAllViews();
                CreatTrainView.trainInit(mContext, child_layout);
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                tab_two.setTextColor(getResources().getColor(R.color.color_green));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case R.id.back_button_img:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case C.SP.SelectTeamCode:
                    teamId = data.getStringExtra("team_id");
                    teamName = data.getStringExtra("team_name");
                    mCreatTrainView.team_name.setText(teamName);
                    mCreatTrainView.team_id = teamId;
                    mCreatMatchView.team_name.setText(teamName);
                    mCreatMatchView.team_id = teamId;
                    break;
                case 12315:
                    teamId = data.getStringExtra("team_id");
                    teamName = data.getStringExtra("team_name");
                    mCreatMatchView.team_name.setText(teamName);
                    mCreatMatchView.team_id = teamId;
                    break;
                case 114:
                    teamId = data.getStringExtra("team_id");
                    teamName = data.getStringExtra("team_name");
                    mCreatMatchView.teamB_name.setText(teamName);
                    mCreatMatchView.teamB_id = teamId;
                    break;
                case 10086:
                    String date = data.getStringExtra("date");
                    mCreatMatchView.time_tv.setText(date);
                    mCreatMatchView.bm_end_time_tv.setText(date);
                    break;
                case 10087:
                    String date2 = data.getStringExtra("date");
                    mCreatTrainView.time_tv.setText(date2);
                    break;
                case 10010:
                    String date1 = data.getStringExtra("date");
                    mCreatMatchView.bm_end_time_tv.setText(date1);
                    break;
                case PLACE_CODE:
                    if (resultCode == RESULT_OK) if (null != data) {
                        mCreatTrainView.place_name_tv.setText(data.getStringExtra("place_name"));
                    }
                case 10000:
                    if (null != data) {
                        mCreatMatchView.place_tv.setText(data.getStringExtra("place_name"));
                    }

            }
        }
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
        mCreatTrainView.time_tv.setText(mYear + "-" + (int) (mMonth + 1) + "-" + mDay);
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
}
