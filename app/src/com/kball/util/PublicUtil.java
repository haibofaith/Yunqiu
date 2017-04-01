package com.kball.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class PublicUtil {
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void refreshToken(Context context){
        NetRequest.getInstance().get(context, NI.refreshToken(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<UserRegisterBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<UserRegisterBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    SPUtil.getInstance().putString(C.SP.USER_ID, result.getData().getUser_id());
                    SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, result.getData().getAccess_token());
                }
                else if(1502 == jsonObject.get("error_code").getAsInt()){
                    ToastAlone.show("请重新登录");
                    SPUtil.getInstance().putString(C.SP.USER_ID, "");
                    SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, "");
                }
                else {
                    ToastAlone.show(jsonObject.get("msg")
                            .getAsString());
                }

            }

            @Override
            public void onFinish() {

            }
        });
    }
    public String timeChange(String str){
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy.MM.dd" );
        Long time=Long.parseLong(str);
        String d = format.format(time);
        Date date= null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String getWeekOfDate(String str)  {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(Long.parseLong(str));
        String d = format.format(time);
        java.util.Date date= null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }
 public static String getWeekOfDate1(String str)  {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(Long.parseLong(str));
        String d = format.format(time);
        java.util.Date date= null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    public static String getStrTime(String cc_time,String type) {
        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat(type);
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));

        return re_StrTime;

    }
}
