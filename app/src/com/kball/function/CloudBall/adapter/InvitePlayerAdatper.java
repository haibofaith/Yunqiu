package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.InvitePlayerBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/18.
 */

public class InvitePlayerAdatper extends KballBaseAdapter<InvitePlayerBean> {

    public InvitePlayerAdatper(Context context, ArrayList<InvitePlayerBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.invite_player_item, null);
            holder.fans_img = (CircleImageView) convertView.findViewById(R.id.fans_img);
            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.pos_lin = (LinearLayout) convertView.findViewById(R.id.pos_lin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getPortrait(), holder.fans_img);
        holder.username.setText(datas.get(position).getNickname());
        holder.address.setText("所在地：" + datas.get(position).getProvince() + " " + datas.get(position).getCity() + " " + datas.get(position).getArea());
        String[] pos = {};
        String str = datas.get(position).getPosition();
        if (!TextUtils.isEmpty(str)) {
            pos = datas.get(position).getPosition().split(",");
            for (int i = 0; i < pos.length; i++) {
                View v = View.inflate(mContext, R.layout.invite_player_tiem_pos, null);
                TextView pos_tv = (TextView) v.findViewById(R.id.pos_tv);
                pos_tv.setText(pos[i]);
                if (i % 2 == 0) {

                } else {
                    pos_tv.setBackgroundColor(Color.parseColor("#f0c930"));
                }

                holder.pos_lin.addView(v);
            }
        }


        return convertView;
    }


    class ViewHolder {
        CircleImageView fans_img;
        TextView username, address;
        LinearLayout pos_lin;
    }
}
