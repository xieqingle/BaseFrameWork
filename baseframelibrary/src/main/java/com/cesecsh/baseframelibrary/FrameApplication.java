package com.cesecsh.baseframelibrary;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by 上海中电
 * on 2017/1/5
 */

public class FrameApplication {
    public static int newCounts = 0;
    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThreadId;

    public static int getNewCounts() {
        return newCounts;
    }

    public static void init(Application application) {
        mContext = application.getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
    }


    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }
}
