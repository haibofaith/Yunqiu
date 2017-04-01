package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CloudBallPeopleAdapter extends KballBaseAdapter<RankPeopleBean> {
    public CloudBallPeopleAdapter(Context context, ArrayList<RankPeopleBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CloudBallPeopleAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.cloud_ball_people_item1, null);
            holder.uesr_img = (CircleImageView)convertView.findViewById(R.id.uesr_img);
            holder.qiuyi_img = (TextView)convertView.findViewById(R.id.qiuyi_img);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.user_type = (TextView)convertView.findViewById(R.id.user_type);
            holder.add_lin = (LinearLayout) convertView.findViewById(R.id.add_lin);
            convertView.setTag(holder);
        } else {
            holder = (CloudBallPeopleAdapter.ViewHolder) convertView.getTag();
        }


        ImageLoader.getInstance().displayImage(C.SP.IMG_URL+datas.get(position).getPortrait(),holder.uesr_img);
        holder.qiuyi_img.setText(datas.get(position).getJersey_no());
        holder.name.setText(datas.get(position).getNickname());
        //身份 1：队长 2：副队长 3：领队 4：教练 5：队员
        holder.user_type.setText(type(datas.get(position).getIdentity()));

        holder.add_lin.removeAllViews();

        if (null != datas.get(position).getPlace()) {
            String[] str = datas.get(position).getPlace().split(",");
            for (int i = 0; i < str.length; i++) {
                View v = View.inflate(mContext, R.layout.rank_people_item_item, null);
                TextView pos_type = (TextView) v.findViewById(R.id.pos_type);
                pos_type.setText(str[i]);
                if (i % 2 == 0) {
                    pos_type.setBackgroundColor(Color.parseColor("#86cc96"));
                } else {
                    pos_type.setBackgroundColor(Color.parseColor("#c6a626"));
                }
                holder.add_lin.addView(v);
            }
        }
        return convertView;
    }

    private String type(String identity) {
        if ("1".equals(identity)){
            return  "队长";
        }else if("2".equals(identity)){
            return  "副队长";
        }else if("3".equals(identity)){
            return  "领队";
        }else if("4".equals(identity)){
            return  "教练";
        }else if("5".equals(identity)){
            return  "队员";
        }
        return "";
    }


    class ViewHolder {
    CircleImageView uesr_img;
        TextView qiuyi_img,name,user_type;
        LinearLayout add_lin;
    }

}