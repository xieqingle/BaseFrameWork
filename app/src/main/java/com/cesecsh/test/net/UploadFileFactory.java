package com.cesecsh.test.net;

import android.icu.util.ChineseCalendar;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RockQ on 2017/3/8
 */

public class UploadFileFactory implements Interceptor {
    private static AtomicReference<UploadFileFactory> afFactory = new AtomicReference<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "multipart/from-data")
                .build();
        return chain.proceed(request);
    }

    public static UploadFileFactory create() {
        for (; ; ) {
            UploadFileFactory uploadFileFactory = afFactory.get();
            if (uploadFileFactory != null)
                return uploadFileFactory;
            uploadFileFactory = new UploadFileFactory();
            if (afFactory.compareAndSet(null, uploadFileFactory))
                return uploadFileFactory;
        }
    }
}
