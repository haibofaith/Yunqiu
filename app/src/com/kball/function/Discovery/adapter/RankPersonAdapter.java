package com.kball.function.Discovery.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Discovery.bean.GetBillboardBean;
import com.kball.function.Discovery.bean.RankPersonBean;
import com.kball.function.Discovery.bean.RankTeamBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/17.
 */

public class RankPersonAdapter extends KballBaseAdapter<GetBillboardBean> {
    public RankPersonAdapter(Context context, ArrayList<GetBillboardBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.rank_person_item, null);
            holder.rank_tv = (TextView) convertView.findViewById(R.id.rank_tv);
            holder.rank_img = (ImageView) convertView.findViewById(R.id.rank_img);
            holder.rank_title_tv = (TextView) convertView.findViewById(R.id.rank_title_tv);
            holder.rank_power_tv = (TextView) convertView.findViewById(R.id.rank_power_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.rank_tv.setText((position+1)+"");
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL+datas.get(position).getPortrait(),holder.rank_img);
        holder.rank_title_tv.setText(datas.get(position).getNickname());
        holder.rank_power_tv.setText(datas.get(position).getPower());
        return convertView;
    }

    class ViewHolder {
        TextView rank_tv,rank_title_tv,rank_power_tv;
        ImageView rank_img;
    }

}
