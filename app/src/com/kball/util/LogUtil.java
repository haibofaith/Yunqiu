package com.kball.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class LogUtil {
    // 默认不打印log
    private static boolean mIsShowLog = true;
    // 默认TAG = motie
    private static String TAG = "weici";

    public static String makeLogTag(Class cls) {
        return cls.getSimpleName();
    }

    public static void init(boolean isShowLog,String defaultTag) {
        mIsShowLog = isShowLog;
        TAG = TAG;
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {

        if (mIsShowLog){
        	msg=formNull(msg);
            Log.v(tag, msg);
        }
    }

    public static void v(Object tag, String msg) {
        v(tag.getClass().getName(), msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {

        if (mIsShowLog){
        	msg=formNull(msg);
            Log.d(tag, msg);
        }
    }

    public static void d(Object tag, String msg) {
        d(tag.getClass().getName(), msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (mIsShowLog){
        	msg=formNull(msg);
            Log.i(tag, msg);
        }
    }

    public static void i(Object tag, String msg) {
        i(tag.getClass().getName(), msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (mIsShowLog){
        	msg=formNull(msg);
            Log.w(tag, msg);
        }
    }

    public static void w(Object tag, String msg) {
        w(tag.getClass().getName(), msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (mIsShowLog){
        	msg=formNull(msg);
        	 Log.e(tag, msg);
        }
           
    }

    public static void e(Object tag, String msg) {
    		e(tag.getClass().getName(), msg);
        
    }

    /**
     * 把一段文字信息保存为文件存储在SD卡上<br>
     * 默认和Log是否显示共享标记位
     *
     * @param dirName    文件夹路径<br>
     *                   默认SD卡根目录<br>
     * @param fileName   文件名字<br>
     *                   默认(log_当前时间.log)
     * @param msg        需要保存的信息
     * @param showStatic 是否保存文件标记
     */
    public static void msg2File(String dirName, String fileName, String msg,
                                ShowStatic showStatic) {

        if (TextUtils.isEmpty(dirName)) {
            dirName = "";
        }
        if (TextUtils.isEmpty(fileName)) {
            fileName = "log_" + System.currentTimeMillis() + ".log";
        }
        if (null == msg) {
            msg = "";
        }

        switch (showStatic) {
            case DEFAULT:
                if (mIsShowLog)
                    saveFile(dirName, fileName, msg);
                break;
            case SAVE:
                saveFile(dirName, fileName, msg);
                break;
            case CANCEL:
                break;
            default:
                break;
        }
    }

    private static void saveFile(String dirName, String fileName, String msg) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();

            if (!TextUtils.isEmpty(dirName)) {
                if (!new File(sdCardDir, dirName).exists()) {
                    new File(sdCardDir, dirName).mkdirs();
                }
            }
            File saveFile = new File(sdCardDir, dirName + "/" + fileName);
            try {
                FileOutputStream outStream = new FileOutputStream(saveFile);
                outStream.write(msg.getBytes());
                outStream.flush();
                outStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            e("信息保存至SD卡：SD不存在或者不可读写");
            try {
                throw new Exception("信息保存至SD卡：SD不存在或者不可读写");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum ShowStatic {
        /**
         * 和log打印共享
         */
        DEFAULT,
        /**
         * 始终保存
         */
        SAVE,
        /**
         * 不保存
         */
        CANCEL
    }
    //对null值进行替换
    private static String formNull(String value){
    	return value==null ?"null":value;
    }
}
