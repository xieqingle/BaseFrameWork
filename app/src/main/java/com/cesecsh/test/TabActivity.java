package com.cesecsh.test;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesecsh.baseframelibrary.tabLayout.STabLayout;
import com.cesecsh.baseframework.R;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        STabLayout tabLayout = (STabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("current page is " + i);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(TabActivity.this, R.layout.layout_tab_layout_item, null);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(list.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
