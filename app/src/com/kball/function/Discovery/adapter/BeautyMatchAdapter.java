package com.kball.function.Discovery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Discovery.bean.BeautyMatchBean;
import com.kball.function.Match.bean.VideoBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class BeautyMatchAdapter extends KballBaseAdapter<VideoBean> {
    public BeautyMatchAdapter(Context context, ArrayList<VideoBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.beauty_match_item, null);
            holder.left_img = (ImageView)convertView.findViewById(R.id.left_img);
            holder.content_tv = (TextView) convertView.findViewById(R.id.content_tv);
            holder.watch_num = (TextView) convertView.findViewById(R.id.watch_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(datas.get(position).getScreenshots(),holder.left_img);
        holder.content_tv.setText(datas.get(position).getVideo_name());
        holder.watch_num.setText(datas.get(position).getBrowse_number());
        return convertView;
    }

    class ViewHolder {
        ImageView left_img;
        TextView content_tv;
        TextView watch_num;
    }

}
