package com.kball.function.home.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.VersionBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.ui.BindPhoneActivity;
import com.kball.function.Login.ui.PassLoginActivity;
import com.kball.function.Match.ui.EntryScoreActivity;
import com.kball.jpush.ExampleUtil;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.kball.util.UpdateReceiver;
import com.kball.util.UpdateService;
import com.kball.view.MyFragmentAdapter;
import com.kball.view.NonSwipableViewPager;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/1/16.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    public static NonSwipableViewPager vPager;
    private HomeYqFragment mYqFragment;
    private HomeYqFragment1 mYqFragment1;
    private HomeBsFragment mBsFragment;
    private HomeFxFragment mFxFragment;
    private HomeMyFragment mMyFragment;
    private ArrayList<Fragment> fragmentsList;
    private RelativeLayout yq_btn, bs_btn, my_btn, fx_btn;
    private TextView yq_tv, bs_tv, fx_tv, my_tv;
    private ImageView bs_img, yq_img, fx_img, my_img;
    private String isForce = "0";
    private UpdateReceiver mUpdateReceiver;// 自动升级receiver
    //    极光推送开始
    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg){
        //拿到msg进行处理
    }
    //    极光推送结束
    @Override
    protected int getContentViewResId() {
        return R.layout.act_home;
    }

    @Override
    protected void initView() {
        vPager = (NonSwipableViewPager) findViewById(R.id.vPager);
        yq_btn = (RelativeLayout) findViewById(R.id.yq_btn);
        bs_btn = (RelativeLayout) findViewById(R.id.bs_btn);
        fx_btn = (RelativeLayout) findViewById(R.id.fx_btn);
        my_btn = (RelativeLayout) findViewById(R.id.my_btn);


        yq_tv = (TextView) findViewById(R.id.yq_tv);
        bs_tv = (TextView) findViewById(R.id.bs_tv);
        my_tv = (TextView) findViewById(R.id.my_tv);
        fx_tv = (TextView) findViewById(R.id.fx_tv);
        bs_img = (ImageView) findViewById(R.id.bs_img);
        yq_img = (ImageView) findViewById(R.id.yq_img);
        fx_img = (ImageView) findViewById(R.id.fx_img);
        my_img = (ImageView) findViewById(R.id.my_img);
        my_tv.setText("我的");
    }

    @Override
    protected void initData() {
        fragmentsList = new ArrayList<Fragment>();
//        long l = System.currentTimeMillis();

        mYqFragment1 = new HomeYqFragment1();
        mBsFragment = new HomeBsFragment();
        mFxFragment = new HomeFxFragment();
        mMyFragment = new HomeMyFragment(mContext);


        fragmentsList.add(mYqFragment1);
        fragmentsList.add(mBsFragment);
        fragmentsList.add(mFxFragment);
        fragmentsList.add(mMyFragment);
//        ToastAlone.show((System.currentTimeMillis() - l)+"");
        vPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragmentsList));
        vPager.setCurrentItem(0, false);
        vPager.setOffscreenPageLimit(3);
//        showDialogCheckVersion(
//                model.getBody().getIsForce(), model.getBody()
//                        .getDownloadUrl(),model.getBody().getUpdateContent());
//        initUpdateReceiver(true, model.getBody()
//                .getUpdateContent());


        getVersion();

    }

    public static void setCurrent(int i) {
        vPager.setCurrentItem(i);
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionCode+"";
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    private void setObjData(VersionBean data) {

        if (data.isUpdate()) {
            showDialogCheckVersion(data.getVersionInfo().getForceUpload(), data.getVersionInfo().getDownload_url(),
                    data.getVersionInfo().getDescription(),data.getVersionInfo().getOutside_version_number());
            initUpdateReceiver(true, data.getVersionInfo().getDescription());
        }


    }

    private void getVersion() {
        NetRequest.getInstance().get(mContext, NI.getVersion(getAppVersionName(mContext)), new RequestHandler() {

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<VersionBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<VersionBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    setObjData(result.getData());
                } else {
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @Override
    protected void setListener() {
        yq_btn.setOnClickListener(this);
        bs_btn.setOnClickListener(this);
        fx_btn.setOnClickListener(this);
        my_btn.setOnClickListener(this);
        vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        yq_tv.setTextColor(Color.parseColor("#001D36"));
                        yq_img.setBackgroundResource(R.drawable.yq_img_n);
                        bs_tv.setTextColor(Color.parseColor("#999999"));
                        bs_img.setBackgroundResource(R.drawable.bs_img_p);
                        fx_tv.setTextColor(Color.parseColor("#999999"));
                        fx_img.setBackgroundResource(R.drawable.fx_img_p);
                        my_tv.setTextColor(Color.parseColor("#999999"));
                        my_img.setBackgroundResource(R.drawable.my_img_p);
                        break;
                    case 1:
                        bs_tv.setTextColor(Color.parseColor("#001D36"));
                        bs_img.setBackgroundResource(R.drawable.bs_img_n);
                        yq_tv.setTextColor(Color.parseColor("#999999"));
                        yq_img.setBackgroundResource(R.drawable.yq_img_p);
                        fx_tv.setTextColor(Color.parseColor("#999999"));
                        fx_img.setBackgroundResource(R.drawable.fx_img_p);
                        my_tv.setTextColor(Color.parseColor("#999999"));
                        my_img.setBackgroundResource(R.drawable.my_img_p);
                        break;
                    case 2:
                        fx_tv.setTextColor(Color.parseColor("#001D36"));
                        fx_img.setBackgroundResource(R.drawable.fx_btn_n);
                        yq_tv.setTextColor(Color.parseColor("#999999"));
                        yq_img.setBackgroundResource(R.drawable.yq_img_p);
                        bs_tv.setTextColor(Color.parseColor("#999999"));
                        bs_img.setBackgroundResource(R.drawable.bs_img_p);
                        my_tv.setTextColor(Color.parseColor("#999999"));
                        my_img.setBackgroundResource(R.drawable.my_img_p);
                        break;
                    case 3:
                        my_tv.setTextColor(Color.parseColor("#001D36"));
                        my_img.setBackgroundResource(R.drawable.my_btn_n);
                        yq_tv.setTextColor(Color.parseColor("#999999"));
                        yq_img.setBackgroundResource(R.drawable.yq_img_p);
                        fx_tv.setTextColor(Color.parseColor("#999999"));
                        fx_img.setBackgroundResource(R.drawable.fx_img_p);
                        bs_tv.setTextColor(Color.parseColor("#999999"));
                        bs_img.setBackgroundResource(R.drawable.bs_img_p);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yq_btn:
                vPager.setCurrentItem(0, false);
                break;
            case R.id.bs_btn:
                if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                    mContext.startActivity(new Intent().setClass(mContext, PassLoginActivity.class));
                } else {
                    vPager.setCurrentItem(1, false);
                }
                break;
            case R.id.fx_btn:
                if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                    mContext.startActivity(new Intent().setClass(mContext, PassLoginActivity.class));
                } else {
                vPager.setCurrentItem(2, false);
                }
                break;
            case R.id.my_btn:
                if ("".equals(spUtil.getString(C.SP.USER_ID, ""))) {
                    startActivityForResult(new Intent(this, PassLoginActivity.class), C.SP.LoginCode);
                } else {
                    vPager.setCurrentItem(3, false);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case C.SP.LoginCode:
                    vPager.setCurrentItem(3);
                    mMyFragment.setUserVisibleHint(true);
                    break;
                case 11111:
                    vPager.setCurrentItem(0);
                    break;
                default:

                    break;
            }
        }
    }


    /**
     * 版本更新弹出框
     */
    private void showDialogCheckVersion(final String forceUpdate, final String updateUrl, String description,String
            versionName) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("发现新版本 V"+versionName).setCancelable(false).setMessage(description)
                .setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 开启更新服务UpdateService
                Intent updateIntent = new Intent(HomeActivity.this, UpdateService.class);
                updateIntent.putExtra("updatePath", updateUrl);
                startService(updateIntent);
            }
        });
        if ("2".equals(forceUpdate)) {
            alert.setNegativeButton("取消", null);
        } else {
            alert.setNegativeButton("退出", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        alert.create().show();
    }

    private void initUpdateReceiver(boolean forceUpdate, String description) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.kball.util.UpdateReceiver");
        mUpdateReceiver = new UpdateReceiver(description, forceUpdate);
        registerReceiver(mUpdateReceiver, intentFilter);
    }

    public void testbt(View view){
        startActivity(new Intent(this, BindPhoneActivity.class));
    }
}
