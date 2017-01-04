package com.cesecsh.baseframelibrary.ui.viewUtils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：谢青仂 on 2016/8/15
 * 邮箱：qingle6616@sina.com
 * <p/>
 * 屏幕尺寸的相关utils
 */

public class DensityUtils {
    private DensityUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    public static float getDipScale(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float displayWidth = metrics.widthPixels;
        return displayWidth / 750;
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
        if (dpValue > 0) {
            float scale = getDipScale(context);
            return (int) (dpValue * scale + 0.5f);
        }
        return (int) (dpValue + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dp(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
        if (pxValue > 0) {
            float scale = getDipScale(context);
            return (int) (pxValue / scale + 0.5f);
        }
        return (int) (pxValue + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spValue) {
//        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (spValue * fontScale + 0.5f);
        if (spValue > 0) {
            float scale = getDipScale(context);
            return (int) (spValue * scale + 0.5f);
        }
        return (int) (spValue + 0.5f);

    }

    /**
     * px转sp
     */
    public static int px2sp(Context context, float pxValue) {
//        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (pxValue / fontScale + 0.5f);
        if (pxValue > 0) {
            float scale = getDipScale(context);
            return (int) (pxValue / scale + 0.5f);
        }
        return (int) (pxValue + 0.5f);
    }

    /**
     * 各种单位转换
     * <p>该方法存在于TypedValue
     */
    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }

    /**
     * 在onCreate()即可强行获取View的尺寸
     * <p>需回调onGetSizeListener接口，在onGetSize中获取view宽高
     * 用法示例如下所示
     * DensityUtils.forceGetViewSize(view);
     * DensityUtils.setListener(new DensityUtils.onGetSizeListener() {
     *
     * @Override public void onGetSize(View view) {
     * Log.d("tag", view.getWidth() + " " + view.getHeight());
     * }
     * });
     */
    public static void forceGetViewSize(final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onGetSize(view);
                }
            }
        });
    }

    /**
     * 获取到View尺寸的监听
     */
    public interface onGetSizeListener {
        void onGetSize(View view);
    }

    public static void setListener(onGetSizeListener listener) {
        mListener = listener;
    }

    private static onGetSizeListener mListener;

    /**
     * ListView中提前测量View尺寸，如headerView
     * <p>用的时候去掉注释拷贝到ListView中即可
     * <p>参照以下注释代码
     */
    public static void measureViewInLV(View view) {
        Log.i("tips", "U should copy the following code.");
        /*
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int height;
        int tempHeight = p.height;
        if (tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight,
                    MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
        */
    }
}
