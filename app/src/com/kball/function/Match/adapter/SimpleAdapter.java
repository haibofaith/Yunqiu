package com.kball.function.Match.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.KballBaseAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public class SimpleAdapter extends KballBaseAdapter<String> {
    public SimpleAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.simple_string_item, null);
            holder.time_item_tv = (TextView) convertView.findViewById(R.id.time_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time_item_tv.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView time_item_tv;
    }
}
