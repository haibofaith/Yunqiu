package com.kball.function.Mine.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;

/**
 * Created by user on 2017/2/17.
 */

public class TitleView extends FrameLayout {

    private RelativeLayout back_button;
    private TextView back_button_text;
    private ImageView back_button_img;
    private ImageView right_button_img;
    private TextView title_text;
    private TextView right_button_text;
    private RelativeLayout right_button;


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title, this);
        back_button = (RelativeLayout) findViewById(R.id.back_button);
        right_button = (RelativeLayout) findViewById(R.id.right_button);
        back_button_img = (ImageView) findViewById(R.id.back_button_img);
        right_button_img = (ImageView) findViewById(R.id.right_button_img);
        back_button_text = (TextView) findViewById(R.id.back_button_text);
        right_button_text = (TextView) findViewById(R.id.right_button_text);
        title_text = (TextView) findViewById(R.id.title_text);

        back_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

    }

    public void setRightButtonVis() {
        right_button.setVisibility(View.VISIBLE);
    }


    public void setTitleText(String text) {
        title_text.setText(text);
    }

    public void setLeftButtonImg(int res) {
        back_button_img.setBackgroundResource(res);
    }

    public void setRightButtonImg(int res) {
        right_button_img.setBackgroundResource(res);
    }

    public void setLeftButtonText(String text) {
        back_button_text.setText(text);
    }

    public void setLeftButtonListener(OnClickListener li) {
        back_button.setOnClickListener(li);
    }

    public void setRightButtonListener(OnClickListener li){
        right_button.setOnClickListener(li);
    }

    public void setLeftButtonImgGone() {
        back_button_img.setVisibility(GONE);
    }

    public void setRightButtonImgVis() {
        right_button_img.setVisibility(VISIBLE);
    }

    public void setRightButtonText(String text) {
        right_button_text.setText(text);
    }
    public void setRightButtonTextColor(String text) {
        right_button_text.setTextColor(Color.parseColor(text));
    }

}
