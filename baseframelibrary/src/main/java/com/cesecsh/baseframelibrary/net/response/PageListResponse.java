package com.cesecsh.baseframelibrary.net.response;


import com.cesecsh.baseframelibrary.manager.ActivityManager;
import com.cesecsh.baseframelibrary.manager.AlertDialogManager;
import com.cesecsh.baseframelibrary.net.constant.CodeState;
import com.cesecsh.baseframelibrary.net.json.PageListInfo;
import com.cesecsh.baseframelibrary.net.json.PageListJson;
import com.cesecsh.baseframelibrary.ui.view.ToastView;

import rx.Subscriber;

/**
 * Created by 上海中电
 * on 2016/12/29
 */

public abstract class PageListResponse<T> extends Subscriber<PageListJson> {

    @Override
    public void onNext(PageListJson pageListJson) {
        if (pageListJson != null) {
            switch (Integer.valueOf(pageListJson.getCode())) {
                case CodeState.SUCCESS:
                    onSuccess(pageListJson.getPageListInfo());
                    break;
                case CodeState.LOGIN_OVERDUE:
                    AlertDialogManager.showLoginOverdue(ActivityManager.getInstance().getTaskTop());
                    break;
                case CodeState.PERMISSION_DENY:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.PERMISSION_DENY), ToastView.TYPE_WARNING);
                    break;
                case CodeState.PARAM_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), pageListJson.getMessage(), ToastView.TYPE_WARNING);
                    break;
                case CodeState.SERVER_ERROR:
                    ToastView.getInstance().toast(ActivityManager.getInstance().getTaskTop(), CodeState.getMessage(CodeState.SERVER_ERROR), ToastView.TYPE_WARNING);
                    break;
                default:
            }
        }

    }

    public abstract void onSuccess(PageListInfo<T> pageListInfo);
}
