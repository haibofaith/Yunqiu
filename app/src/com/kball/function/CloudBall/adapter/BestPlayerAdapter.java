package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class BestPlayerAdapter extends KballBaseAdapter<CloudBallPeopleBean> {
    public BestPlayerAdapter(Context context, ArrayList<CloudBallPeopleBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BestPlayerAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new BestPlayerAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.best_player_item, null);
            convertView.setTag(holder);
        } else {
            holder = (BestPlayerAdapter.ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    class ViewHolder {
    }

}
