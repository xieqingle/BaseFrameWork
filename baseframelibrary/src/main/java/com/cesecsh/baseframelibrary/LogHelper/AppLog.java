package com.cesecsh.baseframelibrary.LogHelper;

import com.orhanobut.logger.Logger;

/**
 * Created by 上海中电
 * on 2017/1/4
 */

public class AppLog {
    public static final String TAG = "XQL_LOGGER";
    private static boolean isShow;

    public static void isShow(boolean isShow) {
        Logger.init(TAG);
        AppLog.isShow = isShow;
    }

    /**
     * log.i
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isShow) {
            Logger.i(msg);
        }
    }

    /**
     * log.d
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isShow) {
            Logger.d(msg);
        }
    }

    /**
     * log.w
     *
     * @param msg
     */
    public static void w(String msg) {
        if (isShow) {
            Logger.w(msg);
        }
    }

    /**
     * log.e
     *
     * @param msg
     */
    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(Throwable e) {
        Logger.e(e, "");
    }

    /**
     * log.json
     */
    public static void json(String json) {
        if (isShow) {
            Logger.json(json);
        }
    }
}
