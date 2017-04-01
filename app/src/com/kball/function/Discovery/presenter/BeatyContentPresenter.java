package com.kball.function.Discovery.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.impl.BeatyContentView;
import com.kball.function.Discovery.impl.DisMatchImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/27.
 */

public class BeatyContentPresenter {
    private BeatyContentView disMatch;

    public BeatyContentPresenter(BeatyContentView disMatch) {
        this.disMatch = disMatch;
    }

}
