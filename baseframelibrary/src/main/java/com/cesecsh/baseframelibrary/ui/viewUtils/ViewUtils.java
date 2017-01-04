package com.cesecsh.baseframelibrary.ui.viewUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 作者：谢青仂 on 2016/8/15
 * 邮箱：qingle6616@sina.com
 * </p>
 * 用于设置空间的高宽以及获取其高宽
 */
public class ViewUtils {
    /**
     * 设置view宽 单位px
     */
    public static void setWidth(View view, int width) {
        try {
            if (view == null) {
                return;
            }
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    Class<?> paramsClass = Class.forName(parent.getClass().getName() + ".LayoutParams");
                    params = (ViewGroup.LayoutParams) paramsClass.newInstance();
                    view.setLayoutParams(params);
                } else {
                    params = new AbsListView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
                    view.setLayoutParams(params);
                    return;
                }
            }
            params.width = width;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取view宽 单位px
     *
     * @return
     */
    public static int getWidth(View view) {
        try {
            if (view == null) {
                return 0;
            }
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    Class<?> paramsClass = Class.forName(parent.getClass().getName() + ".LayoutParams");
                    params = (ViewGroup.LayoutParams) paramsClass.newInstance();
                    view.setLayoutParams(params);
                } else {
                    return 0;
                }
            }
            return params.width;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置view宽 单位px
     *
     * @return
     */
    public static void setHeight(View view, int height) {
        try {
            if (view == null) {
                return;
            }
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    Class<?> paramsClass = Class.forName(parent.getClass().getName() + ".LayoutParams");
                    params = (ViewGroup.LayoutParams) paramsClass.newInstance();
                    view.setLayoutParams(params);
                } else {
                    params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
                    view.setLayoutParams(params);
                    return;
                }
            }
            params.height = height;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取view宽 单位px
     *
     * @return
     */
    public static int getHeight(View view) {
        try {
            if (view == null) {
                return 0;
            }
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    Class<?> paramsClass = Class.forName(parent.getClass().getName() + ".LayoutParams");
                    params = (ViewGroup.LayoutParams) paramsClass.newInstance();
                    view.setLayoutParams(params);
                } else {
                    return 0;
                }
            }
            return params.height;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置最大宽
     */
    public static void setMaxWidth(View view, int maxWidth) {
        try {
            if (view != null) {
                Class<? extends View> clazz = view.getClass();
                Method method = clazz.getMethod("setMaxWidth", int.class);
                if (method != null) {
                    method.invoke(view, maxWidth);
                }
            }
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

    /**
     * 设置最大宽
     */
    public static void setMaxHeight(View view, int maxHeight) {
        try {
            if (view != null) {
                Class<? extends View> clazz = view.getClass();
                Method method = clazz.getMethod("setMaxHeight", int.class);
                if (method != null) {
                    method.invoke(view, maxHeight);
                }
            }
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

    /**
     * 设置最小宽
     */
    public static void setMinWidth(View view, int minWidth) {
        if (view != null) {
            view.setMinimumWidth(minWidth);
        }
    }

    /**
     * 设置最小宽
     */
    public static void setMinHeight(View view, int minHeight) {
        if (view != null) {
            view.setMinimumHeight(minHeight);
        }
    }

    /**
     * 设置Margin 单位px
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        try {
            if (view == null) {
                return;
            }
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    Class<?> paramsClass = Class.forName(parent.getClass().getName() + ".LayoutParams");
                    params = (ViewGroup.LayoutParams) paramsClass.newInstance();
                    view.setLayoutParams(params);
                } else {
                    view.setLayoutParams(params);
                    return;
                }
            }
            Method setMargins = params.getClass().getMethod("setMargins", int.class, int.class, int.class, int.class);
            setMargins.invoke(params, left, top, right, bottom);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置左边距 单位px
     */
    @TargetApi(17)
    public static void setMarginStart(View view, int start) {
        try {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null) {
                Method setMarginStart = ViewGroup.LayoutParams.class.getMethod("setMarginStart", int.class);
                setMarginStart.invoke(params, start);
            }
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

    /**
     * 设置右边距 单位px
     */
    @TargetApi(17)
    public static void setMarginEnd(View view, int end) {
        try {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null) {
                Method setMarginEnd = ViewGroup.LayoutParams.class.getMethod("setMarginEnd", int.class);
                setMarginEnd.invoke(params, end);
            }
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

    /**
     * 获取Margin
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static int[] getMargins(View view) {
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) params;
            int marginLeft = marginParams.getMarginStart();
            int marginRight = marginParams.getMarginEnd();
            if (marginLeft <= 0) {
                marginLeft = marginParams.leftMargin;
            }
            if (marginRight <= 0) {
                marginRight = marginParams.rightMargin;
            }
            return new int[]{marginLeft, marginParams.topMargin, marginRight, marginParams.bottomMargin};
        }
        return null;
    }

    /**
     * 设置内边距 单位px
     */
    public static void setPadding(View view, int left, int top, int right, int bottom) {
        if (view != null) {
            view.setPadding(left, top, right, bottom);
        }
    }

    /**
     * 设置内边距 单位px
     */
    @TargetApi(17)
    public static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
        if (view != null) {
            view.setPaddingRelative(start, top, end, bottom);
        }
    }

    /**
     * 获取Margin
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static int[] getPadding(View view) {
        if (view == null) {
            return null;
        }
        int paddingLeft = view.getPaddingStart();
        int paddingRight = view.getPaddingEnd();
        if (paddingLeft <= 0) {
            paddingLeft = view.getPaddingLeft();
        }
        if (paddingRight <= 0) {
            paddingRight = view.getPaddingRight();
        }

        return new int[]{paddingLeft, view.getPaddingTop(), paddingRight, view.getPaddingBottom()};
    }

    /**
     * 设置Gravity
     */
    public static void setGravity(View view, int gravity) {
        try {
            if (view != null) {
                Class<? extends View> clazz = view.getClass();
                Method method = clazz.getMethod("setGravity", int.class);
                if (method != null) {
                    method.invoke(view, gravity);
                }
            }
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

    /**
     * 设置文本 单位px
     */
    public static void setTextSize(TextView tv, float size) {
        if (tv != null) {
//            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, DensityUtils.sp2px(ResourceUtils.getContext(), size));
        }
    }

    /**
     * 设置背景透明
     */
    public static void setBackgroundAlpha(Activity context, boolean isAlpha) {
        if (isAlpha) {
            WindowManager.LayoutParams params = context.getWindow().getAttributes();
            params.alpha = 0.5f;
            context.getWindow().setAttributes(params);
        } else {
            WindowManager.LayoutParams params = context.getWindow().getAttributes();
            params.alpha = 1.0f;
            context.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置背景透明度
     */
    public static void setBackgroundAlpha(Activity context, float alpha) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = alpha;
        context.getWindow().setAttributes(params);
    }

    public static void setHintAndSize(EditText editText, String str, int size) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(str);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(DensityUtils.sp2px(editText.getContext(), size), true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失

    }


}
