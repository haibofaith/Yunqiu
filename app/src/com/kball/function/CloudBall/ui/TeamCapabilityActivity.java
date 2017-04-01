package com.kball.function.CloudBall.ui;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.bean.GetTeamPowerBean;
import com.kball.function.CloudBall.custom.DataLineView;
import com.kball.function.CloudBall.presenter.TeamCapaPresenter;
import com.kball.function.CloudBall.view.TeamCapaImpl;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.bean.ListBaseBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017/3/19.
 * 球队战力值
 */

public class TeamCapabilityActivity extends BaseActivity implements TeamCapaImpl {
    private TitleView title_view ;
    private TeamCapaPresenter presenter;
    private String team_id;
    private ArrayList<GetTeamPowerBean> datas;
    private DataLineView data_line_view;
    private ArrayList<String> xMonths;
    private ArrayList<Double> yPercents;
    private ArrayList<Double> yPercents2;
    private ArrayList<Double> yPercents3;
    private ArrayList<Double> yPercents4;
    private ArrayList<Double> yPercents5;
    @Override
    protected int getContentViewResId() {
        return R.layout.team_capability_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("球队战力值");
        data_line_view = (DataLineView)findViewById(R.id.data_line_view);
    }

    @Override
    protected void initData() {
        team_id = getIntent().getStringExtra("team_id");
        presenter = new TeamCapaPresenter(this);
        presenter.getTeamPower(this,team_id);
        datas = new ArrayList<>();
        xMonths = new ArrayList<>();
        yPercents = new ArrayList<>();
        yPercents2 = new ArrayList<>();
        yPercents3 = new ArrayList<>();
        yPercents4 = new ArrayList<>();
        yPercents5 = new ArrayList<>();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setGetTeamPowerData(ListBaseBean<GetTeamPowerBean> result) {
        datas = result.getData();
        for (int i=0;i<5;i++){
            if (result.getData().size()>i){
                xMonths.add(timeChange(result.getData().get(i).getCount_time()));
                yPercents.add(Double.parseDouble(result.getData().get(i).getAttack_gross())/100);
                yPercents2.add(Double.parseDouble(result.getData().get(i).getDefensive_gross())/100);
                yPercents3.add(Double.parseDouble(result.getData().get(i).getAggressive_gross())/100);
                yPercents4.add(Double.parseDouble(result.getData().get(i).getTechnology_gross())/100);
                yPercents5.add(Double.parseDouble(result.getData().get(i).getPhysical_gross())/100);
            }else{
                xMonths.add("-月-日");
                yPercents.add((double) 0.3);
                yPercents2.add((double) 0.3);
                yPercents3.add((double) 0.3);
                yPercents4.add((double) 0.3);
                yPercents5.add((double) 0.3);
            }
        }
        data_line_view.setxMonths(xMonths);
        data_line_view.setyPercents(yPercents);
        data_line_view.setyPercents2(yPercents2);
        data_line_view.setyPercents3(yPercents3);
        data_line_view.setyPercents4(yPercents4);
        data_line_view.setyPercents5(yPercents5);
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
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
}
