package com.cesecsh.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cesecsh.baseframelibrary.ui.view.IVProgress;
import com.cesecsh.baseframework.R;

public class ProgressImageActivity extends AppCompatActivity {

    private int progress;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_image);
        IVProgress ivProgress = (IVProgress) findViewById(R.id.image);
        Thread thread = new Thread(() -> {
            while (flag) {
                try {
                    Thread.sleep(200);
                    ivProgress.setProgress(++progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (progress >= 100) {
                    flag = false;
                }
            }
        });
        thread.start();



    }
}
