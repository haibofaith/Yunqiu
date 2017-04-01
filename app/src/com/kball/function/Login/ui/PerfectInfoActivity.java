package com.kball.function.Login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.PerfectInfoView;
import com.kball.function.Login.presenter.PerfectInfoPresenter;
import com.kball.function.Mine.bean.URLBean;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.other.CropperActivity;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.kball.util.UpYunUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.upyun.library.common.Params;
import com.upyun.library.common.UploadEngine;
import com.upyun.library.listener.SignatureListener;
import com.upyun.library.listener.UpCompleteListener;
import com.upyun.library.listener.UpProgressListener;
import com.upyun.library.utils.UpYunUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kball.C.SP.USER_INFO;

/**
 * Created by user on 2017/2/21.
 */

public class PerfectInfoActivity extends BaseActivity implements View.OnClickListener, PerfectInfoView {
    private TitleView title_view;
    private TextView join_yunqiu_tv, bottom_tv, xiangce, paizhao;
    private EditText name_edit;//姓名
    private EditText code_edit;//密码
    private PerfectInfoPresenter presenter;
    private ImageView touxiang_id;
    private boolean touxiao_flag;
    private RelativeLayout touxiang_rel;
    private ImageView biyan;


    private static final int TAKE_PICTURE = 1;
    private static final int CROP_PICTURE = 2;
    private static final int CHOOSE_PICTURE = 3;
    private String fileName;
    private File mBitFile;
    public static final File FILE_SDCARD = Environment.getExternalStorageDirectory();
    public static final File FILE_LOCAL = new File(FILE_SDCARD, "vCar");
    public static final File FILE_PIC_SCREENSHOT = new File(FILE_LOCAL, "images/screenshots");
    String ImageURL = "";
    Context mContext;
    ImageLoader imageLoader;
    private boolean isShow;

    @Override
    protected int getContentViewResId() {
        return R.layout.perfect_info_layout;
    }

    @Override
    protected void initView() {
        presenter = new PerfectInfoPresenter(this);
        title_view = (TitleView) findViewById(R.id.title_view);
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
        bottom_tv = (TextView) findViewById(R.id.bottom_tv);
        xiangce = (TextView) findViewById(R.id.xiangce);
        paizhao = (TextView) findViewById(R.id.paizhao);
        title_view.setTitleText("完善信息");
        name_edit = (EditText) findViewById(R.id.name_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        touxiang_id = (ImageView) findViewById(R.id.touxiang_id);
        biyan = (ImageView) findViewById(R.id.biyan);
        touxiang_rel = (RelativeLayout) findViewById(R.id.touxiang_rel);
        if ("" != SPUtil.getInstance().getString(C.SP.USER_NAME, "")) {
            name_edit.setText(SPUtil.getInstance().getString(C.SP.USER_NAME, ""));
        }
        if ("" != SPUtil.getInstance().getString(C.SP.USER_ICON, "")) {
            ImageLoader.getInstance().displayImage(SPUtil.getInstance().getString(C.SP.USER_ICON, ""), touxiang_id);
            ImageLoader.getInstance().loadImage(SPUtil.getInstance().getString(C.SP.USER_ICON, ""), new
                    SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    saveBitmapFile(loadedImage);
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        join_yunqiu_tv.setOnClickListener(this);
        touxiang_id.setOnClickListener(this);
        bottom_tv.setOnClickListener(this);
        paizhao.setOnClickListener(this);
        xiangce.setOnClickListener(this);
        biyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.biyan:
                if (!isShow){
                    isShow = !isShow;
                    code_edit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    isShow = !isShow;
                    code_edit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                }
                break;
            case R.id.join_yunqiu_tv:


                if (null == ImageURL) {
                    if (ImageURL.length() == 0) {
                        ToastAlone.show("请设置头像");
                        return;
                    }
                    return;
                }

                String name = name_edit.getText().toString().trim();
                if (name.length() == 0) {
                    ToastAlone.show("请输入姓名");
                    return;
                }
                String code = code_edit.getText().toString().trim();
                if (code.length() == 0) {
                    ToastAlone.show("请输入密码");
                    return;
                }
                String userId = SPUtil.getInstance().getString(C.SP.USER_ID, "");
                presenter.perfectInfo(this, userId, code_edit.getText().toString(), name_edit.getText().toString(),
                        ImageURL);
                break;
            case R.id.touxiang_id:
                if (!touxiao_flag) {
                    touxiao_flag = !touxiao_flag;
                    touxiang_rel.setVisibility(View.VISIBLE);
                }
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
        if ("1200".equals(result.getError_code())) {
            SPUtil.getInstance().putString(C.SP.USER_ID, result.getData().getUser_id());
            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, result.getData().getAccess_token());
            SPUtil.getInstance().putString(C.SP.EXPIRES_IN, result.getData().getExpires_in());
            SPUtil.getInstance().putString(C.SP.REFRESH_TOKEN, result.getData().getRefresh_token());
            setResult(RESULT_OK);
            finish();

        }
    }

    @Override
    public void setUrlImg(String result) {
        if (null != result) {
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
        touxiang_id.setImageBitmap(bitmap);
        // TODO 上传头像 访问接口
        // uploadPhoto();
        submitPic(mBitFile);
    }

    private void submitPic(File mBitFile) {
        presenter.uploadFile(this, mBitFile);
    }

    /**
     * 是否有SD卡
     */
    public boolean isHaveSDCard() {
        String SDState = android.os.Environment.getExternalStorageState();
        if (SDState.equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public void saveBitmapFile(Bitmap bitmap) {
        File filePath;
        if (isHaveSDCard()) {
            filePath = Environment.getExternalStorageDirectory();
        } else {
            filePath = Environment.getDataDirectory();
        }
        File file = new File(filePath.getPath() + "/yunqiu/qqtouxiang.png");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        submitPic(file);
    }

}

