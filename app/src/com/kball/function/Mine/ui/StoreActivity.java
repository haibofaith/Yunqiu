package com.kball.function.Mine.ui;

import android.widget.ListView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Mine.adapter.StoreAdapter;
import com.kball.function.Mine.bean.StoreBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.presenter.StorePresenter;
import com.kball.function.home.view.StoreImpl;
import com.kball.library.PullToRefreshBase;
import com.kball.library.PullToRefreshListView;
import com.kball.library.PullToRefreshScrollView;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class StoreActivity extends BaseActivity implements StoreImpl ,PullToRefreshBase.OnRefreshListener2<ListView>{
    private TitleView title_view;
    private ListView list_view;
    private StoreAdapter adapter;
    private ArrayList<MatchListBean<MatchGameBean>> mData;
    private StorePresenter presenter;
    private int pageNum = 1;
    private int pageSize = 20;
    private PullToRefreshListView refreshListView;
    private boolean refreshFlag = false;
    private int total;

    @Override
    protected int getContentViewResId() {
        return R.layout.store_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("收藏");
        refreshListView = (PullToRefreshListView) findViewById(R.id.list_view);
        list_view = refreshListView.getRefreshableView();
        mData = new ArrayList<>();
        adapter = new StoreAdapter(this, mData);
        list_view.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new StorePresenter(this);
        presenter.selectCollectionGameList(this, pageNum+"", pageSize+"");
    }

    @Override
    protected void setListener() {
        refreshListView.setOnRefreshListener(this);
    }

    @Override
    public void setStoreData(BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result) {
        total = Integer.parseInt(result.getData().getPageTotal());
        if ("1200".equals(result.getError_code())) {
            if (refreshFlag){
                mData = result.getData().getList();
                adapter.setDatas(mData);
            }else{
                mData.addAll(result.getData().getList());
                adapter.setDatas(mData);
            }

        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        refreshFlag = true;
        presenter.selectCollectionGameList(this, pageNum+"", pageSize+"");
        completePullWidget();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        completePullWidget();
        refreshFlag = false;
        pageNum++;

        if (pageNum>total){
            ToastAlone.show("已经是最后一条了");
            return;
        }
        presenter.selectCollectionGameList(this, pageNum+"", pageSize+"");
    }
    private void completePullWidget() {
        refreshListView.postDelayed(new Runnable() {

            @Override
            public void run() {
                refreshListView.onRefreshComplete();

            }
        }, 100);
    }

}
