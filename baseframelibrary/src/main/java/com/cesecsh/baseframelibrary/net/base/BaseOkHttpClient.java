package com.cesecsh.baseframelibrary.net.base;


import com.cesecsh.baseframelibrary.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 上海中电
 * on 2016/12/29
 */

public abstract class BaseOkHttpClient {
    private static final long TIMEOUT_CONNECT = 30 * 1000;
    // HttpLoggingInterceptor.Level.BASIC   -->  请求/相应行
    // HttpLoggingInterceptor.Level.HEADER  -->  请求/响应行 + 头
    // HttpLoggingInterceptor.Level.BODY    -->  请求/响应行 + 头 + 体
    // HttpLoggingInterceptor.Level.NONE    -->  不打印

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(BuildConfig.DEBUG ?
                                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));

        builder = customize(builder);

        return builder.build();
    }

    public abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);
}
