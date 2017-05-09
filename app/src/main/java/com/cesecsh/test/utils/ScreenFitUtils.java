package com.cesecsh.test.utils;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;
import com.cesecsh.baseframelibrary.ui.viewUtils.ResourceUtils;

/**
 * 类名 ScreenFitUtils
 * 创建者：RockQ
 * 实现的主要功能： 适用所有的屏幕适配，利用px设置后，获取相对应的值，再根据百分比进行转换
 * <p>
 * 注：所有的布局的参数都是px单位
 * 创建日期：  2017/5/9
 */

public class ScreenFitUtils {
    /**
     * 自动匹配activity
     *
     * @param activity
     */
    public static void auto(Activity activity) {
        if (activity == null)
            return;
        View view = activity.getWindow().getDecorView();
        auto(view);
    }

    /**
     * 自动匹配 view
     *
     * @param view
     */
    public static void auto(View view) {
        if (view == null)
            return;
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view);
        if (view instanceof ViewGroup)
            auto((ViewGroup) view);
    }

    /**
     * 自动匹配viewGroup
     * 遍历viewGroup并且对其子view进行适配
     *
     * @param viewGroup
     */
    public static void auto(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view != null)
                auto(view);
        }
    }

    /**
     * 适配view的宽高
     *
     * @param view
     */
    public static void autoSize(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null)
            return;
        if (lp.width > 0)
            lp.width = px2percentPx(lp.width);
        if (lp.height > 0)
            lp.height = px2percentPx(lp.height);
    }

    /**
     * 适配view的padding
     *
     * @param view
     */
    public static void autoPadding(View view) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = px2percentPx(l);
        t = px2percentPx(t);
        r = px2percentPx(r);
        b = px2percentPx(b);

        view.setPadding(l, t, r, b);
    }

    /**
     * 适配view的margin
     *
     * @param view
     */
    public static void autoMargin(View view) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp == null)
            return;
        lp.leftMargin = px2percentPx(lp.leftMargin);
        lp.topMargin = px2percentPx(lp.topMargin);
        lp.rightMargin = px2percentPx(lp.rightMargin);
        lp.bottomMargin = px2percentPx(lp.bottomMargin);
    }

    /**
     * 适配字体大小
     *
     * @param view
     */
    public static void autoTextSize(View view) {
        if (!(view instanceof TextView))
            return;
        TextView textView = (TextView) view;
        double designPixels = textView.getTextSize();
//        double  display =
        textView.setIncludeFontPadding(false);// 设置TextView不包含额外的顶部和底部填充，完全按照设计来
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, px2percentSp((int) designPixels));

    }


    /**
     * 将获取的px根据屏幕的百分比进行适配
     *
     * @param displayValue
     * @return
     */
    public static int px2percentPx(int displayValue) {
        return DensityUtils.dp2px(ResourceUtils.getContext(), displayValue);
    }

    /**
     * 将获取的px根据屏幕的百分比进行适配
     *
     * @param displayValue
     * @return
     */
    public static int px2percentSp(int displayValue) {
        return DensityUtils.sp2px(ResourceUtils.getContext(), displayValue);
    }
}
