package com.cesecsh.baseframelibrary.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cesecsh.baseframelibrary.R;
import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;
import com.cesecsh.baseframelibrary.ui.viewUtils.ResourceUtils;
import com.cesecsh.baseframelibrary.ui.viewUtils.ViewUtils;


/**
 * Created by 上海中电
 * on 2016/9/7
 */
public class ToastView {
    private static String message;
    private Toast mToast;
    private static ToastView instance;

    public static final int TYPE_SUCCESS = 0; // 操作成功后的toast
    public static final int TYPE_WARNING = 1; // 警示类toast
    public static final int TYPE_DEFAULT = 2; // 默认toast


    private static Toast toast;
    private static String msg;
    private static View view;
    private static TextView mTvToast;

    private ToastView() {
    }

    public static ToastView getInstance() {
        if (instance == null) {
            instance = new ToastView();
        }
        return instance;
    }

    /**
     * 居中的文字toast
     *
     * @param context
     * @param str
     */
    public void toast(Context context, String str) {
        View mView = LayoutInflater.from(context).inflate(R.layout.toast_custom_layout, null);
        TextView text = (TextView) mView.findViewById(R.id.toast_text);
        ImageView iv = (ImageView) mView.findViewById(R.id.toast_iv);
        iv.setVisibility(View.GONE);

        // 连续按只以最后一次为起点显示2s,持续时间不再按次数累加
        text.setText(str);
        if (mToast == null || !str.equals(message)) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(mView);
            message = str;
        }
        mToast.show();

    }

    /**
     * 显示Toast
     *
     * @param context
     * @param str
     */

    public void toast(Context context, String str, int type) {
        View mView = LayoutInflater.from(context).inflate(R.layout.toast_custom_layout, null);
        TextView text = (TextView) mView.findViewById(R.id.toast_text);
        ImageView iv = (ImageView) mView.findViewById(R.id.toast_iv);

        if (type == TYPE_DEFAULT) {
            iv.setVisibility(View.GONE);
        } else if (type == TYPE_SUCCESS) {
            iv.setImageResource(R.drawable.collection_success);
        } else if (type == TYPE_WARNING) {
            iv.setImageResource(R.drawable.collection_warning);
        }
        text.setText(str);

        // 连续按只以最后一次为起点显示2s,持续时间不再按次数累加
        if (mToast == null || !str.equals(message)) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(mView);
            message = str;
        }
        mToast.show();

    }

    /**
     * 收藏、取消收藏
     *
     * @param context
     * @param str
     * @param type
     * @param iconRes
     */
    public void toast(Context context, String str, int type, int iconRes) {
        View mView = LayoutInflater.from(context).inflate(R.layout.toast_custom_layout, null);
        TextView text = (TextView) mView.findViewById(R.id.toast_text);
        ImageView iv = (ImageView) mView.findViewById(R.id.toast_iv);

        iv.setImageResource(iconRes);
        text.setText(str);

        // 连续按只以最后一次为起点显示2s,持续时间不再按次数累加
        if (mToast == null || !str.equals(message)) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(mView);
            message = str;
        }
        mToast.show();

    }

 /*   *//**
     * 默认显示时间为短
     *
     */
    public static void makeText(Context mContext, String str) {
        if (toast == null || !TextUtils.equals(msg, str)) {
            toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            toast.show();
            msg = str;
        } else {
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 第三个随便什么参数，表示显示时间长度为长
     *
     */
    public static void makeText(Context mContext, String str, Object longTime) {
        if (toast == null || !TextUtils.equals(msg, str)) {
            toast = Toast.makeText(mContext, str, Toast.LENGTH_LONG);
            toast.show();
            msg = str;
        } else {
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }



    /**
     * 显示是否居中的Toast
     */
    public static void showText(Context mContext, String str, boolean isCenter) {
        View view = View.inflate(mContext, R.layout.toast_layout, null);
        TextView mTvToast = (TextView) view.findViewById(R.id.tv_toast);
        LinearLayout llToastLayout = (LinearLayout) view.findViewById(R.id.ll_toast_layout);
        ViewUtils.setPadding(llToastLayout, DensityUtils.dp2px(mContext, 60), DensityUtils.dp2px(mContext, 16), DensityUtils.dp2px(mContext, 60), DensityUtils.dp2px(mContext, 16));
        ViewUtils.setTextSize(mTvToast, ResourceUtils.getInteger(R.integer.text_title_size));
        if (toast == null || !TextUtils.equals(str, msg)) {
            toast = new Toast(mContext);
            mTvToast.setText(str);
            toast.setView(view);
            if (isCenter)
                toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            msg = str;
        } else {
            mTvToast.setText(str);
            toast.show();
        }
    }
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
