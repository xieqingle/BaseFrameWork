package com.cesecsh.baseframelibrary.ui.carouse.adapter;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.cesecsh.baseframelibrary.ui.carouse.CarouseView;
import com.cesecsh.baseframelibrary.ui.carouse.HintView;

import java.lang.reflect.Field;

/**
 * BaseFramework
 * Created by RockQ on 2017/3/7.
 */

public abstract class LoopPageAdapter extends PagerAdapter {
    private CarouseView mCarouseView;
    private SparseArray<View> mViewList = new SparseArray<>();
    private int realCount;

    public abstract View getView(ViewGroup parent, int position);

    public abstract int getRealCount();

    public LoopPageAdapter(CarouseView mCarouseView) {
        this.mCarouseView = mCarouseView;
        mCarouseView.setHintViewDelegate(new LoopHintViewDelegate());
    }

    public void setCurrent(int current) {
        try {
            Field field = ViewPager.class.getDeclaredField("mCurItem");
            field.setAccessible(true);
            field.set(mCarouseView.getViewPager(), current);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private class LoopHintViewDelegate implements CarouseView.HintViewDelegate {

        @Override
        public void setCurrentItem(int position, HintView hintView) {
            if (hintView != null && getRealCount() > 0) {
                hintView.setCurrent(position);
            }
        }

        @Override
        public void initView(int length, int gravity, HintView hintView) {
            if (hintView != null)
                hintView.initView(getRealCount(), gravity);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position % getRealCount();
        View itemView = finViewByPosition(container, realPosition);
        container.addView(itemView);
        return itemView;
    }

    protected View finViewByPosition(ViewGroup container, int position) {
        for (int i = 0; i < mViewList.size(); i++) {
            int key = mViewList.keyAt(i);
            if (key == position && mViewList.get(key).getParent() == null) {
                return mViewList.get(key);
            }
        }
        View view = getView(container, position);
        mViewList.put(position, view);
        return mViewList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        initPosition();
    }

    @Override
    public void notifyDataSetChanged() {
        mViewList.clear();
        initPosition();
        super.notifyDataSetChanged();
    }

    protected void initPosition() {
        if (mCarouseView.getViewPager().getCurrentItem() == 0 && getRealCount() > 0) {
            int half = Integer.MAX_VALUE / 2;
            int start = half - half % getRealCount();
            setCurrent(start);
        }
    }

    @Override
    public int getCount() {
        return getRealCount() <= 0 ? getRealCount() : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
