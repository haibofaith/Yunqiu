package com.kball.function.Discovery.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.adapter.DisMatchAdapter;
import com.kball.function.Discovery.bean.DisMatchBean;
import com.kball.function.Discovery.impl.DisMatchImpl;
import com.kball.function.Discovery.presenter.DisMatchPresenter;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.adapter.MatchTabTwoAdapter;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.StatusBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.bean.TeamBean;
import com.kball.function.Match.ui.MatchDetailActivity;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.ShareUtil;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/22.
 */

public class DisMatchActivity extends BaseActivity implements DisMatchImpl, AdapterView.OnItemClickListener ,View.OnClickListener{
    private TitleView title_view;
    private ListView list_view;
    private MatchTabTwoAdapter adapter;
    private ArrayList<MatchTabTwoBean> mData;

    private DisMatchPresenter presenter;
    private Context context;
    private static int pageNum = 1;
    private static int pageSize = 100;
    private String team_id, status;

    private LinearLayout team_lin;
    private LinearLayout status_lin;

    private ArrayList<StatusBean> statusDatas;
    private ArrayList<TeamBean> teamDatas;

    private TextView team_select;
    private TextView status_select;

    private boolean statusFlag = false;
    private boolean teamFlag = false;

    @Override
    protected int getContentViewResId() {
        return R.layout.dis_match_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("赛事");
        list_view = (ListView) findViewById(R.id.list_view);
        mData = new ArrayList<>();
        adapter = new MatchTabTwoAdapter(this, mData);
        list_view.setAdapter(adapter);
        team_lin = (LinearLayout)findViewById(R.id.team_lin);
        status_lin = (LinearLayout)findViewById(R.id.status_lin);

        team_select = (TextView) findViewById(R.id.team_select);
        status_select = (TextView) findViewById(R.id.status_select);
    }

    @Override
    protected void initData() {
        presenter = new DisMatchPresenter(this);
        presenter.meTeamLeagueList(this, pageNum + "", pageSize + "", team_id, status,null,"2");
        presenter.leagueListScreen(this,"2");
    }

    @Override
    protected void setListener() {
        list_view.setOnItemClickListener(this);
        team_select.setOnClickListener(this);
        status_select.setOnClickListener(this);
    }

    @Override
    public void setMeTeamLeagueListData(BaseBean<BaseListBean<MatchTabTwoBean>> result) {
        if ("1200".equals(result.getError_code())) {
            mData = result.getData().getList();
            adapter.setDatas(mData);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent().setClass(context, MatchDetailActivity.class).putExtra("league_id", mData.get
                (position).getLeague_id()).putExtra("match_name", mData.get(position).getLeague_abbreviation()));
    }
    public void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result) {
        if ("1200".equals(result.getError_code())) {
            statusDatas = result.getData().getStatus();
            teamDatas = result.getData().getTeam();
        }
    }

    //队伍筛选
    private void setTeamsView(LinearLayout lin, final ArrayList<TeamBean> teamDatas) {
        lin.removeAllViews();
        if (teamDatas == null || teamDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < teamDatas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.match_select_list_item, null);
            TextView select_item = (TextView) view.findViewById(R.id.select_item);
            select_item.setText(teamDatas.get(i).getTeam_name());
            team_lin.addView(view);
            final int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    team_select.setText(teamDatas.get(position).getTeam_name());
                    team_id= teamDatas.get(position).getTeam_id();
                    teamFlag = !teamFlag;
                    presenter.meTeamLeagueList(getApplicationContext(), pageNum + "", pageSize + "", team_id, status,null,"2");
                    setTeamsView(team_lin, null);
                }
            });
        }
    }

    //状态筛选
    private void setStatusView(LinearLayout lin, final ArrayList<StatusBean> statusDatas) {
        lin.removeAllViews();
        if (statusDatas == null || statusDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < statusDatas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.match_select_list_item, null);
            TextView select_item = (TextView) view.findViewById(R.id.select_item);
            select_item.setText(statusDatas.get(i).getStatus_name());
            status_lin.addView(view);
            final int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status_select.setText(statusDatas.get(position).getStatus_name());
                    status= statusDatas.get(position).getStatus_value();
                    statusFlag=!statusFlag;
                    presenter.meTeamLeagueList(getApplicationContext(), pageNum + "", pageSize + "", team_id, status,null,"2");
                    setStatusView(status_lin, null);
                }
            });
        }
    }

    //设置状态选项数据
    public void setStatusDatas() {
        if (statusDatas == null || statusDatas.size() == 0) {
            return;
        }
        if (statusFlag) {//真
            statusFlag=!statusFlag;
            setStatusView(status_lin, null);
        } else {
            statusFlag=!statusFlag;
            setStatusView(status_lin, statusDatas);
        }
    }

    //设置Team选项数据
    public void setTeamDatas() {
        if (teamDatas == null || teamDatas.size() == 0) {
            return;
        }

        if (teamFlag) {
            teamFlag=!teamFlag;
            setTeamsView(team_lin, null);
        } else {
            teamFlag=!teamFlag;
            setTeamsView(team_lin, teamDatas);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.status_select:
                setStatusDatas();
                break;
            case R.id.team_select:
                setTeamDatas();
                break;
        }
    }
}
