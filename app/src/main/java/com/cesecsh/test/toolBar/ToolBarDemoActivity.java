package com.cesecsh.test.toolBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;
import com.cesecsh.baseframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarDemoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.x_tool_bar)
    XToolBar mXToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_demo);
        ButterKnife.bind(this);
//        mXToolBar.setMiddleViewImage(-1, -1, R.mipmap.ic_launcher, 0);
//        mXToolBar.setMiddleTextRightImage(R.mipmap.back,60,60,20);
//        mXToolBar.setRightCheckBoxHeight(DensityUtils.dp2px(this, 25));
//        mXToolBar.setRightCheckBoxWidth(DensityUtils.dp2px(this, 40));
        mXToolBar.setRightCheckBoxPadding(DensityUtils.dp2px(this, 13), DensityUtils.dp2px(this, 5), DensityUtils.dp2px(this, 26), DensityUtils.dp2px(this, 5));
        mXToolBar.setMiddleTextRightImage(R.mipmap.back, 40, 40, 20);
        //        ViewUtils.setWidth(cbTitleBarChecked,);
//        ViewUtils.setHeight(cbTitleBarChecked, DensityUtils.dp2px(ResourceUtils.getContext(), 40));

//        mXToolBar.getMiddleEditText().setCompoundDrawablesWithIntrinsicBounds(R.mipmap.search,0,0,0);
    }

}
