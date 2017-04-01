package com.kball.function.Match.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.kball.R;
import com.kball.function.Match.adapter.SimpleAdapter;
import com.kball.function.Match.ui.CreatScheduleAct;
import com.kball.util.ToastAlone;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public class ListDialogView extends RelativeLayout implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView list_view;
    private SimpleAdapter simpleAdapter;
    private ArrayList<String> datas = new ArrayList<>();
    private double timeKeep = 0;
    private Context context;
    private LinearLayout lin;
    private CreatScheduleAct activity;
    private RelativeLayout other_rlin;

    public ListDialogView(Context context, AttributeSet attrs, LinearLayout lin) {
        super(context, attrs);
        init(context,lin);
    }

    public ListDialogView(Context context, LinearLayout lin) {
        super(context);
        init(context,lin);
    }

    private void init(Context context,LinearLayout lin) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_list_dialog, lin);
        list_view = (ListView) view.findViewById(R.id.list_view);
        other_rlin= (RelativeLayout) view.findViewById(R.id.other_rlin);
        for (int i=0;i<9;i++){
            timeKeep = timeKeep + 0.5;
            datas.add(timeKeep+"小时");
        }
        this.context = context;
        this.lin = lin;
        this.activity = (CreatScheduleAct) context;
        simpleAdapter = new SimpleAdapter(context,datas);
        list_view.setAdapter(simpleAdapter);
        list_view.setOnItemClickListener(this);
        other_rlin.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        activity.dismissTimeListDialog();
        activity.mCreatTrainView.time_keep_tv.setText(datas.get(position));
    }

    @Override
    public void onClick(View v) {
        activity.dismissTimeListDialog();
    }
}
