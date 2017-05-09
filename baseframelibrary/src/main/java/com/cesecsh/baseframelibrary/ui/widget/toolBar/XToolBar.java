package com.cesecsh.baseframelibrary.ui.widget.toolBar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cesecsh.baseframelibrary.R;
import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;
import com.cesecsh.baseframelibrary.ui.viewUtils.ViewUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * XToolBar
 * create by RockQ
 * 主要功能：完成大部分的标题栏的布局，以便后续使用
 * 创建时间：2017/5/2
 */
public class XToolBar extends RelativeLayout {

    private Context mContext;

    /**
     * 左边图片
     */
    private ImageView mLeftImageView;

    /**
     * 左边文本
     */
    private TextView mLeftTextView;

    /**
     * 中间文本
     */
    private TextView mMiddleTextView;
    /**
     * 中间编辑框
     */
    private EditText mMiddleEditText;

    /**
     * 右边图片
     */
    private ImageView mRightImageView;

    /**
     * 右边文本
     */
    private TextView mRightTextView;
    /**
     * 右边复选框
     */
    private CheckBox mRightCheckBox;

    /**
     * 左边图片宽
     */
    private int mLeftImageWidth;

    /**
     * 左边图片高
     */
    private int mLeftImageHeight;

    /**
     * 左边文本宽
     */
    private int mLeftTextWidth;

    /**
     * 左边文本高
     */
    private int mLeftTextHeight;

    /**
     * 左边文本大小
     */
    private int mLeftTextSize;

    /**
     * 左边文本颜色
     */
    private int mLeftTextColor;

    /**
     * 左边文本Gravity
     */
    private int mLeftTextGravity;

    /**
     * 左边view的padding
     */
    private int[] mLeftViewPadding;

    /**
     * 左边文本margin
     */
    private int[] mLeftViewMargin;

    /**
     * 中间文本宽
     */
    private int mMiddleTextWidth;

    /**
     * 中间文本高
     */
    private int mMiddleTextHeight;

    /**
     * 中间文本大小
     */
    private int mMiddleTextSize;

    /**
     * 中间文本颜色
     */
    private int mMiddleTextColor;

    /**
     * 中间文本padding
     */
    private int[] mMiddleTextPadding;

    /**
     * 中间文本margin
     */
    private int[] mMiddleTextMargin;

    /**
     * 中间文本Gravity
     */
    private int mMiddleTextGravity = Gravity.CENTER;
    /**
     * 中间文本字数限制
     */
    private int middleTextMaxSize;
    // editText
    /**
     * 中间编辑框 hint
     */
    private String mMiddleEditHint;
    /**
     * 中间编辑框 text
     */
    private String mMiddleEditTextText;
    /**
     * 中间编辑框的背景
     */
    private int mMiddleEditBackground;

    /**
     * 中间编辑框的宽
     */
    private int mMiddleEditWidth;
    /**
     * 中间编辑框的高
     */
    private int mMiddleEditHeight;
    /**
     * 中间编辑框的文本字体大小
     */
    private int mMiddleEditTextSize;
    /**
     * 中间编辑框字体颜色
     */
    private int mMiddleEditTextColor;
    /**
     * 中间编辑框 padding
     */
    private int[] mMiddleEditPadding;
    /**
     * 中间编辑框 margin
     */
    private int[] mMiddleEditMargin;
    /**
     * 中间编辑框 gravity
     */
    private int mMiddleEditGravity = Gravity.CENTER;

    /**
     * 右边是checkBox
     */
    private int rightViewCheckBoxBackground;

    /**
     * 右边图片宽
     */
    private int mRightImageWidth;

    /**
     * 右边图片高
     */
    private int mRightImageHeight;
    /**
     * 右边复选框宽
     */
    private int mRightCheckBoxWidth;
    /**
     * 右边复选框高
     */
    private int mRightCheckBoxHeight;
    /**
     * 右边复选框padding
     */
    private int[] mRightCheckBoxPadding;
    /**
     * 右边复选框margin
     */
    private int[] mRightCheckBoxMargin;

    /**
     * 右边文本宽
     */
    private int mRightTextWidth;

    /**
     * 右边文本高
     */
    private int mRightTextHeight;

    /**
     * 右边文本大小
     */
    private int mRightTextSize;

    /**
     * 右边文本颜色
     */
    private int mRightTextColor;

    /**
     * 右边文本padding
     */
    private int[] rightViewPadding;

    /**
     * 右边文本margin
     */
    private int[] mRightViewMargin;

    /**
     * 右边文本Gravity
     */
    private int mRightTextGravity;

    /**
     * LayoutParams
     */
    private AbsListView.LayoutParams mParams;

    /**
     * 左边的LayoutParams
     */
    private LayoutParams mLeftParams;

    /**
     * 中间的LayoutParams
     */
    private LayoutParams mMiddleParams;

    /**
     * 右边的LayoutParams
     */
    private LayoutParams mRightParams;

    public XToolBar(Context context) {
        super(context);
        this.mContext = context;

        initValue();
    }

    public XToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        initValue();
        initAttribute(attrs);
    }

    /**
     * 初始化属性
     */
    private void initAttribute(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.XToolBar);

        int leftImageId = typedArray.getResourceId(R.styleable.XToolBar_leftImageSrc, -1);
        mLeftImageWidth = (int) typedArray.getDimension(R.styleable.XToolBar_leftImageWidth, mLeftImageWidth);
        mLeftImageHeight = (int) typedArray.getDimension(R.styleable.XToolBar_leftImageHeight, mLeftImageHeight);

        String leftText = typedArray.getString(R.styleable.XToolBar_leftText);
        mLeftTextWidth = (int) typedArray.getDimension(R.styleable.XToolBar_leftTextWidth, mLeftTextWidth);
        mLeftTextHeight = (int) typedArray.getDimension(R.styleable.XToolBar_leftTextHeight, mLeftTextHeight);
        mLeftTextSize = (int) typedArray.getDimension(R.styleable.XToolBar_leftTextSize, mLeftTextSize);
        mLeftTextColor = typedArray.getColor(R.styleable.XToolBar_leftTextColor, mLeftTextColor);
        mLeftTextGravity = typedArray.getInt(R.styleable.XToolBar_leftTextGravity, mLeftTextGravity);
        int leftViewPaddingLeft = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewPaddingLeft, 0);
        int leftViewPaddingTop = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewPaddingTop, 0);
        int leftViewPaddingRight = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewPaddingRight, 0);
        int leftViewPaddingBottom = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewPaddingBottom, 0);
        int leftViewMarginLeft = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewMarginLeft, 0);
        int leftViewMarginTop = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewMarginTop, 0);
        int leftViewMarginRight = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewMarginRight, 0);
        int leftViewMarginBottom = (int) typedArray.getDimension(R.styleable.XToolBar_leftViewMarginBottom, 0);

        String middleText = typedArray.getString(R.styleable.XToolBar_middleText);
        mMiddleTextWidth = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextWidth, mMiddleTextWidth);
        mMiddleTextHeight = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextHeight, mMiddleTextHeight);
        mMiddleTextSize = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextSize, mMiddleTextSize);
        mMiddleTextColor = typedArray.getColor(R.styleable.XToolBar_middleTextColor, mMiddleTextColor);
        mMiddleTextGravity = typedArray.getInt(R.styleable.XToolBar_middleTextGravity, mMiddleTextGravity);
        middleTextMaxSize = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextMaxSize, 20);

//      中间文本左侧图片
        int middleTextLeftImage = typedArray.getResourceId(R.styleable.XToolBar_middleTextLeftImage, 0);
//      中间文本上侧图片
        int middleTextTopImage = typedArray.getResourceId(R.styleable.XToolBar_middleTextTopImage, 0);
//      中间文本下侧图片
        int middleTextRightImage = typedArray.getResourceId(R.styleable.XToolBar_middleTextRightImage, 0);
        //中间文本右侧图片
        int middleTextBottomImage = typedArray.getResourceId(R.styleable.XToolBar_middleTextBottomImage, 0);

        int middleTextPaddingLeft = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextPaddingLeft, 0);
        int middleTextPaddingTop = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextPaddingTop, 0);
        int middleTextPaddingRight = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextPaddingRight, 0);
        int middleTextPaddingBottom = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextPaddingBottom, 0);
        int middleTextMarginLeft = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextMarginLeft, 0);
        int middleTextMarginTop = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextMarginTop, 0);
        int middleTextMarginRight = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextMarginRight, 0);
        int middleTextMarginBottom = (int) typedArray.getDimension(R.styleable.XToolBar_middleTextMarginBottom, 0);

        mMiddleEditBackground = typedArray.getResourceId(R.styleable.XToolBar_middleEditBackground, -1);
        mMiddleEditHint = typedArray.getString(R.styleable.XToolBar_middleEditHint);
        mMiddleEditTextText = typedArray.getString(R.styleable.XToolBar_middleEditText);
        mMiddleEditWidth = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditWidth, mMiddleEditWidth);
        mMiddleEditHeight = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditHeight, mMiddleEditHeight);
        mMiddleEditTextSize = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditTextSize, mMiddleEditTextSize);
        mMiddleEditTextColor = typedArray.getColor(R.styleable.XToolBar_middleEditColor, mMiddleEditTextColor);
        int middleEditPadding = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditPadding, -1);
        int middleEditMargin = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditMargin, -1);
        mMiddleEditGravity = typedArray.getInt(R.styleable.XToolBar_middleEditGravity, mMiddleEditGravity);

        int middleEditPaddingLeft = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditPaddingLeft, 0);
        int middleEditPaddingTop = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditPaddingTop, 0);
        int middleEditPaddingRight = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditPaddingRight, 0);
        int middleEditPaddingBottom = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditPaddingBottom, 0);
        int middleEditMarginLeft = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditMarginLeft, 0);
        int middleEditMarginTop = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditMarginTop, 0);
        int middleEditMarginRight = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditMarginRight, 0);
        int middleEditMarginBottom = (int) typedArray.getDimension(R.styleable.XToolBar_middleEditMarginBottom, 0);

        // 中间编辑框左侧图片
        int middleEditLeftImage = typedArray.getResourceId(R.styleable.XToolBar_middleEditLeftImage, 0);
        // 中间编辑框上侧图片
        int middleEditTopImage = typedArray.getResourceId(R.styleable.XToolBar_middleEditTopImage, 0);
        // 中间编辑框下侧图片
        int middleEditRightImage = typedArray.getResourceId(R.styleable.XToolBar_middleEditRightImage, 0);
        //中间编辑框右侧图片
        int middleEditBottomImage = typedArray.getResourceId(R.styleable.XToolBar_middleEditBottomImage, 0);

        int rightImageId = typedArray.getResourceId(R.styleable.XToolBar_rightImageSrc, -1);
        mRightImageWidth = (int) typedArray.getDimension(R.styleable.XToolBar_rightImageWidth, mRightImageWidth);
        mRightImageHeight = (int) typedArray.getDimension(R.styleable.XToolBar_rightImageHeight, mRightImageHeight);

        String rightText = typedArray.getString(R.styleable.XToolBar_rightText);
        mRightTextWidth = (int) typedArray.getDimension(R.styleable.XToolBar_rightTextWidth, mRightTextWidth);
        mRightTextHeight = (int) typedArray.getDimension(R.styleable.XToolBar_rightTextHeight, mRightTextHeight);
        mRightTextSize = (int) typedArray.getDimension(R.styleable.XToolBar_rightTextSize, mRightTextSize);
        mRightTextColor = typedArray.getColor(R.styleable.XToolBar_rightTextColor, mRightTextColor);
        mRightTextGravity = typedArray.getInt(R.styleable.XToolBar_rightTextGravity, mRightTextGravity);
        rightViewCheckBoxBackground = typedArray.getResourceId(R.styleable.XToolBar_rightViewCheckBoxBackground, -1);
        boolean rightViewIsCheck = typedArray.getBoolean(R.styleable.XToolBar_rightViewIsCheck, false);
        mRightCheckBoxWidth = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewCheckBoxWidth, mRightCheckBoxWidth);
        mRightCheckBoxHeight = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewCheckBoxHeight, mRightCheckBoxHeight);
        int rightCheckBoxButton = typedArray.getResourceId(R.styleable.XToolBar_rightCheckBoxButton, -1);

        int rightViewPaddingLeft = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewPaddingLeft, 0);
        int rightViewPaddingTop = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewPaddingTop, 0);
        int rightViewPaddingRight = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewPaddingRight, 0);
        int rightViewPaddingBottom = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewPaddingBottom, 0);
        int rightViewMarginLeft = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewMarginLeft, 0);
        int rightViewMarginTop = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewMarginTop, 0);
        int rightViewMarginRight = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewMarginRight, 0);
        int rightViewMarginBottom = (int) typedArray.getDimension(R.styleable.XToolBar_rightViewMarginBottom, 0);
        Drawable rightImageBackground = typedArray.getDrawable(R.styleable.XToolBar_rightTextBackground);

        final String onLeftViewClick = typedArray.getString(R.styleable.XToolBar_onLeftViewClick);
        final String onRightViewClick = typedArray.getString(R.styleable.XToolBar_onRightViewClick);

        typedArray.recycle();

        if (leftImageId != -1) {
            this.setLeftImageResource(leftImageId);
        }
        if (!TextUtils.isEmpty(leftText)) {
            this.setLeftText(leftText);
        }

        mLeftViewPadding = new int[]{leftViewPaddingLeft, leftViewPaddingTop, leftViewPaddingRight, leftViewPaddingBottom};
        mLeftViewMargin = new int[]{leftViewMarginLeft, leftViewMarginTop, leftViewMarginRight, leftViewMarginBottom};
        if (mLeftImageView != null) {
            ViewUtils.setWidth(mLeftImageView, mLeftImageWidth);
            ViewUtils.setHeight(mLeftImageView, mLeftImageHeight);
            if (mLeftViewPadding != null) {
                mLeftImageView.setPadding(mLeftViewPadding[0], mLeftViewPadding[1], mLeftViewPadding[2], mLeftViewPadding[3]);
            }
            if (mLeftViewMargin != null) {
                ViewUtils.setMargins(mLeftImageView, mLeftViewMargin[0], mLeftViewMargin[1], mLeftViewMargin[2], mLeftViewMargin[3]);
            }
        }
        if (mLeftTextView != null) {
            ViewUtils.setWidth(mLeftTextView, mLeftTextWidth);
            ViewUtils.setHeight(mLeftTextView, mLeftTextHeight);
            mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
            if (mLeftViewPadding != null) {
                mLeftTextView.setPadding(mLeftViewPadding[0], mLeftViewPadding[1], mLeftViewPadding[2], mLeftViewPadding[3]);
            }
            if (mLeftViewMargin != null) {
                ViewUtils.setMargins(mLeftTextView, mLeftViewMargin[0], mLeftViewMargin[1], mLeftViewMargin[2], mLeftViewMargin[3]);
            }

        }
        if (!TextUtils.isEmpty(middleText)) {
            this.setMiddleText(middleText);
        }
        setMiddleViewImage(middleTextLeftImage, middleTextTopImage, middleTextRightImage, middleTextBottomImage);

        mMiddleTextPadding = new int[]{middleTextPaddingLeft, middleTextPaddingTop, middleTextPaddingRight, middleTextPaddingBottom};
        mMiddleTextMargin = new int[]{middleTextMarginLeft, middleTextMarginTop, middleTextMarginRight, middleTextMarginBottom};

        if (mMiddleTextView != null) {
            ViewUtils.setWidth(mMiddleTextView, mMiddleTextWidth);
            ViewUtils.setHeight(mMiddleTextView, mMiddleTextHeight);
            mMiddleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMiddleTextSize);
            if (mMiddleTextPadding != null) {
                mMiddleTextView.setPadding(mMiddleTextPadding[0], mMiddleTextPadding[1], mMiddleTextPadding[2], mMiddleTextPadding[3]);
            }
            if (mMiddleTextMargin != null) {
                ViewUtils.setMargins(mMiddleTextView, mMiddleTextMargin[0], mMiddleTextMargin[1], mMiddleTextMargin[2], mMiddleTextMargin[3]);
            }

        }
        if (rightImageId != -1) {
            this.setRightImageResource(rightImageId);
        }
        if (rightViewCheckBoxBackground != -1) {
            this.setCheckBoxBackground(rightViewCheckBoxBackground);
            this.setRightCheckBoxCheckable(rightViewIsCheck);
        }
        if (rightCheckBoxButton != -1) {
            this.setCheckBoxButton(rightCheckBoxButton);
            this.setRightCheckBoxCheckable(rightViewIsCheck);
        }

        if (!TextUtils.isEmpty(rightText)) {
            this.setRightText(rightText);
        }
        if (rightImageBackground != null) {
            setRightTextBackground(rightImageBackground);
        }

        rightViewPadding = new int[]{rightViewPaddingLeft, rightViewPaddingTop, rightViewPaddingRight, rightViewPaddingBottom};
        mRightViewMargin = new int[]{rightViewMarginLeft, rightViewMarginTop, rightViewMarginRight, rightViewMarginBottom};
        if (mRightImageView != null) {
            ViewUtils.setWidth(mRightImageView, mRightImageWidth);
            ViewUtils.setHeight(mRightImageView, mRightImageHeight);
            if (rightViewPadding != null) {
                ViewUtils.setPadding(mRightImageView, rightViewPadding[0], rightViewPadding[1], rightViewPadding[2], rightViewPadding[3]);
            }
            if (mRightViewMargin != null) {
                ViewUtils.setMargins(mRightImageView, mRightViewMargin[0], mRightViewMargin[1], mRightViewMargin[2], mRightViewMargin[3]);
            }
        }
        if (mRightTextView != null) {
            ViewUtils.setWidth(mRightTextView, mRightTextWidth);
            ViewUtils.setHeight(mRightTextView, mRightTextHeight);
            mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
            if (rightViewPadding != null) {
                mRightTextView.setPadding(rightViewPadding[0], rightViewPadding[1], rightViewPadding[2], rightViewPadding[3]);
            }
            if (mRightViewMargin != null) {
                ViewUtils.setMargins(mRightTextView, mRightViewMargin[0], mRightViewMargin[1], mRightViewMargin[2], mRightViewMargin[3]);
            }

        }
        if (mRightCheckBox != null) {
            ViewUtils.setWidth(mRightCheckBox, mRightCheckBoxWidth);
            ViewUtils.setHeight(mRightCheckBox, mRightImageHeight);
            if (mRightCheckBoxMargin != null) {
                ViewUtils.setMargins(mRightCheckBox, mRightCheckBoxMargin[0], mRightCheckBoxMargin[1], mRightCheckBoxMargin[2], mRightCheckBoxMargin[3]);
            }
            if (mRightCheckBoxPadding != null) {
                mRightCheckBox.setPadding(mRightCheckBoxPadding[0], mRightCheckBoxPadding[1], mRightCheckBoxPadding[2], mRightCheckBoxPadding[3]);
            }

        }
        if (!TextUtils.isEmpty(onLeftViewClick)) {

            OnClickListener onLeftViewClickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        Method onLeftImageClickMethod = getContext().getClass().getMethod(onLeftViewClick, View.class);
                        onLeftImageClickMethod.invoke(getContext(), XToolBar.this);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            };

            if (mLeftTextView != null) {
                mLeftTextView.setOnClickListener(onLeftViewClickListener);
            }
            if (mLeftImageView != null) {
                mLeftImageView.setOnClickListener(onLeftViewClickListener);
            }
        }

        if (!TextUtils.isEmpty(onRightViewClick)) {
            OnClickListener onRightViewClickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        Method onRightImageClickMethod = getContext().getClass().getMethod(onRightViewClick, View.class);
                        onRightImageClickMethod.invoke(getContext(), XToolBar.this);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            };

            if (mRightTextView != null) {
                mRightTextView.setOnClickListener(onRightViewClickListener);
            }
            if (mRightImageView != null) {
                mRightImageView.setOnClickListener(onRightViewClickListener);
            }

        }
        mMiddleEditPadding = new int[]{middleEditPaddingLeft, middleEditPaddingTop, middleEditPaddingRight, middleEditPaddingBottom};
        mMiddleEditMargin = new int[]{middleEditMarginLeft, middleEditMarginTop, middleEditMarginRight, middleEditMarginBottom};

        // editText
        if (middleEditMargin > 0)
            mMiddleEditMargin = new int[]{middleEditMargin, middleEditMargin, middleEditMargin, middleEditMargin};
        if (middleEditPadding > 0)
            mMiddleEditPadding = new int[]{middleEditPadding, middleEditPadding, middleEditPadding, middleEditPadding};
        if (mMiddleEditBackground != -1) {
            this.setMiddleEditBackground(mMiddleEditBackground);
            this.setMiddleEditHint(mMiddleEditHint);
            this.setMiddleText(mMiddleEditTextText);
        }
        setMiddleEditImage(middleEditLeftImage, middleEditTopImage, middleEditRightImage, middleEditBottomImage);
        if (mMiddleEditText != null) {
            ViewUtils.setWidth(mMiddleEditText, mMiddleEditWidth);
            ViewUtils.setHeight(mMiddleEditText, mMiddleEditHeight);
            mMiddleEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMiddleEditTextSize);
            if (mMiddleEditPadding != null) {
                mMiddleEditText.setPadding(mMiddleEditPadding[0], mMiddleEditPadding[1], mMiddleEditPadding[2], mMiddleEditPadding[3]);
            }
            if (mMiddleEditMargin != null) {
                ViewUtils.setMargins(mMiddleEditText, mMiddleEditMargin[0], mMiddleEditMargin[1], mMiddleEditMargin[2], mMiddleEditMargin[3]);
            }

        }
    }


    /**
     * 初始化值
     */
    private void initValue() {
        mLeftImageWidth = LayoutParams.WRAP_CONTENT;
        mLeftImageHeight = LayoutParams.WRAP_CONTENT;
        mLeftTextWidth = LayoutParams.WRAP_CONTENT;
        mLeftTextHeight = LayoutParams.WRAP_CONTENT;
        mLeftTextColor = 0xffffffff;
        mLeftTextSize = DensityUtils.sp2px(mContext, 26);

        mMiddleTextWidth = LayoutParams.WRAP_CONTENT;
        mMiddleTextHeight = LayoutParams.WRAP_CONTENT;
        mMiddleTextSize = DensityUtils.sp2px(mContext, 34);
        mMiddleTextColor = 0xffffffff;

        mMiddleEditWidth = LayoutParams.MATCH_PARENT;
        mMiddleEditHeight = LayoutParams.WRAP_CONTENT;
        mMiddleEditTextSize = DensityUtils.sp2px(mContext, 26);
        mMiddleEditTextColor = 0xffffffff;

        mRightTextWidth = LayoutParams.WRAP_CONTENT;
        mRightTextHeight = LayoutParams.WRAP_CONTENT;
        mRightImageWidth = LayoutParams.WRAP_CONTENT;
        mRightImageHeight = LayoutParams.WRAP_CONTENT;
        mRightCheckBoxWidth = LayoutParams.WRAP_CONTENT;
        mRightCheckBoxHeight = LayoutParams.WRAP_CONTENT;
        mRightTextColor = 0xffffffff;
        mRightTextSize = DensityUtils.sp2px(mContext, 26);

        mParams = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, DensityUtils.dp2px(mContext, 88));
        this.setLayoutParams(mParams);
    }

    /**
     * 设置宽
     */
    public void setWidth(int width) {
        mParams.width = width;
    }

    /**
     * 设置高
     */
    public void setHeight(int height) {
        mParams.height = height;
    }

    /**
     * 检车右边image是否为空，为空则创建，不为空则返回
     */
    private void checkLeftImageIsNull() {
        if (mLeftImageView != null)
            return;
        mLeftImageView = new ImageView(mContext);
        mLeftImageView.setId(R.id.left_image_view);
        initLeftParams();
        this.addView(mLeftImageView, mLeftParams);
        mLeftTextView = null;
    }

    /**
     * 设置左边图片
     */
    public void setLeftImageResource(int resId) {
        checkLeftImageIsNull();
        mLeftImageView.setImageResource(resId);
    }

    /**
     * 设置左边图片
     */
    public void setLeftImageDrawable(Drawable drawable) {
        checkLeftImageIsNull();
        mLeftImageView.setImageDrawable(drawable);
    }

    /**
     * 设置左边图片
     */
    public void setLeftImageBitmap(Bitmap bm) {
        checkLeftImageIsNull();
        mLeftImageView.setImageBitmap(bm);
    }

    /**
     * 设置左边图片宽
     */
    public void setLeftImageWidth(int width) {
        this.mLeftImageWidth = width;
    }

    /**
     * 设置左边图片高
     */
    public void setLeftImageHeight(int height) {
        this.mLeftImageHeight = height;
    }

    /**
     * 设置左边图片背景
     */
    public void setLeftImageBackground(Drawable drawable) {
        mLeftImageView.setBackgroundDrawable(drawable);
    }

    /**
     * 设置左边文本
     */
    public void setLeftText(CharSequence text) {
        mLeftTextView = new TextView(mContext);
        mLeftTextView.setId(R.id.left_text_view);
        mLeftTextView.setText(text);
        initLeftParams();
        this.addView(mLeftTextView, mLeftParams);
        mLeftImageView = null;
    }

    /**
     * 设置左边文本宽
     */
    public void setLeftTextWidth(int leftTextWidth) {
        this.mLeftTextWidth = leftTextWidth;
    }

    /**
     * 设置左边文本高
     */
    public void setLeftTextHeight(int leftTextHeight) {
        this.mLeftTextHeight = leftTextHeight;
    }

    /**
     * 设置左边文本大小
     */
    public void setLeftTextSize(int size) {
        this.mLeftTextSize = size;
    }

    /**
     * 设置左边文本大小
     */
    public void setLeftTextColor(int color) {
        this.mLeftTextColor = color;
    }

    /**
     * 设置左边文本内Padding
     */
    public void setLeftViewPadding(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) {
        mLeftViewPadding = new int[4];
        mLeftViewPadding[0] = paddingLeft;
        mLeftViewPadding[1] = paddingRight;
        mLeftViewPadding[2] = paddingTop;
        mLeftViewPadding[3] = paddingBottom;
    }

    /**
     * 设置左边文本Margin
     */
    public void setLeftViewMargin(int marginLeft, int marginRight, int marginTop, int marginBottom) {
        mLeftViewMargin = new int[4];
        mLeftViewMargin[0] = marginLeft;
        mLeftViewMargin[1] = marginRight;
        mLeftViewMargin[2] = marginTop;
        mLeftViewMargin[3] = marginBottom;
    }

    /**
     * 设置左边文本Gravity
     */
    public void setLeftTextGravity(int gravity) {
        this.mLeftTextGravity = gravity;
    }

    /**
     * 设置左边文本背景
     */
    public void setLeftTextBackground(Drawable drawable) {
        mLeftTextView.setBackgroundDrawable(drawable);
    }

    /**
     * 设置中间文本
     * 设置标题的字数限制为英文20个，中文10个，避免换行
     */
    public void setMiddleText(CharSequence text) {
        if (mMiddleTextView != null)
            return;
        mMiddleTextView = new TextView(mContext);
        mMiddleTextView.setId(R.id.middle_text_view);
        mMiddleTextView.setGravity(mMiddleTextGravity);
        mMiddleTextView.setSingleLine();
        initMiddleParams();
        this.addView(mMiddleTextView, mMiddleParams);
        mMiddleTextView.setFilters(new InputFilter[]{new TextInputFilter(middleTextMaxSize)});
        mMiddleTextView.setText(text);
    }

    /**
     * 中间文本宽
     */
    public void setMiddleTextWidth(int middleTextWidth) {
        this.mMiddleTextWidth = middleTextWidth;
    }

    /**
     * 中间文本高
     */
    public void setMiddleTextHeight(int middleTextHeight) {
        this.mMiddleTextHeight = middleTextHeight;
    }

    /**
     * 设置中间文本大小
     */
    public void setMiddleTextSize(int size) {
        this.mMiddleTextSize = DensityUtils.sp2px(mContext, size);
    }

    /**
     * 设置中间文本颜色
     */
    public void setMiddleTextColor(int color) {
        this.mMiddleTextColor = color;
    }

    /**
     * 设置中间文本Padding
     */
    public void setMiddleTextPadding(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) {
        mMiddleTextPadding = new int[4];
        mMiddleTextPadding[0] = paddingLeft;
        mMiddleTextPadding[1] = paddingRight;
        mMiddleTextPadding[2] = paddingTop;
        mMiddleTextPadding[3] = paddingBottom;
    }

    /**
     * 设置中间文本Margin
     */
    public void setMiddleTextMargin(int marginLeft, int marginRight, int marginTop, int marginBottom) {
        mMiddleTextMargin = new int[4];
        mMiddleTextMargin[0] = marginLeft;
        mMiddleTextMargin[1] = marginRight;
        mMiddleTextMargin[2] = marginTop;
        mMiddleTextMargin[3] = marginBottom;
    }

    /**
     * 设置中间文本Gravity
     */
    public void setMiddleTextGravity(int gravity) {
        this.mMiddleTextGravity = gravity;
    }

    /**
     * 设置中间文本背景
     */
    public void setMiddleTextBackground(Drawable drawable) {
        mMiddleTextView.setBackgroundDrawable(drawable);
    }

    /**
     * 获取中间View，用于扩展的属性
     */
    public TextView getMiddleTextView() {
        return mMiddleTextView;
    }

    /**
     * 中间view添加周围图片,按照图片的原始比例显示
     *
     * @param left   middleText左方图片
     * @param top    middleText上方图片
     * @param right  middleText右方图片
     * @param bottom middleText下方图片
     */
    public void setMiddleViewImage(@DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        if (mMiddleTextView != null) {
            mMiddleTextView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }

    }

    /**
     * 中间view添加周围图片,按照图片的原始比例显示
     *
     * @param left   middleText左方图片
     * @param top    middleText上方图片
     * @param right  middleText右方图片
     * @param bottom middleText下方图片
     */
    public void setMiddleViewImage(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (mMiddleTextView != null) {
            mMiddleTextView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }

    }


    /**
     * 设置中间view左边图片
     *
     * @param leftImageRes 图片资源
     * @param width        图片宽度
     * @param height       图片高度
     */
    public void setMiddleTextLeftImage(@DrawableRes int leftImageRes, int width, int height) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(leftImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param leftImageRes 图片资源
     * @param width        图片宽度
     * @param height       图片高度
     * @param padding      图片padding值
     */
    public void setMiddleTextLeftImage(@DrawableRes int leftImageRes, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(leftImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleTextLeftImage(Drawable drawable, int width, int height) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleTextLeftImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param topImageRes 图片资源
     * @param width       图片宽度
     * @param height      图片高度
     */
    public void setMiddleTextTopImage(@DrawableRes int topImageRes, int width, int height) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(topImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param topImageRes 图片资源
     * @param width       图片宽度
     * @param height      图片高度
     * @param padding     图片padding值
     */
    public void setMiddleTextTopImage(@DrawableRes int topImageRes, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(topImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleTextTopImage(Drawable drawable, int width, int height) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleTextTopImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param rightImageRes 图片资源
     * @param width         图片宽度
     * @param height        图片高度
     */
    public void setMiddleTextRightImage(@DrawableRes int rightImageRes, int width, int height) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(rightImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param rightImageRes 图片资源
     * @param width         图片宽度
     * @param height        图片高度
     * @param padding       图片padding值
     */
    public void setMiddleTextRightImage(@DrawableRes int rightImageRes, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(rightImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleTextRightImage(Drawable drawable, int width, int height) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleTextRightImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param bottomImageRes 图片资源
     * @param width          图片宽度
     * @param height         图片高度
     */
    public void setMiddleTextBottomImage(@DrawableRes int bottomImageRes, int width, int height) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(bottomImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param bottomImageRes 图片资源
     * @param width          图片宽度
     * @param height         图片高度
     * @param padding        图片padding值
     */
    public void setMiddleTextBottomImage(@DrawableRes int bottomImageRes, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            Drawable drawable = mContext.getResources().getDrawable(bottomImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleTextBottomImage(Drawable drawable, int width, int height) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(20);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleTextBottomImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleTextView != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleTextView.setCompoundDrawablePadding(padding);
            mMiddleTextView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 检查中间边几款是否为空为空则创建editText
     */
    private void checkMiddleEditIsNull() {
        if (mMiddleEditText != null)
            return;
        mMiddleEditText = new EditText(mContext);
        mMiddleEditText.setId(R.id.middle_edit_view);
        setMiddleEditGravity(mMiddleTextGravity);
        setMiddleTextSingleLines();
//        initMiddleParams();
        initMiddleEditParams();
        this.addView(mMiddleEditText, mMiddleParams);

    }

    /**
     * 设置edit字体布局
     *
     * @param gravity 布局
     */
    public void setMiddleEditGravity(int gravity) {
        if (mMiddleEditText != null)
            mMiddleEditText.setGravity(gravity);
    }

    /**
     * 设置edit单行显示
     */
    public void setMiddleTextSingleLines() {
        if (mMiddleEditText != null)
            mMiddleEditText.setSingleLine();
    }

    /**
     * 设置edit背景
     *
     * @param middleEditBackground
     */
    public void setMiddleEditBackground(int middleEditBackground) {
        checkMiddleEditIsNull();
        mMiddleEditText.setBackgroundResource(middleEditBackground);
    }

    /**
     * 设置edit 背景
     *
     * @param drawable 背景
     */
    public void setMiddleEditBackground(Drawable drawable) {
        checkMiddleEditIsNull();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            mMiddleEditText.setBackgroundDrawable(drawable);
        else
            mMiddleEditText.setBackground(drawable);
    }

    /**
     * 设置edit 背景
     *
     * @param colorStateList 背景
     */
    public void setMiddleEditBackgound(ColorStateList colorStateList) {
        checkMiddleEditIsNull();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mMiddleEditText.setBackgroundTintList(colorStateList);
        else ViewCompat.setBackgroundTintList(mMiddleEditText, colorStateList);
    }

    /**
     * 设置edit hint
     *
     * @param middleEditHint edit hint
     */
    public void setMiddleEditHint(String middleEditHint) {
        if (mMiddleEditText != null && !TextUtils.isEmpty(middleEditHint))
            mMiddleEditText.setHint(middleEditHint);
    }

    /**
     * 给editText设置Text
     *
     * @param middleEditText 显示的Text
     */
    public void setMiddleEditText(String middleEditText) {
        if (mMiddleEditText != null && !TextUtils.isEmpty(middleEditText))
            mMiddleEditText.setHint(middleEditText);
    }

    /**
     * 中间view添加周围图片,按照图片的原始比例显示
     *
     * @param left   middleText左方图片
     * @param top    middleText上方图片
     * @param right  middleText右方图片
     * @param bottom middleText下方图片
     */
    public void setMiddleEditImage(@DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        if (mMiddleEditText != null) {
            mMiddleEditText.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }

    }

    /**
     * 设置中间view左边图片
     *
     * @param leftImageRes 图片资源
     * @param width        图片宽度
     * @param height       图片高度
     */
    public void setMiddleEditLeftImage(@DrawableRes int leftImageRes, int width, int height) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(leftImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param leftImageRes 图片资源
     * @param width        图片宽度
     * @param height       图片高度
     * @param padding      图片padding值
     */
    public void setMiddleEditLeftImage(@DrawableRes int leftImageRes, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(leftImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleEditLeftImage(Drawable drawable, int width, int height) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view左边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleEditLeftImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param topImageRes 图片资源
     * @param width       图片宽度
     * @param height      图片高度
     */
    public void setMiddleEditTopImage(@DrawableRes int topImageRes, int width, int height) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(topImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param topImageRes 图片资源
     * @param width       图片宽度
     * @param height      图片高度
     * @param padding     图片padding值
     */
    public void setMiddleEditTopImage(@DrawableRes int topImageRes, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(topImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleEditTopImage(Drawable drawable, int width, int height) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view上边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleEditTopImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param rightImageRes 图片资源
     * @param width         图片宽度
     * @param height        图片高度
     */
    public void setMiddleEditRightImage(@DrawableRes int rightImageRes, int width, int height) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(rightImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param rightImageRes 图片资源
     * @param width         图片宽度
     * @param height        图片高度
     * @param padding       图片padding值
     */
    public void setMiddleEditRightImage(@DrawableRes int rightImageRes, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(rightImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleEditRightImage(Drawable drawable, int width, int height) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view右边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleEditRightImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param bottomImageRes 图片资源
     * @param width          图片宽度
     * @param height         图片高度
     */
    public void setMiddleEditBottomImage(@DrawableRes int bottomImageRes, int width, int height) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(bottomImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param bottomImageRes 图片资源
     * @param width          图片宽度
     * @param height         图片高度
     * @param padding        图片padding值
     */
    public void setMiddleEditBottomImage(@DrawableRes int bottomImageRes, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            Drawable drawable = mContext.getResources().getDrawable(bottomImageRes);
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     */
    public void setMiddleEditBottomImage(Drawable drawable, int width, int height) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(20);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置中间view下边图片
     *
     * @param drawable 图片资源
     * @param width    图片宽度
     * @param height   图片高度
     * @param padding  图片padding值
     */
    public void setMiddleEditBottomImage(Drawable drawable, int width, int height, int padding) {
        if (mMiddleEditText != null) {
            drawable.setBounds(0, 0, width, height);
            mMiddleEditText.setCompoundDrawablePadding(padding);
            mMiddleEditText.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 提供中间编辑框，以便用户自定义属性
     *
     * @return 编辑框
     */
    public EditText getMiddleEditText() {
        return mMiddleEditText;
    }

    /**
     * 设置checkBox 背景
     *
     * @param checkBoxBackground
     */
    public void setCheckBoxBackground(int checkBoxBackground) {
        checkBoxIsNull();
        mRightCheckBox.setBackgroundResource(checkBoxBackground);
        mRightCheckBox.setButtonDrawable(null);
    }

    /**
     * 设置checkBox 背景
     *
     * @param checkBoxButton
     */
    public void setCheckBoxButton(int checkBoxButton) {
        checkBoxIsNull();
        mRightCheckBox.setButtonDrawable(checkBoxButton);
    }

    /**
     * 检车checkbox是否为空，为空创建checkbox 并且配置相关属性
     */
    private void checkBoxIsNull() {
        if (mRightCheckBox == null) {
            mRightCheckBox = new CheckBox(mContext);
            mRightCheckBox.setId(R.id.right_check_box_view);
            initRightParams();
            this.addView(mRightCheckBox, mRightParams);
            mRightTextView = null;
            mRightImageView = null;
        }
    }

    /**
     * 设置checkBox是否可以点击
     *
     * @param rightCheckBoxCheckable
     */
    public void setRightCheckBoxCheckable(boolean rightCheckBoxCheckable) {
        if (mRightCheckBox != null)
            mRightCheckBox.setChecked(rightCheckBoxCheckable);
    }

    /**
     * 设置右边check宽
     */
    public void setRightCheckBoxWidth(int rightCheckBoxWidth) {
        this.mRightCheckBoxWidth = rightCheckBoxWidth;
    }

    /**
     * 设置右边check高
     */
    public void setRightCheckBoxHeight(int rightCheckBoxHeight) {
        this.mRightCheckBoxHeight = rightCheckBoxHeight;
    }

    /**
     * 设置右边check margin
     */
    public void setRightCheckBoxMargin(int left, int top, int right, int bottom) {
        mRightCheckBoxMargin = new int[4];
        mRightCheckBoxMargin[0] = left;
        mRightCheckBoxMargin[1] = top;
        mRightCheckBoxMargin[2] = right;
        mRightCheckBoxMargin[3] = bottom;
    }

    /**
     * 设置右边check padding
     */
    public void setRightCheckBoxPadding(int left, int top, int right, int bottom) {
        mRightCheckBoxPadding = new int[4];
        mRightCheckBoxPadding[0] = left;
        mRightCheckBoxPadding[1] = top;
        mRightCheckBoxPadding[2] = right;
        mRightCheckBoxPadding[3] = bottom;
    }

    /**
     * 提供CheckBox，以便实现该View没有的方法
     *
     * @return mRightCheckBox
     */
    public CheckBox getRightCheckBox() {
        return mRightCheckBox;
    }

    /**
     * 设置右边
     */
    public void setRightImageDrawable(Drawable drawable) {
        checkRightImageIsNull();
        mRightImageView.setImageDrawable(drawable);
    }

    /**
     * 设置右边图片
     */
    public void setRightImageResource(int resId) {
        checkRightImageIsNull();
        mRightImageView.setImageResource(resId);
    }

    /**
     * 判断右边image是否为空，如果为空创建如果不为空则返回
     */
    private void checkRightImageIsNull() {
        if (mRightImageView != null)
            return;
        mRightImageView = new ImageView(mContext);
        mRightImageView.setId(R.id.right_image_view);
        initRightParams();
        this.addView(mRightImageView, mRightParams);
        mRightTextView = null;
    }


    /**
     * 设置右边图片
     */
    public void setRightImageBitmap(Bitmap bm) {
        checkRightImageIsNull();
        mRightImageView.setImageBitmap(bm);
    }

    /**
     * 设置右边图片宽
     */
    public void setRightImageWidth(int width) {
        this.mRightImageWidth = width;
    }

    /**
     * 设置右边图片高
     */
    public void setRightImageHeight(int height) {
        this.mRightImageHeight = height;
    }

    /**
     * 设置右边图片背景
     */
    public void setRightImageBackground(Drawable drawable) {
        if (mRightImageView != null) {
            mRightImageView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置右边文本
     */
    public void setRightText(CharSequence text) {
        if (mRightTextView == null) {
            mRightTextView = new TextView(mContext);
            mRightTextView.setId(R.id.right_text_view);
            initRightParams();
            this.addView(mRightTextView, mRightParams);
        }
        mRightTextView.setText(text);
        mRightImageView = null;
    }

    /**
     * 设置右边文本宽
     */
    public void setRightTextWidth(int width) {
        this.mRightTextWidth = width;
    }

    /**
     * 设置右边文本高
     */
    public void setRightTextHeight(int height) {
        this.mRightTextHeight = height;
    }

    /**
     * 设置右边文本大小
     */
    public void setRightTextSize(int size) {
        this.mRightTextSize = DensityUtils.sp2px(mContext, size);
    }

    /**
     * 设置右边文本颜色
     */
    public void setRightTextColor(int color) {
        this.mRightTextColor = color;
    }

    /**
     * 设置右边View的Padding
     */
    public void setRightViewPadding(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) {
        rightViewPadding = new int[4];
        rightViewPadding[0] = paddingLeft;
        rightViewPadding[1] = paddingRight;
        rightViewPadding[2] = paddingTop;
        rightViewPadding[3] = paddingBottom;
    }

    /**
     * 设置右边View的Margin
     */
    public void setRighViewMargin(int marginLeft, int marginRight, int marginTop, int marginBottom) {
        mRightViewMargin = new int[4];
        mRightViewMargin[0] = marginLeft;
        mRightViewMargin[1] = marginRight;
        mRightViewMargin[2] = marginTop;
        mRightViewMargin[3] = marginBottom;
    }

    /**
     * 设置右边文本Gravity
     */
    public void setRightTextGravity(int gravity) {
        this.mRightTextGravity = gravity;
    }

    /**
     * 设置右边文本背景
     */
    public void setRightTextBackground(Drawable drawable) {
        if (mRightTextView != null) {
            mRightTextView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 左边view点击事件
     */
    public void setLeftViewOnClickListener(OnClickListener onClickListener) {
        if (mLeftImageView != null) {
            mLeftImageView.setOnClickListener(onClickListener);
        } else if (mLeftTextView != null) {
            mLeftTextView.setOnClickListener(onClickListener);
        }
    }

    /**
     * 右边view点击事件
     */
    public void setRightViewOnClickListener(OnClickListener onClickListener) {
        if (mRightImageView != null) {
            mRightImageView.setOnClickListener(onClickListener);
        } else if (mRightTextView != null) {
            mRightTextView.setOnClickListener(onClickListener);
        }
    }

    public void setRightViewClickable(boolean isClickAble) {
        if (mRightTextView != null) {
            mRightTextView.setClickable(isClickAble);
        } else if (mRightImageView != null) {
            mRightImageView.setClickable(isClickAble);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mParams.height, MeasureSpec.EXACTLY);
        }
        measureLeftImage();
        measureLeftText();
        measureMiddleText();
        measureMiddleEdit();
        measureRightImage();
        measureRightCheckBox();
        measureRightText();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutLeftText();
            layoutMiddleText();
            layoutMiddleEdit();
            layoutRightText();
        }
        super.onLayout(changed, l, t, r, b);
    }

    private void layoutMiddleEdit() {
        if (mMiddleEditText != null) {
            mMiddleEditText.setTextColor(mMiddleTextColor);
            mMiddleEditText.setGravity(mMiddleEditGravity);
        }
    }

    /**
     * 测量左边图片
     */
    private void measureLeftImage() {
        if (mLeftImageView != null) {
            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mLeftImageView.measure(measureSize, measureSize);
        }
    }

    /**
     * 测量左边文本
     */
    private void measureLeftText() {
        if (mLeftTextView != null) {
            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mLeftTextView.measure(measureSize, measureSize);
        }
    }

    /**
     * 布局左边文本
     */
    private void layoutLeftText() {
        if (mLeftTextView != null) {
            mLeftTextView.setTextColor(mLeftTextColor);
            mLeftTextView.setGravity(mLeftTextGravity);
        }
    }

    private void measureMiddleEdit() {
        if (mMiddleEditText != null) {

            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mMiddleEditText.measure(measureSize, measureSize);
        }
    }

    /**
     * 测量中间文本
     */
    private void measureMiddleText() {
        if (mMiddleTextView != null) {

            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mMiddleTextView.measure(measureSize, measureSize);
        }
    }

    /**
     * 布局中间文本
     */
    private void layoutMiddleText() {
        if (mMiddleTextView != null) {
            mMiddleTextView.setTextColor(mMiddleTextColor);
            mMiddleTextView.setGravity(mMiddleTextGravity);
        }
    }

    /**
     * 测量右边图片
     */
    private void measureRightImage() {
        if (mRightImageView != null) {


            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mRightImageView.measure(measureSize, measureSize);
        }
    }

    /**
     * 测量右边复选框
     */
    private void measureRightCheckBox() {
        if (mRightCheckBox != null) {

            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mRightCheckBox.measure(measureSize, measureSize);
        }
    }

    /**
     * 测量右边文本
     */
    private void measureRightText() {
        if (mRightTextView != null) {

            int measureSize = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            mRightTextView.measure(measureSize, measureSize);
        }
    }

    /**
     * 布局右边文本
     */
    private void layoutRightText() {
        if (mRightTextView != null) {
            mRightTextView.setTextColor(mRightTextColor);
            mRightTextView.setGravity(mRightTextGravity);
        }
    }

    /**
     * 初始化左边LayoutParams
     */
    private void initLeftParams() {
        if (mLeftParams == null) {
            mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        }
    }

    /**
     * 初始化中间LayoutParams
     * 默认设置 设置布局的规则，位于左view的右方，右view的左方以及文字居中
     */
    private void initMiddleParams() {
        if (mMiddleParams == null) {
            mMiddleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mMiddleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//            if (mLeftImageView != null)
//                mMiddleParams.addRule(RelativeLayout.RIGHT_OF, mLeftImageView.getId());
//            if (mLeftTextView != null)
//                mMiddleParams.addRule(RelativeLayout.RIGHT_OF, mLeftTextView.getId());
//            if (mRightImageView != null)
//                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightImageView.getId());
//            if (mRightTextView != null)
//                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightTextView.getId());
//            if (mRightCheckBox != null)
//                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightCheckBox.getId());

        }
    }

    /**
     * 初始化中间LayoutParams
     * 默认设置 设置布局的规则，位于左view的右方，右view的左方以及文字居中
     */
    private void initMiddleEditParams() {
        if (mMiddleParams == null) {
            mMiddleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mMiddleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            if (mLeftImageView != null)
                mMiddleParams.addRule(RelativeLayout.RIGHT_OF, mLeftImageView.getId());
            if (mLeftTextView != null)
                mMiddleParams.addRule(RelativeLayout.RIGHT_OF, mLeftTextView.getId());
            if (mRightImageView != null)
                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightImageView.getId());
            if (mRightTextView != null)
                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightTextView.getId());
            if (mRightCheckBox != null)
                mMiddleParams.addRule(RelativeLayout.LEFT_OF, mRightCheckBox.getId());

        }
    }

    /**
     * 初始化右边LayoutParams
     */
    private void initRightParams() {
        if (mRightParams == null) {
            mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        }
    }


}
