package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.CloudBallBean;
import com.kball.function.CloudBall.ui.CircleView;
import com.kball.function.Match.adapter.MatchOtherAdapter;
import com.kball.function.home.bean.RanksTJBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CloudBallAdapter extends KballBaseAdapter<RanksTJBean> {
    private ImageLoader imageLoader;
    public CloudBallAdapter(Context context, ArrayList<RanksTJBean> datas, ImageLoader imageLoader) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
        this.imageLoader = imageLoader;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.cloud_ball_item, null);
            holder.view1 =(CircleView) convertView.findViewById(R.id.view1);
            holder.img =(ImageView) convertView.findViewById(R.id.img);
            holder.name_tv =(TextView) convertView.findViewById(R.id.name_tv);
            holder.add =(TextView) convertView.findViewById(R.id.add);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        imageLoader.displayImage(C.SP.IMG_URL+datas.get(position).getBadge(),holder.img);
        holder.name_tv.setText(datas.get(position).getTeam_name());
        holder.add.setText(datas.get(position).getProvince()+datas.get(position).getCity()+datas.get(position)
                .getArea());
        double dd = Double.parseDouble(datas.get(position).getPower());
        holder.view1.setNumText((int)dd+"");
        holder.view1.setNumTextSize(10);
        //设置占比
        double b = Double.parseDouble(datas.get(position).getPower());
        holder.view1.setPercent(Float.parseFloat((b/100)+""));
        //设置圆环厚度
        holder.view1.setThickness(2);
        //设置上下文字间距
        holder.view1.setPaddingtext(5);
        return convertView;
    }


    class ViewHolder {
        ImageView img;
        TextView name_tv,add;
        CircleView view1;
    }

}