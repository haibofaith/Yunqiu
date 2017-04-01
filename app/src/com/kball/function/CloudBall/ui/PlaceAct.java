package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.presenter.PlacePresenter;
import com.kball.function.CloudBall.view.PlaceView;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

/**
 * Created by xiaole.wang on 17/2/21.
 */

public class PlaceAct  extends BaseActivity implements View.OnClickListener ,PlaceView {
    private LinearLayout old_place_lin;
    private ImageView message_tv;
    private EditText search_edit;
    private String mSearchName;
    private ImageView search_img;
    private PlacePresenter mPresenter;
    private String oldPlace;
    private TextView seach_no_data;

    @Override
    protected int getContentViewResId() {
        return R.layout.place_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView)findViewById(R.id.message_tv);
        search_edit = (EditText) findViewById(R.id.search_edit);
        search_img = (ImageView) findViewById(R.id.search_img);
        old_place_lin = (LinearLayout) findViewById(R.id.old_place_lin);
        seach_no_data = (TextView) findViewById(R.id.seach_no_data);
    }

    @Override
    protected void initData() {
        oldPlace = SPUtil.getInstance().getString("old_place","");

        if (oldPlace.length()>0) {
            old_place_lin.removeAllViews();
            final String str[] = oldPlace.split(",");
            for (int i = 0; i < str.length; i++) {
                View v = View.inflate(mContext,R.layout.place_item,null);
                TextView place_name_tv = (TextView)v.findViewById(R.id.place_name_tv);
                place_name_tv.setText(str[i]);
                final int pos = i;
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.putExtra("place_name",str[pos]);
                        setResult(RESULT_OK,i);
                        finish();
                    }
                });
                old_place_lin.addView(v);
            }
        }
    }

    @Override
    protected void setListener() {
        message_tv.setOnClickListener(this);
        search_img.setOnClickListener(this);
        seach_no_data.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seach_no_data:
                mSearchName = search_edit.getText().toString().trim();

                Intent i = new Intent();
                i.putExtra("place_name",mSearchName);
                setResult(RESULT_OK,i);
                SPUtil.getInstance().putString("old_place",SPUtil.getInstance().getString("old_place","")
                        +mSearchName+",");
                finish();
                break;
            case R.id.message_tv:

                finish();
                break;
            case R.id.search_img:
                mSearchName = search_edit.getText().toString().trim();
                if (mSearchName.length() == 0){
                    ToastAlone.show("请输入搜索的场地名称");
                    return;
                }
                old_place_lin.setVisibility(View.GONE);
                seach_no_data.setVisibility(View.VISIBLE);
                seach_no_data.setText("直接输入\""+mSearchName+"\"");
                break;
        }

    }
}