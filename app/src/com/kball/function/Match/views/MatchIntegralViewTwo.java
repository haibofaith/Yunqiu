package com.kball.function.Match.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Match.bean.IntegralDetailBean;
import com.kball.function.Match.bean.MatchIntegralBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class MatchIntegralViewTwo extends LinearLayout {
    public MatchIntegralViewTwo(Context context) {
        super(context);
    }

    private Context mContext;
    private LinearLayout add_lin;
    private MatchIntegralBean<IntegralDetailBean> DataResult;



    public MatchIntegralViewTwo(Context mContext, MatchIntegralBean<IntegralDetailBean>
            DataResult) {
        super(mContext);
        this.mContext = mContext;
        this.DataResult = DataResult;
        initView();
    }

    private void initView() {
        View v = View.inflate(mContext, R.layout.integral_item2,null);
        TextView name_tv = (TextView) v.findViewById(R.id.name_tv);
        TextView teamA = (TextView) v.findViewById(R.id.teamA);
        TextView teamB = (TextView) v.findViewById(R.id.teamB);
        LinearLayout add_lin = (LinearLayout)v.findViewById(R.id.add_lin);
        name_tv.setText(DataResult.getGroup());

        if (DataResult.getDetailed().size()>0){
            teamA.setText(DataResult.getDetailed().get(0).getTeamA_name());
            teamB.setText(DataResult.getDetailed().get(0).getTeamB_name());
            ImageView imgA = (ImageView)v.findViewById(R.id.imgA);
            ImageView imgB = (ImageView)v.findViewById(R.id.imgB);

            ImageLoader.getInstance().displayImage(C.SP.IMG_URL+DataResult.getDetailed().get(0).getTeamA_badge(),
                    imgA);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL+DataResult.getDetailed().get(0).getTeamB_badge(),
                    imgB);
        }

        for (int i = 0; i < DataResult.getDetailed().size(); i++) {
            View v1 = View.inflate(mContext, R.layout.integral_item2_item,add_lin);
            TextView time_tv = (TextView)v1.findViewById(R.id.time_tv);
            TextView teamA_name = (TextView)v1.findViewById(R.id.teamA_name);
            TextView teamB_name = (TextView)v1.findViewById(R.id.teamB_name);
            TextView bifen_tv = (TextView)v1.findViewById(R.id.bifen_tv);


            ImageView imgA = (ImageView)v1.findViewById(R.id.imgA);
            ImageView imgB = (ImageView)v1.findViewById(R.id.imgB);

            ImageLoader.getInstance().displayImage(C.SP.IMG_URL+DataResult.getDetailed().get(i).getTeamA_badge(),
                    imgA);
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL+DataResult.getDetailed().get(i).getTeamB_badge(),
                    imgB);
            time_tv.setText(getStrTime(DataResult.getDetailed().get(i).getGame_time()));
            teamA_name.setText(DataResult.getDetailed().get(i).getTeamA_name());
            teamB_name.setText(DataResult.getDetailed().get(i).getTeamB_name());
            bifen_tv.setText(DataResult.getDetailed().get(i).getScore_teamA()+":"+DataResult.getDetailed().get(i).getScore_teamA());
        }



        addView(v);
    }

    public static String getStrTime(String cc_time) {
        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));

        return re_StrTime;

    }
}
