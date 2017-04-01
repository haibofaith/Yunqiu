package com.kball.function.Match.views;

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
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.adapter.VideoDetailViewAdapter;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Match.bean.VideoDetailViewBean;
import com.kball.function.Match.impls.VideoDetailViews;
import com.kball.function.Match.presenter.VideoDetailPresenter;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.neliveplayerdemo.NEVideoPlayerActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/24.
 */

public class VideoDetailView extends RelativeLayout implements VideoDetailViews {
    private VideoDetailViewAdapter adapter;
    private ArrayList<VideoDetailViewBean> datas;
    private VideoDetailPresenter mPrensenter;
    private LinearLayout add_lin;
    private Context mContext;
    private ImageLoader imageLoader;

    private VideoDetailView(Context context, LinearLayout lin, String league_id,ImageLoader imageLoader) {
        super(context);
        mPrensenter = new VideoDetailPresenter(this);
        mContext = context;
        this.imageLoader = imageLoader;
        init(context, lin, league_id);
    }


    private void init(Context context, LinearLayout lin, String league_id) {
        View v = LayoutInflater.from(context).inflate(R.layout.video_detail_view, lin);
        add_lin = (LinearLayout) v.findViewById(R.id.add_lin);
        adapter = new VideoDetailViewAdapter(context, datas);
        mPrensenter.getVideo(context,league_id);
    }

    public static VideoDetailView homeInit(Context context, LinearLayout lin, String league_id, ImageLoader imageLoader) {
        return new VideoDetailView(context, lin, league_id,imageLoader);
    }

    @Override
    public void setListData(final BaseBean<BaseListBean<VideoBean>> result) {

        for (int i = 0; i <result.getData().getList().size() ; i++) {
            View v1 =  View.inflate(mContext, R.layout.video_detail_view_item, null);
            ImageView img = (ImageView)v1.findViewById(R.id.img);
            TextView video_name = (TextView)v1.findViewById(R.id.video_name);
            video_name.setText(result.getData().getList().get(i).getVideo_name());
            imageLoader.displayImage(result.getData().getList().get(i).getScreenshots(),img);
            final int pos = i;
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NEVideoPlayerActivity.class);
                    intent.putExtra("media_type", "software");
                    intent.putExtra("decode_type", "hardware");
                    intent.putExtra("videoPath", result.getData().getList().get(pos).getVideo_address_ordinary());
                    mContext.startActivity(intent);
                }
            });
            add_lin.addView(v1);
        }
    }
}
