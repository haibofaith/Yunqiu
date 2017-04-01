package com.kball.function.Login.ui;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.ToastAlone;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/22.
 */

public class ScorePositionActivity extends BaseActivity implements View.OnClickListener {
    private TitleView title_view;
    private TextView qf_1, qf_2, qf_3, qf_4, qf_5;
    private TextView zc_1, zc_2, zc_3, zc_4, zc_5;
    private TextView hw_1, hw_2, hw_3, hw_4, hw_5, hw_6;
    private TextView mj_1;
    private Intent intent;
    private static int maxSelect = 0;

    ArrayList<StringBean> selectedList = new ArrayList<>();

    private StringBean qf_1_flag = new StringBean("0"), qf_2_flag = new StringBean("0"), qf_3_flag = new StringBean
            ("0"), qf_4_flag = new StringBean("0"), qf_5_flag = new StringBean("0");
    private StringBean zc_1_flag = new StringBean("0"), zc_2_flag = new StringBean("0"), zc_3_flag = new StringBean
            ("0"), zc_4_flag = new StringBean("0"), zc_5_flag = new StringBean("0");
    private StringBean hw_1_flag = new StringBean("0"), hw_2_flag = new StringBean("0"), hw_3_flag = new StringBean
            ("0"), hw_4_flag = new StringBean("0"), hw_5_flag = new StringBean("0"), hw_6_flag = new StringBean("0");
    private StringBean mj_1_flag = new StringBean("0");

    private StringBean removeStr;

    private String returnStr;

    @Override
    protected int getContentViewResId() {
        return R.layout.score_position_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("选择场上位置");
        title_view.setRightButtonVis();
        title_view.setRightButtonText("完成");


        qf_1 = (TextView) findViewById(R.id.qf_1);
        qf_2 = (TextView) findViewById(R.id.qf_2);
        qf_3 = (TextView) findViewById(R.id.qf_3);
        qf_4 = (TextView) findViewById(R.id.qf_4);
        qf_5 = (TextView) findViewById(R.id.qf_5);

        zc_1 = (TextView) findViewById(R.id.zc_1);
        zc_2 = (TextView) findViewById(R.id.zc_2);
        zc_3 = (TextView) findViewById(R.id.zc_3);
        zc_4 = (TextView) findViewById(R.id.zc_4);
        zc_5 = (TextView) findViewById(R.id.zc_5);

        hw_1 = (TextView) findViewById(R.id.hw_1);
        hw_2 = (TextView) findViewById(R.id.hw_2);
        hw_3 = (TextView) findViewById(R.id.hw_3);
        hw_4 = (TextView) findViewById(R.id.hw_4);
        hw_5 = (TextView) findViewById(R.id.hw_5);
        hw_6 = (TextView) findViewById(R.id.hw_6);

        mj_1 = (TextView) findViewById(R.id.mj_1);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        maxSelect = 0;
    }

    @Override
    protected void setListener() {
        qf_1.setOnClickListener(this);
        qf_2.setOnClickListener(this);
        qf_3.setOnClickListener(this);
        qf_4.setOnClickListener(this);
        qf_5.setOnClickListener(this);

        zc_1.setOnClickListener(this);
        zc_2.setOnClickListener(this);
        zc_3.setOnClickListener(this);
        zc_4.setOnClickListener(this);
        zc_5.setOnClickListener(this);

        hw_1.setOnClickListener(this);
        hw_2.setOnClickListener(this);
        hw_3.setOnClickListener(this);
        hw_4.setOnClickListener(this);
        hw_5.setOnClickListener(this);
        hw_6.setOnClickListener(this);

        mj_1.setOnClickListener(this);


        title_view.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j =0;
                for (int i = 0; i < selectedList.size(); i++) {
                    if (i == 0) {
                        returnStr = selectedList.get(i).getFlag();
                    } else {
                        returnStr = returnStr + "," + selectedList.get(i).getFlag();

                    }
                }
                if (j>3){
                    ToastAlone.show("只能选择3个位置");
                    return;
                }
                intent.putExtra("returnStr", returnStr);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qf_1://前锋
                setFlag(qf_1_flag, (TextView) v);
                break;
            case R.id.qf_2://中锋
                setFlag(qf_2_flag, (TextView) v);
                break;
            case R.id.qf_3://影锋
                setFlag(qf_3_flag, (TextView) v);
                break;
            case R.id.qf_4://左边锋
                setFlag(qf_4_flag, (TextView) v);
                break;
            case R.id.qf_5://右边锋
                setFlag(qf_5_flag, (TextView) v);
                break;

            case R.id.zc_1://左前卫
                setFlag(zc_1_flag, (TextView) v);
                break;
            case R.id.zc_2://右前卫
                setFlag(zc_2_flag, (TextView) v);
                break;
            case R.id.zc_3://前腰
                setFlag(zc_3_flag, (TextView) v);
                break;
            case R.id.zc_4://后腰
                setFlag(zc_4_flag, (TextView) v);
                break;
            case R.id.zc_5://中前卫
                setFlag(zc_5_flag, (TextView) v);
                break;

            case R.id.hw_1://左后卫
                setFlag(hw_1_flag, (TextView) v);
                break;
            case R.id.hw_2://右后卫
                setFlag(hw_2_flag, (TextView) v);
                break;
            case R.id.hw_3://中后卫
                setFlag(hw_3_flag, (TextView) v);
                break;
            case R.id.hw_4://清道夫
                setFlag(hw_4_flag, (TextView) v);
                break;
            case R.id.hw_5://左翼卫
                setFlag(hw_5_flag, (TextView) v);
                break;
            case R.id.hw_6://右翼卫
                setFlag(hw_6_flag, (TextView) v);
                break;
            case R.id.mj_1://门将
                setFlag(mj_1_flag, (TextView) v);
                break;
        }
    }

    private void setFlag(StringBean flag, TextView view) {
        TextView textView = (TextView) view;

        if ("1".equals(flag.getFlag())) {
            //已选中，变取消
            flag.setFlag("0");
            maxSelect--;
            view.setTextColor(Color.parseColor("#999999"));
            view.setBackgroundResource(R.drawable.gray_bg);
            for (StringBean str : selectedList) {
                if (str.getFlag().equals(textView.getText().toString())) {
                    removeStr = str;
                }
            }
            selectedList.remove(removeStr);

        } else {
            //已取消，变选中
            if (maxSelect == 3) {
                ToastAlone.show("最多选择3个场上位置，常用程度由高到低");
            } else {
                flag.setFlag("1");
                maxSelect++;
                view.setTextColor(Color.parseColor("#ffffff"));
                view.setBackgroundResource(R.drawable.green_bg);

                selectedList.add(new StringBean(textView.getText().toString()));
            }
        }


    }

    class StringBean implements Serializable {
        private String flag;

        public StringBean(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
