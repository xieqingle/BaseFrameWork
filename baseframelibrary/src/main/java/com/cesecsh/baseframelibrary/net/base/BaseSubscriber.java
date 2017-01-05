package com.cesecsh.baseframelibrary.net.base;

import com.cesecsh.baseframelibrary.LogHelper.AppLog;

import rx.Subscriber;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        AppLog.d("access web complete");
    }

    @Override
    public void onError(Throwable e) {
        AppLog.d("access web error");
    }

    @Override
    public void onNext(T t) {
        AppLog.d("access web success");
    }
}
