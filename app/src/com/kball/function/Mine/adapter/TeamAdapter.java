package com.kball.function.Mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Mine.bean.FansBean;
import com.kball.function.Mine.bean.TeamBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class TeamAdapter extends KballBaseAdapter<FansBean> {
    public TeamAdapter(Context context, ArrayList<FansBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.team_item, null);
            holder.left_img = (CircleImageView) convertView.findViewById(R.id.left_img);
            holder.rank_name = (TextView) convertView.findViewById(R.id.rank_name);
            holder.people_num = (TextView) convertView.findViewById(R.id.people_num);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getPortrait(), holder.left_img);

        holder.rank_name.setText(datas.get(position).getTeam_name());
        holder.people_num.setText("球队人数:" + datas.get(position).getNumber());
        holder.address.setText("所在地:" + datas.get(position).getProvince() + " " + datas.get(position).getCity());
        return convertView;
    }

    class ViewHolder {
        CircleImageView left_img;
        TextView rank_name, people_num, address;
    }

}
