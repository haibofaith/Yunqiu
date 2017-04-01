package com.kball.function.Login.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.bean.TagBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/28.
 */

public class PeopleTagAct extends BaseActivity implements View.OnClickListener {
    private ImageView message_tv;
    private TextView submit_btn;
    private LinearLayout add_lin1,add_lin2;
    private String tag2[] = {"禁区之王", "扑点专家", "擅补单刀", "空中拦截", "后防中坚", "抢球机器", "意识帝", "圆月弯刀", "掷手榴弹", "盘带魔术师", "手术刀传球",
            "中场屏障", "重炮手", "绿荫快马", "过人如麻", "精准长线", "超级替补", "左右开工", "街球达人", " "};
    private String tag1[] = {"球场小白", "中规中矩", "院队水平", "校队水平", "梯队退役", "职业队员", "野球达人", " "};
    private ArrayList<TagBean> mData1,mData2;
    private int num1 = 0;
    private int num2 = 0;
    private int selectNum1 = 0;
    private int selectNum2 = 0;
    private TextView tag_tv111,tag_tv222;

    @Override
    protected int getContentViewResId() {
        return R.layout.prople_tag_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView) findViewById(R.id.message_tv);
        submit_btn = (TextView) findViewById(R.id.submit_btn);
        tag_tv111 = (TextView) findViewById(R.id.tag_tv111);
        tag_tv222 = (TextView) findViewById(R.id.tag_tv222);
        add_lin1 = (LinearLayout) findViewById(R.id.add_lin1);
        add_lin2 = (LinearLayout) findViewById(R.id.add_lin2);
    }

    @Override
    protected void initData() {
        mData1 = new ArrayList<TagBean>();
        for (int i = 0; i < tag1.length; i++) {
            TagBean b = new TagBean();
            b.setTagName(tag1[i]);
            mData1.add(b);
        }
        setlayout1();
        mData2 = new ArrayList<TagBean>();
        for (int i = 0; i < tag2.length; i++) {
            TagBean b = new TagBean();
            b.setTagName(tag2[i]);
            mData2.add(b);
        }
        setlayout2();
    }

    private void setlayout2() {
        num2 = 0;
        add_lin2.removeAllViews();
        for (int i = 0; i < 5; i++) {
            View v = View.inflate(mContext, R.layout.tag_horizator_lin, null);
            LinearLayout tag_horizontal_lin = (LinearLayout) v.findViewById(R.id.tag_horizontal_lin);
            for (int j = 0; j < 4; j++) {
                final int pos = num2;
                View v1 = View.inflate(mContext, R.layout.tag_item, null);
                TextView tag_tv = (TextView) v1.findViewById(R.id.tag_tv);
                tag_tv.setText(mData2.get(pos).getTagName());
                v1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT, 1.0f));

                if (" ".equals(mData2.get(pos).getTagName())) {
                    v1.setVisibility(View.INVISIBLE);
                } else {
                    v1.setVisibility(View.VISIBLE);
                }


                v1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mData2.get(pos).isSelect()) {
                            if (selectNum2 == 3) {

                            } else {
                                mData2.get(pos).setSelect(true);
                                selectNum2++;
                            }
                        } else {
                            mData2.get(pos).setSelect(false);
                            selectNum2--;
                        }
                        setlayout2();

                        tag_tv222.setText("技术特点 (" + selectNum2 + "/3)");
                    }
                });
                if (mData2.get(pos).isSelect()) {
                    tag_tv.setTextColor(Color.parseColor("#ffffff"));
                    tag_tv.setBackgroundResource(R.drawable.green_bg);
                } else {
                    tag_tv.setTextColor(Color.parseColor("#999999"));
                    tag_tv.setBackgroundResource(R.drawable.gray_bg);
                }
                tag_horizontal_lin.addView(v1);

                num2++;
            }

            add_lin2.addView(v);
        }
    }

    @Override
    protected void setListener() {
        message_tv.setOnClickListener(this);
        submit_btn.setOnClickListener(this);
    }

    private void setlayout1() {
        num1 = 0;
        add_lin1.removeAllViews();
        for (int i = 0; i < 2; i++) {
            View v = View.inflate(mContext, R.layout.tag_horizator_lin, null);
            LinearLayout tag_horizontal_lin = (LinearLayout) v.findViewById(R.id.tag_horizontal_lin);
            for (int j = 0; j < 4; j++) {
                final int pos = num1;
                View v1 = View.inflate(mContext, R.layout.tag_item, null);
                TextView tag_tv = (TextView) v1.findViewById(R.id.tag_tv);
                tag_tv.setText(mData1.get(pos).getTagName());
                v1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT, 1.0f));

                if (" ".equals(mData1.get(pos).getTagName())) {
                    v1.setVisibility(View.INVISIBLE);
                } else {
                    v1.setVisibility(View.VISIBLE);
                }


                v1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mData1.get(pos).isSelect()) {
                            if (selectNum1 == 1) {

                            } else {
                                mData1.get(pos).setSelect(true);
                                selectNum1++;
                            }
                        } else {
                            mData1.get(pos).setSelect(false);
                            selectNum1--;
                        }
                        setlayout1();

                        tag_tv111.setText("水平层次 (" + selectNum1 + "/1)");
                    }
                });
                if (mData1.get(pos).isSelect()) {
                    tag_tv.setTextColor(Color.parseColor("#ffffff"));
                    tag_tv.setBackgroundResource(R.drawable.green_bg);
                } else {
                    tag_tv.setTextColor(Color.parseColor("#999999"));
                    tag_tv.setBackgroundResource(R.drawable.gray_bg);
                }
                tag_horizontal_lin.addView(v1);

                num1++;
            }

            add_lin1.addView(v);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_tv:
                finish();
                break;
            case R.id.submit_btn:


                ArrayList<TagBean> data = new ArrayList<TagBean>();
                for (int i = 0; i < mData1.size(); i++) {

                    if (mData1.get(i).isSelect()){
                        data.add(mData1.get(i));
                    }
                }
                for (int i = 0; i < mData2.size(); i++) {

                    if (mData2.get(i).isSelect()){
                        data.add(mData2.get(i));
                    }
                }


                Intent i = new Intent();
                i.putExtra("tagList", data);

                setResult(RESULT_OK,i);
                finish();
                break;
        }

    }
}
