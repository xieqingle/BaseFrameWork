package com.cesecsh.baseframelibrary.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.cesecsh.baseframelibrary.R;
import com.cesecsh.baseframelibrary.manager.ActivityManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 上海中电
 * on 2017/1/4
 */

public class PermissionActivity extends RxAppCompatActivity {
    private PermissionListener mListener;
    private Context mContext;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getInstance().setActivity(this);
    }

    public void requestPermission(PermissionListener listener, String... requestPermissions) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            this.mListener = listener;
            List<String> permissions = new ArrayList<>();
            for (String requestPermission : requestPermissions) {
                if (ContextCompat.checkSelfPermission(this, requestPermission) != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(requestPermission);
                }
            }
            if (!permissions.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), 1);
            } else {
                listener.onGranted();
            }
        } else {
//            ToastView.getInstance().toast(this, "权限已开通");
            listener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                        showMissingPermissionDialog();
                    }
                }
        }
    }

    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityManager.getInstance().getTaskTop());
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_permission_help_text);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, (dialog, which) -> ActivityManager.getInstance().closeAllActivity())
                .setPositiveButton(R.string.settings, (dialog, which) -> startAppSettings());
        builder.setCancelable(false);
        builder.show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(this);
    }
}
