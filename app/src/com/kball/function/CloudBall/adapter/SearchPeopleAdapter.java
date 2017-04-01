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
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class SearchPeopleAdapter extends KballBaseAdapter<SearchPeopleBean> {
    public SearchPeopleAdapter(Context context, ArrayList<SearchPeopleBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchPeopleAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new SearchPeopleAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.search_prople_item, null);
             holder.img1 = (CircleImageView)convertView.findViewById(R.id.img1);
             holder.name = (TextView)convertView.findViewById(R.id.name);
             holder.address_tv = (TextView)convertView.findViewById(R.id.address_tv);
             holder.add_lin = (LinearLayout) convertView.findViewById(R.id.add_lin);
            convertView.setTag(holder);
        } else {
            holder = (SearchPeopleAdapter.ViewHolder) convertView.getTag();
        }

        holder.name.setText(datas.get(position).getNickname());
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL+datas.get(position).getPortrait(),holder.img1);
        holder.add_lin.removeAllViews();

        if (null != datas.get(position).getPosition()) {
            String[] str = datas.get(position).getPosition().split(",");
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


    class ViewHolder {
        CircleImageView img1;
        TextView name,address_tv;
        LinearLayout add_lin;
    }

}