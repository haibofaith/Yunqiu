package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.CloudBallHonorBean;
import com.kball.function.CloudBall.bean.CloudBallPeopleBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class CloudBallHonorAdapter extends KballBaseAdapter<CloudBallHonorBean> {
    public CloudBallHonorAdapter(Context context, ArrayList<CloudBallHonorBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CloudBallHonorAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new CloudBallHonorAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.cloud_ball_honor_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (CloudBallHonorAdapter.ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getImg_url(), holder.img);
        holder.name.setText(datas.get(position).getHonor_name());
        return convertView;
    }


    class ViewHolder {
        ImageView img;
        TextView name;
    }

}