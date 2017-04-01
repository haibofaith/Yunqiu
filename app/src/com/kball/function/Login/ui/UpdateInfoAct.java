package com.kball.function.Login.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.CloudBall.bean.AreaBean;
import com.kball.function.CloudBall.bean.CityBean;
import com.kball.function.CloudBall.bean.ProviceBean;
import com.kball.function.CloudBall.bean.TagBean;
import com.kball.function.CloudBall.presenter.ProvicePresenter;
import com.kball.function.CloudBall.view.ProviceView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.SelectInfoBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.PerfectInfoView;
import com.kball.function.Login.interfaceView.UpdateInfoImpl;
import com.kball.function.Login.presenter.PerfectInfoPresenter;
import com.kball.function.Login.presenter.UpdateInfoPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.bean.BasisBean;
import com.kball.function.other.CropperActivity;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaole.wang on 17/3/10.
 */

public class UpdateInfoAct extends BaseActivity implements View.OnClickListener, PerfectInfoView, ProviceView,
        UpdateInfoImpl {
    private TitleView title_view;
    private PerfectInfoPresenter presenter;

    //性别
    private LinearLayout sex_lin;
    private View zz_view, zzc_view;
    private TextView man_tv, woman_tv, sex_tv;
    private RelativeLayout sex_rel;
    private String sex_str = "0";

    //姓名
    private EditText user_name;
    private String userName = "";

    //省市区域
    private TextView enter_add_lin, cancle_add_lin;
    private LinearLayout add_lin;
    private RelativeLayout address_lin;


    //身高
    private EditText shengao;
    private TextView tv1;
    private String shengao_str = "";

    //体重
    private EditText tizhong;
    private TextView tv2;
    private String tizhong_str = "";

    //惯用脚
    private LinearLayout foot_lin;
    private TextView left_foot_tv;
    private TextView right_foot_tv, foot_tv, all_foot_tv;
    private RelativeLayout foot_lin_onclick;
    private String foot_str = "0";


    //头像
    private ImageView img_bg;
    private RelativeLayout touxiang_rel;
    private TextView paizhao, xiangce;
    private boolean touxiao_flag;
    private static final int TAKE_PICTURE = 1;
    private static final int CROP_PICTURE = 2;
    private static final int CHOOSE_PICTURE = 3;
    private String fileName = "";
    private File mBitFile;
    public static final File FILE_SDCARD = Environment.getExternalStorageDirectory();
    public static final File FILE_LOCAL = new File(FILE_SDCARD, "vCar");
    public static final File FILE_PIC_SCREENSHOT = new File(FILE_LOCAL, "images/screenshots");
    String ImageURL = "";
    private TextView bottom_tv;

    //出生年月
    private TextView data_tv;
    private RelativeLayout data_rel;
    private Calendar ca;
    private int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    private ProvicePresenter mProvicepresenter;
    private LinearLayout provice_lin, city_lin, area_lin;
    private int provice_pos, city_pos, area_pos;
    private String proviceName, cityName, areaName, diquStr;

    private RelativeLayout position_rlin;//位置
    private String returnStr;
    private TextView position_edit;

    private UpdateInfoPresenter updateInfoPresenter;

    private String user_id, nickname, portrait, stature, weight, sex, birthday, veteran, foot, position, province,
            city, label;

    private TextView address_edit;

    private BasisBean basis;

    //个人标签
    private RelativeLayout lable_rel;
    private LinearLayout lable_lin;
    private ArrayList<TagBean> tagData;
    private String mTag;

    @Override
    protected int getContentViewResId() {
        return R.layout.update_info_act;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        sex_lin = (LinearLayout) findViewById(R.id.sex_lin);
        add_lin = (LinearLayout) findViewById(R.id.add_lin);
        foot_lin = (LinearLayout) findViewById(R.id.foot_lin);
        lable_lin = (LinearLayout) findViewById(R.id.lable_lin);
        foot_lin_onclick = (RelativeLayout) findViewById(R.id.foot_lin_onclick);
        address_lin = (RelativeLayout) findViewById(R.id.address_lin);
        touxiang_rel = (RelativeLayout) findViewById(R.id.touxiang_rel);
        position_rlin = (RelativeLayout) findViewById(R.id.position_rlin);
        lable_rel = (RelativeLayout) findViewById(R.id.lable_rel);
        zz_view = (View) findViewById(R.id.zz_view);
        zzc_view = (View) findViewById(R.id.zzc_view);
        man_tv = (TextView) findViewById(R.id.man_tv);
        woman_tv = (TextView) findViewById(R.id.woman_tv);
        all_foot_tv = (TextView) findViewById(R.id.all_foot_tv);
        sex_tv = (TextView) findViewById(R.id.sex_tv);
        data_tv = (TextView) findViewById(R.id.data_tv);
        data_rel = (RelativeLayout) findViewById(R.id.date_rel);
        tizhong = (EditText) findViewById(R.id.tizhong);
        address_edit = (TextView) findViewById(R.id.address_edit);
        sex_rel = (RelativeLayout) findViewById(R.id.sex_rel);
        user_name = (EditText) findViewById(R.id.user_name);
        shengao = (EditText) findViewById(R.id.shengao);
        position_edit = (TextView) findViewById(R.id.position_edit);
        enter_add_lin = (TextView) findViewById(R.id.enter_add_lin);
        cancle_add_lin = (TextView) findViewById(R.id.cancle_add_lin);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        left_foot_tv = (TextView) findViewById(R.id.left_foot_tv);
        right_foot_tv = (TextView) findViewById(R.id.right_foot_tv);
        foot_tv = (TextView) findViewById(R.id.foot_tv);
        paizhao = (TextView) findViewById(R.id.paizhao);
        xiangce = (TextView) findViewById(R.id.xiangce);
        bottom_tv = (TextView) findViewById(R.id.bottom_tv);
        img_bg = (ImageView) findViewById(R.id.img_bg);
        provice_lin = (LinearLayout) findViewById(R.id.provice_lin);
        city_lin = (LinearLayout) findViewById(R.id.city_lin);
        area_lin = (LinearLayout) findViewById(R.id.area_lin);
    }

    @Override
    protected void initData() {
        title_view.setTitleText("个人信息");
        title_view.setRightButtonText("保存");
        title_view.setRightButtonTextColor("#59be5e");
        presenter = new PerfectInfoPresenter(this);
        updateInfoPresenter = new UpdateInfoPresenter(this);
        updateInfoPresenter.selectUserInfo(this);

        title_view.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        mProvicepresenter = new ProvicePresenter(mContext, this);
        mProvicepresenter.selectProvince();
    }

    private void submit() {
        userName = user_name.getText().toString().trim();
        shengao_str = shengao.getText().toString().trim();
        tizhong_str = tizhong.getText().toString().trim();
        if (userName.length() == 0) {
            ToastAlone.show("请输入姓名");
            return;
        }
        if (shengao_str.length() == 0) {
            ToastAlone.show("请输入身高");
            return;
        }
        if (tizhong_str.length() == 0) {
            ToastAlone.show("请输入体重");
            return;
        }
        if (foot_str.equals("0")) {
            ToastAlone.show("请选择惯用脚");
            return;
        }
        if (sex_str.equals("0")) {
            ToastAlone.show("请选择性别");
            return;
        }
        if (ImageURL.equals("")) {
            ToastAlone.show("请选择头像");
            return;
        }
        if (data_tv.getText().toString().trim().length() == 0) {
            ToastAlone.show("请选择出生年月");
            return;
        }
        if (returnStr == null || returnStr.length() == 0) {
            ToastAlone.show("请选择位置");
            return;
        }
//        user_id, nickname, portrait, stature, weight, sex, birthday, veteran, foot, position,
//          province, city, label
        user_id = SPUtil.getInstance().getString(C.SP.USER_ID, "");
        nickname = user_name.getText().toString().trim();
        portrait = ImageURL;
        stature = shengao_str;
        weight = tizhong_str;
        sex = sex_str;
        birthday = data_tv.getText().toString().trim();
        veteran = null;
        foot = foot_str;
        position = returnStr;
        province = proviceName;
        city = cityName;



        label = mTag;
        updateInfoPresenter.modifyInfo(this, user_id, nickname, portrait, stature, weight, sex, birthday, veteran,
                foot, position, province, city, label);
    }

    @Override
    protected void setListener() {
        img_bg.setOnClickListener(this);
        sex_rel.setOnClickListener(this);
        left_foot_tv.setOnClickListener(this);
        right_foot_tv.setOnClickListener(this);
        man_tv.setOnClickListener(this);
        woman_tv.setOnClickListener(this);
        cancle_add_lin.setOnClickListener(this);
        address_lin.setOnClickListener(this);
        enter_add_lin.setOnClickListener(this);
        foot_lin_onclick.setOnClickListener(this);
        touxiang_rel.setOnClickListener(this);
        xiangce.setOnClickListener(this);
        paizhao.setOnClickListener(this);
        data_rel.setOnClickListener(this);
        bottom_tv.setOnClickListener(this);
        position_rlin.setOnClickListener(this);
        all_foot_tv.setOnClickListener(this);
        lable_rel.setOnClickListener(this);

        shengao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (shengao.length() == 0) {

                    tv1.setVisibility(View.INVISIBLE);
                } else {
                    tv1.setVisibility(View.VISIBLE);
                }
            }
        });
        tizhong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tizhong.length() == 0) {
                    tv2.setVisibility(View.INVISIBLE);
                } else {
                    tv2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lable_rel:
                startActivityForResult(new Intent().setClass(this, PeopleTagAct.class), 10086);
                break;
            case R.id.all_foot_tv:
                foot_str = "3";
                foot_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                foot_tv.setText("左右脚");
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
            case R.id.img_bg:
                if (!touxiao_flag) {
                    touxiao_flag = !touxiao_flag;
                    touxiang_rel.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.foot_lin_onclick:
                foot_lin.setVisibility(View.VISIBLE);
                zz_view.setVisibility(View.VISIBLE);
                break;
            case R.id.left_foot_tv:
                foot_str = "1";
                foot_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                foot_tv.setText("左脚");
                break;
            case R.id.right_foot_tv:
                foot_str = "2";
                foot_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                foot_tv.setText("右脚");
                break;
            case R.id.enter_add_lin:

                //TODO 判断 省市区域是否为空
                add_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                address_edit.setText(proviceName + " " + cityName + " " + areaName);
                break;
            case R.id.cancle_add_lin:
                add_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                break;
            case R.id.address_lin:
                add_lin.setVisibility(View.VISIBLE);
                zz_view.setVisibility(View.VISIBLE);

                break;
            case R.id.sex_rel:
                sex_lin.setVisibility(View.VISIBLE);
                zz_view.setVisibility(View.VISIBLE);
                break;

            case R.id.man_tv:
                sex_str = "1";
                sex_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                sex_tv.setText("男");
                break;
            case R.id.woman_tv:
                sex_str = "2";
                sex_lin.setVisibility(View.GONE);
                zz_view.setVisibility(View.GONE);
                sex_tv.setText("女");
                break;
            case R.id.date_rel:
                showDialog(DATE_DIALOG);
                break;
            case R.id.position_rlin:
                Intent intent = new Intent(this, ScorePositionActivity.class);
                startActivityForResult(intent, 10018);
                break;

        }
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
    public void setObjData(BaseBean<UserRegisterBean> result) {

    }

    @Override
    public void setUrlImg(String result) {
        ImageURL = result;
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
     * 相机回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                case 10018:
                    returnStr = data.getStringExtra("returnStr");
                    Log.e("TAG", returnStr);
                    position_edit.setText(returnStr);
                    break;
                case 10086:
                    if (null != data) {
                        tagData = (ArrayList<TagBean>) data.getSerializableExtra("tagList");
                        lable_lin.removeAllViews();
                        lable_lin.setVisibility(View.VISIBLE);

                        for (int i = 0; i < tagData.size(); i++) {
                            View v = View.inflate(mContext, R.layout.creat_tag_item, null);
                            TextView tag_name = (TextView) v.findViewById(R.id.tag_name);
                            tag_name.setText(tagData.get(i).getTagName());
                            lable_lin.addView(v);
                        }

                        mTag = "";
                        for (int i = 0; i < tagData.size(); i++) {
                            mTag = mTag + tagData.get(i).getTagName() + ",";
                        }

                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置返回后的裁剪图片
     */
    private void setImage(String path) {
        Bitmap bitmap;
        mBitFile = new File(path);
        // Log.i("mLeftBefore_file", mBitFile.toString());
        bitmap = BitmapFactory.decodeFile(path);
        img_bg.setImageBitmap(bitmap);
        // TODO 上传头像 访问接口
        // uploadPhoto();
        submitPic(mBitFile);
    }

    private void submitPic(File mBitFile) {
        presenter.uploadFile(this, mBitFile);
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
        data_tv.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
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
    public void setModifyInfoResult(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("修改个人资料成功");
            finish();
        }
    }

    @Override
    public void setSelectUserInfoData(SelectInfoBean data) {

        ImageURL = data.getPortrait();
        ImageLoader.getInstance().displayImage(C.SP.IMG_URL + ImageURL, img_bg);

        nickname = data.getNickname();
        user_name.setText(nickname);
        proviceName = data.getProvince();
        if (TextUtils.isEmpty(data.getCity())||"市辖区".equals(data.getCity())){
            cityName = "";
        }else {
            cityName = data.getCity();
        }

        if (TextUtils.isEmpty( data.getArea())||"市辖区".equals(data.getAge())){
            areaName = "";
        }else {
            areaName = data.getArea();
        }
        returnStr = data.getPosition();
        position_edit.setText(returnStr);
        data_tv.setText(data.getBirthday());
        address_edit.setText(proviceName + " " + cityName + " " + areaName);
        if ("1".equals(data.getSex())) {
            sex_str = "1";
            sex_tv.setText("男");
        } else if ("2".equals(data.getSex())) {
            sex_str = "2";
            sex_tv.setText("女");
        }
        if (TextUtils.isEmpty(data.getStature())) {
            tv1.setVisibility(View.INVISIBLE);
        } else {
            tv1.setVisibility(View.VISIBLE);
            shengao.setText(data.getStature());
        }

        if (TextUtils.isEmpty(data.getWeight())) {
            tv2.setVisibility(View.INVISIBLE);
        } else {
            tv2.setVisibility(View.VISIBLE);
            tizhong.setText(data.getWeight());
        }
        foot_str = data.getFoot();
        if ("1".equals(data.getFoot())) {
            foot_tv.setText("左脚");
        } else if ("2".equals(data.getFoot())) {
            foot_tv.setText("右脚");
        } else if ("3".equals(data.getFoot())) {
            foot_tv.setText("左右脚");
        }

        mTag = data.getLabel();
        mTag.replaceAll("null", "");
        if (mTag != null) {
            String str[] = mTag.split(",");
            lable_lin.removeAllViews();
            lable_lin.setVisibility(View.VISIBLE);

            for (int i = 0; i < str.length; i++) {
                View v = View.inflate(mContext, R.layout.creat_tag_item, null);
                TextView tag_name = (TextView) v.findViewById(R.id.tag_name);
                tag_name.setText(str[i]);
                lable_lin.addView(v);
            }
        }
    }
}
