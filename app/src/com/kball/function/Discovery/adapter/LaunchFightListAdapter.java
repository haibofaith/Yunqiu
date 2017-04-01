package com.kball.function.Discovery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Discovery.bean.DisMatchBean;
import com.kball.function.Discovery.bean.LaunchFightListBean;
import com.kball.function.other.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017/2/17.
 * team_id	String	球队id
 * team_name	String	球队名称
 * badge	String	队徽
 * game_id	String	比赛id
 * game_time	long	比赛时间
 * game_site	String	比赛场地
 * game_system	int	赛制
 */

public class LaunchFightListAdapter extends KballBaseAdapter<LaunchFightListBean> {
    public LaunchFightListAdapter(Context context, ArrayList<LaunchFightListBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.launch_fight_list_item, null);
            holder.left_img = (CircleImageView) convertView.findViewById(R.id.left_img);
            holder.right_img = (ImageView) convertView.findViewById(R.id.right_img);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.team_a_name = (TextView) convertView.findViewById(R.id.team_a_name);
            holder.pro_city_area = (TextView) convertView.findViewById(R.id.pro_city_area);
            holder.team_b_name = (TextView) convertView.findViewById(R.id.team_b_name);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL_YPY + datas.get(position).getBadge(), holder.left_img);
        holder.address.setText(datas.get(position).getGame_site());
        holder.pro_city_area.setText(saizhi(datas.get(position).getGame_system()));
        holder.team_a_name.setText(datas.get(position).getTeam_name());
        holder.team_b_name.setText("等待应战");
        holder.right_img.setImageResource(R.drawable.ddyz_img);
        holder.time.setText(timeChange(datas.get(position).getGame_time()));

        return convertView;
    }

    class ViewHolder {
        CircleImageView left_img;
        ImageView right_img;
        TextView address;
        TextView pro_city_area;
        TextView team_a_name;
        TextView team_b_name;
        TextView time;
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

}
