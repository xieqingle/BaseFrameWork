package com.cesecsh.baseframelibrary.net.constant;

import android.util.SparseArray;

/**
 * Created by 上海中电
 * on 2016/10/27
 */

public class CodeState {
    public static final int SUCCESS = 200;// 加载成功
    public static final int PARAM_ERROR = 404;// 参数错误
    public static final int PERMISSION_DENY = 405;//权限不足
    public static final int LOGIN_OVERDUE = 406;// 登录过期
    public static final int SERVER_ERROR = 500;// 服务器错误
    public static SparseArray<String> messages;
    public static int mCurrentCode = SUCCESS;

    public static String getMessage(int codeState) {
        getData();
        return messages.get(codeState);
    }

    public static void getData() {
        if (messages == null) {
            messages = new SparseArray<>();
            messages.put(SUCCESS, "数据加载成功");
            messages.put(PARAM_ERROR, "参数填写错误");
            messages.put(PERMISSION_DENY, "您的权限不足");
            messages.put(LOGIN_OVERDUE, "您的登录过期");
            messages.put(SERVER_ERROR, "操作失败，请重试");
        }
    }
}
