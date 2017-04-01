package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.MessageBean;
import com.kball.function.CloudBall.bean.MessageRanksBean;
import com.kball.util.PublicUtil;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/26.
 */

public class MessageRanksAdapter extends KballBaseAdapter<MessageRanksBean> {
    public MessageRanksAdapter(Context context, ArrayList<MessageRanksBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageRanksAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new MessageRanksAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.message_item, null);
            holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.message_content = (TextView) convertView.findViewById(R.id.message_content);
            holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        } else {
            holder = (MessageRanksAdapter.ViewHolder) convertView.getTag();
        }

        holder.name_tv.setText(datas.get(position).getMessage_title());
        holder.message_content.setText(datas.get(position).getMessage());
        if (TextUtils.isEmpty(datas.get(position).getPush_time())) {

        } else {
            holder.time_tv.setText(PublicUtil.getStrTime(datas.get(position).getPush_time(),"yyyy-MM-dd HH:mm:ss"));
        }
        return convertView;
    }


    class ViewHolder {
        TextView name_tv, message_content, time_tv;
    }

}