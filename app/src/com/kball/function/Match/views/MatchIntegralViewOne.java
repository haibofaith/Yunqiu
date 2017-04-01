package com.kball.function.Match.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.function.Match.bean.IntegralDetailBean;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchIntegralBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class MatchIntegralViewOne extends LinearLayout {
    public MatchIntegralViewOne(Context context) {
        super(context);
    }

    private Context mContext;
    private LinearLayout add_lin;
    private MatchIntegralBean<IntegralDetailBean> DataResult;
    private ImageLoader imageLoader;



    public MatchIntegralViewOne(Context mContext, MatchIntegralBean<IntegralDetailBean>
            DataResult,ImageLoader imageLoader) {
        super(mContext);
        this.mContext = mContext;
        this.DataResult = DataResult;
        this.imageLoader = imageLoader;
        initView();
    }

    private void initView() {
        View v = View.inflate(mContext,R.layout.integral_item1,null);
        TextView name_tv = (TextView) v.findViewById(R.id.name_tv);
        LinearLayout match_lin = (LinearLayout) v.findViewById(R.id.match_lin);
        name_tv.setText(DataResult.getGroup());

        for (int i = 0; i < DataResult.getDetailed().size(); i++) {
            View v1 = View.inflate(mContext,R.layout.integral_item1_item,null);
            TextView paiming = (TextView)v1.findViewById(R.id.paiming);
            TextView rank_name = (TextView)v1.findViewById(R.id.rank_name);
            TextView game_number = (TextView)v1.findViewById(R.id.game_number);
            TextView victory = (TextView)v1.findViewById(R.id.victory);
            TextView flat = (TextView)v1.findViewById(R.id.flat);
            TextView ds = (TextView)v1.findViewById(R.id.ds);
            TextView jf = (TextView)v1.findViewById(R.id.jf);
            ImageView img = (ImageView)v1.findViewById(R.id.img);
            ImageView duihui = (ImageView)v1.findViewById(R.id.duihui);
            TextView lose = (TextView)v1.findViewById(R.id.lose);


//            duihui
            ImageLoader.getInstance().displayImage(C.SP.IMG_URL+DataResult.getDetailed().get(i).getBadge(),duihui);
            lose.setText(DataResult.getDetailed().get(i).getNegation());
            paiming.setText(DataResult.getDetailed().get(i).getCurrent_ranking());
            rank_name.setText(DataResult.getDetailed().get(i).getTeam_name());
            game_number.setText(DataResult.getDetailed().get(i).getGame_number());
            victory.setText(DataResult.getDetailed().get(i).getVictory());
            flat.setText(DataResult.getDetailed().get(i).getFlat());
            ds.setText(DataResult.getDetailed().get(i).getGoal()+"/"+DataResult.getDetailed().get(i).getLose());
            jf.setText(DataResult.getDetailed().get(i).getIntegral());
            if (Integer.parseInt(DataResult.getDetailed().get(i).getLifting())>0){
                img.setImageResource(R.drawable.mine_up_icon);
            }else if(Integer.parseInt(DataResult.getDetailed().get(i).getLifting())<0){
                img.setImageResource(R.drawable.mine_down_icon);
            }else {
                img.setImageResource(R.drawable.mine_keep_icon);
            }

            match_lin.addView(v1);
        }

        addView(v);
    }
}
