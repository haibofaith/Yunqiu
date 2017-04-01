package com.kball.util;

import android.util.Log;

import com.kball.C;
import com.upyun.library.common.Params;
import com.upyun.library.common.UploadEngine;
import com.upyun.library.listener.UpCompleteListener;
import com.upyun.library.listener.UpProgressListener;
import com.upyun.library.utils.UpYunUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/18.
 * 使用方式：UpYunUtil.upYunImg(本地路径)
 */

public class UpYunUtil {
    //又拍云
    public static void upYunImg(File temp){
        //进度回调，可为空
        UpProgressListener progressListener = new UpProgressListener() {
            @Override
            public void onRequestProgress(final long bytesWrite, final long contentLength) {
                Log.e("UpYunUtil", (100 * bytesWrite) / contentLength + "%");
            }
        };

        //结束回调，不可为空
        UpCompleteListener completeListener = new UpCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String result) {
                // 服务名.b0.upaiyun.com/存储路径
                // 返回参数如："url":"\/uploads\/20170318\/wn7c27i883dyxa2bnaaw3f343dfdvt4d.jpg"
                // 返回参数拼接成：http://picture-services.b0.upaiyun.com/uploads/20170318/wn7c27i883dyxa2bnaaw3f343dfdvt4d.jpg
                Log.e("UpYunUtil", isSuccess + ":" + result);
            }
        };
        final Map<String, Object> paramsMap = new HashMap<>();
        //上传空间
        paramsMap.put(Params.BUCKET, C.SP.SPACE);
        //保存路径，任选其中一个
        paramsMap.put(Params.SAVE_KEY, C.SP.SAVEPATH);

        paramsMap.put(Params.CONTENT_MD5, UpYunUtils.md5Hex(temp));
        //可选参数（详情见api文档介绍）
        paramsMap.put(Params.RETURN_URL, "httpbin.org/post");

        UploadEngine.getInstance().formUpload(temp, paramsMap, C.SP.OPERATER, UpYunUtils.md5(C.SP.PASSWORD),
                completeListener, progressListener);

    }

    //又拍云
    public static void upYunImg(File temp,UpCompleteListener completeListener){
        //进度回调，可为空
        UpProgressListener progressListener = new UpProgressListener() {
            @Override
            public void onRequestProgress(final long bytesWrite, final long contentLength) {
                Log.e("UpYunUtil", (100 * bytesWrite) / contentLength + "%");
            }
        };

//        //结束回调，不可为空
//        UpCompleteListener completeListener = new UpCompleteListener() {
//            @Override
//            public void onComplete(boolean isSuccess, String result) {
//                // 服务名.b0.upaiyun.com/存储路径
//                // 返回参数如："url":"\/uploads\/20170318\/wn7c27i883dyxa2bnaaw3f343dfdvt4d.jpg"
//                // 返回参数拼接成：http://picture-services.b0.upaiyun.com/uploads/20170318/wn7c27i883dyxa2bnaaw3f343dfdvt4d.jpg
//                Log.e("UpYunUtil", isSuccess + ":" + result);
//            }
//        };
        final Map<String, Object> paramsMap = new HashMap<>();
        //上传空间
        paramsMap.put(Params.BUCKET, C.SP.SPACE);
        //保存路径，任选其中一个
        paramsMap.put(Params.SAVE_KEY, C.SP.SAVEPATH);

        paramsMap.put(Params.CONTENT_MD5, UpYunUtils.md5Hex(temp));
        //可选参数（详情见api文档介绍）
        paramsMap.put(Params.RETURN_URL, "httpbin.org/post");

        UploadEngine.getInstance().formUpload(temp, paramsMap, C.SP.OPERATER, UpYunUtils.md5(C.SP.PASSWORD),
                completeListener, progressListener);

    }
}
