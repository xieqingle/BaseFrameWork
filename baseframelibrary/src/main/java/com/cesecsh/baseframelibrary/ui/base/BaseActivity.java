package com.cesecsh.baseframelibrary.ui.base;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by RockQ on 2017/3/30
 */

public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    /**
     * 用来存储控件
     */
    private SparseArray<View> mViews;

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initListener();

    protected abstract void processClick(View view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
        initListener();
    }

    protected <E extends View> E findViews(int id) {
        E view = (E) mViews.get(id);
        if (view == null) {
            view = (E) findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    protected <E extends View> void setOnclick(E view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
