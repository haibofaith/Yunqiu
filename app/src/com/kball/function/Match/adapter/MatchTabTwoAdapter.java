package com.kball.function.Match.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Match.bean.MatchOtherBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.util.PublicUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/16.
 */

public class MatchTabTwoAdapter extends KballBaseAdapter<MatchTabTwoBean> {
    public MatchTabTwoAdapter(Context context, ArrayList<MatchTabTwoBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.match_tab_two_item, null);
            convertView.setTag(holder);

            holder.img_bg = (ImageView) convertView.findViewById(R.id.img_bg);
            holder.title_img = (ImageView) convertView.findViewById(R.id.title_img);
            holder.time_tv1 = (TextView) convertView.findViewById(R.id.time_tv1);
            holder.time_tv2 = (TextView) convertView.findViewById(R.id.time_tv2);
            holder.address_tv = (TextView) convertView.findViewById(R.id.address_tv);
            holder.match_type = (TextView) convertView.findViewById(R.id.match_type);
            holder.match_name = (TextView) convertView.findViewById(R.id.match_name);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(C.SP.IMG_URL + datas.get(position).getLeague_icon(), holder.img_bg);
        holder.time_tv1.setText("报名时间段：" + PublicUtil.getStrTime(datas.get(position).getApply_start_time(),
                "yyyy.MM.dd") + "-" +
                PublicUtil.getStrTime(datas.get(position).getApply_end_time(), "yyyy.MM.dd"));
        holder.time_tv2.setText("比赛时间段：" + PublicUtil.getStrTime(datas.get(position).getStart_time(), "yyyy.MM.dd") +
                "-" + PublicUtil.getStrTime(datas.get(position).getEnd_time(), "yyyy.MM.dd"));
        holder.address_tv.setText("地点：" + datas.get(position).getProvince() + " " + datas.get(position).getCity() + "" +
                " " + datas.get(position).getArea());
        holder.match_type.setText("赛制：" + saizhi(datas.get(position).getGame_system()) + "制");
        holder.match_name.setText("" + datas.get(position).getLeague_abbreviation());

        switch (datas.get(position).getStatus()) {
            case "1"://报名中
                holder.title_img.setImageResource(R.drawable.bmz_img_tab);
                holder.time_tv1.setVisibility(View.VISIBLE);
                holder.time_tv2.setVisibility(View.GONE);
                break;
            case "2":
                holder.title_img.setVisibility(View.INVISIBLE);
                holder.time_tv1.setVisibility(View.GONE);
                holder.time_tv2.setVisibility(View.GONE);
                break;
            case "3"://进行中
                holder.title_img.setImageResource(R.drawable.jxz_img_tab);
                holder.time_tv1.setVisibility(View.GONE);
                holder.time_tv2.setVisibility(View.VISIBLE);
                break;
            case "4"://已结束
                holder.title_img.setImageResource(R.drawable.yjs_img_tab);
                holder.time_tv1.setVisibility(View.GONE);
                holder.time_tv2.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
        return convertView;
    }


    class ViewHolder {
        TextView time_tv1;
        TextView time_tv2;
        TextView address_tv;
        TextView match_type;
        TextView match_name;
        ImageView img_bg;
        ImageView title_img;
    }

    private String saizhi(String str) {
        if ("1".equals(str)) {
            return "3人";
        } else if ("2".equals(str)) {
            return "5人";
        } else if ("3".equals(str)) {
            return "7人";
        } else if ("4".equals(str)) {
            return "8人";
        } else if ("5".equals(str)) {
            return "9人";
        } else if ("6".equals(str)) {
            return "11人";
        } else {
            return "0人";
        }
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Long time = Long.parseLong(str);
        String d = format.format(time);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

}