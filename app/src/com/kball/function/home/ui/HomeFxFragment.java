package com.kball.function.home.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseFragment;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.ui.SearchBallAct;
import com.kball.function.Discovery.adapter.BeautyMatchAdapter;
import com.kball.function.Discovery.bean.BeautyMatchBean;
import com.kball.function.Discovery.ui.BeatyContentActivity;
import com.kball.function.Discovery.ui.DisMatchActivity;
import com.kball.function.Discovery.ui.DisRankListActivity;
import com.kball.function.Discovery.ui.DisSelectActivity;
import com.kball.function.Discovery.ui.LaunchFightActivity;
import com.kball.function.Discovery.ui.LaunchFightListActivity;
import com.kball.function.Discovery.ui.VideoDetailActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.adapter.MatchOtherAdapter;
import com.kball.function.Match.bean.MatchOtherBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Match.ui.CreatScheduleAct;
import com.kball.function.home.adapter.FxPagerAdapter;
import com.kball.function.home.bean.BannerBean;
import com.kball.function.home.bean.FxListBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.impl.FxImpl;
import com.kball.function.home.presenter.FxPresenter;
import com.kball.neliveplayerdemo.NEVideoPlayerActivity;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/1/16.
 */
public class HomeFxFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, FxImpl, ViewPager.OnPageChangeListener {
    private View rootView;
    private ListView list_view;
    private BeautyMatchAdapter adapter;

    private LinearLayout beauty_content_lin;//精彩内容
    private LinearLayout match_lin;//赛事
    private LinearLayout rank_lis_lin;//排行榜
    private LinearLayout launch_match_lin;//约战

    private FxPresenter presenter;
    private ArrayList<BannerBean> banners;
    private ArrayList<VideoBean> videos;

    private TextView search_edit;//搜索编辑框
    private ImageView search_icon;

    private ViewPager view_pager;
    private FxPagerAdapter pagerAdapter;
    private ArrayList<View> viewList;
    private LayoutInflater inflater;

    private LinearLayout dots_id_lin;//小点

    public HomeFxFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_sc, null, false);
        return rootView;
    }

    @Override
    protected void initView() {
        list_view = (ListView) findViewById(R.id.list_view);

        adapter = new BeautyMatchAdapter(getActivity(), videos);
        list_view.setAdapter(adapter);

        beauty_content_lin = (LinearLayout) findViewById(R.id.beauty_content_lin);
        match_lin = (LinearLayout) findViewById(R.id.match_lin);
        rank_lis_lin = (LinearLayout) findViewById(R.id.rank_lis_lin);
        launch_match_lin = (LinearLayout) findViewById(R.id.launch_match_lin);

        search_edit = (TextView)findViewById(R.id.search_edit);
        search_icon = (ImageView)findViewById(R.id.search_icon);

        view_pager = (ViewPager) findViewById(R.id.view_pager);
        viewList = new ArrayList<>();
        inflater = LayoutInflater.from(getActivity());
        pagerAdapter = new FxPagerAdapter(getActivity(),viewList);
        view_pager.setAdapter(pagerAdapter);

        dots_id_lin = (LinearLayout)findViewById(R.id.dots_id_lin);
    }

    @Override
    protected void initData() {
        presenter = new FxPresenter(this);
        presenter.index(getActivity());
        presenter.getVideo(getActivity(),null);
    }


    @Override
    protected void setListener() {
        beauty_content_lin.setOnClickListener(this);
        match_lin.setOnClickListener(this);
        rank_lis_lin.setOnClickListener(this);
        launch_match_lin.setOnClickListener(this);
        list_view.setOnItemClickListener(this);
        search_icon.setOnClickListener(this);
        search_edit.setOnClickListener(this);
        view_pager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beauty_content_lin://精彩内容
                startActivity(new Intent(getActivity(), BeatyContentActivity.class));
                break;
            case R.id.rank_lis_lin://排行榜
                startActivity(new Intent(getActivity(), DisRankListActivity.class));
                break;
            case R.id.launch_match_lin://约战广场
                startActivity(new Intent(getActivity(), LaunchFightListActivity.class));
                break;
            case R.id.match_lin://赛事
                startActivity(new Intent(getActivity(), DisMatchActivity.class));
                break;
            case R.id.search_icon:
                startActivity(new Intent(getActivity(), SearchBallAct.class).putExtra("searchName",""));
                break;
            case R.id.search_edit:
                startActivity(new Intent(getActivity(), SearchBallAct.class).putExtra("searchName",""));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        startActivity(new Intent(getActivity(), VideoDetailActivity.class));
        Intent intent = new Intent(mContext, NEVideoPlayerActivity.class);
        intent.putExtra("media_type", "software");
        intent.putExtra("decode_type", "hardware");
        intent.putExtra("videoPath", videos.get(position).getVideo_address_ordinary());
        mContext.startActivity(intent);
    }

    @Override
    public void setIndexData(BaseListDataBean<BannerBean> result) {
        banners = result.getData();
        for (int i=0;i<banners.size();i++){
            final View view = inflater.inflate(R.layout.view_pager_item,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.view_pager_img);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL_YPY+banners.get(i).getImg_url(),imageView);
            viewList.add(view);
        }
        pagerAdapter.setViewList(viewList);

        setDots(view_pager.getCurrentItem());
    }

    @Override
    public void setGetVideoData(BaseBean<BaseListBean<VideoBean>>  result) {
        if ("1200".equals(result.getError_code())){
            videos = result.getData().getList();
            adapter.setDatas(videos);
        }
    }

    /**
     * 加viewPager底部点
     * */
    private void setDots(int position){
        dots_id_lin.removeAllViews();
        for (int i = 0; i < banners.size(); i++) {
            if(i==position){
                View dot = LayoutInflater.from(getActivity()).inflate(
                        R.layout.small_dot_s_layout, null);
                dots_id_lin.addView(dot);
            }else{
                View dot = LayoutInflater.from(getActivity())
                        .inflate(R.layout.small_dot_d_layout, null);
                dots_id_lin.addView(dot);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setDots(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
