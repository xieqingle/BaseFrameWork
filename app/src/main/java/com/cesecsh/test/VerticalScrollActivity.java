package com.cesecsh.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

import com.cesecsh.baseframework.R;

public class VerticalScrollActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertival_scroll);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.vf);
        for (int i = 0; i < 3; i++) {
            viewFlipper.addView(View.inflate(this, R.layout.item_vertical_scroll, null));
        }
    }
}
