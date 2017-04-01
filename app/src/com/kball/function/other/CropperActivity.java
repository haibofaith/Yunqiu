package com.kball.function.other;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.other.cropper.CropImageView;

/**
 * 上传头像，拍照类
 * @author wxl
 *
 */
public class CropperActivity extends BaseActivity implements OnClickListener {

    private static final int aspectRatioX=35,aspectRatioY=35;
    private static final String PATH = Environment.getExternalStorageDirectory() + "/";
    private Button mSure;
    private Button mCancel;
    private CropImageView mCropImage;
    private String mPath;
//    private int mFileName;
    public int screenWidth = 0;
    public int screenHeight = 0;
    /* (non-Javadoc)
     * @see com.itotem.tianhe.TianHeBaseActivity#initView()
     */
    @Override
    protected void initView() {
       
        mCropImage = (CropImageView) findViewById(R.id.crop);
        mSure = (Button) findViewById(R.id.btn_sure);
        mCancel = (Button) findViewById(R.id.btn_cancel);
    }

    /* (non-Javadoc)
     * @see com.itotem.tianhe.TianHeBaseActivity#initData()
     */
    @Override
    protected void initData() {
        mCancel.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mSure.setOnClickListener(this);
        mCropImage.setGuidelines(0);
        getWindowWH();
        Intent intent = getIntent();
        mPath = intent.getStringExtra("path");
//        mFileName = intent.getIntExtra("name", 0);
        // mBitmap = BitmapFactory.;
        Bitmap createBitmap = createBitmap(mPath, screenWidth, screenHeight);
        mCropImage.setImageBitmap(createBitmap);
        mCropImage.setFixedAspectRatio(true);
        mCropImage.setAspectRatio(aspectRatioX, aspectRatioY);
    }
    
    /**
     * 获取屏幕的高和宽
     */
    private void getWindowWH() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    /* (non-Javadoc)
     * @see com.itotem.tianhe.TianHeBaseActivity#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }
    public Bitmap createBitmap(String path, int w, int h) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // 这里是整个方法的关键，inJustDecodeBounds设为true时将不为图片分配内存。
            BitmapFactory.decodeFile(path, opts);
            int srcWidth = opts.outWidth;// 获取图片的原始宽度
            int srcHeight = opts.outHeight;// 获取图片原始高度
            int destWidth = 0;
            int destHeight = 0;
            // 缩放的比例
            double ratio = 0.0;
            if (srcWidth < w || srcHeight < h) {
                ratio = 0.0;
                destWidth = srcWidth;
                destHeight = srcHeight;
            } else if (srcWidth > srcHeight) {// 按比例计算缩放后的图片大小，maxLength是长或宽允许的最大长度
                ratio = (double) srcWidth / w;
                destWidth = w;
                destHeight = (int) (srcHeight / ratio);
            } else {
                ratio = (double) srcHeight / h;
                destHeight = h;
                destWidth = (int) (srcWidth / ratio);
            }
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放的比例，缩放是很难按准备的比例进行缩放的，目前我只发现只能通过inSampleSize来进行缩放，其值表明缩放的倍数，SDK中建议其值是2的指数值
            newOpts.inSampleSize = (int) ratio + 1;
            // inJustDecodeBounds设为false表示把图片读进内存中
            newOpts.inJustDecodeBounds = false;
            // 设置大小，这个一般是不准确的，是以inSampleSize的为准，但是如果不设置却不能缩放
            newOpts.outHeight = destHeight;
            newOpts.outWidth = destWidth;
            // 获取缩放后图片
            return BitmapFactory.decodeFile(path, newOpts);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    /**
     * 保存图片到SD卡
     * 
     * @param imagePath
     * @param buffer
     * @throws IOException
     */
    public static void saveImage(String imagePath, byte[] buffer)
            throws IOException {
        File f = new File(imagePath);
        File parentFile = f.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(imagePath);
        fos.write(buffer);
        fos.flush();
        fos.close();
    }

    /**
     * Bitmap转换到Byte[]
     * 
     * @param bm
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bm) throws Exception {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bas);
        return bas.toByteArray();
    }

    
     /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                this.finish();
                break;
            case R.id.btn_sure:
                Bitmap croppedImage = mCropImage.getCroppedImage();
                String path =PATH+110 + ".jpg";
                try {
                    saveImage(path, bitmap2Bytes(croppedImage));
                    Intent intent = new Intent();
                    intent.putExtra("path", path);
                    setResult(RESULT_OK, intent);
                    this.finish();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            default:
                break;
            }
    }

	@Override
	protected int getContentViewResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_cropper;
	}

}
