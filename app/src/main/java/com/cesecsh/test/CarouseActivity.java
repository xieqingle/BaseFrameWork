package com.cesecsh.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cesecsh.baseframelibrary.ui.widget.carouse.CarouseView;
import com.cesecsh.baseframelibrary.ui.widget.carouse.HintView;
import com.cesecsh.baseframelibrary.ui.widget.carouse.OnItemClickListener;
import com.cesecsh.baseframelibrary.ui.widget.carouse.adapter.LoopPageAdapter;
import com.cesecsh.baseframework.R;

import java.util.ArrayList;
import java.util.List;

public class CarouseActivity extends AppCompatActivity {

    private CarouseView carouseView;
    private List<Integer> mDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouse);
        carouseView = (CarouseView) findViewById(R.id.carouse_view);
        mDrawables = new ArrayList<>();
        mDrawables.add(R.drawable.img1);
        mDrawables.add(R.drawable.img2);
        mDrawables.add(R.drawable.img3);
        mDrawables.add(R.drawable.img4);
        mDrawables.add(R.drawable.img5);
        MyAdapter adapter = new MyAdapter(carouseView);
//        HintView hintView = new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal);
//        carouseView.setHintView(hintView);
        carouseView.setAdapter(adapter);
        carouseView.setPlayDelay(1000);
        carouseView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(CarouseActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class MyAdapter extends LoopPageAdapter {

        public MyAdapter(CarouseView mCarouseView) {
            super(mCarouseView);
        }

        @Override
        public View getView(ViewGroup parent, int position) {
            ImageView imageView = new ImageView(CarouseActivity.this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(mDrawables.get(position));
            return imageView;
        }

        @Override
        public int getRealCount() {
            return mDrawables.size();
        }
    }
}
