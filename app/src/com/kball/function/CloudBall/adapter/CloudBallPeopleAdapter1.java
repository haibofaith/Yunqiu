package com.kball.function.CloudBall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.KballBaseAdapter;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.view.ManagerPeopleView;
import com.kball.function.other.CircleImageView;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CloudBallPeopleAdapter1 extends KballBaseAdapter<RankPeopleBean> {
    ManagerPeopleView mImpl;
    private boolean isEdit;

    public CloudBallPeopleAdapter1(Context context, ArrayList<RankPeopleBean> datas, ManagerPeopleView mImpl) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
        this.mImpl = mImpl;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CloudBallPeopleAdapter1.ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.cloud_ball_people_item, null);
            holder.img1 = (CircleImageView) convertView.findViewById(R.id.img1);
            holder.qiuyi_img = (TextView) convertView.findViewById(R.id.img);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.user_type = (TextView) convertView.findViewById(R.id.user_type);
            holder.yc_tv = (TextView) convertView.findViewById(R.id.yc_tv);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.xg_num = (TextView) convertView.findViewById(R.id.xg_num);
            holder.sh_tv = (TextView) convertView.findViewById(R.id.sh_tv);
            holder.shenfen = (TextView) convertView.findViewById(R.id.shenfen);
            holder.weizhi = (TextView) convertView.findViewById(R.id.weizhi);
            holder.add_lin = (LinearLayout) convertView.findViewById(R.id.add_lin);
            holder.lin_set = (LinearLayout) convertView.findViewById(R.id.lin_set);
            holder.show_img = (ImageView) convertView.findViewById(R.id.show_img);
            convertView.setTag(holder);
        } else {
            holder = (CloudBallPeopleAdapter1.ViewHolder) convertView.getTag();
        }

        holder.yc_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.yichu(position);
            }
        });
        holder.shenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.setShenfen(position);
            }
        });
        holder.sh_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.shenhe(position);
            }
        });
        holder.xg_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.xiugai(position);
            }
        });
        holder.weizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.weizhi(position);
            }
        });

        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + datas.get(position).getPortrait(), holder.img1);
        holder.qiuyi_img.setText(datas.get(position).getJersey_no());
        holder.name.setText(datas.get(position).getNickname());
        //身份 1：队长 2：副队长 3：领队 4：教练 5：队员
        holder.user_type.setText(type(datas.get(position).getIdentity()));

        holder.add_lin.removeAllViews();

        if (isEdit){
            holder.show_img.setVisibility(View.VISIBLE);
        }else{
            holder.show_img.setVisibility(View.GONE);
        }
        if (datas.get(position).isShow()) {
            holder.lin_set.setVisibility(View.VISIBLE);
            holder.show_img.setBackgroundResource(R.drawable.close_img);
        } else {
            holder.lin_set.setVisibility(View.GONE);
            holder.show_img.setBackgroundResource(R.drawable.open_img);
        }
        if (null != datas.get(position).getPlace()) {
            String[] str = datas.get(position).getPlace().split(",");
            for (int i = 0; i < str.length; i++) {
                View v = View.inflate(mContext, R.layout.rank_people_item_item, null);
                TextView pos_type = (TextView) v.findViewById(R.id.pos_type);
                pos_type.setText(str[i]);
                if (i % 2 == 0) {
                    pos_type.setBackgroundColor(Color.parseColor("#86cc96"));
                } else {
                    pos_type.setBackgroundColor(Color.parseColor("#c6a626"));
                }
                holder.add_lin.addView(v);
            }
        }
        //状态 0:待审核 1：正式球员 2：离队
        if ("0".equals(datas.get(position).getStatus())){
            holder.status.setVisibility(View.VISIBLE);
            holder.yc_tv.setVisibility(View.GONE);
            holder.sh_tv.setVisibility(View.VISIBLE);

        }else{
            holder.status.setVisibility(View.GONE);
            holder.yc_tv.setVisibility(View.VISIBLE);
            holder.sh_tv.setVisibility(View.GONE);
        }

        return convertView;
    }

    private String getStatus(String str) {
        if (str.equals("0")) {
            return "待审核";
        } else return "";
    }

    private String type(String identity) {
        if ("1".equals(identity)) {
            return "队长";
        } else if ("2".equals(identity)) {
            return "副队长";
        } else if ("3".equals(identity)) {
            return "领队";
        } else if ("4".equals(identity)) {
            return "教练";
        } else if ("5".equals(identity)) {
            return "队员";
        }
        return "";
    }

    public void setEdit(boolean edit) {
        this.isEdit = edit;
        notifyDataSetChanged();
    }


    class ViewHolder {
        CircleImageView img1;
        TextView qiuyi_img, name, user_type;
        LinearLayout add_lin, lin_set;
        ImageView show_img;
        TextView yc_tv, status, xg_num, sh_tv, shenfen,weizhi;
    }

}