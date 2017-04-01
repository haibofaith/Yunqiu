package com.kball.function.Match.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Match.adapter.MemberAdapter;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Mine.ui.PersonInfoActivity;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/25.
 */

public class PeopleListAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mlistView;
    private ImageView back_img;

    private ArrayList<MemberBean> mData;
    private ArrayList<MemberBean> member;
    private MemberAdapter mAdapter;
    private String type;

    private TextView title_tv;

    @Override
    protected int getContentViewResId() {
        return R.layout.exploits_act;
    }

    @Override
    protected void initView() {

        mlistView = (ListView) findViewById(R.id.mlistView);
        back_img = (ImageView) findViewById(R.id.back_img);
        title_tv = (TextView) findViewById(R.id.title_tv);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        member = (ArrayList<MemberBean>) getIntent().getSerializableExtra("member");
        mData = new ArrayList<MemberBean>();

        switch (type) {
            case "1":
                title_tv.setText("报名球员");
                break;
            case "2":
                title_tv.setText("待定球员");
                break;
            case "3":
                title_tv.setText("请假球员");
                break;
        }

        for (int i = 0; i < member.size(); i++) {

            if (type.equals(member.get(i).getStatus())) {
                mData.add(member.get(i));
            }

        }
        mAdapter = new MemberAdapter(this, mData);
        mlistView.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        back_img.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, PersonInfoActivity.class).putExtra("userId", mData.get(position).getUser_id()));
    }
}