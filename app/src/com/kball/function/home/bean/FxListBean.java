package com.kball.function.home.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/21.
 */

public class FxListBean implements Serializable {
    private ArrayList<BannerBean> banner;

    public ArrayList<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(ArrayList<BannerBean> banner) {
        this.banner = banner;
    }
}
