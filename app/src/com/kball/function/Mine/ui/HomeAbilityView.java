package com.kball.function.Mine.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.Mine.custom.RadarView;
import com.kball.function.home.bean.CapacityBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.bean.PowerBean;

/**
 * Created by user on 2017/2/27.
 */

public class HomeAbilityView extends RelativeLayout {
    private RadarView radar_view;
    private float[] percents;
    private CapacityBean capacityBean;
    private TextView attack_gross;
    private TextView defensive_gross;
    private TextView physical_gross;
    private TextView technology_gross;
    private TextView aggressive_gross;

    private TextView attack_gains;
    private TextView defensive_gains;
    private TextView physical_gains;
    private TextView technology_gains;
    private TextView aggressive_gains;

    private ImageView attack_img;
    private ImageView defensive_img;
    private ImageView physical_img;
    private ImageView technology_img;
    private ImageView aggressive_img;


    private HomeAbilityView(Context context, LinearLayout lin) {
        super(context);
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_ability_view, lin);
        radar_view = (RadarView) v.findViewById(R.id.radar_view);

        attack_gross = (TextView) v.findViewById(R.id.attack_gross);
        defensive_gross = (TextView) v.findViewById(R.id.defensive_gross);
        physical_gross = (TextView) v.findViewById(R.id.physical_gross);
        technology_gross = (TextView) v.findViewById(R.id.technology_gross);
        aggressive_gross = (TextView) v.findViewById(R.id.aggressive_gross);

        attack_gains = (TextView) v.findViewById(R.id.attack_gains);
        defensive_gains = (TextView) v.findViewById(R.id.defensive_gains);
        physical_gains = (TextView) v.findViewById(R.id.physical_gains);
        technology_gains = (TextView) v.findViewById(R.id.technology_gains);
        aggressive_gains = (TextView) v.findViewById(R.id.aggressive_gains);

        attack_img = (ImageView) v.findViewById(R.id.attack_img);
        defensive_img = (ImageView) v.findViewById(R.id.defensive_img);
        physical_img = (ImageView) v.findViewById(R.id.physical_img);
        technology_img = (ImageView) v.findViewById(R.id.technology_img);
        aggressive_img = (ImageView) v.findViewById(R.id.aggressive_img);
    }

    public void setData(MyInfoBaseBean data) {
        if (data == null || data.getCapacity() == null) {
            return;
        }
        capacityBean = data.getCapacity();
        //进攻，技术，倾略性，体能，防守
        percents = new float[5];
        percents[0] = Float.parseFloat(capacityBean.getAttack_gross()) / 100;
        percents[1] = Float.parseFloat(capacityBean.getTechnology_gross()) / 100;
        percents[2] = Float.parseFloat(capacityBean.getAggressive_gross()) / 100;
        percents[3] = Float.parseFloat(capacityBean.getPhysical_gross()) / 100;
        percents[4] = Float.parseFloat(capacityBean.getDefensive_gross()) / 100;
        setDetailData(percents);

    }

    public void setData1(CapacityBean data) {
        if (data == null) {
            return;
        }
        //进攻，技术，倾略性，体能，防守
        capacityBean = data;
        percents = new float[5];
        percents[0] = Float.parseFloat(data.getAttack_gross()) / 100;
        percents[1] = Float.parseFloat(data.getTechnology_gross()) / 100;
        percents[2] = Float.parseFloat(data.getAggressive_gross()) / 100;
        percents[3] = Float.parseFloat(data.getPhysical_gross()) / 100;
        percents[4] = Float.parseFloat(data.getDefensive_gross()) / 100;
        setDetailData(percents);

    }

    public void setDetailData(float[] percents) {

        radar_view.setPercents(percents);
        radar_view.invalidate();
        if (capacityBean == null) {
            return;
        }
        attack_gross.setText(capacityBean.getAttack_gross() + "");
        defensive_gross.setText(capacityBean.getDefensive_gross() + "");
        physical_gross.setText(capacityBean.getPhysical_gross() + "");
        technology_gross.setText(capacityBean.getTechnology_gross() + "");
        aggressive_gross.setText(capacityBean.getAggressive_gross() + "");
        if (Integer.parseInt(capacityBean.getAttack_gains()) > 0) {
            attack_img.setImageResource(R.drawable.mine_up_icon);
            attack_gains.setText(capacityBean.getAttack_gains());
        } else if (Integer.parseInt(capacityBean.getAttack_gains()) == 0) {
            attack_img.setImageResource(R.drawable.mine_keep_icon);
            attack_gains.setVisibility(INVISIBLE);
        } else {
            attack_img.setImageResource(R.drawable.mine_down_icon);
            attack_gains.setText(Math.abs(Integer.parseInt(capacityBean.getAttack_gains())));
        }

        if (Integer.parseInt(capacityBean.getDefensive_gains()) > 0) {
            defensive_img.setImageResource(R.drawable.mine_up_icon);
            defensive_gains.setText(capacityBean.getDefensive_gains());
        } else if (Integer.parseInt(capacityBean.getDefensive_gains()) == 0) {
            defensive_img.setImageResource(R.drawable.mine_keep_icon);
            defensive_gains.setVisibility(INVISIBLE);
        } else {
            defensive_img.setImageResource(R.drawable.mine_down_icon);
            defensive_gains.setText(Math.abs(Integer.parseInt(capacityBean.getDefensive_gains())));
        }

        if (Integer.parseInt(capacityBean.getPhysical_gains()) > 0) {
            physical_img.setImageResource(R.drawable.mine_up_icon);
            physical_gains.setText(capacityBean.getPhysical_gains());
        } else if (Integer.parseInt(capacityBean.getPhysical_gains()) == 0) {
            physical_img.setImageResource(R.drawable.mine_keep_icon);
            physical_gains.setVisibility(INVISIBLE);
        } else {
            physical_img.setImageResource(R.drawable.mine_down_icon);
            physical_gains.setText(Math.abs(Integer.parseInt(capacityBean.getPhysical_gains())));
        }

        if (Integer.parseInt(capacityBean.getTechnology_gains()) > 0) {
            technology_img.setImageResource(R.drawable.mine_up_icon);
            technology_gains.setText(capacityBean.getTechnology_gains());
        } else if (Integer.parseInt(capacityBean.getTechnology_gains()) == 0) {
            technology_img.setImageResource(R.drawable.mine_keep_icon);
            technology_gains.setVisibility(INVISIBLE);
        } else {
            technology_img.setImageResource(R.drawable.mine_down_icon);
            technology_gains.setText(Math.abs(Integer.parseInt(capacityBean.getTechnology_gains())));
        }

        if (Integer.parseInt(capacityBean.getAggressive_gains()) > 0) {
            aggressive_img.setImageResource(R.drawable.mine_up_icon);
            aggressive_gains.setText(capacityBean.getAggressive_gains());
        } else if (Integer.parseInt(capacityBean.getAggressive_gains()) == 0) {
            aggressive_img.setImageResource(R.drawable.mine_keep_icon);
            aggressive_gains.setVisibility(INVISIBLE);
        } else {
            aggressive_img.setImageResource(R.drawable.mine_down_icon);
            aggressive_gains.setText(Math.abs(Integer.parseInt(capacityBean.getAggressive_gains())));
        }
    }

    public static HomeAbilityView homeAbilityInit(Context context, LinearLayout lin) {
        return new HomeAbilityView(context, lin);
    }

}
