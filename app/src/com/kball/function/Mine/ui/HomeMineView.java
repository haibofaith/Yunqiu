package com.kball.function.Mine.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.Mine.custom.CircleView;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.bean.PersonalBean;

/**
 * Created by user on 2017/2/21.
 */

public class HomeMineView extends RelativeLayout {
    private PersonalBean personalBean;
    private CircleView circle_view;
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

    private TextView league_number;
    private TextView game_number;

    private TextView jin_qiu_score;
    private TextView zhu_gong_score;
    private TextView hong_huang_score;


    public HomeMineView(Context context, LinearLayout lin) {
        super(context);
        init(context, lin);
    }

    public static HomeMineView homeMineInit(Context context, LinearLayout lin) {
        return new HomeMineView(context, lin);
    }

    private void init(Context context, LinearLayout lin) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_mine_view, null);
        circle_view = (CircleView) v.findViewById(R.id.circle_view);

        victory = (TextView) v.findViewById(R.id.victory);
        flat = (TextView) v.findViewById(R.id.flat);
        lose = (TextView) v.findViewById(R.id.lose);
        no_entry = (TextView) v.findViewById(R.id.no_entry);

        victory_p = (TextView) v.findViewById(R.id.victory_p);
        flat_p = (TextView) v.findViewById(R.id.flat_p);
        lose_p = (TextView) v.findViewById(R.id.lose_p);
        no_entry_p = (TextView) v.findViewById(R.id.no_entry_p);

        game_number = (TextView) v.findViewById(R.id.game_number);
        league_number = (TextView) v.findViewById(R.id.league_number);

        jin_qiu_score = (TextView) v.findViewById(R.id.jin_qiu_score);
        zhu_gong_score = (TextView) v.findViewById(R.id.zhu_gong_score);
        hong_huang_score = (TextView) v.findViewById(R.id.hong_huang_score);

        addView(v);
    }

    public void setData(MyInfoBaseBean data) {
        personalBean = data.getPersonal_data();
        league_number.setText("参加联赛：" + personalBean.getLeague_number());
        game_number.setText("参与场数：" + personalBean.getGame_number());
        totalNum = Float.parseFloat(personalBean.getVictory()) + Float.parseFloat(personalBean.getLose()) + Float
                .parseFloat(personalBean.getNo_entry()) + Float.parseFloat(personalBean.getFlat());
        if (totalNum == 0) {
            circle_view.setPercent((float) 0.25);
            circle_view.setPercent2((float) 0.25);
            circle_view.setPercent3((float) 0.25);
            circle_view.invalidate();
            victoryP = 0;
            flatP = 0;
            loseP = 0;
            noEntryP = 0;
        } else {
            victoryP = Float.parseFloat(personalBean.getVictory()) / totalNum;
            flatP = Float.parseFloat(personalBean.getFlat()) / totalNum;
            loseP = Float.parseFloat(personalBean.getLose()) / totalNum;
            noEntryP = Float.parseFloat(personalBean.getNo_entry()) / totalNum;
        }
        //胜利，平，失败
        circle_view.setPercent(victoryP);
        circle_view.setPercent2(flatP);
        circle_view.setPercent3(loseP);
        circle_view.setDetailText(personalBean.getGame_number());
        circle_view.invalidate();


        victory_p.setText((int) (victoryP * 100) + "%");
        flat_p.setText((int) (flatP * 100) + "%");
        lose_p.setText((int) (loseP * 100) + "%");
        no_entry_p.setText((int) (noEntryP * 100) + "%");

        victory.setText("胜（" + personalBean.getVictory() + "）");
        flat.setText("平（" + personalBean.getFlat() + "）");
        lose.setText("负（" + personalBean.getLose() + "）");
        no_entry.setText("未录入（" + personalBean.getNo_entry() + "）");

        jin_qiu_score.setText(personalBean.getGoal());
        zhu_gong_score.setText(personalBean.getAssists());


        String red = "0";
        String yellow = "0";
        if (null == personalBean.getRed_card()){
            red = "0";
        }else{
            red= personalBean.getRed_card();
        }
        if (null == personalBean.getYellow_card()) {
            yellow = "0";
        } else {
            yellow = personalBean.getYellow_card();
        }
        hong_huang_score.setText(red + "/" +yellow);
    }

}
