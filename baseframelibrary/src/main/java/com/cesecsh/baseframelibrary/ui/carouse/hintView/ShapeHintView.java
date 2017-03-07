package com.cesecsh.baseframelibrary.ui.carouse.hintView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cesecsh.baseframelibrary.ui.carouse.HintView;
import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;

/**
 * BaseFramework
 * Created by RockQ on 2017/3/7.
 */

public abstract class ShapeHintView extends LinearLayout implements HintView {
    private ImageView[] mDots;
    private int length;
    private int lastPosition;
    private Drawable dotNormal;
    private Drawable dotFocus;

    public ShapeHintView(Context context) {
        super(context);
    }

    public ShapeHintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeHintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setCurrent(int current) {
        if (current < 0 || current > length - 1)
            return;
        mDots[lastPosition].setBackgroundDrawable(dotNormal);
        mDots[current].setBackgroundDrawable(dotFocus);
        lastPosition = current;
    }

    @Override
    public void initView(int length, int gravity) {
        removeAllViews();
        lastPosition = 0;
        setOrientation(HORIZONTAL);
        setGravity(gravity);
        this.length = length;
        mDots = new ImageView[length];
        dotNormal = makeNormalDrawable();
        dotFocus = makeFocusDrawable();
        for (int i = 0; i < length; i++) {
            mDots[i] = new ImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(DensityUtils.dp2px(getContext(), 5), 0, DensityUtils.dp2px(getContext(), 5), 0);
            mDots[i].setLayoutParams(layoutParams);
            mDots[i].setBackgroundDrawable(dotNormal);
            addView(mDots[i]);
        }
        setCurrent(0);

    }

    public abstract Drawable makeFocusDrawable();

    public abstract Drawable makeNormalDrawable();
}
