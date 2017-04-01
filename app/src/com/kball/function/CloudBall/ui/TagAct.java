package com.kball.function.CloudBall.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.bean.TagBean;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/14.
 */

public class TagAct extends BaseActivity implements View.OnClickListener {

    private LinearLayout add_lin;
    private ImageView message_tv;
    private TextView submit_btn;
    private String tag[] = {"球场小白", "中规中矩", "院队水平", "校队水平", "梯队退役", "职业队员", "野球达人", "禁区之王", "扑点专家", "擅补单刀", "空中拦截",
            "后防中坚", "抢球机器", "意识帝", "圆月弯刀", "掷手榴弹", "盘带魔术师", "手术刀传球", "中场屏障", "重炮手", "绿荫快马", "过人如麻", "精准长线", "超级替补",
            "左右开工", "街球达人", " ", " "};

    private int num = 0;
    private ArrayList<TagBean> mData;
    private TextView tag_tv111;
    private int selectNum = 0;

    @Override
    protected int getContentViewResId() {
        return R.layout.tag_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView) findViewById(R.id.message_tv);
        add_lin = (LinearLayout) findViewById(R.id.add_lin);
        submit_btn = (TextView) findViewById(R.id.submit_btn);
        tag_tv111 = (TextView) findViewById(R.id.tag_tv111);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<TagBean>();
        for (int i = 0; i < tag.length; i++) {
            TagBean b = new TagBean();
            b.setTagName(tag[i]);
            mData.add(b);
        }
        setlayout();
    }

    private void setlayout() {
        num = 0;
        add_lin.removeAllViews();
        for (int i = 0; i < 7; i++) {
            View v = View.inflate(mContext, R.layout.tag_horizator_lin, null);
            LinearLayout tag_horizontal_lin = (LinearLayout) v.findViewById(R.id.tag_horizontal_lin);
            for (int j = 0; j < 4; j++) {
                final int pos = num;
                View v1 = View.inflate(mContext, R.layout.tag_item, null);
                TextView tag_tv = (TextView) v1.findViewById(R.id.tag_tv);
                tag_tv.setText(mData.get(pos).getTagName());
                v1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT, 1.0f));

                if (" ".equals(mData.get(pos).getTagName())) {
                    v1.setVisibility(View.INVISIBLE);
                } else {
                    v1.setVisibility(View.VISIBLE);
                }


                v1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mData.get(pos).isSelect()) {
                            if (selectNum == 5) {

                            } else {
                                mData.get(pos).setSelect(true);
                                selectNum++;
                            }
                        } else {
                            mData.get(pos).setSelect(false);
                            selectNum--;
                        }
                        setlayout();

                        tag_tv111.setText("球队标签 (" + selectNum + "/5)");
                    }
                });
                if (mData.get(pos).isSelect()) {
                    tag_tv.setTextColor(Color.parseColor("#ffffff"));
                    tag_tv.setBackgroundResource(R.drawable.green_bg);
                } else {
                    tag_tv.setTextColor(Color.parseColor("#999999"));
                    tag_tv.setBackgroundResource(R.drawable.gray_bg);
                }
                tag_horizontal_lin.addView(v1);

                num++;
            }

            add_lin.addView(v);
        }
    }

    @Override
    protected void setListener() {
        message_tv.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (selectNum > 5) {

                } else {
                    ArrayList<TagBean> data = new ArrayList<TagBean>();
                    for (int i = 0; i < mData.size(); i++) {

                        if (mData.get(i).isSelect()){
                            data.add(mData.get(i));
                        }
                    }
                    Intent i = new Intent();
                    i.putExtra("tagList", data);
                    setResult(RESULT_OK, i);
                    finish();
                }
                break;
            case R.id.message_tv:
                finish();
                break;
        }
    }
}