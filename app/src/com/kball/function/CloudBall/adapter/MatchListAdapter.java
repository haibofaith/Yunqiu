package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.ui.BSListItemView;
import com.kball.function.Match.ui.MatchProgrammeAct;
import com.kball.function.Match.views.MatchDetailSCView;
import com.kball.util.PublicUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class MatchListAdapter extends KballBaseAdapter<MatchListBean<MatchGameBean>> {

    public MatchListAdapter(Context context, ArrayList<MatchListBean<MatchGameBean>> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchListAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new MatchListAdapter.ViewHolder();
            convertView = View.inflate(mContext, R.layout.match_list_item, null);
            holder.date_tv = (TextView) convertView.findViewById(R.id.date_tv);
            holder.match_game_list = (LinearLayout) convertView.findViewById(R.id.match_game_list);
            convertView.setTag(holder);
        } else {
            holder = (MatchListAdapter.ViewHolder) convertView.getTag();
        }
        if (datas.get(position).getGroups() != null) {
            holder.date_tv.setText(datas.get(position).getGroups());
        } else if (datas.get(position).getGroup() != null) {
            holder.date_tv.setText(datas.get(position).getGroup());
        }
        holder.match_game_list.removeAllViews();
        for (int i = 0; i < datas.get(position).getGame().size(); i++) {
            BSListItemView msc = new BSListItemView(mContext,datas.get(position).getGame().get(i));
            holder.match_game_list.addView(msc);
        }

        return convertView;
    }

    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }


    class ViewHolder {
        LinearLayout match_game_list;
        TextView date_tv;
    }

}
