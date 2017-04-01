package com.kball.function.Match.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.kball.R;
import com.kball.function.Match.adapter.IntegralDetailViewAdapter;
import com.kball.function.Match.bean.IntegralDetailBean;
import com.kball.function.Match.bean.IntegralDetailViewBean;
import com.kball.function.Match.bean.MatchIntegralBean;
import com.kball.function.Match.presenter.IntegralDetailPresenter;
import com.kball.function.Match.view.IntegralDetailViews;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/24.
 */

public class IntegralDetailView extends RelativeLayout implements IntegralDetailViews {
    private IntegralDetailViewAdapter adapter;
    private ArrayList<IntegralDetailViewBean> datas;
    private String league_id;
    private LinearLayout add_lin;
    private IntegralDetailPresenter mPresenter;
    private Context mContext;

    private IntegralDetailView(Context context, LinearLayout lin,String league_id) {
        super(context);
        this.league_id = league_id;
        mPresenter = new IntegralDetailPresenter(this);
        mContext = context;
        init(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.integral_detail_view, lin);
        add_lin = (LinearLayout)v.findViewById(R.id.add_lin);

        mPresenter.getVideo(context,league_id);
    }

    public static IntegralDetailView homeInit(Context context, LinearLayout lin,String league_id) {
        return new IntegralDetailView(context, lin,league_id);
    }

    @Override
    public void setListData(ArrayList<MatchIntegralBean<IntegralDetailBean>> data) {
        add_lin.removeAllViews();


        for (int i = 0; i < data.size() ; i++) {

            if ("1".equals(data.get(i).getLeague_type())){

                MatchIntegralViewOne msc = new MatchIntegralViewOne(mContext,data.get(i), ImageLoader.getInstance());
                add_lin.addView(msc);
            }
            else if ("2".equals(data.get(i).getLeague_type())){

                MatchIntegralViewTwo msc = new MatchIntegralViewTwo(mContext,data.get(i));
                add_lin.addView(msc);
            }
            else if ("3".equals(data.get(i).getLeague_type())){

                MatchIntegralViewTwo msc = new MatchIntegralViewTwo(mContext,data.get(i));
                add_lin.addView(msc);
            }

            else if ("4".equals(data.get(i).getLeague_type())){

                MatchIntegralViewOne msc = new MatchIntegralViewOne(mContext,data.get(i),ImageLoader.getInstance());
                add_lin.addView(msc);
            }
        }

    }
}
