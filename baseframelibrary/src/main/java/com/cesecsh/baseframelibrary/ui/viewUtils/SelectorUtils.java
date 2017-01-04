package com.cesecsh.baseframelibrary.ui.viewUtils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * 1、代码构建Selector以便设置字体、图片选择器
 * 2、手动绘制空心形状选择器
 */
public class SelectorUtils {

    /**
     * 初始化空心的形状选择器
     * ra:表示圆角的弧度
     * <p/>
     * isRoundRect:是否圆角矩形
     * false:矩形
     * true:圆角矩形
     * <p/>
     * lineWidth 线的粗细
     */
    public static ShapeDrawable initStrokeShapeSelector(Context context, int lineWidth, int color, boolean isRoundRect, int ra) {
        float[] outerRadii;
        float[] innerRadii;

        // 是否圆角
        if (isRoundRect) {
            int radian = DensityUtils.dp2px(context, ra);
            outerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
            innerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
        } else {
            outerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
            innerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }

        RectF inset = new RectF(lineWidth, lineWidth, lineWidth, lineWidth);

        RoundRectShape rrs = new RoundRectShape(outerRadii, inset, innerRadii);
        ShapeDrawable drawable = new ShapeDrawable(rrs);
        drawable.getPaint().setColor(color);

        return drawable;
    }

    public static ShapeDrawable initStrokeShapeSelector(Context context, int lineWidth, int color, boolean isRoundRect) {
        float[] outerRadii;
        float[] innerRadii;

        // 是否圆角
        if (isRoundRect) {
            int radian = DensityUtils.dp2px(context, 5);
            outerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
            innerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
        } else {
            outerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
            innerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }

        RectF inset = new RectF(lineWidth, lineWidth, lineWidth, lineWidth);

        RoundRectShape rrs = new RoundRectShape(outerRadii, inset, innerRadii);
        ShapeDrawable drawable = new ShapeDrawable(rrs);
        drawable.getPaint().setColor(color);

        return drawable;
    }

    /**
     * 初始化实心的形状选择器
     * <p/>
     * isRoundRect:是否圆角矩形
     * false:矩形
     * true:圆角矩形
     */
    public static ShapeDrawable initSolidShapeSelector(Context context, int color, boolean isRoundRect) {
        float[] outerRadii;
        float[] innerRadii;

        // 是否圆角
        if (isRoundRect) {
            int radian = DensityUtils.dp2px(context, 5);
            outerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
            innerRadii = new float[]{radian, radian, radian, radian, radian, radian, radian, radian};
        } else {
            outerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
            innerRadii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }

        RoundRectShape rrs = new RoundRectShape(outerRadii, null, innerRadii);
        ShapeDrawable drawable = new ShapeDrawable(rrs);
        drawable.getPaint().setColor(color);

        return drawable;
    }

    /**
     * 初始化按钮选择器
     */
    public static StateListDrawable initStatePressedSelector(int normalColor, int pressedColor) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pressedColor));
        sld.addState(new int[]{}, new ColorDrawable(normalColor));
        return sld;
    }

    /**
     * 初始化按钮选择器
     */
    public static StateListDrawable initStatePressedSelector(Drawable normalDrawable, Drawable pressedDrawable) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        sld.addState(new int[]{}, normalDrawable);
        return sld;
    }

    /**
     * 初始化单选选择器
     */
    public static StateListDrawable initStateSelectedSelector(Drawable normalDrawable, Drawable selectedDrawable) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
        sld.addState(new int[]{}, normalDrawable);
        return sld;
    }

    /**
     * 初始化焦点选择器
     */
    public static StateListDrawable initStateFocusedSelector(Drawable normalDrawable, Drawable focusedDrawable) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_focused}, focusedDrawable);
        sld.addState(new int[]{}, normalDrawable);
        return sld;
    }

    /**
     * 初始化单选选择器
     */
    public static StateListDrawable initStateCheckedSelector(Drawable normalDrawable, Drawable checkedDrawable) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_checked}, checkedDrawable);
        sld.addState(new int[]{}, normalDrawable);
        return sld;
    }

    /**
     * 初始化颜色选择器
     */
    public static ColorStateList initColorSelectedSelector(int normalColor, int selectedColor) {
        ColorStateList csl = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{}}, new int[]{selectedColor, normalColor});
        return csl;
    }

    /**
     * 初始化颜色选择器
     */
    public static ColorStateList initColorCheckedSelector(int normalColor, int selectedColor) {
        ColorStateList csl = new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}}, new int[]{selectedColor, normalColor});
        return csl;
    }

    /**
     * 初始化实心圆形选择器
     *
     * @param startAngle 开始角度
     * @param sweepAngle 结束角度
     * @param color      颜色
     */
    public static ShapeDrawable initSolidArcShapeSelector(float startAngle, float sweepAngle, int color) {
        ArcShape arcShape = new ArcShape(startAngle, sweepAngle);
        ShapeDrawable drawable = new ShapeDrawable(arcShape);
        drawable.getPaint().setColor(color);
        drawable.getPaint().setStyle(Paint.Style.FILL);
        return drawable;
    }

}
