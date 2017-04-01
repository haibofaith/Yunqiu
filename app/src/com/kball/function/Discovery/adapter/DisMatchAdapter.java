package com.kball.function.Discovery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Discovery.bean.DisMatchBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class DisMatchAdapter extends KballBaseAdapter<DisMatchBean> {
    public DisMatchAdapter(Context context, ArrayList<DisMatchBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.dis_match_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
    }

}
