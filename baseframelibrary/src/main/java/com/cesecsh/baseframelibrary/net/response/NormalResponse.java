package com.cesecsh.baseframelibrary.net.response;


import com.cesecsh.baseframelibrary.manager.ActivityManager;
import com.cesecsh.baseframelibrary.manager.AlertDialogManager;
import com.cesecsh.baseframelibrary.net.constant.CodeState;
import com.cesecsh.baseframelibrary.net.json.NormalJson;
import com.cesecsh.baseframelibrary.ui.widget.ToastView;

import rx.Subscriber;

/**
 * Created by 上海中电
 * on 2016/12/29
 */

public abstract class NormalResponse<T> extends Subscriber<NormalJson> {

    @Override
    public void onNext(NormalJson normalJson) {
        if (normalJson != null) {
            switch (Integer.valueOf(normalJson.getCode())) {
                case CodeState.SUCCESS:
                    onSuccess((T) normalJson.getObj());
                    break;
                case CodeState.LOGIN_OVERDUE:
                    AlertDialogManager.showLoginOverdue(ActivityManager.getInstance().getTaskTop());
                    break;
                case CodeState.PERMISSION_DENY:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.PERMISSION_DENY), ToastView.TYPE_WARNING);
                    break;
                case CodeState.PARAM_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), normalJson.getMessage(), ToastView.TYPE_WARNING);
                    break;
                case CodeState.SERVER_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.SERVER_ERROR), ToastView.TYPE_WARNING);
                    break;
                default:
            }
        }

    }

    public abstract void onSuccess(T t);
}
