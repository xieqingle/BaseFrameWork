package com.cesecsh.baseframelibrary.ui.widget.carouse.hintView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;

/**
 * BaseFramework
 * 设置颜色用于作为 indicator
 * Created by RockQ on 2017/3/7.
 */

public class ColorPointHintView extends ShapeHintView {
    private int focusColor;
    private int normalColor;

    public ColorPointHintView(Context context, int focusColor, int normalColor) {
        super(context);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
    }


    @Override
    public Drawable makeFocusDrawable() {
        GradientDrawable dotFocusDrawable = new GradientDrawable();
        dotFocusDrawable.setColor(focusColor);
        dotFocusDrawable.setCornerRadius(DensityUtils.dp2px(getContext(), 7));
        dotFocusDrawable.setSize(DensityUtils.dp2px(getContext(), 14), DensityUtils.dp2px(getContext(), 14));
        return dotFocusDrawable;
    }

    @Override
    public Drawable makeNormalDrawable() {
        GradientDrawable dotNormalDrawable = new GradientDrawable();
        dotNormalDrawable.setColor(normalColor);
        dotNormalDrawable.setCornerRadius(DensityUtils.dp2px(getContext(), 7));
        dotNormalDrawable.setSize(DensityUtils.dp2px(getContext(), 14), DensityUtils.dp2px(getContext(), 14));
        return dotNormalDrawable;
    }
}
