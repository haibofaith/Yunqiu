package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.BestPlayerAdapter;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter;
import com.kball.function.CloudBall.adapter.SearchMacthAdapter;
import com.kball.function.CloudBall.adapter.SearchPeopleAdapter;
import com.kball.function.CloudBall.adapter.SearchRanksAdapter;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchMacthBean;
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.bean.SearchRanksBean;
import com.kball.function.CloudBall.presenter.SearchPresenter;
import com.kball.function.CloudBall.view.SearchView;
import com.kball.function.Match.ui.MatchDetailActivity;
import com.kball.function.Mine.ui.PersonInfoActivity;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class SearchBallAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        SearchView {

    private ListView mlistView;
    private ArrayList<SearchPeopleBean> mPeopleData;
    private ArrayList<SearchRankBean> mRanksData;
    private ArrayList<SearchMacthBean> mMacthData;
    private SearchPeopleAdapter mSearchPeopleAdapter;
    private SearchRanksAdapter mSearchRanksAdapter;
    private SearchMacthAdapter mSearchMacthAdapter;
    private BestPlayerAdapter mBestAdapter;
    private View view1, view2, view3;
    private TextView tab_one, tab_two, tab_three, cannel_tv;
    private SearchPresenter mPresenter;
    private String searchName, type = "2";
    private EditText search_tv;
    private ImageView back_tv;

    @Override
    protected int getContentViewResId() {
        return R.layout.search_act;
    }

    @Override
    protected void initView() {
        mlistView = (ListView) findViewById(R.id.mlistView);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        view3 = (View) findViewById(R.id.view3);
        tab_one = (TextView) findViewById(R.id.tab_one);
        tab_two = (TextView) findViewById(R.id.tab_two);
        tab_three = (TextView) findViewById(R.id.tab_three);
        cannel_tv = (TextView) findViewById(R.id.cannel_tv);
        search_tv = (EditText) findViewById(R.id.search_tv);
        back_tv = (ImageView) findViewById(R.id.back_tv);
    }

    @Override
    protected void initData() {
        mPresenter = new SearchPresenter(mContext, this);
        searchName = getIntent().getStringExtra("searchName");
        search_tv.setText(searchName);
        mPeopleData = new ArrayList<SearchPeopleBean>();
        mRanksData = new ArrayList<SearchRankBean>();
        mMacthData = new ArrayList<SearchMacthBean>();
        mSearchPeopleAdapter = new SearchPeopleAdapter(mContext, mPeopleData);
        mSearchRanksAdapter = new SearchRanksAdapter(mContext, mRanksData);
        mSearchMacthAdapter = new SearchMacthAdapter(mContext, mMacthData);
        mlistView.setAdapter(mSearchPeopleAdapter);
        mSearchPeopleAdapter.setDatas(mPeopleData);
        mSearchRanksAdapter.setDatas(mRanksData);
        mSearchMacthAdapter.setDatas(mMacthData);
        mPresenter.getPeople(searchName, "2");


    }

    @Override
    protected void setListener() {
        mlistView.setOnItemClickListener(this);
        tab_one.setOnClickListener(this);
        tab_two.setOnClickListener(this);
        tab_three.setOnClickListener(this);
        cannel_tv.setOnClickListener(this);
        back_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_one:
                mlistView.setAdapter(mSearchPeopleAdapter);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                tab_one.setTextColor(getResources().getColor(R.color.color_green));
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_66));
                mPresenter.getPeople(searchName, "2");
                type = "1";
                break;
            case R.id.tab_two:
                mlistView.setAdapter(mSearchRanksAdapter);
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                tab_two.setTextColor(getResources().getColor(R.color.color_green));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_66));
                mPresenter.getRank(searchName, "1");
                type = "2";
                break;
            case R.id.tab_three:
                mlistView.setAdapter(mSearchMacthAdapter);
                view2.setVisibility(View.INVISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                tab_two.setTextColor(getResources().getColor(R.color.color_66));
                tab_one.setTextColor(getResources().getColor(R.color.color_66));
                tab_three.setTextColor(getResources().getColor(R.color.color_green));
                mPresenter.getMatch(searchName, "3");
                type = "3";
                break;
            case R.id.back_tv:
                finish();
                break;
            case R.id.cannel_tv:
                searchName = search_tv.getText().toString().trim();
                if ("2".equals(type)) {
                    mPresenter.getPeople(searchName, "2");
                } else if ("1".equals(type)) {
                    mPresenter.getRank(searchName, "1");
                } else if ("3".equals(type)) {
                    mPresenter.getMatch(searchName, "3");
                }
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if ("2".equals(type)) {
            startActivity(new Intent(mContext, PersonInfoActivity.class).putExtra("userId", mPeopleData.get(position)
                    .getUser_id()));

        } else if ("1".equals(type)) {
            startActivity(new Intent().setClass(mContext, RanksDetailAct.class).putExtra("team_id", mRanksData.get
                    (position).getTeam_id()));
        } else if ("3".equals(type)) {
            startActivity(new Intent().setClass(mContext, MatchDetailActivity.class).putExtra("league_id", mMacthData
                    .get(position).getLeague_id()).putExtra("match_name", mMacthData.get(position).getLeague_name()));
        }

    }

    @Override
    public void setInfoData(SearchBaseBean<SearchRankBean> data) {
        mRanksData = data.getTeam();
        mSearchRanksAdapter.setDatas(mRanksData);
    }

    @Override
    public void setInfoPeopleData(SearchBaseBean<SearchPeopleBean> data) {
        mPeopleData = data.getUser();
        mSearchPeopleAdapter.setDatas(mPeopleData);
    }

    @Override
    public void setInfoMatchData(SearchBaseBean data) {
        mMacthData = data.getGame();
        mSearchMacthAdapter.setDatas(mMacthData);
    }

}