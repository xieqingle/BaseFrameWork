package com.cesecsh.baseframelibrary.ui.carouse.hintView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;
import com.cesecsh.baseframelibrary.ui.viewUtils.DrawableUtils;


/**
 * BaseFramework
 * Created by RockQ on 2017/3/7.
 */

public class IconHintView extends ShapeHintView {
    @DrawableRes
    private int mNormalRes;
    @DrawableRes
    private int mFocusRes;
    private int mSize;

    public IconHintView(Context context, @DrawableRes int focusResId, @DrawableRes int normalResId) {
        this(context, focusResId, normalResId, DensityUtils.dp2px(context, 64));
    }

    public IconHintView(Context context, int focusResId, int normalResId, int size) {
        super(context);
        this.mFocusRes = focusResId;
        this.mNormalRes = normalResId;
        this.mSize = size;
    }


    @Override
    public Drawable makeFocusDrawable() {
        Drawable drawable = getContext().getResources().getDrawable(mFocusRes);
        if (mSize > 0) {
            drawable = DrawableUtils.zoomDrawable(drawable, mSize, mSize);
        }
        return drawable;
    }

    @Override
    public Drawable makeNormalDrawable() {
        Drawable drawable = getContext().getResources().getDrawable(mNormalRes);
        if (mSize > 0) {
            drawable = DrawableUtils.zoomDrawable(drawable, mSize, mSize);
        }
        return drawable;
    }
}
