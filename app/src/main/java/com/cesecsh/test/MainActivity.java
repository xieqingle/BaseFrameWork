package com.cesecsh.test;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cesecsh.baseframelibrary.BuildConfig;
import com.cesecsh.baseframelibrary.LogHelper.AppLog;
import com.cesecsh.baseframelibrary.permission.PermissionActivity;
import com.cesecsh.baseframelibrary.permission.PermissionListener;
import com.cesecsh.baseframework.R;

import java.util.List;

public class MainActivity extends PermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG)
            AppLog.isShow(true);
    }

    public void onClick(View view) {
        requestPermission(new PermissionListener() {
            @Override
            public void onGranted() {
                System.out.println("所有权限都允许了");
                Toast.makeText(MainActivity.this, "所有权限都允许了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
//                System.out.println(deniedPermissions.toArray());
                Toast.makeText(MainActivity.this, deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.RECORD_AUDIO);
    }

    public void onClick1(View view) {
        startActivity(new Intent(this, VerticalScrollActivity.class));
    }
}

