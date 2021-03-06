package com.kball.function.Match.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Match.bean.IntegralDetailViewBean;
import com.kball.function.Match.bean.MatchDetailViewBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class IntegralDetailViewAdapter extends KballBaseAdapter<IntegralDetailViewBean> {
    public IntegralDetailViewAdapter(Context context, ArrayList<IntegralDetailViewBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.integral_detail_view_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
    }

}
