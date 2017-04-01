package com.kball.function.Mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Mine.bean.FansBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class FansAdapter extends KballBaseAdapter<FansBean> {
    private String[] pos = {};

    public FansAdapter(Context context, ArrayList<FansBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.fans_item, null);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.pos_lin = (LinearLayout) convertView.findViewById(R.id.pos_lin);
            holder.fans_img = (ImageView) convertView.findViewById(R.id.fans_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.username.setText(datas.get(position).getNickname());
        String provice = "北京市";
        String city = "海淀区";
        if (TextUtils.isEmpty(datas.get(position).getProvince())) {

        } else {
            provice = datas.get(position).getProvince();
        }
        if (TextUtils.isEmpty(datas.get(position).getCity())) {

        } else {
            city = datas.get(position).getCity();
        }
        holder.address.setText("所在地：" + provice + " " + city);

        if (null != datas && null != datas.get(position) && null != datas.get(position).getPosition()) {
            pos = datas.get(position).getPosition().split(",");
        }
        holder.pos_lin.removeAllViews();
        for (int i = 0; i < pos.length; i++) {
            View v = View.inflate(mContext, R.layout.fans_tiem_pos, null);
            TextView pos_tv = (TextView) v.findViewById(R.id.pos_tv);
            pos_tv.setText(pos[i]);
            if (i % 2 == 0) {

            } else {
                pos_tv.setBackgroundColor(Color.parseColor("#f0c930"));
            }

            holder.pos_lin.addView(v);
        }

        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getPortrait(), holder.fans_img);
        return convertView;
    }

    class ViewHolder {
        TextView username, address;
        LinearLayout pos_lin;
        ImageView fans_img;
    }

}
