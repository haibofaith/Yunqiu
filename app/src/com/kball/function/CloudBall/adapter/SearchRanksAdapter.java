package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.CloudBallHonorBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.bean.SearchRanksBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class SearchRanksAdapter extends KballBaseAdapter<SearchRankBean> {
    public SearchRanksAdapter(Context context, ArrayList<SearchRankBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchRanksAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new SearchRanksAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.search_ranks_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.people_num = (TextView) convertView.findViewById(R.id.people_num);
            holder.address_tv = (TextView) convertView.findViewById(R.id.address_tv);
            convertView.setTag(holder);
        } else {
            holder = (SearchRanksAdapter.ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getBadge(), holder.img);
        holder.name_tv.setText(datas.get(position).getTeam_name());
        holder.people_num.setText("球队人数：" + datas.get(position).getTotal_member());
        holder.address_tv.setText("所在地：" + datas.get(position).getProvince() + datas.get(position).getCity() + datas
                .get(position).getArea());
        return convertView;
    }


    class ViewHolder {
        ImageView img;
        TextView name_tv, people_num, address_tv;
    }

}