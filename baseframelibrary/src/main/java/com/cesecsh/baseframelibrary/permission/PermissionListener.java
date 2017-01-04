package com.cesecsh.baseframelibrary.permission;

import java.util.List;

/**
 * Created by 上海中电
 * on 2017/1/4
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);

}
