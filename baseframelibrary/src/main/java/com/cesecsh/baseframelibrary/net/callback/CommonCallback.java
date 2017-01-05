package com.cesecsh.baseframelibrary.net.callback;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public interface CommonCallback<T> {
    void onSuccess(T t);

    void onError(Throwable throwable);

    void onStart();

    void onComplete();


}
