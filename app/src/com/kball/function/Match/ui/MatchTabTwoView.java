package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.adapter.MatchTabTwoAdapter;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.StatusBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.bean.TeamBean;
import com.kball.function.Match.bean.TimeBean;
import com.kball.function.Match.impls.MatchTabTwoViewImpl;
import com.kball.function.Match.presenter.MatchTabTwoPresenter;
import com.kball.library.PullToRefreshBase;
import com.kball.library.PullToRefreshListView;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/6.
 */

public class MatchTabTwoView extends RelativeLayout implements AdapterView.OnItemClickListener, MatchTabTwoViewImpl,
        View.OnClickListener,PullToRefreshBase.OnRefreshListener2<ListView> {
    private LinearLayout lin;
    private Context context;
    private View view;
    private ListView mlistView;
    private PullToRefreshListView refreshListView;
    private ArrayList<MatchTabTwoBean> mData;
    private MatchTabTwoAdapter mAdapter;
    private MatchTabTwoPresenter presenter;

    private String team_id, status;
    private static int pageNum = 1;
    private static int pageSize = 100;

    private boolean refreshFlag = true;
    private String league_id;
    private LinearLayout team_lin;
    private LinearLayout status_lin;

    private ArrayList<StatusBean> statusDatas;
    private ArrayList<TeamBean> teamDatas;

    private TextView team_select;
    private TextView status_select;

    private boolean statusFlag = false;
    private boolean teamFlag = false;

    private MatchTabTwoView(Context context, LinearLayout lin) {
        super(context);
        this.lin = lin;
        this.context = context;
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        view = LayoutInflater.from(context).inflate(R.layout.match_tab_two_view, lin);
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.mlistView);
        mlistView = refreshListView.getRefreshableView();
        mData = new ArrayList<>();
        mAdapter = new MatchTabTwoAdapter(context, mData);
        mlistView.setAdapter(mAdapter);
        mAdapter.setDatas(mData);
        presenter = new MatchTabTwoPresenter(this);
        team_lin = (LinearLayout) view.findViewById(R.id.team_lin);
        status_lin = (LinearLayout) view.findViewById(R.id.status_lin);

        team_select = (TextView) view.findViewById(R.id.team_select);
        status_select = (TextView) view.findViewById(R.id.status_select);
        setOnClick();
        getData();
    }

    private void getData() {
        presenter.meTeamLeagueList(context, pageNum + "", pageSize + "", team_id, status);
        presenter.leagueListScreen(context);
    }


    private void setOnClick() {
        team_select.setOnClickListener(this);
        status_select.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
        refreshListView.setOnRefreshListener(this);
    }

    public static MatchTabTwoView homeInit(Context context, LinearLayout lin) {
        return new MatchTabTwoView(context, lin);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        league_id = mData.get(position).getLeague_id();
        context.startActivity(new Intent().setClass(context, MatchDetailActivity.class).putExtra("league_id",
                league_id).putExtra("match_name", mData.get(position-1).getLeague_abbreviation()));
    }

    @Override
    public void setListData(BaseBean<BaseListBean<MatchTabTwoBean>> result) {
        if ("1200".equals(result.getError_code())) {
//            ToastAlone.show("meTeamLeagueList调用成功！");
            completePullWidget();

            if (refreshFlag){
                mData = result.getData().getList();
                mAdapter.setDatas(mData);
            }else{
                mData.addAll(result.getData().getList());
                if (result.getData().getList().size()==0){
                    ToastAlone.show("已经加载到最后一页了");
                    return;
                }
                mAdapter.setDatas(mData);
            }

        }
    }
    private void completePullWidget() {
        refreshListView.postDelayed(new Runnable() {

            @Override
            public void run() {
                refreshListView.onRefreshComplete();

            }
        }, 100);
    }

    //筛选条件
    @Override
    public void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result) {
        if ("1200".equals(result.getError_code())) {
            statusDatas = result.getData().getStatus();
            teamDatas = result.getData().getTeam();
            teamDatas.add(0, new TeamBean("", "全部"));
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {

    }

    //队伍筛选
    private void setTeamsView(LinearLayout lin, final ArrayList<TeamBean> teamDatas) {
        lin.removeAllViews();
        if (teamDatas == null || teamDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < teamDatas.size(); i++) {
            final View view = LayoutInflater.from(context).inflate(R.layout.match_select_list_item, null);
            TextView select_item = (TextView) view.findViewById(R.id.select_item);
            select_item.setText(teamDatas.get(i).getTeam_name());
            team_lin.addView(view);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    team_select.setText(teamDatas.get(position).getTeam_name());
                    team_id = teamDatas.get(position).getTeam_id();
                    teamFlag = !teamFlag;
                    presenter.meTeamLeagueList(context, pageNum + "", pageSize + "", team_id, status);
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
            final View view = LayoutInflater.from(context).inflate(R.layout.match_select_list_item, null);
            TextView select_item = (TextView) view.findViewById(R.id.select_item);
            select_item.setText(statusDatas.get(i).getStatus_name());
            status_lin.addView(view);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    status_select.setText(statusDatas.get(position).getStatus_name());
                    status = statusDatas.get(position).getStatus_value();
                    statusFlag = !statusFlag;
                    presenter.meTeamLeagueList(context, pageNum + "", pageSize + "", team_id, status);
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
            statusFlag = !statusFlag;
            setStatusView(status_lin, null);
        } else {
            statusFlag = !statusFlag;
            setStatusView(status_lin, statusDatas);
        }
    }

    //设置Team选项数据
    public void setTeamDatas() {
        if (teamDatas == null || teamDatas.size() == 0) {
            return;
        }

        if (teamFlag) {
            teamFlag = !teamFlag;
            setTeamsView(team_lin, null);
        } else {
            teamFlag = !teamFlag;
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

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        refreshFlag = true;
        pageNum = 1;
        presenter.meTeamLeagueList(context, pageNum + "", pageSize + "", team_id, status);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        refreshFlag = false;
        pageNum++;
        presenter.meTeamLeagueList(context, pageNum + "", pageSize + "", team_id, status);
    }
}
