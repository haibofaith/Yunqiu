package com.kball.function.Mine.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordChildBean;
import com.kball.function.CloudBall.ui.RanksMilitaryView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.ui.MatchListAct;
import com.kball.function.Mine.Views.PersonInfoImpl;
import com.kball.function.Mine.bean.LeagueBean;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.function.Mine.presenter.PersonInfoPresenter;
import com.kball.function.home.bean.BasisBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.bean.ParticipateTeamBean;
import com.kball.function.home.bean.TeamInfoBean;
import com.kball.function.other.CircleImageView;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017/3/14.
 * 个人数据
 */

public class PersonInfoActivity extends BaseActivity implements View.OnClickListener, PersonInfoImpl {
    private RelativeLayout back_button;//返回
    private TextView right_button_text;//右侧文案
    private TextView userName;//
    private TextView position;//中锋
    private TextView address;//地址年龄
    private CircleImageView my_photo_img;//头像

    private HomeAbilityView homeAbilityView;
    private LinearLayout home_mine_lin, home_ability_lin;
    private HomeMineView homeMineView;
    private PersonInfoPresenter presenter;

    private LinearLayout labels_lin;
    private static String userId;

    private TeamInfoBean teamInfoBean;

    //    private ArrayList<MatchTabTwoBean> mData;
    private ArrayList<ExploitsListRecordBean<ExploitsListRecordChildBean>> exploitsData;
    private ImageView img_bg;
    private ImageView title_img;
    private TextView match_name;
    private TextView time_tv1;
    private TextView time_tv2;
    private TextView address1_tv;
    private TextView match_type;
    private TextView ssxq_tv;
    private RelativeLayout list_first_item;

    private static int pageNum = 1;
    private static int pageSize = 10;
    private static String team_id = null;
    private LinearLayout own_team_lin;
    private TextView own_team_name;

    private String access_token;
    private ArrayList<ParticipateTeamBean> ParticipateTeamDatas;
    private LeagueBean leaguedata;

    private RelativeLayout right_button;
    private String focus;
    private String focus_status;//关注状态

    @Override
    protected int getContentViewResId() {
        return R.layout.person_info_layout;
    }

    @Override
    protected void initView() {
        back_button = (RelativeLayout) findViewById(R.id.back_button);
        right_button_text = (TextView) findViewById(R.id.right_button_text);
        right_button = (RelativeLayout) findViewById(R.id.right_button);
        userName = (TextView) findViewById(R.id.userName);
        position = (TextView) findViewById(R.id.position);
        address = (TextView) findViewById(R.id.address);

        my_photo_img = (CircleImageView) findViewById(R.id.my_photo_img);

        home_ability_lin = (LinearLayout) findViewById(R.id.home_ability_lin);
        home_mine_lin = (LinearLayout) findViewById(R.id.home_mine_lin);
        homeAbilityView = HomeAbilityView.homeAbilityInit(this, home_ability_lin);
        homeMineView = HomeMineView.homeMineInit(this, home_mine_lin);
        home_mine_lin.addView(homeMineView);

        labels_lin = (LinearLayout) findViewById(R.id.labels_lin);

        list_first_item = (RelativeLayout) findViewById(R.id.list_first_item);

        img_bg = (ImageView) findViewById(R.id.img_bg);
        title_img = (ImageView) findViewById(R.id.title_img);
        match_name = (TextView) findViewById(R.id.match_name);
        time_tv1 = (TextView) findViewById(R.id.time_tv1);
        time_tv2 = (TextView) findViewById(R.id.time_tv2);
        address1_tv = (TextView) findViewById(R.id.address1_tv);
        match_type = (TextView) findViewById(R.id.match_type);
        ssxq_tv = (TextView) findViewById(R.id.ssxq_tv);


        own_team_lin = (LinearLayout) findViewById(R.id.own_team_lin);
        own_team_name = (TextView) findViewById(R.id.own_team_name);
    }

    @Override
    protected void initData() {
        presenter = new PersonInfoPresenter(this);
        userId = getIntent().getStringExtra("userId");
        focus = getIntent().getStringExtra("userId");
        access_token = getIntent().getStringExtra("access_token");
        if (userId != null) {
            presenter.selectUserInfo(this, userId);
        }
//        presenter.selectTeamList(this);
//        presenter.meTeamLeagueList(this,pageNum+"",pageSize+"",team_id,null);
    }

    @Override
    protected void setListener() {
        back_button.setOnClickListener(this);
        right_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.right_button:
                //focus:关注userid
                if (null != focus) {
                    //focus_status:关注状态
                    if (focus_status!=null){
                    switch (focus_status) {
                        //关注状态 1：未关注 2：已关注 当type=1时，该值不存在
                        case "1":
                            presenter.attention(this, focus);
                            break;
                        case "2":
                            presenter.cancelAttention(this, focus);
                            break;
                        default:
                            right_button_text.setText("");
                            break;
                    }

                    }
                }
                break;
            case R.id.saishi_rel:
                if (teamInfoBean != null && teamInfoBean.getTeam_id() != null) {
                    startActivity(new Intent().setClass(mContext, MatchListAct.class).putExtra("team_id",
                            teamInfoBean.getTeam_id()));
                }
                break;
        }
    }

    @Override
    public void setObjData(MyInfoBaseBean data) {
        setPersonInfo(data.getBasis());
        homeAbilityView.setData(data);
        homeMineView.setData(data);
        setListData(data.getPersonal_data().getLeague());
        setSelectTeamListData(data.getParticipate_team());
    }

    @Override
    public void setAttentionData(BaseBean result) {
        if ("1200".equals(result.getError_code())){
            ToastAlone.show("关注成功");
            presenter.selectUserInfo(this,userId);
        }
    }

    @Override
    public void setCancelAttentionData(BaseBean result) {
        if ("1200".equals(result.getError_code())){
            ToastAlone.show("取消关注成功");
            presenter.selectUserInfo(this,userId);
        }
    }

    //参加赛事
    public void setListData(LeagueBean leaguedata) {
        if (leaguedata == null) {
            list_first_item.setVisibility(View.GONE);
            ssxq_tv.setText("赛事详情（0）");
            return;
        }
        ssxq_tv.setText("赛事详情");
        list_first_item.setVisibility(View.VISIBLE);

        imageLoader.displayImage(C.SP.IMG_URL + leaguedata.getLeague_icon(), img_bg);

        match_name.setText(leaguedata.getLeague_abbreviation() + "");
        time_tv1.setText("报名时间段：" + timeChange(leaguedata.getApply_start_time()) + "-" + timeChange(leaguedata.getApply_end_time()) + "");
        time_tv2.setText("比赛时间段：" + timeChange(leaguedata.getStart_time()) + "-" + timeChange(leaguedata
                .getEnd_time()));
        address1_tv.setText("地点：" + leaguedata.getProvince() + " " + leaguedata.getCity() + " " + leaguedata.getArea() + "");
        match_type.setText("赛制：" + saizhi(leaguedata.getGame_system()) + "");
        switch (leaguedata.getStatus()) {
            case "1"://报名中
                title_img.setImageResource(R.drawable.bmz_img_tab);
                time_tv1.setVisibility(View.VISIBLE);
                time_tv2.setVisibility(View.GONE);
                break;
            case "2":
                title_img.setVisibility(View.INVISIBLE);
                time_tv1.setVisibility(View.GONE);
                time_tv2.setVisibility(View.GONE);
                break;
            case "3"://进行中
                title_img.setImageResource(R.drawable.jxz_img_tab);
                time_tv1.setVisibility(View.GONE);
                time_tv2.setVisibility(View.VISIBLE);
                break;
            case "4"://已结束
                title_img.setImageResource(R.drawable.yjs_img_tab);
                time_tv1.setVisibility(View.GONE);
                time_tv2.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }

    //效力球队信息
    public void setSelectTeamListData(ArrayList<ParticipateTeamBean> ParticipateTeamDatas) {
        if (0 == ParticipateTeamDatas.size()) {
            return;
        } else {
            own_team_name.setText("效力球队( " + ParticipateTeamDatas.size() + " )");
        }
        own_team_lin.removeAllViews();
        for (int i = 0; i < ParticipateTeamDatas.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.own_team_view_item, null);
            ImageView own_team_img = (ImageView) view.findViewById(R.id.own_team_img);
            imageLoader.displayImage(C.SP.IMG_URL + ParticipateTeamDatas.get(i).getBadge(), own_team_img);
            TextView team_name = (TextView) view.findViewById(R.id.team_name);
            TextView team_person_num = (TextView) view.findViewById(R.id.team_person_num);
            TextView team_role = (TextView) view.findViewById(R.id.team_role);
            team_name.setText(ParticipateTeamDatas.get(i).getTeam_name());
            team_person_num.setText(ParticipateTeamDatas.get(i).getTotal_member());
            team_role.setVisibility(View.INVISIBLE);
//                team_role.setText(result.getData().get(i).);
            own_team_lin.addView(view);
            team_id = ParticipateTeamDatas.get(i).getTeam_id();
        }
    }

    private String saizhi(String str) {
        if ("1".equals(str)) {
            return "3人";
        } else if ("2".equals(str)) {
            return "5人";
        } else if ("3".equals(str)) {
            return "7人";
        } else if ("4".equals(str)) {
            return "8人";
        } else if ("5".equals(str)) {
            return "9人";
        } else if ("6".equals(str)) {
            return "11人";
        } else {
            return "0人";
        }
    }

    public void setPersonInfo(BasisBean personInfo) {
        userName.setText(personInfo.getNickname());
        position.setText("中锋");
        imageLoader.displayImage(C.SP.IMG_URL + personInfo.getPortrait(), my_photo_img);
        //address.setText(personInfo.getPosition());
        String province = "";
        String city = "";
        String area = "";
        String age = "";
        if (!TextUtils.isEmpty(personInfo.getProvince())){
            province = personInfo.getProvince();
        }
        if (!TextUtils.isEmpty(personInfo.getCity())){
            city = personInfo.getCity();
        }
        if (!TextUtils.isEmpty(personInfo.getArea())){
            area = personInfo.getArea();
        }
        if (!TextUtils.isEmpty(personInfo.getAge())){
            age = personInfo.getAge();
        }
        address.setText(province+" " + city+" "+ area + " " + "    年龄:" + age);
        setTeamInfo(personInfo.getLabel());
        focus_status = personInfo.getFocus_status();
        switch (personInfo.getFocus_status()) {
            //关注状态 1：未关注 2：已关注 当type=1时，该值不存在
            case "1":
                right_button_text.setText("关注");
                break;
            case "2":
                right_button_text.setText("已关注");
                break;
            default:
                right_button_text.setText("");
                break;
        }
        //setTeamInfo("技术控,大力出奇迹,左右开工");
    }

    private String timeChange(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Long time = Long.parseLong(str);
        String d = format.format(time);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    private void setTeamInfo(String label) {
        if (label == null) {
            return;
        }
        String[] labels;
        labels = label.split(",");
        if (labels.length > 0) {
            for (int i = 0; i < labels.length; i++) {
                final View v = LayoutInflater.from(this).inflate(R.layout.info_labels_item_view, null);
                TextView label_item = (TextView) v.findViewById(R.id.label_item);
                label_item.setText(labels[i]);
                labels_lin.addView(v);
            }
        }
    }
}
