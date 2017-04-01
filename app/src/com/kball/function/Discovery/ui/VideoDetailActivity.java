package com.kball.function.Discovery.ui;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/2/22.
 */

public class VideoDetailActivity extends BaseActivity {
    private TitleView title_view;
    @Override
    protected int getContentViewResId() {
        return R.layout.video_detail_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("视频详情");
        title_view.setRightButtonVis();
        title_view.setRightButtonImgVis();
        title_view.setRightButtonImg(R.drawable.send_icon);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
