package com.kball.function.Mine.ui;

import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/3/20.
 */

public class AboutUsActivity extends BaseActivity {
    private TitleView title_view;
    private TextView about_me_tv;
    private TextView about_me_tv2;
    private TextView about_me_tv3;
    private String aboutMe;
    private String aboutMe2;
    private String aboutMe3;
    @Override
    protected int getContentViewResId() {
        return R.layout.about_us_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView)findViewById(R.id.title_view);
        title_view.setTitleText("关于我们");
        about_me_tv = (TextView)findViewById(R.id.about_me_tv);
        about_me_tv2 = (TextView)findViewById(R.id.about_me_tv2);
        about_me_tv3 = (TextView)findViewById(R.id.about_me_tv3);
    }

    @Override
    protected void initData() {
        aboutMe = "云球是专注于全民足球和青少年足球队互联网体育运营商。云球APP是国内首个为踢球者提供视频、数据、赛事／球队／球队管理等功能的应用。";
        aboutMe2 ="云聚足球人口，为足球爱好者打造梦想中的足球乐园";
        aboutMe3 = "云球， \"互联网+足球\"的先驱者，为广大足球爱好者提供专业的技术统计服务，打造深入草根的足球社区平台，用数据和足球连接你和我，共同享受足球的快乐。";
        about_me_tv.setText(aboutMe);
        about_me_tv2.setText(aboutMe2);
        about_me_tv3.setText(aboutMe3);
    }

    @Override
    protected void setListener() {

    }
}
