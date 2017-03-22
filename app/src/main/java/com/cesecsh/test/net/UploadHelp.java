package com.cesecsh.test.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.cesecsh.baseframelibrary.net.base.BaseApiService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by RockQ on 2017/3/9
 */

public class UploadHelp {
    private String BASE_URL = "";
    private IProgress iProgressListener;

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private BaseApiService api;
    private Subscription boduSubscription;
    private MediaType dataMediaType = MediaType.parse("multipart/from-data");

    private long curUploadProgress = 0;
    private int fileIndex = 1;

    private final int HANDLER_CODE = 0X66;
    private final int HANDLER_DELAY = 100;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (iProgressListener != null && msg.what == HANDLER_CODE) {
                iProgressListener.onProgress(msg.arg1, msg.arg2, fileIndex, msg.arg1 >= msg.arg2);
            }
        }
    };
    private final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable) o)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public UploadHelp() {
    }

    private void initClient(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(BaseApiService.class);
    }

    public void upload(final FileUploadEntity entity, final IProgress callback) {
        try {
            URL url = url = new URL(entity.getUrl());
            Observable.just(url)
                    .map(s -> filterUpload(s, callback))
                    .compose(transformer)
                    .onBackpressureBuffer();
        } catch (MalformedURLException e) {
            callback.onFailed(e, e.getMessage());
            e.printStackTrace();
        }
    }

    private void justUpload(FileUploadEntity entity, IProgress callback) {
    }

    private Integer filterUpload(URL url, IProgress callback) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            return conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailed(e, e.getMessage());
            return 404;
        }
    }
}
