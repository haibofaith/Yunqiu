package com.kball.function.Match.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Login.presenter.MatchAboutPresenter;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.impls.MatchAboutViews;
import com.kball.function.Match.impls.MatchTabTwoViewImpl;
import com.kball.function.Match.presenter.MatchTabTwoPresenter;
import com.kball.util.PublicUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/22.
 */

public class XLDetailAct extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        MatchAboutViews {

    private String game_id, team_id;
    private MatchAboutPresenter mPresenter;
    private ImageView back_img, more_bg, share_bg;
    private TextView xl_name, rank_name, time_tv, place_tv;
    private TextView qingjia_tv, baoming_tv, daiding_tv;
    private LinearLayout status_lin;

    private View zz_v, zz_view;
    private LinearLayout edit_lin;
    private TextView edit_xunlian, cancle_xunlian;

    private LinearLayout cancle_lin;
    private TextView cancle_title, cancle_cancle, cancle_enter;
    private EditText cancle_edit;
    private String cancleStr;
    private String join_status;//1：报名 2：待定 3：请假
    private LinearLayout ybm_lin, dd_lin, qj_lin;
    private TextView qj_title, bm_title, dd_title;
    private RelativeLayout bm_rel,daiding_rel,qj_rel;
    private ArrayList<MemberBean> member;

    @Override
    protected int getContentViewResId() {
        return R.layout.xunlian_detail;
    }

    @Override
    protected void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        more_bg = (ImageView) findViewById(R.id.more_bg);
        share_bg = (ImageView) findViewById(R.id.share_img);
        xl_name = (TextView) findViewById(R.id.xl_name);
        qj_title = (TextView) findViewById(R.id.qj_title);
        bm_title = (TextView) findViewById(R.id.bm_title);
        dd_title = (TextView) findViewById(R.id.dd_title);
        rank_name = (TextView) findViewById(R.id.rank_name);
        time_tv = (TextView) findViewById(R.id.time_tv);
        place_tv = (TextView) findViewById(R.id.place_tv);
        qingjia_tv = (TextView) findViewById(R.id.qingjia_tv);
        baoming_tv = (TextView) findViewById(R.id.baoming_tv);
        daiding_tv = (TextView) findViewById(R.id.daiding_tv);
        edit_xunlian = (TextView) findViewById(R.id.edit_xunlian);
        cancle_xunlian = (TextView) findViewById(R.id.cancle_xunlian);
        cancle_title = (TextView) findViewById(R.id.cancle_title);
        cancle_cancle = (TextView) findViewById(R.id.cancle_cancle);
        cancle_enter = (TextView) findViewById(R.id.cancle_enter);
        status_lin = (LinearLayout) findViewById(R.id.status_lin);
        cancle_lin = (LinearLayout) findViewById(R.id.cancle_lin);
        zz_v = (View) findViewById(R.id.zz_v);
        zz_view = (View) findViewById(R.id.zz_view);
        edit_lin = (LinearLayout) findViewById(R.id.edit_lin);
        ybm_lin = (LinearLayout) findViewById(R.id.ybm_lin);
        qj_lin = (LinearLayout) findViewById(R.id.qj_lin);
        dd_lin = (LinearLayout) findViewById(R.id.dd_lin);
        cancle_edit = (EditText) findViewById(R.id.cancle_edit);
        bm_rel = (RelativeLayout) findViewById(R.id.bm_rel);
        daiding_rel = (RelativeLayout) findViewById(R.id.daiding_rel);
        qj_rel = (RelativeLayout) findViewById(R.id.qj_rel);
    }

    @Override
    protected void initData() {

        game_id = getIntent().getStringExtra("game_id");
        mPresenter = new MatchAboutPresenter(this);
        mPresenter.selectGameInfo(this, game_id);
    }

    @Override
    protected void setListener() {

        back_img.setOnClickListener(this);
        qingjia_tv.setOnClickListener(this);
        baoming_tv.setOnClickListener(this);
        daiding_tv.setOnClickListener(this);
        zz_v.setOnClickListener(this);
        more_bg.setOnClickListener(this);
        cancle_xunlian.setOnClickListener(this);
        edit_xunlian.setOnClickListener(this);
        cancle_cancle.setOnClickListener(this);
        cancle_enter.setOnClickListener(this);
        bm_rel.setOnClickListener(this);
        daiding_rel.setOnClickListener(this);
        qj_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qj_rel:
                startActivity(new Intent().setClass(this, PeopleListAct.class).putExtra("type", "3").putExtra
                        ("member", member));
                break;
            case R.id.daiding_rel:
                startActivity(new Intent().setClass(this, PeopleListAct.class).putExtra("type", "2").putExtra
                        ("member", member));
                break;
            case R.id.bm_rel:
                startActivity(new Intent().setClass(this, PeopleListAct.class).putExtra("type", "1").putExtra
                        ("member", member));
                break;
            case R.id.cancle_enter:
                cancleStr = cancle_edit.getText().toString().trim();
                if (cancleStr.length() == 0) {
                    ToastAlone.show("请输入取消的原因");
                    return;
                }
                mPresenter.cancelGame(mContext, game_id, cancleStr);
                break;
            case R.id.cancle_cancle:
                zz_view.setVisibility(View.GONE);
                cancle_lin.setVisibility(View.GONE);
                break;
            case R.id.cancle_xunlian:
                zz_v.setVisibility(View.GONE);
                edit_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.VISIBLE);
                cancle_lin.setVisibility(View.VISIBLE);
                cancle_title.setText("请输入取消训练的原因");
                break;
            case R.id.edit_xunlian:
                zz_v.setVisibility(View.GONE);
                edit_lin.setVisibility(View.GONE);
                startActivityForResult(new Intent(this, EditTrainActivity.class).putExtra("game_id", game_id), 120);
                break;
            case R.id.zz_v:
                zz_v.setVisibility(View.GONE);
                edit_lin.setVisibility(View.GONE);
                break;
            case R.id.qingjia_tv:
                join_status = "3";
                mPresenter.participationGame(this, game_id, team_id, join_status);
                break;
            case R.id.baoming_tv:
                join_status = "1";
                mPresenter.participationGame(this, game_id, team_id, join_status);
                break;
            case R.id.daiding_tv:
                join_status = "2";
                mPresenter.participationGame(this, game_id, team_id, join_status);
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.more_bg:
                zz_v.setVisibility(View.VISIBLE);
                edit_lin.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void setData(BaseBean<GameInfoBean<MemberBean>> result) {
        member = result.getData().getMember();
        team_id = result.getData().getGame_info().getEntry_teamA();
        xl_name.setText(result.getData().getGame_info().getGame_name());
        rank_name.setText(result.getData().getGame_info().getTeamA_name());
        time_tv.setText(PublicUtil.getStrTime(result.getData().getGame_info().getGame_time(), "yyyy-MM-dd HH:mm"));
        place_tv.setText(result.getData().getGame_info().getGame_site());

//比赛状态 0：不需要审核，不需要应战（赛事的比赛） 1 待审核 2：待应战 3：报名中 4：报名结束 5：进行中 6：已结束 7：已取消 8：拒绝比赛 9：拒绝应战
        if ("3".equals(result.getData().getGame_info().getGame_status())) {
            //
            status_lin.setVisibility(View.VISIBLE);
        } else {
            status_lin.setVisibility(View.GONE);
        }

        //报名状态 0：未报名 1：已报名 2：已待定 3：已请假
        if ("0".equals(result.getData().getJoin_status())) {
            qingjia_tv.setVisibility(View.VISIBLE);
            baoming_tv.setVisibility(View.VISIBLE);
            daiding_tv.setVisibility(View.VISIBLE);
        } else if ("1".equals(result.getData().getJoin_status())) {
            qingjia_tv.setVisibility(View.VISIBLE);
            baoming_tv.setVisibility(View.GONE);
            daiding_tv.setVisibility(View.VISIBLE);
        } else if ("2".equals(result.getData().getJoin_status())) {
            qingjia_tv.setVisibility(View.VISIBLE);
            baoming_tv.setVisibility(View.VISIBLE);
            daiding_tv.setVisibility(View.GONE);
        } else if ("3".equals(result.getData().getJoin_status())) {
            qingjia_tv.setVisibility(View.GONE);
            baoming_tv.setVisibility(View.VISIBLE);
            daiding_tv.setVisibility(View.GONE);
        }

        if ("0".equals(result.getData().getGame_info().getIdentity())) {
            more_bg.setVisibility(View.INVISIBLE);
        } else {
            if ("0".equals(result.getData().getGame_info().getJurisdictionA())) {
                more_bg.setVisibility(View.INVISIBLE);
            } else {
                more_bg.setVisibility(View.VISIBLE);
            }
        }
        int ybm_num = 0;
        int dd_num = 0;
        int qj_num = 0;
        ybm_lin.removeAllViews();
        dd_lin.removeAllViews();
        qj_lin.removeAllViews();
        for (int i = 0; i < result.getData().getMember().size(); i++) {


            switch (result.getData().getMember().get(i).getStatus()) {
                case "1"://已报名
                    final View view = LayoutInflater.from(this).inflate(R.layout.head_img_view, null);
                    ImageView imageView = (ImageView) view.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView);
                    ybm_num++;
                    ybm_lin.addView(view);
                    break;
                case "2"://待定
                    final View view2 = LayoutInflater.from(this).inflate(R.layout.head_img_view, null);
                    ImageView imageView2 = (ImageView) view2.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView2);
                    dd_num++;
                    dd_lin.addView(view2);
                    break;
                case "3"://请假
                    final View view3 = LayoutInflater.from(this).inflate(R.layout.head_img_view, null);
                    ImageView imageView3 = (ImageView) view3.findViewById(R.id.head_img_item);
                    ImageLoader.getInstance().displayImage(C.SP.IMG_URL + result.getData().getMember().get(i)
                            .getPortrait(), imageView3);
                    qj_num++;
                    qj_lin.addView(view3);
                    break;

            }

        }
        qj_title.setText("请假 " + qj_num + "人");
        dd_title.setText("待定 " + dd_num + "人");
        bm_title.setText("已报名 " + ybm_num + "人");

    }

    @Override
    public void setAuditOrFightGameData(BaseBean result) {

    }

    @Override
    public void setParticipationGameData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (join_status) {
                //1：报名 2：待定 3：请假
                case "1"://报名成功
                    baoming_tv.setVisibility(View.GONE);
                    daiding_tv.setVisibility(View.GONE);
                    qingjia_tv.setVisibility(View.VISIBLE);
                    mPresenter.selectGameInfo(this, game_id);
                    break;
                case "2"://待定成功
                    baoming_tv.setVisibility(View.VISIBLE);
                    daiding_tv.setVisibility(View.GONE);
                    qingjia_tv.setVisibility(View.VISIBLE);
                    mPresenter.selectGameInfo(this, game_id);
                    break;
                case "3":
                    baoming_tv.setVisibility(View.VISIBLE);
                    daiding_tv.setVisibility(View.GONE);
                    qingjia_tv.setVisibility(View.GONE);
                    mPresenter.selectGameInfo(this, game_id);
                    break;
            }
        }
    }

    @Override
    public void cancleGame() {
        zz_view.setVisibility(View.GONE);
        cancle_lin.setVisibility(View.GONE);
        ToastAlone.show("训练已取消");
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 120:
                    mPresenter.selectGameInfo(this, game_id);
                    break;
            }
        }
    }
}
