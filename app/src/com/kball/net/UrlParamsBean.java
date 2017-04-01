package com.kball.net;

import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * 地址和参数的封装
 */
public class UrlParamsBean {
    public String url;
    public RequestParams params;
    public Map<String, String> signParams;


    /**
     * @param url    接口地址
     * @param params 参数列表
     */
    private UrlParamsBean(String url, RequestParams params) {
        this.url = url;
        this.params = params;
    }

    /**
     * @param url 接口、默认没有参数
     */
    public UrlParamsBean(String url) {
        this.url = url;
        this.params = new RequestParams();
    }

    /**
     * @param url        接口
     * @param signParams post参数
     */
    public UrlParamsBean(String url, Map<String, String> signParams) {
        this.url = url;
        this.signParams = signParams;
        this.params = new RequestParams(signParams);
    }

    public void setFileParam(String key, File file) {
        if (null != params) {
            try {
                params.put(key, file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void setStringParam(String key,String value){
    	params.put(key, value);
    }

    public void setInputStreamParam(String key, InputStream is) {
        if (null != params) {
            params.put(key, is);
        }
    }
}