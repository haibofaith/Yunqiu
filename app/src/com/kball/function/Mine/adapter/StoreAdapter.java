package com.kball.function.Mine.adapter;

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
import com.kball.function.Match.ui.MatchProgrammeAct;
import com.kball.util.PublicUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/2/17.
 */

public class StoreAdapter extends KballBaseAdapter<MatchListBean<MatchGameBean>> {
    public StoreAdapter(Context context, ArrayList<MatchListBean<MatchGameBean>> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.store_item, null);
            holder.date_tv = (TextView) convertView.findViewById(R.id.date_tv);
            holder.match_game_list = (LinearLayout) convertView.findViewById(R.id.match_game_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (datas.get(position).getGroups() != null) {
            holder.date_tv.setText(datas.get(position).getGroups());
        } else if (datas.get(position).getGroup() != null) {
            holder.date_tv.setText(datas.get(position).getGroup());
        }
        holder.match_game_list.removeAllViews();
        for (int i = 0; i < datas.get(position).getGame().size(); i++) {
            final View view = LayoutInflater.from(this.mContext).inflate(R.layout.match_list_item_child, null);
            ImageView left_img = (ImageView) view.findViewById(R.id.left_img);
            ImageView right_img = (ImageView) view.findViewById(R.id.right_img);
            TextView middle_text = (TextView) view.findViewById(R.id.middle_text);
            TextView left_team = (TextView) view.findViewById(R.id.left_team);
            TextView right_team = (TextView) view.findViewById(R.id.right_team);
            TextView xingqi_tv = (TextView) view.findViewById(R.id.xingqi_tv);
            ImageView right_top_img = (ImageView) view.findViewById(R.id.right_top_img);
            TextView middle_text_under = (TextView) view.findViewById(R.id.middle_text_under);
            TextView date_tv = (TextView) view.findViewById(R.id.date_tv);
            TextView week_tv = (TextView) view.findViewById(R.id.week_tv);
            TextView add = (TextView) view.findViewById(R.id.add);
            LinearLayout baoming_lin = (LinearLayout) view.findViewById(R.id.baoming_lin);
            LinearLayout xunlian_lin = (LinearLayout) view.findViewById(R.id.xunlian_lin);
            LinearLayout jiesu_lin = (LinearLayout) view.findViewById(R.id.jiesu_lin);
//            ImageLoader.getInstance().displayImage(datas.get(position).getGame().get(i).getTeamA_badge(),left_img);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getGame().get(i).getTeamA_badge
                    (), left_img);
//            ImageLoader.getInstance().displayImage(datas.get(position).getGame().get(i).getTeamB_badge(),right_img);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getGame().get(i).getTeamB_badge
                    (), right_img);
            middle_text.setText(datas.get(position).getGame().get(i).getScore_teamA() + ":" + datas.get(position)
                    .getGame().get(i).getScore_teamB());
            left_team.setText(datas.get(position).getGame().get(i).getTeamA_name());
            right_team.setText(datas.get(position).getGame().get(i).getTeamB_name());
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Long game_time = Long.parseLong(datas.get(position).getGame().get(i).getGame_time());
            String game_time_new = format.format(game_time);
            xingqi_tv.setText(game_time_new);
            if ("1".equals(datas.get(position).getGame().get(i).getIsVideo())) {
                middle_text_under.setVisibility(View.GONE);
            } else if ("2".equals(datas.get(position).getGame().get(i).getIsVideo())) {
                middle_text_under.setVisibility(View.VISIBLE);
            }
            middle_text_under.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            /**
             * 比赛状态 0：不需要审核，不需要应战（赛事的比赛） 1 待审核 2：待应战 3：报名中 4：报名结束 5：进行中
             * 6：已结束 7：已取消 8：拒绝比赛 9：拒绝应战
             * */

            xunlian_lin.setVisibility(View.GONE);


            if ("1".equals(datas.get(position).getGame().get(i).getScore_status()) && "6".equals(datas.get(position)
                    .getGame().get(i).getGame_status())) {
                middle_text.setVisibility(View.VISIBLE);
            } else {
                middle_text.setVisibility(View.GONE);
            }
            switch (datas.get(position).getGame().get(i).getGame_status()) {
                case "0":
                    break;
                case "1":
                    right_top_img.setImageResource(R.drawable.ddsh_img);
                    break;
                case "2":
                    right_top_img.setImageResource(R.drawable.dyz_img);
                    break;
                case "3":
                    right_top_img.setImageResource(R.drawable.bmz_img);
                    break;
                case "4":
                    right_top_img.setImageResource(R.drawable.bmjz_img);
                    break;
                case "5":
                    right_top_img.setImageResource(R.drawable.zzjx_img);
                    break;
                case "6":
                    right_top_img.setImageResource(R.drawable.yjs_img);
                    break;
                case "7":
                    right_top_img.setImageResource(R.drawable.yqx_img);
                    break;
                case "8":
                    right_top_img.setImageResource(R.drawable.shjj_img);
                    break;
                case "9":
                    right_top_img.setImageResource(R.drawable.jjyz_img);
                    break;
            }

            if ("6".equals(datas.get(position).getGame().get(i).getGame_status())) {
                baoming_lin.setVisibility(View.GONE);
                jiesu_lin.setVisibility(View.VISIBLE);
            } else{
                baoming_lin.setVisibility(View.VISIBLE);
                jiesu_lin.setVisibility(View.GONE);
                date_tv.setText(PublicUtil.getStrTime(datas.get(position).getGame().get(i).getGame_time(),
                        "yyyy年MM月dd日"));
                add.setText(datas.get(position).getGame().get(i).getGame_site());
                week_tv.setText(PublicUtil.getWeekOfDate(datas.get(position).getGame().get(i).getGame_time()) + "  " +
                        PublicUtil.getStrTime(datas.get(position).getGame().get(i).getGame_time(), "HH:mm"));

            }
            final int position_new = position;
            final int i_new = i;
            holder.match_game_list.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastAlone.show("game_id:"+datas.get(position_new).getGame().get(i_new).getGame_id());
                    mContext.startActivity(new Intent(mContext, MatchProgrammeAct.class).putExtra("game_id", datas
                            .get(position_new).getGame().get(i_new).getGame_id()).putExtra("teamA", datas.get
                            (position_new).getGame().get(i_new).getTeamA_badge()).putExtra("teamB", datas.get
                            (position_new).getGame().get(i_new).getTeamB_badge()));
                }
            });
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
        LinearLayout match_game_list,xunlian_lin;
        TextView date_tv;
    }

}
