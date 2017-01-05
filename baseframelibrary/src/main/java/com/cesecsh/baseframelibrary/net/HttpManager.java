package com.cesecsh.baseframelibrary.net;

import com.cesecsh.baseframelibrary.net.base.BaseRetrofit;
import com.cesecsh.baseframelibrary.net.base.CommonApiService;
import com.cesecsh.baseframelibrary.net.callback.CommonCallback;
import com.cesecsh.baseframelibrary.net.response.NormalResponse;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public class HttpManager {
    private BaseRetrofit retrofit;

    public<T> void executePostNormal(String url, String params, RxAppCompatActivity activity, final CommonCallback<T> callback) {
        Subscription subscribe = retrofit.getICSAPiService()
                .create(CommonApiService.class)
                .executePostNormal(url, params)
                .subscribeOn(Schedulers.io())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // todo something show dialog
                        callback.onStart();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        // todo something dismiss dialog
                        callback.onComplete();
                    }
                })
                .subscribe(new NormalResponse<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onSuccess(T t) {

                    }
                });
    }


}
