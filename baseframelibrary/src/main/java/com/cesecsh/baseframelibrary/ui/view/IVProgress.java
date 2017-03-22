package com.cesecsh.baseframelibrary.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by RockQ on 2017/3/22
 * 用来显示图片上传进度
 */

@SuppressLint("AppCompatCustomView")
public class IVProgress extends ImageView {
    private int progress;

    public IVProgress(Context context) {
        super(context);
    }

    public IVProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IVProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final Paint mPaint = new Paint();
    private final Rect rect = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.parseColor("#30000000"));//半透明
        canvas.drawRect(0, 0, getWidth(), getHeight() - getHeight() * progress / 100, mPaint);

        mPaint.setColor(Color.parseColor("#00000000"));//全透明
        canvas.drawRect(0, getHeight() - getHeight() * progress / 100, getWidth(), getHeight(), mPaint);

        mPaint.setTextSize(30);
        mPaint.setColor(Color.parseColor("#FFFFFF"));
        mPaint.setStrokeWidth(2);

        mPaint.getTextBounds("100%", 0, "100%".length(), rect);//确定文字的宽度
        canvas.drawText(progress + "%", getWidth() / 2 - rect.width() / 2, getHeight() / 2, mPaint);
    }

    public void setProgress(int progress) {
        if (progress < 0)
            progress = 0;
        if (progress > 100)
            progress = 100;
        this.progress = progress;
        postInvalidate();
    }
}
