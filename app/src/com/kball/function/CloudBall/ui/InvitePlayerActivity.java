package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.InvitePlayerAdatper;
import com.kball.function.CloudBall.bean.InvitePlayerBean;
import com.kball.function.CloudBall.bean.SearchTUGList;
import com.kball.function.CloudBall.presenter.InvitePlayerPresenter;
import com.kball.function.CloudBall.view.InvitePresenterImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.ui.PersonInfoActivity;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/18.
 * 邀请球员
 */

public class InvitePlayerActivity extends BaseActivity implements InvitePresenterImpl, View.OnClickListener,
        AdapterView.OnItemClickListener {
    private TitleView title_view;
    private ListView list_view;
    private InvitePlayerAdatper adatper;
    private ArrayList<InvitePlayerBean> datas;
    private InvitePlayerPresenter presenter;
    private int number = 20;
    private EditText search_edit;
    private ImageView search_icon;

    @Override
    protected int getContentViewResId() {
        return R.layout.invite_player_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("邀请球员");
        list_view = (ListView) findViewById(R.id.list_view);
        datas = new ArrayList<>();
        adatper = new InvitePlayerAdatper(this, datas);
        list_view.setAdapter(adatper);
        search_edit = (EditText) findViewById(R.id.search_edit);
        search_icon = (ImageView) findViewById(R.id.search_icon);
    }

    @Override
    protected void initData() {

        presenter = new InvitePlayerPresenter(this);
        presenter.getRecommendationUser(this,"20");
    }

    @Override
    protected void setListener() {
        search_icon.setOnClickListener(this);
        list_view.setOnItemClickListener(this);
    }

    @Override
    public void setGetRecommendationUserData(BaseBean<ArrayList<InvitePlayerBean>> result) {
        if ("1200".equals(result.getError_code())) {
            datas = result.getData();
            adatper.setDatas(datas);
        }
    }

    @Override
    public void setSearchTeamAndUserAndGameData(BaseBean<SearchTUGList> result) {
        if ("1200".equals(result.getError_code())) {
            datas = result.getData().getUser();
            adatper.setDatas(datas);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_icon:
                if (search_edit.getText().toString() == null || search_edit.getText().toString().length() == 0) {
                    ToastAlone.show("请输入要搜索的球队、球员");
                    return;
                }
                //类型 0：全部（球队、球员、赛事） 1：球队 2：球员 3：赛事
                presenter.searchTeamAndUserAndGame(this, search_edit.getText().toString(), "2");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, PersonInfoActivity.class).putExtra("userId", datas.get(position)
                .getUser_id()));

    }
}
