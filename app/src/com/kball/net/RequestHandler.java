package com.kball.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.kball.util.ToastAlone;

/**
 * 请求返回管理
 * <p>
 * lizheng -- 15/1/11
 * <p>
 * weici
 */
public abstract class RequestHandler {
    private boolean isUseDefaultToastShowMsg;

    public RequestHandler() {
        isUseDefaultToastShowMsg = true;
    }

    /**
     * @param isUseDefaultToastShowMsg true 显示默认吐司
     */
    public RequestHandler(boolean isUseDefaultToastShowMsg) {
        this.isUseDefaultToastShowMsg = isUseDefaultToastShowMsg;
    }

    public abstract void onStart();

    public abstract void onSuccess(String response);

    public void onSuccess_(String response) {
        if (TextUtils.isEmpty(response)) {
            onFailure("数据返回为空", null);
            return;
        }

        JsonObject jsonObject = null;
        try {
            jsonObject = new Gson().fromJson(response, JsonObject.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        if (null == jsonObject) {
            onFailure("数据无法解析", null);
            return;
        }

        onSuccess(response);
    }


    public void onFailure(String responseBody, Throwable error) {
        if (isUseDefaultToastShowMsg) {
            if (null != error) {
//                ToastAlone.show("很抱歉！出问题了\n" + responseBody + "\n" + error.toString());
                ToastAlone.show("网络返回发生错误！");
            } else {
//                ToastAlone.show("很抱歉！出问题了\n" + responseBody);
                ToastAlone.show("网络返回发生错误！");
            }
        }
    }

    public abstract void onFinish();

    public void onProgress(long bytesWritten, long totalSize) {
    }

    public void customCacheData(String responseData) {
    }

    public void onCancel() {
    }

}
