package com.cesecsh.baseframelibrary.net;

import com.cesecsh.baseframelibrary.net.base.BaseRetrofit;
import com.cesecsh.baseframelibrary.net.callback.CommonCallback;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.OkHttpClient;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public class HttpManager {
    private BaseRetrofit retrofit;

    public <T> void executePostNormal(String url, String params, RxAppCompatActivity activity, final CommonCallback<T> callback) {
        retrofit = new BaseRetrofit() {
            @Override
            public OkHttpClient getHttpClient() {
                return null;
            }
        };

    }


}
