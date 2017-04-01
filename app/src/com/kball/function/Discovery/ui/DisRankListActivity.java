package com.kball.function.Discovery.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.Discovery.adapter.RankPersonAdapter;
import com.kball.function.Discovery.adapter.RankTeamAdapter;
import com.kball.function.Discovery.bean.GetBillboardBean;
import com.kball.function.Discovery.bean.RankPersonBean;
import com.kball.function.Discovery.bean.RankTeamBean;
import com.kball.function.Discovery.impl.GetBillboardImpl;
import com.kball.function.Discovery.presenter.GetBillboardPresenter;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.ui.PersonInfoActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/22.
 */

public class DisRankListActivity extends BaseActivity implements View.OnClickListener, GetBillboardImpl ,AdapterView.OnItemClickListener{
    private TitleView title_view;
    private TextView qd_tv;
    private TextView qy_tv;
    private View qd_view;
    private View qy_view;
    private int GreenColor = Color.parseColor("#59be5e");
    private int BlackColor = Color.parseColor("#333333");
    private ListView ranklist_qy_list;
    private ListView ranklist_list;
    private RankTeamAdapter qd_adapter;
    private RankPersonAdapter qy_adapter;

    private TextView rank_title_tv;

    private GetBillboardPresenter presenter;

    private String type = "1";
    private String user_id,nickname,portrait,power;
    private String team_id,team_name,badge,mean_power;

    private ArrayList<GetBillboardBean> datas;
    private ArrayList<GetBillboardBean> datas2;

    @Override
    protected int getContentViewResId() {
        return R.layout.dis_rank_list_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("排行榜");
        qd_tv = (TextView) findViewById(R.id.qd_tv);
        qy_tv = (TextView) findViewById(R.id.qy_tv);
        rank_title_tv = (TextView) findViewById(R.id.rank_title_tv);
        qd_view = (View) findViewById(R.id.qd_view);
        qy_view = (View) findViewById(R.id.qy_view);
        qd_tv.setTextColor(GreenColor);
        qd_view.setVisibility(View.VISIBLE);
        qy_tv.setTextColor(BlackColor);
        qy_view.setVisibility(View.GONE);
        ranklist_qy_list = (ListView) findViewById(R.id.ranklist_qy_list);
        ranklist_list = (ListView) findViewById(R.id.ranklist_list);
        //默认显示球队
        ranklist_list.setVisibility(View.VISIBLE);
        ranklist_qy_list.setVisibility(View.GONE);

        datas = new ArrayList<>();
        datas2 = new ArrayList<>();
        qd_adapter = new RankTeamAdapter(this, datas);
        qy_adapter = new RankPersonAdapter(this, datas2);
        ranklist_list.setAdapter(qd_adapter);
        ranklist_qy_list.setAdapter(qy_adapter);
    }

    @Override
    protected void initData() {
        presenter = new GetBillboardPresenter(this);
//        类型 1：球队 2：球员
        type = "1";
        presenter.getBillboard(this, "1");
    }

    @Override
    protected void setListener() {
        qd_tv.setOnClickListener(this);
        qy_tv.setOnClickListener(this);
        ranklist_qy_list.setOnItemClickListener(this);
        ranklist_list.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qd_tv:
                type="1";
                presenter.getBillboard(this, "1");
                qd_tv.setTextColor(GreenColor);
                qd_view.setVisibility(View.VISIBLE);
                qy_tv.setTextColor(BlackColor);
                qy_view.setVisibility(View.GONE);
                ranklist_list.setVisibility(View.VISIBLE);
                ranklist_qy_list.setVisibility(View.GONE);
                rank_title_tv.setText("球队");
                break;

            case R.id.qy_tv:
                type ="2";
                presenter.getBillboard(this, "2");
                qd_tv.setTextColor(BlackColor);
                qd_view.setVisibility(View.GONE);
                qy_tv.setTextColor(GreenColor);
                qy_view.setVisibility(View.VISIBLE);
                ranklist_list.setVisibility(View.GONE);
                ranklist_qy_list.setVisibility(View.VISIBLE);
                rank_title_tv.setText("球员");
                break;
        }
    }

    @Override
    public void setGetBillboardData(BaseListDataBean<GetBillboardBean> result) {
        if ("1200".equals(result.getError_code())) {
            switch (type) {
                //类型 1：球队 2：球员
                case "1":
                    datas = result.getData();
                    qd_adapter.setDatas(datas);
                    break;
                case "2":
                    datas2 = result.getData();
                    qy_adapter.setDatas(datas2);
                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type.equals("1")){
            startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", datas.get
                    (position).getTeam_id()));
        }else if(type.equals("2")){
            startActivity(new Intent(mContext, PersonInfoActivity.class).putExtra("userId", datas2.get(position)
                    .getUser_id()));
        }
    }
}
