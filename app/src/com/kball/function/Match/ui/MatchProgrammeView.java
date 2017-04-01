package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.adapter.MatchListAdapter;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.GameFiltrateConditionsBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.bean.StatusBean;
import com.kball.function.Match.bean.TeamBean;
import com.kball.function.Match.bean.TimeBean;
import com.kball.function.Match.presenter.MatchListPresenter;
import com.kball.function.Match.presenter.MatchProgramPresenter;
import com.kball.function.Match.impls.MatchListView;
import com.kball.function.Match.impls.MatchProgramView;
import com.kball.function.home.impl.DialogView;
import com.kball.library.PullToRefreshBase;
import com.kball.library.PullToRefreshListView;
import com.kball.library.PullToRefreshScrollView;
import com.kball.util.LoadingDialog;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/22.
 */

public class MatchProgrammeView extends RelativeLayout implements AdapterView.OnItemClickListener, PullToRefreshBase
        .OnRefreshListener2<ListView>, MatchProgramView, View.OnClickListener, MatchListView {


    private Context context;
    private LinearLayout lin;

    private ArrayList<MatchListBean<MatchGameBean>> mData;
    private MatchListAdapter mAdapter;
    private MatchProgramPresenter presenter;

    private LinearLayout status;
    private LinearLayout team;
    private LinearLayout time;
    private ArrayList<StatusBean> statusDatas;
    private ArrayList<TimeBean> timeDatas;
    private ArrayList<TeamBean> teamDatas;

    private TextView team_select;
    private TextView status_select;
    private TextView time_select;

    private MatchListPresenter listPresenter;

    private String game_status;
    private String game_time;
    private String team_id;

    private ArrayList<MatchListBean<MatchGameBean>> gameListDatas;

    private static int pageNo = 1;
    private static int pageSize = 10;

    private static int pageTotal = 0;

    private boolean refreshFlag = true;//刷新

    private boolean statusFlag = false;
    private boolean teamFlag = false;
    private boolean timeFlag = false;

    private LinearLayout add_lin;

    private LoadingDialog loadingDialog;
    private DialogView mhehe;
    private PullToRefreshScrollView refresh_lin;
    boolean isrefresh = true;
    private RelativeLayout no_data;
    private TextView creat_btn;

    private MatchProgrammeView(Context context, LinearLayout lin, DialogView mhehe) {
        super(context);
        loadingDialog = new LoadingDialog(context);
        init(context, lin, mhehe);
    }

    private void init(Context context, LinearLayout lin, DialogView mhehe) {
        this.context = context;
        this.lin = lin;
        this.mhehe = mhehe;
        View v = LayoutInflater.from(context).inflate(R.layout.match_programme_view, lin);
        refresh_lin = (PullToRefreshScrollView) v.findViewById(R.id.refresh_lin);
        refresh_lin.setMode(PullToRefreshBase.Mode.BOTH);

        status = (LinearLayout) v.findViewById(R.id.status);
        team = (LinearLayout) v.findViewById(R.id.team);
        time = (LinearLayout) v.findViewById(R.id.time);
        add_lin = (LinearLayout) v.findViewById(R.id.add_lin);

        time_select = (TextView) v.findViewById(R.id.time_select);
        team_select = (TextView) v.findViewById(R.id.team_select);
        status_select = (TextView) v.findViewById(R.id.status_select);
        creat_btn = (TextView) v.findViewById(R.id.creat_btn);

        no_data = (RelativeLayout) v.findViewById(R.id.no_data);
        setOnClick();
        initData();
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
            team.addView(view);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    team_select.setText(teamDatas.get(position).getTeam_name());
                    team_id = teamDatas.get(position).getTeam_id();
                    pageNo = 1;
                    listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time,
                            team_id, refreshFlag);
                    teamFlag = !teamFlag;
                    setTeamsView(team, null);
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
            status.addView(view);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    status_select.setText(statusDatas.get(position).getStatus_name());
                    game_status = statusDatas.get(position).getStatus_value();
                    pageNo = 1;
                    listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time,
                            team_id, refreshFlag);
                    statusFlag = !statusFlag;
                    setStatusView(status, null);
                }
            });
        }
    }

    //时间筛选
    private void setTimesView(LinearLayout lin, final ArrayList<TimeBean> timeDatas) {
        lin.removeAllViews();
        if (timeDatas == null || timeDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < timeDatas.size(); i++) {
            final View view = LayoutInflater.from(context).inflate(R.layout.match_select_list_item, null);
            TextView select_item = (TextView) view.findViewById(R.id.select_item);
            select_item.setText(timeDatas.get(i).getTime_name());
            time.addView(view);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    time_select.setText(timeDatas.get(position).getTime_name());
                    game_time = timeDatas.get(position).getTime_value();
                    pageNo = 1;
                    listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time,
                            team_id, refreshFlag);
                    timeFlag = !timeFlag;
                    setTimesView(time, null);
                }
            });
        }
    }

    private void setOnClick() {
        time_select.setOnClickListener(this);
        team_select.setOnClickListener(this);
        status_select.setOnClickListener(this);
        creat_btn.setOnClickListener(this);
        refresh_lin.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                OnComplete(refresh_lin);
                isrefresh = true;
                pageNo = 1;
                listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time, team_id,
                        refreshFlag);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                OnComplete(refresh_lin);
                isrefresh = false;
                pageNo++;
                listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time, team_id,
                        refreshFlag);

            }
        });

    }

    @SuppressWarnings("rawtypes")
    protected void OnComplete(final PullToRefreshBase pullToRefreshBase) {
        if (pullToRefreshBase != null) {
            pullToRefreshBase.postDelayed(new Runnable() {

                @Override
                public void run() {
                    pullToRefreshBase.onRefreshComplete();
                }
            }, 100);
        }
    }


    private void initData() {
        mData = new ArrayList<>();
        presenter = new MatchProgramPresenter(this);
        listPresenter = new MatchListPresenter(this);
        Log.d("Match", "ACCESS_TOKEN" + SPUtil.getInstance().getString(C.SP.ACCESS_TOKEN, ""));
        Log.d("Match", "USER_ID" + SPUtil.getInstance().getString(C.SP.USER_ID, ""));
        getData();
    }

    private void getData() {
        presenter.gameFiltrateConditions(context);
        pageNo = 1;
        listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time, team_id, refreshFlag);
    }


    public static MatchProgrammeView MatchProgrammeInit(Context context, LinearLayout lin, DialogView mhehe) {
        return new MatchProgrammeView(context, lin, mhehe);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        game_status = null;
        game_time = null;
        team_id = null;
        pageNo = 1;
        refreshFlag = true;
        listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time, team_id, refreshFlag);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (pageNo <= pageTotal) {
            refreshFlag = false;
            listPresenter.selectGameList(context, pageNo + "", pageSize + "", game_status, game_time, team_id,
                    refreshFlag);
        } else {
            completePullWidget();
            ToastAlone.show("已经是最后一页");
        }
    }

    private void completePullWidget() {
        refresh_lin.postDelayed(new Runnable() {

            @Override
            public void run() {
                refresh_lin.onRefreshComplete();

            }
        }, 100);
    }

    //筛选条件返回
    @Override
    public void setObjData(BaseBean<GameFiltrateConditionsBean> result) {
        if ("1200".equals(result.getError_code())) {
            statusDatas = result.getData().getStatus();
            teamDatas = result.getData().getTeam();
            teamDatas.add(0, new TeamBean("", "全部"));
            timeDatas = result.getData().getTime();
            timeDatas.add(0, new TimeBean("", "全部"));
        }
    }

    @Override
    public void dis() {
        mhehe.dismiss();
    }

    @Override
    public void show() {
        mhehe.show();
    }

    //设置状态选项数据
    public void setStatusDatas() {
        if (statusDatas == null || statusDatas.size() == 0) {
            return;
        }
        if (statusFlag) {//真
            statusFlag = !statusFlag;
            setStatusView(status, null);
        } else {
            statusFlag = !statusFlag;
            setStatusView(status, statusDatas);
        }
    }

    //设置Team选项数据
    public void setTeamDatas() {
        if (teamDatas == null || teamDatas.size() == 0) {
            return;
        }

        if (teamFlag) {
            teamFlag = !teamFlag;
            setTeamsView(team, null);
        } else {
            teamFlag = !teamFlag;
            setTeamsView(team, teamDatas);
        }
    }

    //设置time选项数据
    public void setTimeDatas() {
        if (timeDatas == null || timeDatas.size() == 0) {
            return;
        }

        if (timeFlag) {
            timeFlag = !timeFlag;
            setTimesView(time, null);
        } else {
            timeFlag = !timeFlag;
            setTimesView(time, timeDatas);
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
            case R.id.time_select:
                setTimeDatas();
                break;
            case R.id.creat_btn:
                context.startActivity(new Intent(context, CreatScheduleAct.class));
                break;
        }
    }

    @Override
    public void showLoading() {
        mhehe.show();
    }

    @Override
    public void dismissLoading() {
        mhehe.dismiss();
    }

    //通过当前页面调用
    @Override
    public void setListData(BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result) {
        if ("1200".equals(result.getError_code())) {

            if (isrefresh) {
                mData = result.getData().getList();
                if (mData.size() == 0) {
                    no_data.setVisibility(View.VISIBLE);
                } else {

                    no_data.setVisibility(View.GONE);
                    add_lin.removeAllViews();
                    for (int i = 0; i < mData.size(); i++) {
                        View v = View.inflate(context, R.layout.match_list_item, null);
                        TextView date_tv = (TextView) v.findViewById(R.id.date_tv);
                        LinearLayout match_game_list = (LinearLayout) v.findViewById(R.id.match_game_list);

                        if (mData.get(i).getGroups() != null) {
                            date_tv.setText(mData.get(i).getGroups());
                        } else if (mData.get(i).getGroup() != null) {
                            date_tv.setText(mData.get(i).getGroup());
                        }
                        match_game_list.removeAllViews();
                        for (int j = 0; j < mData.get(i).getGame().size(); j++) {
                            BSListItemView msc = new BSListItemView(context, mData.get(i).getGame().get(j));
                            match_game_list.addView(msc);
                        }
                        add_lin.addView(v);
                    }

                }
            } else {
                if (result.getData().getList().size() == 0) {
                    ToastAlone.show("已加载到最后一页了");
                    return;
                }
                add_lin.removeAllViews();
                mData.addAll(result.getData().getList());
                long l = System.currentTimeMillis();
                for (int i = 0; i < mData.size(); i++) {
                    View v = View.inflate(context, R.layout.match_list_item, null);
                    TextView date_tv = (TextView) v.findViewById(R.id.date_tv);
                    LinearLayout match_game_list = (LinearLayout) v.findViewById(R.id.match_game_list);

                    if (mData.get(i).getGroups() != null) {
                        date_tv.setText(mData.get(i).getGroups());
                    } else if (mData.get(i).getGroup() != null) {
                        date_tv.setText(mData.get(i).getGroup());
                    }
                    match_game_list.removeAllViews();
                    for (int j = 0; j < mData.get(i).getGame().size(); j++) {
                        BSListItemView msc = new BSListItemView(context, mData.get(i).getGame().get(j));
                        match_game_list.addView(msc);
                    }
                    add_lin.addView(v);

                }
                ToastAlone.show((System.currentTimeMillis() - l) + "");
            }


        }
    }


}