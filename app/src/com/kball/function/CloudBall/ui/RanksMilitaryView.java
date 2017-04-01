package com.kball.function.CloudBall.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.home.bean.GradeBean;

/**
 * Created by user on 2017/2/21.
 */

public class RanksMilitaryView extends RelativeLayout {
    private GradeBean data;
    private com.kball.function.Mine.custom.CircleView circle_view;

    private float victoryP;
    private float flatP;
    private float loseP;
    private float noEntryP;
    private float totalNum;

    private TextView victory;
    private TextView flat;
    private TextView lose;
    private TextView no_entry;

    private TextView victory_p;
    private TextView flat_p;
    private TextView lose_p;
    private TextView no_entry_p;

    private RanksMilitaryView(Context context, LinearLayout lin) {
        super(context);
        init(context, lin);
    }

    public static RanksMilitaryView RanksMilitaryInit(Context context, LinearLayout lin) {
        return new RanksMilitaryView(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.ranks_military_view, lin);
        circle_view = (com.kball.function.Mine.custom.CircleView) v.findViewById(R.id.circle_view);

        victory = (TextView)v.findViewById(R.id.victory);
        flat = (TextView)v.findViewById(R.id.flat);
        lose = (TextView)v.findViewById(R.id.lose);
        no_entry = (TextView)v.findViewById(R.id.no_entry);

        victory_p = (TextView)v.findViewById(R.id.victory_p);
        flat_p = (TextView)v.findViewById(R.id.flat_p);
        lose_p = (TextView)v.findViewById(R.id.lose_p);
        no_entry_p = (TextView)v.findViewById(R.id.no_entry_p);
    }


    public void setData(GradeBean data) {
        this.data = data;
        totalNum = Float.parseFloat(data.getVictory())+Float.parseFloat(data.getLose())+Float.parseFloat(data.getNo_entry())+Float.parseFloat(data.getFlat());
        if (totalNum==0){
            circle_view.setPercent((float) 0.25);
            circle_view.setPercent2((float) 0.25);
            circle_view.setPercent3((float) 0.25);
            circle_view.invalidate();
            return;
        }
        victoryP = Float.parseFloat(data.getVictory())/totalNum;
        flatP = Float.parseFloat(data.getFlat())/totalNum;
        loseP = Float.parseFloat(data.getLose())/totalNum;
        noEntryP = Float.parseFloat(data.getNo_entry())/totalNum;
        //胜利，平，失败
        circle_view.setPercent(victoryP);
        circle_view.setPercent2(flatP);
        circle_view.setPercent3(loseP);
        circle_view.setDetailText((int)totalNum+"");
        circle_view.invalidate();

        victory_p.setText((int)(victoryP*100)+"%");
        flat_p.setText((int)(flatP*100)+"%");
        lose_p.setText((int)(loseP*100)+"%");
        no_entry_p.setText((int)(noEntryP*100)+"%");

        victory.setText("胜（"+data.getVictory()+"）");
        flat.setText("平（"+data.getFlat()+"）");
        lose.setText("负（"+data.getLose()+"）");
        no_entry.setText("未录入（"+data.getNo_entry()+"）");
    }
}
