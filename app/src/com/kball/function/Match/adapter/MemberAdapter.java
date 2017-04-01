package com.kball.function.Match.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.adapter.CloudBallPeopleAdapter;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/25.
 */

public class MemberAdapter  extends KballBaseAdapter<MemberBean> {
    public MemberAdapter(Context context, ArrayList<MemberBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.cloud_ball_people_item2, null);
            holder.uesr_img = (CircleImageView)convertView.findViewById(R.id.uesr_img);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ImageLoader.getInstance().displayImage(C.SP.IMG_URL+datas.get(position).getPortrait(),holder.uesr_img);
        holder.name.setText(datas.get(position).getNickname());
        return convertView;
    }



    class ViewHolder {
        CircleImageView uesr_img;
        TextView name;
    }

}