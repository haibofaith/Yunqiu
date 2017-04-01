package com.kball.function.Match.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.util.PublicUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;

/**
 * Created by xiaole.wang on 17/3/18.
 */

public class BSListItemView extends LinearLayout {
    public BSListItemView(Context context) {
        super(context);
    }

    private Context mContext;
    private LinearLayout add_lin;
    MatchGameBean matchGameBean;

    public BSListItemView(Context mContext, MatchGameBean matchGameBean) {
        super(mContext);
        this.mContext = mContext;
        this.matchGameBean = matchGameBean;
        initView();
    }


    private void initView() {
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
        TextView xunlin_shijian = (TextView) view.findViewById(R.id.xunlin_shijian);
        TextView xunlian_add = (TextView) view.findViewById(R.id.xunlian_add);
        TextView xunlian_name = (TextView) view.findViewById(R.id.xunlian_name);
        LinearLayout baoming_lin = (LinearLayout) view.findViewById(R.id.baoming_lin);
        LinearLayout xunlian_lin = (LinearLayout) view.findViewById(R.id.xunlian_lin);
        LinearLayout jiesu_lin = (LinearLayout) view.findViewById(R.id.jiesu_lin);
        if (!TextUtils.isEmpty(matchGameBean.getTeamA_badge())) {
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + matchGameBean.getTeamA_badge(), left_img);
        }
        if (!TextUtils.isEmpty(matchGameBean.getTeamB_badge())) {
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + matchGameBean.getTeamB_badge(), right_img);
        }
        middle_text.setText(matchGameBean.getScore_teamA() + ":" + matchGameBean.getScore_teamB());
        left_team.setText(matchGameBean.getTeamA_name());
        right_team.setText(matchGameBean.getTeamB_name());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        Long game_time = Long.parseLong(matchGameBean.getGame_time());
        String game_time_new = format.format(game_time);
        xingqi_tv.setText(game_time_new);
        if ("1".equals(matchGameBean.getIsVideo())) {
            middle_text_under.setVisibility(View.GONE);
        } else if ("2".equals(matchGameBean.getIsVideo())) {
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


        if ("1".equals(matchGameBean.getScore_status()) && "6".equals(matchGameBean.getGame_status())) {
            middle_text.setVisibility(View.VISIBLE);
        } else {
            middle_text.setVisibility(View.GONE);
        }
        switch (matchGameBean.getGame_status()) {
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
                //比赛列表页，拒绝审核/拒绝应战状态的比赛，状态名应均显示为已取消
                right_top_img.setImageResource(R.drawable.yqx_img);
                break;
            case "9":
                //比赛列表页，拒绝审核/拒绝应战状态的比赛，状态名应均显示为已取消
                right_top_img.setImageResource(R.drawable.yqx_img);
                break;
        }


        if ("6".equals(matchGameBean.getGame_status())) {
            baoming_lin.setVisibility(View.GONE);
            jiesu_lin.setVisibility(View.VISIBLE);
        } else {
            baoming_lin.setVisibility(View.VISIBLE);
            jiesu_lin.setVisibility(View.GONE);
            date_tv.setText(PublicUtil.getStrTime(matchGameBean.getGame_time(), "yyyy年MM月dd日"));
            add.setText(matchGameBean.getGame_site());
            week_tv.setText(PublicUtil.getWeekOfDate(matchGameBean.getGame_time()) + "  " +
                    PublicUtil.getStrTime(matchGameBean.getGame_time(), "HH:mm"));

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("3".equals(matchGameBean.getClassify())) {
                    mContext.startActivity(new Intent().setClass(mContext, XLDetailAct.class).putExtra("game_id",
                            matchGameBean.getGame_id()));
                } else {
                    mContext.startActivity(new Intent(mContext, MatchProgrammeAct.class).putExtra("game_id",
                            matchGameBean.getGame_id()).putExtra("teamA", matchGameBean.getTeamA_badge()).putExtra
                            ("teamB", matchGameBean.getTeamB_badge()));

                }


            }
        });

        if ("3".equals(matchGameBean.getClassify())) {
            xunlian_lin.setVisibility(View.VISIBLE);
            jiesu_lin.setVisibility(View.GONE);
            baoming_lin.setVisibility(View.GONE);
            xunlin_shijian.setText(PublicUtil.getStrTime(matchGameBean.getGame_time(), "yyyy年MM月dd日") + " " +
                    PublicUtil.getWeekOfDate(matchGameBean.getGame_time()) + "  " +
                    PublicUtil.getStrTime(matchGameBean.getGame_time(), "HH:mm"));
            xunlian_add.setText(matchGameBean.getGame_site());
            right_img.setVisibility(View.INVISIBLE);
            xunlian_name.setText(matchGameBean.getGame_name());

        } else {
            xunlian_lin.setVisibility(View.GONE);
        }
        addView(view);
    }
}
