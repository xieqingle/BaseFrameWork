package com.cesecsh.test;

import android.app.Application;

import com.cesecsh.baseframelibrary.FrameApplication;

/**
 * 类名
 * 创建者：RockQ
 * 实现的主要功能：
 * 创建日期：  2017/5/9
 */

public class BaseFrameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FrameApplication.init(this);
    }
}
