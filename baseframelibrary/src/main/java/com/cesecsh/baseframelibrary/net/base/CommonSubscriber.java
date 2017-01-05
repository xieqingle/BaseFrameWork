package com.cesecsh.baseframelibrary.net.base;

import okhttp3.ResponseBody;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public class CommonSubscriber extends BaseSubscriber<ResponseBody> {
    @Override
    public void onCompleted() {
        super.onCompleted();

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        super.onNext(responseBody);
    }
}
