package com.cesecsh.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cesecsh.baseframelibrary.ui.viewUtils.ScreenFitUtils;
import com.cesecsh.baseframework.R;


public class ScreenFitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ScreenFitUtils.auto(this);
    }
}
