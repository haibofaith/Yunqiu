package com.kball.function.Match.views;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.ui.MatchProgrammeAct;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class MatchDetailSCView extends LinearLayout {
    public MatchDetailSCView(Context context) {
        super(context);
    }

    private Context mContext;
    private LinearLayout add_lin;
    private MatchListBean<MatchDetailViewBean> DataResult;


    public MatchDetailSCView(Context mContext, MatchListBean<MatchDetailViewBean> DataResult) {
        super(mContext);
        this.mContext = mContext;
        this.DataResult = DataResult;
        initView();
    }

    private void initView() {
        View v = View.inflate(mContext, R.layout.match_detail_item_lin_item, null);
        TextView time_tv = (TextView) v.findViewById(R.id.time_tv);
        LinearLayout match_lin = (LinearLayout) v.findViewById(R.id.match_lin);
        time_tv.setText(DataResult.getGroups());

        for (int i = 0; i < DataResult.getGame().size(); i++) {
            View v1 = View.inflate(mContext, R.layout.match_list_item_child, null);
            TextView xingqi_tv = (TextView) v1.findViewById(R.id.xingqi_tv);
            TextView middle_text = (TextView) v1.findViewById(R.id.middle_text);
            TextView middle_text_under = (TextView) v1.findViewById(R.id.middle_text_under);
            TextView left_team = (TextView) v1.findViewById(R.id.left_team);
            TextView right_team = (TextView) v1.findViewById(R.id.right_team);
            ImageView left_img = (ImageView) v1.findViewById(R.id.left_img);
            ImageView right_img = (ImageView) v1.findViewById(R.id.right_img);
            LinearLayout  jiesu_lin = (LinearLayout)v1.findViewById(R.id.jiesu_lin);
            LinearLayout  baoming_lin = (LinearLayout)v1.findViewById(R.id.baoming_lin);
            LinearLayout  xunlian_lin = (LinearLayout)v1.findViewById(R.id.xunlian_lin);
            xingqi_tv.setVisibility(View.GONE);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + DataResult.getGame().get(i).getTeamA_badge(),
                    left_img);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL + DataResult.getGame().get(i).getTeamB_badge(),
                    right_img);

            xunlian_lin.setVisibility(View.GONE);

            left_team.setText(DataResult.getGame().get(i).getTeamA_name());
            right_team.setText(DataResult.getGame().get(i).getTeamB_name());
            if ("6".equals(DataResult.getGame().get(i).getGame_status())){
                middle_text.setText(DataResult.getGame().get(i).getScore_teamA() + " : " + DataResult.getGame().get(i)
                        .getScore_teamB());
                jiesu_lin.setVisibility(View.VISIBLE);
                middle_text.setVisibility(View.VISIBLE);
                baoming_lin.setVisibility(View.GONE);
            }
            else{
                middle_text.setText("VS");
                middle_text.setVisibility(View.VISIBLE);
                jiesu_lin.setVisibility(View.VISIBLE);
                baoming_lin.setVisibility(View.GONE);
            }
            if ("1".equals(DataResult.getGame().get(i).getIsVideo())){
                middle_text_under.setVisibility(View.GONE);
            }else{
                middle_text_under.setVisibility(View.VISIBLE);
            }
            middle_text_under.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastAlone.show("视频点播");
                }
            });
            final int pos = i;
            v1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MatchProgrammeAct.class).putExtra("game_id",
                            DataResult.getGame().get(pos).getGame_id()).putExtra("teamA", DataResult.getGame().get
                            (pos).getTeamA_badge()).putExtra("teamB", DataResult.getGame().get(pos).getTeamB_badge()));
                }
            });
            match_lin.addView(v1);
        }

        addView(v);
    }

}
