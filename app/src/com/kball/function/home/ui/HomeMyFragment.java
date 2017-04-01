package com.kball.function.home.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseFragment;
import com.kball.function.Login.ui.BindPhoneActivity;
import com.kball.function.Login.ui.PassLoginActivity;
import com.kball.function.Login.ui.UserSettingAct;
import com.kball.function.Mine.custom.CircleView;
import com.kball.function.Mine.ui.ConcernActivity;
import com.kball.function.Mine.ui.FansActivity;
import com.kball.function.Mine.ui.HomeAbilityView;
import com.kball.function.Mine.ui.HomeMineView;
import com.kball.function.Mine.ui.StoreActivity;
import com.kball.function.home.bean.BasisBean;
import com.kball.function.home.bean.HonorBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.bean.MyInfoBean;
import com.kball.function.home.bean.MyInfoView;
import com.kball.function.home.presenter.MyInfoPresenter;
import com.kball.function.other.CircleImageView;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/1/16.
 */
@SuppressLint("ValidFragment")
public class HomeMyFragment extends BaseFragment implements View.OnClickListener, MyInfoView {
    private View rootView;
    private LinearLayout fans;
    private LinearLayout concern;
    private LinearLayout store;
    private Intent intent;
    private HomeMineView homeMineView;
    private LinearLayout home_mine_lin;
    private CircleImageView my_photo_img;//头像
    private ImageView setting_icon;//设置按钮
    private LinearLayout home_ability_lin;
    private HomeAbilityView homeAbilityView;
    private MyInfoPresenter mPresenter;
    private TextView userName, position, zhanli, guanzhu, fans_tv, shoucang;
    private Context mContext;

    private HonorBean honorBean;

    public ViewPager vPager;
    private ImageView left_img;
    private TextView honor_title;

    private RelativeLayout honor_rlin;

    public HomeMyFragment() {

    }

    @SuppressLint("ValidFragment")
    public HomeMyFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MyInfoPresenter(mContext, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_my, null, false);
        return rootView;
    }

    @Override
    protected void initView() {
        home_mine_lin = (LinearLayout) rootView.findViewById(R.id.home_mine_lin);
        home_ability_lin = (LinearLayout) rootView.findViewById(R.id.home_ability_lin);
        fans = (LinearLayout) rootView.findViewById(R.id.fans);
        concern = (LinearLayout) rootView.findViewById(R.id.concern);
        store = (LinearLayout) rootView.findViewById(R.id.store);
        honor_rlin = (RelativeLayout) rootView.findViewById(R.id.honor_rlin);

        my_photo_img = (CircleImageView) rootView.findViewById(R.id.my_photo_img);
        setting_icon = (ImageView) rootView.findViewById(R.id.setting_icon);
        homeAbilityView = HomeAbilityView.homeAbilityInit(getActivity(), home_ability_lin);
        userName = (TextView) findViewById(R.id.userName);
        position = (TextView) findViewById(R.id.position);
        zhanli = (TextView) findViewById(R.id.zhanli);
        guanzhu = (TextView) findViewById(R.id.guanzhu);
        fans_tv = (TextView) findViewById(R.id.fans_tv);
        shoucang = (TextView) findViewById(R.id.shoucang);

        homeMineView = new HomeMineView(getActivity(), home_mine_lin);
        home_mine_lin.addView(homeMineView);

        left_img = (ImageView) findViewById(R.id.left_img);
        honor_title = (TextView) findViewById(R.id.honor_title);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setObjData(MyInfoBaseBean data) {
        //TODO  海波这里塞数据
        setPersonInfo(data.getBasis());
        homeAbilityView.setData(data);
        homeMineView.setData(data);
        honorBean = data.getHonor();
        setHonorData(honorBean);
    }

    private void setHonorData(HonorBean honorBean) {
        if (honorBean == null) {
            honor_rlin.setVisibility(View.GONE);
        } else {
            honor_rlin.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(honorBean.getImg_url(), left_img);
            honor_title.setText(honorBean.getHonor_name());
        }
    }

    public void setPersonInfo(BasisBean personInfo) {
        userName.setText(personInfo.getNickname());
        position.setText("中锋");
        zhanli.setText("能力值: " + personInfo.getPower());
        guanzhu.setText(personInfo.getFocus());
        fans_tv.setText(personInfo.getFans());
        shoucang.setText(personInfo.getCollect());
        imageLoader.displayImage(C.SP.IMG_URL + personInfo.getPortrait(), my_photo_img);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
                mContext.startActivity(new Intent().setClass(mContext, PassLoginActivity.class));
            } else {
                mPresenter.getData();
            }
        } else {
            //相当于Fragment的onPause
        }
    }

    @Override
    protected void initData() {


    }


    @Override
    protected void setListener() {
        fans.setOnClickListener(this);
        concern.setOnClickListener(this);
        store.setOnClickListener(this);
        my_photo_img.setOnClickListener(this);
        setting_icon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fans:
                intent = new Intent(getActivity(), FansActivity.class);
                getActivity().startActivity(intent, null);
                break;
            case R.id.concern:
                intent = new Intent(getActivity(), ConcernActivity.class);
                getActivity().startActivity(intent, null);
                break;
            case R.id.store:
//                ToastAlone.show("功能正在开发中");
                intent = new Intent(getActivity(), StoreActivity.class);
                getActivity().startActivity(intent, null);
                break;
            case R.id.my_photo_img:
//                intent = new Intent(getActivity(), BindPhoneActivity.class);
//                getActivity().startActivity(intent, null);
                break;
            case R.id.setting_icon:

                startActivityForResult(new Intent(mContext, UserSettingAct.class), 11111);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11111) {
            if (resultCode == 11) {
                HomeActivity.setCurrent(0);
            }
        }
    }


    @Override
    public void showLoading1() {
        showLoading();
    }

    @Override
    public void dismissLoading1() {
        dismissLoading();
    }

}

