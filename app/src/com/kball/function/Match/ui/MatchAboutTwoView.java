package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Match.impls.MatchAboutTwoViews;
import com.kball.function.Match.presenter.MatchAboutTwoPresenter;
import com.kball.function.Match.views.HeheView;
import com.kball.neliveplayerdemo.NEVideoPlayerActivity;
import com.kball.neliveplayerdemo.util.NEVideoPlayerActivity2;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/24.
 */
public class MatchAboutTwoView extends RelativeLayout implements MatchAboutTwoViews, View.OnClickListener {
    private Context context;
    private LinearLayout lin,add_lin;
    private MatchAboutTwoPresenter mPresenter;
    private ImageView screenshots;
    private ImageLoader imageLoader;
    private String url;
    private String game_id;
    private LinearLayout layout_lin;
    private TextView no_data_tv, jianjie;
    private RelativeLayout jingcai_rel;
    private HeheView mView;


    private MatchAboutTwoView(Context context, LinearLayout lin, ImageLoader imageLoader, String game_id,HeheView mView) {
        super(context);
        mPresenter = new MatchAboutTwoPresenter(this);
        this.imageLoader = imageLoader;
        this.mView = mView;
        init(context, lin, game_id);
    }

    private void init(Context context, LinearLayout lin, String game_id) {
        this.context = context;
        this.lin = lin;
        this.game_id = game_id;
        View v = LayoutInflater.from(context).inflate(R.layout.match_about_view2, lin);
        screenshots = (ImageView) v.findViewById(R.id.screenshots);
        layout_lin = (LinearLayout) v.findViewById(R.id.layout_lin);
        add_lin = (LinearLayout) v.findViewById(R.id.add_lin);
        no_data_tv = (TextView) v.findViewById(R.id.no_data_tv);
        jianjie = (TextView) v.findViewById(R.id.jianjie);
        jingcai_rel = (RelativeLayout) v.findViewById(R.id.jingcai_rel);
        setOnClick();
        initData();
    }

    private void setOnClick() {
        screenshots.setOnClickListener(this);
    }


    private void initData() {
        mPresenter.getVideo(context, game_id);
    }


    public static MatchAboutTwoView matchAboutTwoInit(Context context, LinearLayout lin, ImageLoader imageLoader,
                                                      String game_id,HeheView mView) {
        return new MatchAboutTwoView(context, lin, imageLoader, game_id,mView);
    }

    @Override
    public void setListData(ArrayList<VideoBean> result) {
        if (result.size() > 0) {
            imageLoader.displayImage(result.get(0).getScreenshots(), screenshots);
            url = result.get(0).getVideo_address_ordinary();
            layout_lin.setVisibility(View.VISIBLE);
            no_data_tv.setVisibility(View.GONE);
            jianjie.setText(result.get(0).getBrief());
            if (result.size() > 1) {
                jingcai_rel.setVisibility(View.VISIBLE);
            } else {
                jingcai_rel.setVisibility(View.GONE);
            }
        } else {
            layout_lin.setVisibility(View.GONE);
            no_data_tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screenshots:


                mView.setVideo("software","hardware",url);
                break;
        }
    }
}
