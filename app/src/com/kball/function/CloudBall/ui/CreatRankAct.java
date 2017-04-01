package com.kball.function.CloudBall.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.adapter.ExploitsAdapter;
import com.kball.function.CloudBall.bean.AreaBean;
import com.kball.function.CloudBall.bean.CityBean;
import com.kball.function.CloudBall.bean.ExploitsBean;
import com.kball.function.CloudBall.bean.ProviceBean;
import com.kball.function.CloudBall.bean.TagBean;
import com.kball.function.CloudBall.bean.TeamIdBean;
import com.kball.function.CloudBall.presenter.CreatRanPresenter;
import com.kball.function.CloudBall.presenter.ProvicePresenter;
import com.kball.function.CloudBall.view.CreatRankView;
import com.kball.function.CloudBall.view.ProviceView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.PerfectInfoView;
import com.kball.function.Login.presenter.PerfectInfoPresenter;
import com.kball.function.Mine.bean.URLBean;
import com.kball.function.home.bean.TeamInfoBean;
import com.kball.function.other.CircleImageView;
import com.kball.function.other.CropperActivity;
import com.kball.util.ToastAlone;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class CreatRankAct extends BaseActivity implements View.OnClickListener, PerfectInfoView, ProviceView, CreatRankView {

    private ImageView message_tv;
    private TextView creat_btn;
    private View zz_view;
    private static int PLACE_CODE = 119;
    private static int TAG_CODE = 120;
    private PerfectInfoPresenter presenter;
    private CreatRanPresenter mCtreatPresenter;
    private ProvicePresenter mProvicepresenter;

    //球队名称
    private EditText rank_name;
    private String mRankName;

    //球队类型
    private RelativeLayout rank_type_rel;
    private LinearLayout rank_type_show_rel;
    private TextView type_1, type_2, type_3, type_4, type_tv;
    private String mTypeName="0";

    //成立时间
    private RelativeLayout time_rel;
    private TextView time_tv;
    private Calendar ca;
    private int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    private String mTime;

    //主场
    private RelativeLayout place_rel;
    private TextView place_tv;
    private String mPlace;

    //队徽
    private CircleImageView rank_img;

    private RelativeLayout touxiang_rel;
    private TextView paizhao, xiangce,creat_ranks_tv;
    private boolean touxiao_flag;
    private static final int TAKE_PICTURE = 1;
    private static final int CROP_PICTURE = 2;
    private static final int CHOOSE_PICTURE = 3;
    private String fileName;
    private File mBitFile;
    public static final File FILE_SDCARD = Environment.getExternalStorageDirectory();
    public static final File FILE_LOCAL = new File(FILE_SDCARD, "vCar");
    public static final File FILE_PIC_SCREENSHOT = new File(FILE_LOCAL, "images/screenshots");
    String ImageURL = "";
    private TextView bottom_tv;

    //标签
    private RelativeLayout tag_rel;
    private LinearLayout tag_lin;
    private ArrayList<TagBean> tagData;
    private String mTag;

    //地区
    private RelativeLayout add_rel;
    private RelativeLayout add_tv;
    private TextView add_cancle_tv, add_enter_tv, diqu_tv;
    private LinearLayout provice_lin, city_lin, area_lin;
    private int provice_pos, city_pos, area_pos;
    private String proviceName, cityName, areaName, diquStr;

    //背景图
    private TextView bg_tv;
    private String imgType = "1";
    private String BgImgURL;
    private RelativeLayout cloud_home_bg;


    TeamInfoBean team_info;
    int  act_type;
    private String team_id;

    @Override
    protected int getContentViewResId() {
        return R.layout.creat_ranks_act;
    }

    @Override
    protected void initView() {
        message_tv = (ImageView) findViewById(R.id.message_tv);
        rank_name = (EditText) findViewById(R.id.rank_name);
        creat_btn = (TextView) findViewById(R.id.creat_btn);
        creat_ranks_tv = (TextView) findViewById(R.id.creat_ranks_tv);
        rank_type_rel = (RelativeLayout) findViewById(R.id.rank_type_rel);
        time_rel = (RelativeLayout) findViewById(R.id.time_rel);
        place_rel = (RelativeLayout) findViewById(R.id.place_rel);
        tag_rel = (RelativeLayout) findViewById(R.id.tag_rel);
        add_rel = (RelativeLayout) findViewById(R.id.add_rel);
        rank_type_show_rel = (LinearLayout) findViewById(R.id.rank_type_show_rel);
        provice_lin = (LinearLayout) findViewById(R.id.provice_lin);
        city_lin = (LinearLayout) findViewById(R.id.city_lin);
        area_lin = (LinearLayout) findViewById(R.id.area_lin);
        tag_lin = (LinearLayout) findViewById(R.id.tag_lin);
        zz_view = (View) findViewById(R.id.zz_view);
        bg_tv = (TextView) findViewById(R.id.bg_tv);
        type_1 = (TextView) findViewById(R.id.type_1);
        type_2 = (TextView) findViewById(R.id.type_2);
        type_3 = (TextView) findViewById(R.id.type_3);
        type_4 = (TextView) findViewById(R.id.type_4);
        type_tv = (TextView) findViewById(R.id.type_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        place_tv = (TextView) findViewById(R.id.place_tv);
        rank_img = (CircleImageView) findViewById(R.id.rank_img);
        paizhao = (TextView) findViewById(R.id.paizhao);
        xiangce = (TextView) findViewById(R.id.xiangce);
        bottom_tv = (TextView) findViewById(R.id.bottom_tv);
        add_cancle_tv = (TextView) findViewById(R.id.add_cancle_tv);
        add_enter_tv = (TextView) findViewById(R.id.add_enter_tv);
        diqu_tv = (TextView) findViewById(R.id.diqu_tv);
        touxiang_rel = (RelativeLayout) findViewById(R.id.touxiang_rel);
        add_tv = (RelativeLayout) findViewById(R.id.add_tv);
        cloud_home_bg = (RelativeLayout) findViewById(R.id.cloud_home_bg);
    }

    @Override
    protected void initData() {
        act_type = getIntent().getIntExtra("act_type",0);
        if (act_type == 1){
            creat_btn.setText("创建");
            creat_ranks_tv.setText("创建球队");
        }else if (act_type == 2){
            creat_btn.setText("保存");
            creat_ranks_tv.setText("编辑球队");
            team_info = (TeamInfoBean) getIntent().getSerializableExtra("teamDetail");
            team_id = team_info.getTeam_id();
            mRankName = team_info.getTeam_name();
            rank_name.setText(mRankName);

            mTypeName = team_info.getTeam_type();
            type_tv.setText(getType(team_info.getTeam_type()));

            diqu_tv.setText(team_info.getProvince()+team_info.getCity()+team_info.getArea());

            ImageURL = team_info.getBadge();
            BgImgURL = team_info.getBackground();
            place_tv.setText(team_info.getHome());
            time_tv.setText(team_info.getEstablish_time());



            imageLoader.getInstance().displayImage(C.SP.IMG_URL+ImageURL,rank_img);
            mTag = team_info.getLabel();
            mTag.replaceAll("null","");
            if (mTag!=null) {
                String str[] = mTag.split(",");
                tag_lin.removeAllViews();
                tag_lin.setVisibility(View.VISIBLE);

                for (int i = 0; i < str.length; i++) {
                    View v = View.inflate(mContext, R.layout.creat_tag_item, null);
                    TextView tag_name = (TextView) v.findViewById(R.id.tag_name);
                    tag_name.setText(str[i]);
                    tag_lin.addView(v);
                }
            }
        }
        presenter = new PerfectInfoPresenter(this);
        mProvicepresenter = new ProvicePresenter(mContext, this);
        mCtreatPresenter = new CreatRanPresenter(mContext, this);
        tagData = new ArrayList<TagBean>();
        ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mProvicepresenter.selectProvince();

    }
    private String getType(String str){
        if ("1".equals(str)){
            return "校园球队";
        }else if("2".equals(str)){
            return "业余爱好";
        }else if("3".equals(str)){
            return "公司球队";
        }else{
            return "青少年球队";
        }
    }

    @Override
    protected void setListener() {
        message_tv.setOnClickListener(this);
        creat_btn.setOnClickListener(this);
        rank_type_rel.setOnClickListener(this);
        type_1.setOnClickListener(this);
        type_2.setOnClickListener(this);
        type_3.setOnClickListener(this);
        type_4.setOnClickListener(this);
        time_rel.setOnClickListener(this);
        place_rel.setOnClickListener(this);
        rank_img.setOnClickListener(this);
        bottom_tv.setOnClickListener(this);
        tag_rel.setOnClickListener(this);
        add_tv.setOnClickListener(this);
        add_cancle_tv.setOnClickListener(this);
        add_enter_tv.setOnClickListener(this);
        bg_tv.setOnClickListener(this);
        zz_view.setOnClickListener(this);
        paizhao.setOnClickListener(this);
        bottom_tv.setOnClickListener(this);
        xiangce.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zz_view:
                break;
            case R.id.rank_img:
                imgType = "2";
                if (!touxiao_flag) {
                    touxiao_flag = !touxiao_flag;
                    touxiang_rel.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.bg_tv:
                imgType = "1";
                if (!touxiao_flag) {
                    touxiao_flag = !touxiao_flag;
                    touxiang_rel.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.add_enter_tv:
                zz_view.setVisibility(View.GONE);
                add_rel.setVisibility(View.GONE);
                diqu_tv.setText(proviceName + cityName + areaName);
                break;
            case R.id.add_cancle_tv:
                proviceName = "";
                cityName = "";
                areaName = "";
                diqu_tv.setText(proviceName + cityName + areaName);
                zz_view.setVisibility(View.GONE);
                add_rel.setVisibility(View.GONE);
                break;
            case R.id.add_tv:
                zz_view.setVisibility(View.VISIBLE);
                add_rel.setVisibility(View.VISIBLE);
                break;
            case R.id.tag_rel:
                startActivityForResult(new Intent().setClass(mContext, TagAct.class), TAG_CODE);
                break;
            case R.id.bottom_tv:
                touxiang_rel.setVisibility(View.GONE);
                touxiao_flag = false;
                break;
            case R.id.paizhao:
                // 从照相机获取
                touxiang_rel.setVisibility(View.GONE);
                touxiao_flag = false;
                if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                    ToastAlone.show("未检测到SD卡");
                    return;
                }
                selectCamera();
                // gotoCamera();
                break;
            case R.id.xiangce:
                // 从相册获取
                touxiang_rel.setVisibility(View.GONE);
                touxiao_flag = false;
                // gotoPhotos();
                if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                    ToastAlone.show("未检测到SD卡");
                    return;
                }
                selectPhoto();
                break;

            case R.id.place_rel:
                startActivityForResult(new Intent().setClass(mContext, PlaceAct.class), PLACE_CODE);
                break;
            case R.id.time_rel:
                showDialog(DATE_DIALOG);
                break;
            case R.id.type_1:
                mTypeName = "2";
                type_tv.setText("业余球队");
                zz_view.setVisibility(View.GONE);
                rank_type_show_rel.setVisibility(View.GONE);
                break;
            case R.id.type_2:
                mTypeName = "1";
                type_tv.setText("校园球队");
                zz_view.setVisibility(View.GONE);
                rank_type_show_rel.setVisibility(View.GONE);
                break;
            case R.id.type_3:
                mTypeName = "3";
                type_tv.setText("公司球队");
                zz_view.setVisibility(View.GONE);
                rank_type_show_rel.setVisibility(View.GONE);
                break;
            case R.id.type_4:
                mTypeName = "4";
                type_tv.setText("青少年球队");
                zz_view.setVisibility(View.GONE);
                rank_type_show_rel.setVisibility(View.GONE);
                break;
            case R.id.rank_type_rel:
                zz_view.setVisibility(View.VISIBLE);
                rank_type_show_rel.setVisibility(View.VISIBLE);
                break;
            case R.id.message_tv:
                finish();
                break;
            case R.id.creat_btn:
                creat();
                break;
        }
    }

    private void creat() {



        mRankName = rank_name.getText().toString().trim();
        if (mRankName.length() == 0) {
            ToastAlone.show("请输入球队名称");
            return;
        }
        if (mTypeName.equals("0")){
            ToastAlone.show("请选择球队类型");
            return;
        }

        diquStr = diqu_tv.getText().toString().trim();
        if (diquStr.length() == 0) {
            ToastAlone.show("请选择地区");
            return;
        }
        mPlace = place_tv.getText().toString().trim();
        if (mPlace.length() == 0) {
            ToastAlone.show("请选择主场");
            return;
        }

        mTime = time_tv.getText().toString().trim();

        mTag = "";
        for (int i = 0; i < tagData.size(); i++) {

            mTag =  mTag + tagData.get(i).getTagName()+",";
        }


        if (act_type==1){
            mCtreatPresenter.insertTeam(ImageURL,mRankName,mTypeName,proviceName,cityName,areaName,mPlace,mTime,mTag,BgImgURL);
        }else if(act_type == 2) {

            mCtreatPresenter.updateTeamInfo(team_id,ImageURL, mRankName, mTypeName, proviceName, cityName, areaName, mPlace,
                    mTime, mTag, BgImgURL);

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_CODE) {
            if (resultCode == RESULT_OK) if (null != data) {
                place_tv.setText(data.getStringExtra("place_name"));
            }
        }
        if (requestCode == TAG_CODE) {
            if (resultCode == RESULT_OK) {
                if (null != data) {
                    tagData = (ArrayList<TagBean>) data.getSerializableExtra("tagList");
                    tag_lin.removeAllViews();
                    tag_lin.setVisibility(View.VISIBLE);

                    for (int i = 0; i < tagData.size(); i++) {
                        View v = View.inflate(mContext, R.layout.creat_tag_item, null);
                        TextView tag_name = (TextView) v.findViewById(R.id.tag_name);
                        tag_name.setText(tagData.get(i).getTagName());
                        tag_lin.addView(v);
                    }

                }
            }
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PICTURE:
                    if (data != null) {
                        Uri uri = data.getData();
                        if (!TextUtils.isEmpty(uri.getAuthority())) {
                            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media
                                    .DATA}, null, null, null);
                            if (null == cursor) {
                                ToastAlone.show("图片没找到");
                                return;
                            }
                            cursor.moveToFirst();
                            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            cursor.close();
                            Intent intent = new Intent(this, CropperActivity.class);
                            intent.putExtra("path", path);
                            // intent.putExtra("name", select_Num);
                            startActivityForResult(intent, CROP_PICTURE);
                        } else {
                            Intent intent = new Intent(this, CropperActivity.class);
                            intent.putExtra("path", uri.getPath());
                            // intent.putExtra("name", select_Num);
                            startActivityForResult(intent, CROP_PICTURE);
                        }
                    }
                    break;
                case TAKE_PICTURE:
                    File f = new File(FILE_PIC_SCREENSHOT, fileName);
                    Intent intent = new Intent(this, CropperActivity.class);
                    intent.putExtra("path", f.getAbsolutePath());
                    // intent.putExtra("name", select_Num);
                    startActivityForResult(intent, CROP_PICTURE);
                    break;
                case CROP_PICTURE:
                    String path = data.getStringExtra("path");
                    setImage(path);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth + 1, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        time_tv.setText(mYear + "-" + (mMonth+1) + "-" + mDay);
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

    @Override
    public void setObjData(BaseBean<UserRegisterBean> result) {

    }

    @Override
    public void setUrlImg(String result) {
        if ("1".equals(imgType)){
            BgImgURL = result;
        }else{
            ImageURL = result;
        }
    }


    /**
     * 相机选择
     */
    private void selectCamera() {
        fileName = String.valueOf((new Date()).getTime()) + ".jpg";
        File filePath = FILE_PIC_SCREENSHOT;
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(filePath, fileName);
        // localTempImgDir和localTempImageFileName是自己定义的名字
        Uri u = Uri.fromFile(f);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    ;


    /**
     * 设置返回后的裁剪图片
     */
    private void setImage(String path) {
        Bitmap bitmap;
        mBitFile = new File(path);
        // Log.i("mLeftBefore_file", mBitFile.toString());
        bitmap = BitmapFactory.decodeFile(path);
        if ("1".equals(imgType)){//背景图
            cloud_home_bg.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }else{
            rank_img.setImageBitmap(bitmap);
        }
        // TODO 上传头像 访问接口
        // uploadPhoto();
        submitPic(mBitFile);
    }

    private void submitPic(File mBitFile) {
        presenter.uploadFile(this, mBitFile);
    }

    /**
     * 选择图片方式
     */
    private void selectPhoto() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    @Override
    public void setProvice(ArrayList<ProviceBean> data) {


        setProviceData(data);
        if (null != data) {
            mProvicepresenter.selectCity(data.get(0).getProvince_id());
        }
    }

    private void setProviceData(final ArrayList<ProviceBean> data) {
        provice_lin.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View v = View.inflate(mContext, R.layout.provice_item, null);
            TextView tv1 = (TextView) v.findViewById(R.id.tv1);
            tv1.setText(data.get(i).getProvince());
            final int pos = i;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    city_pos = 0;
                    provice_pos = pos;
                    setProviceData(data);
                    mProvicepresenter.selectCity(data.get(pos).getProvince_id());


                }
            });
            proviceName = data.get(provice_pos).getProvince();
            if (provice_pos == i) {
                tv1.setBackgroundColor(Color.parseColor("#cccccc"));
            } else {
                tv1.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            provice_lin.addView(v);
        }
    }

    @Override
    public void setCity(final ArrayList<CityBean> data) {


        setCityData(data);
        if (null != data) {
            mProvicepresenter.selectArea(data.get(0).getCity_id());
        }
    }

    private void setCityData(final ArrayList<CityBean> data) {
        city_lin.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View v = View.inflate(mContext, R.layout.provice_item, null);
            TextView tv1 = (TextView) v.findViewById(R.id.tv1);
            tv1.setText(data.get(i).getCity());
            final int pos = i;

            cityName = data.get(city_pos).getCity();
            if (city_pos == i) {
                tv1.setBackgroundColor(Color.parseColor("#cccccc"));
            } else {
                tv1.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    area_pos = 0;
                    city_pos = pos;
                    setCityData(data);
                    mProvicepresenter.selectArea(data.get(pos).getCity_id());
                }
            });

            city_lin.addView(v);
        }
    }

    @Override
    public void setArea(final ArrayList<AreaBean> data) {
        area_lin.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View v = View.inflate(mContext, R.layout.provice_item, null);
            TextView tv1 = (TextView) v.findViewById(R.id.tv1);
            tv1.setText(data.get(i).getArea());
            final int pos = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    area_pos = pos;
                    setArea(data);

                }
            });
            areaName = data.get(area_pos).getArea();
            if (area_pos == i) {
                tv1.setBackgroundColor(Color.parseColor("#cccccc"));
            } else {
                tv1.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            area_lin.addView(v);
        }
    }

    @Override
    public void setInfoData(TeamIdBean data) {
        ToastAlone.show("球队创建成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setupdate() {
        ToastAlone.show("球队信息修改成功");
        setResult(RESULT_OK);
        finish();
    }
}