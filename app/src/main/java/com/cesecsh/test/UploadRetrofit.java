package com.cesecsh.test;

import com.cesecsh.baseframelibrary.net.base.BaseOkHttpClient;
import com.cesecsh.baseframelibrary.net.base.BaseRetrofit;
import com.cesecsh.test.utils.NetworkUtils;
import com.cesecsh.test.utils.PhoneUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
                        .addHeader("ip", PhoneUtils.getPhoneType())
                        .addHeader("clientKey", NetworkUtils.getIpAddress())
                        .addHeader("token","eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE3MTk1ODY5ODYyIiwiY2xpZW50X2tleSI6InVua25vd25bYW5kcm9pZCA3LjEuMV0iLCJjbGllbnRfdXNlcl9pZCI6IjQwMjg4MDdiNTk5YWNhMGMwMTU5OWFkYTM5MzkwMDJhIiwiZXh0IjoxNDkxOTYyOTk3MTEyfQ.TTjAuNbkJ-UbPIKisPpFKprJ29Jb80x_LbJrQ6wa__w")
                        .build();
                return chain.proceed(request);
            });
            return builder;
        }
    }
}
