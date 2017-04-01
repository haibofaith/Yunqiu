package com.kball.function.Match.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.RankDetailBean;
import com.kball.function.Match.bean.SelectCrunchiesBean;
import com.kball.function.Match.presenter.RankDetailPresenter;
import com.kball.function.Match.view.RankDetailViews;
import com.kball.function.Mine.custom.CircleView;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class RankDetailView extends RelativeLayout implements RankDetailViews, View.OnClickListener {


    private Context mContext;
    private RankDetailPresenter mPresenter;
    //    private LinearLayout add_lin;
    private ImageLoader imageLoader;
    //    private TextView zhugong,jinqiu,huang,hong;
    private String league_id;

    private LinearLayout content_lin;

    private RankDetailView(Context context, LinearLayout lin, String league_id, ImageLoader imageLoader) {
        super(context);
        mContext = context;
        mPresenter = new RankDetailPresenter(this);
        this.league_id = league_id;
        this.imageLoader = imageLoader;
        init(context, lin, league_id);
    }


    private void init(Context context, LinearLayout lin, String league_id) {
        View v = LayoutInflater.from(context).inflate(R.layout.rank_detail_view, lin);
        content_lin = (LinearLayout) v.findViewById(R.id.content_lin);
        mPresenter.selectCrunchies(context, league_id);
    }

    public static RankDetailView rankInit(Context context, LinearLayout lin, String league_id, ImageLoader imageLoader) {
        return new RankDetailView(context, lin, league_id, imageLoader);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
        }
    }

    @Override
    public void setSelectCrunchiesData(BaseBean<SelectCrunchiesBean> result) {
        if ("1200".equals(result.getError_code())) {
            content_lin.removeAllViews();

            final View Goal_v = LayoutInflater.from(mContext).inflate(R.layout.crunchies_lin_item, null);
            if (result.getData().getGoal_list().size() > 0) {
                LinearLayout Goal_title_text_lin = (LinearLayout) Goal_v.findViewById(R.id.title_text_lin);
                TextView Goal_title_text = (TextView) Goal_v.findViewById(R.id.title_text);
                Goal_title_text.setText("进球榜");
                LinearLayout Goal_add_lin = (LinearLayout) Goal_v.findViewById(R.id.add_lin);
                content_lin.addView(Goal_v);
                for (int i = 0; i < result.getData().getGoal_list().size(); i++) {
                    final View view = LayoutInflater.from(mContext).inflate(R.layout.rank_detail_view_item, null);
                    TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
                    CircleImageView img = (CircleImageView) view.findViewById(R.id.img);
                    TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                    TextView duiwu = (TextView) view.findViewById(R.id.duiwu);
                    TextView num = (TextView) view.findViewById(R.id.num);
                    id_tv.setText((i + 1) + "");
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getGoal_list().get(i).getPortrait(), img);
                    name_tv.setText(result.getData().getGoal_list().get(i).getNickname());
                    duiwu.setText(result.getData().getGoal_list().get(i).getTeam_name());
                    num.setText(result.getData().getGoal_list().get(i).getTotal_number());
                    Goal_add_lin.addView(view);
                }
            }

            final View Assist_v = LayoutInflater.from(mContext).inflate(R.layout.crunchies_lin_item, null);
            if (result.getData().getAssist_list().size() > 0) {
                LinearLayout Assist_title_text_lin = (LinearLayout) Assist_v.findViewById(R.id.title_text_lin);
                TextView Assist_title_text = (TextView) Assist_v.findViewById(R.id.title_text);
                Assist_title_text.setText("助攻榜");
                LinearLayout Assist_add_lin = (LinearLayout) Assist_v.findViewById(R.id.add_lin);
                content_lin.addView(Assist_v);
                for (int i = 0; i < result.getData().getAssist_list().size(); i++) {
                    final View view = LayoutInflater.from(mContext).inflate(R.layout.rank_detail_view_item, null);
                    TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
                    CircleImageView img = (CircleImageView) view.findViewById(R.id.img);
                    TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                    TextView duiwu = (TextView) view.findViewById(R.id.duiwu);
                    TextView num = (TextView) view.findViewById(R.id.num);

                    id_tv.setText((i + 1) + "");
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getAssist_list().get(i).getPortrait(), img);
                    name_tv.setText(result.getData().getAssist_list().get(i).getNickname());
                    duiwu.setText(result.getData().getAssist_list().get(i).getTeam_name());
                    num.setText(result.getData().getAssist_list().get(i).getTotal_number());
                    Assist_add_lin.addView(view);
                }
            }

            final View RedCard_v = LayoutInflater.from(mContext).inflate(R.layout.crunchies_lin_item, null);
            if (result.getData().getRedCard_list().size() > 0) {
                LinearLayout RedCard_title_text_lin = (LinearLayout) RedCard_v.findViewById(R.id.title_text_lin);
                TextView RedCard_title_text = (TextView) RedCard_v.findViewById(R.id.title_text);
                RedCard_title_text.setText("红牌");
                LinearLayout RedCard_add_lin = (LinearLayout) RedCard_v.findViewById(R.id.add_lin);
                content_lin.addView(RedCard_v);
                for (int i = 0; i < result.getData().getRedCard_list().size(); i++) {
                    final View view = LayoutInflater.from(mContext).inflate(R.layout.rank_detail_view_item, null);
                    TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
                    CircleImageView img = (CircleImageView) view.findViewById(R.id.img);
                    TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                    TextView duiwu = (TextView) view.findViewById(R.id.duiwu);
                    TextView num = (TextView) view.findViewById(R.id.num);

                    id_tv.setText((i + 1) + "");
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getRedCard_list().get(i).getPortrait(), img);
                    name_tv.setText(result.getData().getRedCard_list().get(i).getNickname());
                    duiwu.setText(result.getData().getRedCard_list().get(i).getTeam_name());
                    num.setText(result.getData().getRedCard_list().get(i).getTotal_number());
                    RedCard_add_lin.addView(view);
                }
            }

            final View YellowCard_v = LayoutInflater.from(mContext).inflate(R.layout.crunchies_lin_item, null);
            if (result.getData().getYellowCard_list().size() > 0) {
                LinearLayout YellowCard_title_text_lin = (LinearLayout) YellowCard_v.findViewById(R.id.title_text_lin);
                TextView YellowCard_title_text = (TextView) YellowCard_v.findViewById(R.id.title_text);
                YellowCard_title_text.setText("黄牌");
                LinearLayout YellowCard_add_lin = (LinearLayout) YellowCard_v.findViewById(R.id.add_lin);
                content_lin.addView(YellowCard_v);
                for (int i = 0; i < result.getData().getYellowCard_list().size(); i++) {
                    final View view = LayoutInflater.from(mContext).inflate(R.layout.rank_detail_view_item, null);
                    TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
                    CircleImageView img = (CircleImageView) view.findViewById(R.id.img);
                    TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                    TextView duiwu = (TextView) view.findViewById(R.id.duiwu);
                    TextView num = (TextView) view.findViewById(R.id.num);

                    id_tv.setText((i + 1) + "");
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getYellowCard_list().get(i).getPortrait(), img);
                    name_tv.setText(result.getData().getYellowCard_list().get(i).getNickname());
                    duiwu.setText(result.getData().getYellowCard_list().get(i).getTeam_name());
                    num.setText(result.getData().getYellowCard_list().get(i).getTotal_number());
                    YellowCard_add_lin.addView(view);
                }
            }
        }
    }

}
