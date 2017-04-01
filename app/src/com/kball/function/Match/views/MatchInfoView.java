package com.kball.function.Match.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.LeagueInfoBean;
import com.kball.function.Match.bean.LeagueInfoData;
import com.kball.function.Match.presenter.MatchInfoPresenter;
import com.kball.function.Match.ui.BmRankAct;
import com.kball.function.Match.view.MatchInfoViews;
import com.kball.util.ToastAlone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/25.
 */

public class MatchInfoView extends RelativeLayout implements MatchInfoViews {

    private String league_id;
    private MatchInfoPresenter mPresenter;
    private TextView match_name;
    private TextView match_zbf;
    private TextView match_zzf;
    private TextView match_cbf;
    private TextView match_xbf;
    private TextView match_add;
    private TextView match_cd;
    private TextView match_time;
    private TextView match_type;
    private TextView match_info;
    private TextView baoming;
    private TextView baoming_rank, type_tv;
    private RelativeLayout bm_rank_rel;

    private LeagueInfoBean datas;
    private MatchDetaiView mImpl;

    private MatchInfoView(Context context, LinearLayout lin, String league_id,MatchDetaiView mImpl) {
        super(context);
        this.league_id = league_id;
        mPresenter = new MatchInfoPresenter(this);
        this.mImpl = mImpl;
        init(context, lin);
    }

    public static MatchInfoView HomeInit(Context context, LinearLayout lin, String league_id,MatchDetaiView mImpl) {
        return new MatchInfoView(context, lin, league_id,mImpl);
    }

    private void init(final Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.match_detail_info_lin, lin);
        mPresenter.getMatchInfo(context, league_id);
        match_name = (TextView) v.findViewById(R.id.match_name);
        match_zbf = (TextView) v.findViewById(R.id.match_zbf);
        match_zzf = (TextView) v.findViewById(R.id.match_zzf);
        match_cbf = (TextView) v.findViewById(R.id.match_cbf);
        match_xbf = (TextView) v.findViewById(R.id.match_xbf);
        match_add = (TextView) v.findViewById(R.id.match_add);
        match_cd = (TextView) v.findViewById(R.id.match_cd);
        match_time = (TextView) v.findViewById(R.id.match_time);
        match_type = (TextView) v.findViewById(R.id.match_type);
        match_info = (TextView) v.findViewById(R.id.match_info);
        baoming = (TextView) v.findViewById(R.id.baoming);
        baoming_rank = (TextView) v.findViewById(R.id.baoming_rank);
        type_tv = (TextView) v.findViewById(R.id.type_tv);
        bm_rank_rel = (RelativeLayout) v.findViewById(R.id.bm_rank_rel);


        bm_rank_rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent().setClass(context, BmRankAct.class).putExtra("id", league_id));
            }
        });
        baoming.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastAlone.show("功能开发中");


            }
        });
    }

    @Override
    public void setListInfoData(BaseBean<LeagueInfoData<LeagueInfoBean>> result) {
        mImpl.setUrl(result.getData().getShare_url());
        if ("1200".equals(result.getError_code())) {
            Log.d(getClass().getSimpleName(), "leagueInfo成功");
            datas = result.getData().getInfo();
            setData();
            if ("1".equals(result.getData().getShowButton())) {
                baoming.setVisibility(View.GONE);
            } else {
                baoming.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setData() {
        match_name.setText(datas.getLeague_name());
        match_zbf.setText(datas.getDirect());
        match_zzf.setText(datas.getSponsor());
        match_cbf.setText(datas.getUndertake());
        match_xbf.setText(datas.getAssisting());
        match_add.setText(datas.getProvince() + " " + datas.getCity() + " " + datas.getArea());
        match_cd.setText(datas.getLeague_site());
        match_time.setText(timeChange(datas.getStart_time()) + "-" + timeChange(datas.getEnd_time()));
        match_type.setText(saizhi(datas.getGame_system()) + "制");
        match_info.setText(datas.getIntroduce());
//        baoming_rank.setText(datas.getIntroduce());
        type_tv.setText(type(datas.getLeague_type()));

    }


    private String type(String str) {
        if ("1".equals(str)) {
            return "联赛单循环";
        } else if ("2".equals(str)) {
            return "联赛双循环";
        } else if ("3".equals(str)) {
            return "小组赛单循环+淘汰赛";
        } else if ("4".equals(str)) {
            return "小组赛双循环+淘汰赛";
        } else if ("5".equals(str)) {
            return "杯赛";
        } else {
            return "";
        }
    }

    private String saizhi(String str) {
        if ("1".equals(str)) {
            return "3人";
        } else if ("2".equals(str)) {
            return "5人";
        } else if ("3".equals(str)) {
            return "7人";
        } else if ("4".equals(str)) {
            return "8人";
        } else if ("5".equals(str)) {
            return "9人";
        } else if ("6".equals(str)) {
            return "11人";
        } else {
            return "0人";
        }
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
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