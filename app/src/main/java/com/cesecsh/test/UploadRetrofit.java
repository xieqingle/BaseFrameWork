package com.cesecsh.test;

import com.cesecsh.baseframelibrary.net.base.BaseOkHttpClient;
import com.cesecsh.baseframelibrary.net.base.BaseRetrofit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * BaseFramework
 * Created by RockQ on 2017/3/8.
 */

public class UploadRetrofit extends BaseRetrofit {
    @Override
    public OkHttpClient getHttpClient() {
        return new ICSHTTPClient().get();
    }

    private class ICSHTTPClient extends BaseOkHttpClient {

        @Override
        public OkHttpClient.Builder customize(final OkHttpClient.Builder builder) {
            builder.addInterceptor(chain -> {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(request);
            });
            return builder;
        }
    }
}
