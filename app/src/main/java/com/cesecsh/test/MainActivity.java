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
import com.cesecsh.test.toolBar.ToolBarDemoActivity;

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
                Toast.makeText(MainActivity.this, deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.RECORD_AUDIO);
    }

    public void onClick1(View view) {
        startActivity(new Intent(this, VerticalScrollActivity.class));
    }

    public void onClick2(View view) {
        startActivity(new Intent(this, CarouseActivity.class));
    }

    public void onClick3(View view) {
        startActivity(new Intent(this, DialogView.class));
    }

    public void onClick4(View view) {
        startActivity(new Intent(this, UploadFilesActivity.class));
    }

    public void onClick5(View view) {
        startActivity(new Intent(this, TabActivity.class));
    }

    public void onClick6(View view) {
        startActivity(new Intent(this, ProgressImageActivity.class));
    }

    public void onClick7(View view) {
        startActivity(new Intent(this, ToolBarDemoActivity.class));
    }

    public void onClick8(View view) {
        startActivity(new Intent(this, ScreenFitActivity.class));
    }
}

