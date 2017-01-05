package com.cesecsh.baseframelibrary.net.response;


import com.cesecsh.baseframelibrary.manager.ActivityManager;
import com.cesecsh.baseframelibrary.manager.AlertDialogManager;
import com.cesecsh.baseframelibrary.net.constant.CodeState;
import com.cesecsh.baseframelibrary.net.json.SimpleJson;
import com.cesecsh.baseframelibrary.ui.view.ToastView;

import rx.Subscriber;

/**
 * Created by 上海中电
 * on 2017/1/3
 */

public abstract class SimpleResponse<T> extends Subscriber<SimpleJson> {

    @Override
    public void onNext(SimpleJson simpleJson) {
        if (simpleJson != null) {
            switch (Integer.valueOf(simpleJson.getCode())) {
                case CodeState.SUCCESS:
                    onSuccess(simpleJson);
                    break;
                case CodeState.LOGIN_OVERDUE:
                    AlertDialogManager.showLoginOverdue(ActivityManager.getInstance().getTaskTop());
                    break;
                case CodeState.PERMISSION_DENY:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.PERMISSION_DENY), ToastView.TYPE_WARNING);
                    break;
                case CodeState.PARAM_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), simpleJson.getMessage(), ToastView.TYPE_WARNING);
                    break;
                case CodeState.SERVER_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.SERVER_ERROR), ToastView.TYPE_WARNING);
                    break;
                default:
            }
        }
    }


    public abstract void onSuccess(SimpleJson simpleJson);
}
