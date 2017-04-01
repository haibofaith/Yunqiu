package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.SearchMacthBean;
import com.kball.util.PublicUtil;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class SearchMacthAdapter extends KballBaseAdapter<SearchMacthBean> {
    public SearchMacthAdapter(Context context, ArrayList<SearchMacthBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchMacthAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new SearchMacthAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.match_other_item, null);
            holder.img_bg = (ImageView) convertView.findViewById(R.id.img_bg);
            holder.tag_tv = (ImageView) convertView.findViewById(R.id.tag_tv);
            holder.match_name = (TextView) convertView.findViewById(R.id.match_name);
            holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
            holder.match_type = (TextView) convertView.findViewById(R.id.match_type);
            holder.address_tv = (TextView) convertView.findViewById(R.id.address_tv);
            convertView.setTag(holder);
        } else {
            holder = (SearchMacthAdapter.ViewHolder) convertView.getTag();
        }

        imageLoader.displayImage(C.SP.IMG_URL + datas.get(position).getLeague_icon(), holder.img_bg);
        holder.match_name.setText(datas.get(position).getLeague_name());
        holder.address_tv.setText("地点:" + datas.get(position).getProvince() + datas.get(position).getCity() + datas
                .get(position).getArea());
        holder.time_tv.setText("时间段：" + PublicUtil.getStrTime(datas.get(position).getStart_time(), "yyyy-MM-dd " +
                "HH:mm") + "--" + PublicUtil.getStrTime(datas.get(position).getEnd_time(), "yyyy-MM-dd HH:mm"));

        holder.match_type.setText("赛制:" + saizhi(datas.get(position).getGame_system()) + "制");
        switch (datas.get(position).getStatus()) {
            case "1"://报名中
                holder.tag_tv.setImageResource(R.drawable.bmz_img_tab);
                break;
            case "2":
                holder.tag_tv.setVisibility(View.INVISIBLE);
                break;
            case "3"://进行中
                holder.tag_tv.setImageResource(R.drawable.jxz_img_tab);
                break;
            case "4"://已结束
                holder.tag_tv.setImageResource(R.drawable.yjs_img_tab);
                break;
            default:
                break;

        }
        return convertView;
    }

    private String saizhi(String game_system) {
        if ("1".equals(game_system)) {
            return "3人";
        } else if ("2".equals(game_system)) {
            return "5人";
        } else if ("3".equals(game_system)) {
            return "7人";
        } else if ("4".equals(game_system)) {
            return "8人";
        } else if ("5".equals(game_system)) {
            return "9人";
        } else if ("6".equals(game_system)) {
            return "11人";
        } else return "";
    }

    class ViewHolder {
        ImageView img_bg, tag_tv;
        TextView match_name, time_tv, address_tv, match_type;


    }

}