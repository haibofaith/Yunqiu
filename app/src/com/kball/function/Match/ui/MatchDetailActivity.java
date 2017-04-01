package com.kball.function.Match.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Match.views.IntegralDetailView;
import com.kball.function.Match.views.MatchDetaiView;
import com.kball.function.Match.views.MatchDetailView;
import com.kball.function.Match.views.MatchInfoView;
import com.kball.function.Match.views.VideoDetailView;
import com.kball.util.ShareUtil;

/**
 * Created by user on 2017/2/24.
 */

public class MatchDetailActivity extends BaseActivity implements View.OnClickListener,MatchDetaiView {
    private LinearLayout match_lin;
    private LinearLayout integral_lin;
    private LinearLayout video_lin;
    private LinearLayout rank_lin;
    private LinearLayout event_lin;
    private ImageView match_img;
    private ImageView integral_img;
    private ImageView video_img;
    private ImageView rank_img;
    private ImageView event_img;
    private int ColorBlack = Color.parseColor("#333333");
    private int ColorGray = Color.parseColor("#666666");
    private TextView match_tv;
    private TextView integral_tv;
    private TextView video_tv;
    private TextView rank_tv;
    private TextView event_tv,match_name;
    private RelativeLayout back_button;

    private LinearLayout lin_content;
    private String league_id;
    private String match_name_tv;
    private ImageView send_button_img;
    private String share_url;


    @Override
    protected int getContentViewResId() {
        return R.layout.match_detail_layout;
    }

    @Override
    protected void initView() {

        match_lin = (LinearLayout) findViewById(R.id.match_lin);
        integral_lin = (LinearLayout) findViewById(R.id.integral_lin);
        video_lin = (LinearLayout) findViewById(R.id.video_lin);
        rank_lin = (LinearLayout) findViewById(R.id.rank_lin);
        event_lin = (LinearLayout) findViewById(R.id.event_lin);
        match_img = (ImageView) findViewById(R.id.match_img);
        integral_img = (ImageView) findViewById(R.id.integral_img);
        video_img = (ImageView) findViewById(R.id.video_img);
        rank_img = (ImageView) findViewById(R.id.rank_img);
        event_img = (ImageView) findViewById(R.id.event_img);
        send_button_img = (ImageView) findViewById(R.id.send_button_img);
        match_tv = (TextView) findViewById(R.id.match_tv);
        integral_tv = (TextView) findViewById(R.id.integral_tv);
        video_tv = (TextView) findViewById(R.id.video_tv);
        rank_tv = (TextView) findViewById(R.id.rank_tv);
        event_tv = (TextView) findViewById(R.id.event_tv);
        match_name = (TextView) findViewById(R.id.match_name);

        lin_content = (LinearLayout) findViewById(R.id.lin_content);
        back_button = (RelativeLayout) findViewById(R.id.back_button);
    }

    @Override
    protected void initData() {
        league_id = getIntent().getStringExtra("league_id");
        match_name_tv = getIntent().getStringExtra("match_name");
        match_name.setText(match_name_tv);
        match_img.setImageResource(R.drawable.match_c_icon);
        integral_img.setImageResource(R.drawable.integral_d_icon);
        video_img.setImageResource(R.drawable.video_d_icon);
        rank_img.setImageResource(R.drawable.rank_d_icon);
        event_img.setImageResource(R.drawable.event_d_icon);
        match_tv.setTextColor(ColorBlack);
        integral_tv.setTextColor(ColorGray);
        video_tv.setTextColor(ColorGray);
        rank_tv.setTextColor(ColorGray);
        event_tv.setTextColor(ColorGray);
        lin_content.removeAllViews();
        MatchDetailView.HomeInit(this, lin_content, league_id);
    }

    @Override
    protected void setListener() {
        match_lin.setOnClickListener(this);
        integral_lin.setOnClickListener(this);
        video_lin.setOnClickListener(this);
        rank_lin.setOnClickListener(this);
        event_lin.setOnClickListener(this);
        back_button.setOnClickListener(this);
        send_button_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_button_img:
                ShareUtil.showShare(this, "云球", share_url);
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.match_lin:
                match_img.setImageResource(R.drawable.match_c_icon);
                integral_img.setImageResource(R.drawable.integral_d_icon);
                video_img.setImageResource(R.drawable.video_d_icon);
                rank_img.setImageResource(R.drawable.rank_d_icon);
                event_img.setImageResource(R.drawable.event_d_icon);

                match_tv.setTextColor(ColorBlack);
                integral_tv.setTextColor(ColorGray);
                video_tv.setTextColor(ColorGray);
                rank_tv.setTextColor(ColorGray);
                event_tv.setTextColor(ColorGray);

                lin_content.removeAllViews();
                MatchDetailView.HomeInit(this, lin_content, league_id);
                break;
            case R.id.integral_lin:
                match_img.setImageResource(R.drawable.match_d_icon);
                integral_img.setImageResource(R.drawable.integral_c_icon);
                video_img.setImageResource(R.drawable.video_d_icon);
                rank_img.setImageResource(R.drawable.rank_d_icon);
                event_img.setImageResource(R.drawable.event_d_icon);

                match_tv.setTextColor(ColorGray);
                integral_tv.setTextColor(ColorBlack);
                video_tv.setTextColor(ColorGray);
                rank_tv.setTextColor(ColorGray);
                event_tv.setTextColor(ColorGray);

                lin_content.removeAllViews();
                IntegralDetailView.homeInit(this, lin_content, league_id);
                break;
            case R.id.video_lin:
                match_img.setImageResource(R.drawable.match_d_icon);
                integral_img.setImageResource(R.drawable.integral_d_icon);
                video_img.setImageResource(R.drawable.video_c_icon);
                rank_img.setImageResource(R.drawable.rank_d_icon);
                event_img.setImageResource(R.drawable.event_d_icon);

                match_tv.setTextColor(ColorGray);
                integral_tv.setTextColor(ColorGray);
                video_tv.setTextColor(ColorBlack);
                rank_tv.setTextColor(ColorGray);
                event_tv.setTextColor(ColorGray);

                lin_content.removeAllViews();
                VideoDetailView.homeInit(this, lin_content, league_id, imageLoader);
                break;
            case R.id.rank_lin:
                match_img.setImageResource(R.drawable.match_d_icon);
                integral_img.setImageResource(R.drawable.integral_d_icon);
                video_img.setImageResource(R.drawable.video_d_icon);
                rank_img.setImageResource(R.drawable.rank_c_icon);
                event_img.setImageResource(R.drawable.event_d_icon);

                match_tv.setTextColor(ColorGray);
                integral_tv.setTextColor(ColorGray);
                video_tv.setTextColor(ColorGray);
                rank_tv.setTextColor(ColorBlack);
                event_tv.setTextColor(ColorGray);

                lin_content.removeAllViews();
                RankDetailView.rankInit(this, lin_content, league_id, imageLoader);
                break;
            case R.id.event_lin:
                match_img.setImageResource(R.drawable.match_d_icon);
                integral_img.setImageResource(R.drawable.integral_d_icon);
                video_img.setImageResource(R.drawable.video_d_icon);
                rank_img.setImageResource(R.drawable.rank_d_icon);
                event_img.setImageResource(R.drawable.event_c_icon);

                match_tv.setTextColor(ColorGray);
                integral_tv.setTextColor(ColorGray);
                video_tv.setTextColor(ColorGray);
                rank_tv.setTextColor(ColorGray);
                event_tv.setTextColor(ColorBlack);

                lin_content.removeAllViews();
                MatchInfoView.HomeInit(this, lin_content, league_id,this);
                break;
        }
    }

    @Override
    public void setUrl(String share_url) {
        share_url = share_url;
    }
}
