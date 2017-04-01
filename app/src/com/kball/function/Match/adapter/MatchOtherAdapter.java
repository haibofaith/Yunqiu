package com.kball.function.Match.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.Match.bean.MatchOtherBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/16.
 */

public class MatchOtherAdapter  extends KballBaseAdapter<MatchOtherBean> {
    public MatchOtherAdapter(Context context, ArrayList<MatchOtherBean> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.match_other_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    class ViewHolder {
    }

}