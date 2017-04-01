package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Mine.bean.selectTeamListBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public class SelectTeamAdapter extends KballBaseAdapter<selectTeamListBean> {

    public SelectTeamAdapter(Context context, ArrayList<selectTeamListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.select_team_view_item, null);
            holder.own_team_img = (ImageView) convertView.findViewById(R.id.own_team_img);
            holder.team_name = (TextView) convertView.findViewById(R.id.team_name);
            holder.team_person_num = (TextView) convertView.findViewById(R.id.team_person_num);
            holder.team_addr = (TextView) convertView.findViewById(R.id.team_addr);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(C.SP.IMG_URL+datas.get(position).getBadge(),holder.own_team_img);
        holder.team_name.setText(datas.get(position).getTeam_name());
        holder.team_person_num.setText("球队人数："+datas.get(position).getNumber());
        holder.team_addr.setText("所在地:"+datas.get(position).getProvince()+" "+datas.get(position).getCity()+" "+datas.get(position).getArea());

        return convertView;
    }

    class ViewHolder {
        ImageView own_team_img;
        TextView team_name,team_person_num,team_addr;
    }
}
