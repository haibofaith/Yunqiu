package com.kball.function.Match.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.bean.BaseListBean;
import com.kball.function.Match.adapter.MatchDetailViewAdapter;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.presenter.MatchDetailPresenter;
import com.kball.function.Match.view.MatchDetailViews;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/24.
 */

public class MatchDetailView extends RelativeLayout implements MatchDetailViews ,View.OnClickListener{
    private ListView list_view;
    private MatchDetailViewAdapter adapter;
    private ArrayList<MatchDetailViewBean> datas;
    private LinearLayout add_lin;
    private MatchDetailPresenter mPresenter;
    private Context mContext;
    private String league_id;
    private TextView dangqian_lun,syl_tv,xyl_tv;
    BaseListBean<MatchListBean<MatchDetailViewBean>> result;
    private LinearLayout group_lin;

    private MatchDetailView(Context context, LinearLayout lin, String league_id) {
        super(context);

        mContext = context;
        this.mContext = context;
        this.league_id = league_id;
        init(context, lin);
    }

    public static MatchDetailView HomeInit(Context context, LinearLayout lin, String league_id) {

        return new MatchDetailView(context, lin, league_id);

    }

    private void init(Context context, LinearLayout lin) {
        mPresenter = new MatchDetailPresenter(this);
        View v = LayoutInflater.from(context).inflate(R.layout.match_detail_item_lin, lin);
        add_lin = (LinearLayout) v.findViewById(R.id.add_lin);
        group_lin = (LinearLayout) v.findViewById(R.id.group_lin);
        dangqian_lun = (TextView) v.findViewById(R.id.dangqian_lun);
        syl_tv = (TextView) v.findViewById(R.id.syl_tv);
        xyl_tv = (TextView) v.findViewById(R.id.xyl_tv);
        mPresenter.match_saichen(this.mContext, league_id, 0);
        xyl_tv.setOnClickListener(this);
        syl_tv.setOnClickListener(this);
        dangqian_lun.setOnClickListener(this);
    }


    @Override
    public void setListData(final BaseListBean<MatchListBean<MatchDetailViewBean>> result) {
        this.result = result;
        dangqian_lun.setText("第" + result.getCurve_rounds() + "轮");
        group_lin.removeAllViews();
        for (int i = 0; i < result.getTotal_rounds(); i++) {
            View v = View.inflate(mContext,R.layout.match_detail_item,null);
            TextView group_name = (TextView)v.findViewById(R.id.group_name);
            group_name.setText("第" + (i+1) + "轮");
            final int pos = i ;
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.match_saichen(mContext, league_id,pos+1);
                    group_lin.setVisibility(GONE);
                }
            });
            group_lin.addView(v);
        }

        add_lin.removeAllViews();
        for (int i = 0; i < result.getList().size(); i++) {

            MatchDetailSCView msc = new MatchDetailSCView(mContext,result.getList().get(i));

            add_lin.addView(msc);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dangqian_lun:
                group_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.syl_tv:
                if ((result.getCurve_rounds() -1 )== 0){
                    ToastAlone.show("已经是第一轮了");
                }
                else{
                    mPresenter.match_saichen(this.mContext, league_id,result.getCurve_rounds() -1);
                }
                break;
            case R.id.xyl_tv:
                if ((result.getCurve_rounds() +1 )> result.getTotal_rounds()){
                    ToastAlone.show("已经是最后一轮了");
                }
                else{
                    mPresenter.match_saichen(this.mContext, league_id,result.getCurve_rounds() +1);
                }
                break;
        }
    }
}
