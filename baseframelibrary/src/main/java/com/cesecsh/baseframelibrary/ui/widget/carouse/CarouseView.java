package com.cesecsh.baseframelibrary.ui.widget.carouse;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cesecsh.baseframelibrary.R;
import com.cesecsh.baseframelibrary.ui.widget.carouse.adapter.LoopPageAdapter;
import com.cesecsh.baseframelibrary.ui.widget.carouse.hintView.ColorPointHintView;
import com.cesecsh.baseframelibrary.ui.viewUtils.DensityUtils;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * BaseFramework
 * Created by RockQ on 2017/3/7.
 */

public class CarouseView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private WeakReference<Context> wfContext;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;

    private long mRecentTouchTime;

    // 播放延迟
    private int delay;

    // hint位置
    private int gravity;

    // hint颜色
    private int color;

    // hint 背景透明度 0-100
    private int alpha;

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private int padding;

    private View mHintView;
    private Timer timer;

    private HintViewDelegate mHintViewDelegate = new HintViewDelegate() {
        @Override
        public void setCurrentItem(int position, HintView hintView) {
            if (hintView != null)
                hintView.setCurrent(position);
        }

        @Override
        public void initView(int length, int gravity, HintView hintView) {
            if (hintView != null)
                hintView.initView(length, gravity);
        }
    };
    private HintViewDelegate hintViewDelegate;
    private TimeTaskHandler mHandler = new TimeTaskHandler(this);
    private boolean automatic;

    public CarouseView(Context context) {
        this(context, null, 0);
    }

    public CarouseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        wfContext = new WeakReference<>(context);
        if (mViewPager != null)
            removeView(mViewPager);
        initAttrs(attrs);
        mViewPager = new ViewPager(wfContext.get());
        mViewPager.setId(R.id.carouse_inner);
        mViewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mViewPager);
        initHint(new ColorPointHintView(wfContext.get(), Color.parseColor("#E3AC42"), Color.parseColor("#88ffffff")));
        mGestureDetector = new GestureDetector(wfContext.get(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mOnItemClickListener != null) {
                    if (mAdapter instanceof LoopPageAdapter)
                        mOnItemClickListener.onItemClick(mViewPager.getCurrentItem() % ((LoopPageAdapter) mAdapter).getRealCount());
                    else
                        mOnItemClickListener.onItemClick(mViewPager.getCurrentItem());
                }
                return super.onSingleTapUp(e);
            }
        });
    }

    /**
     * 初始化hintView布局
     *
     * @param hintView
     */
    private void initHint(HintView hintView) {
        if (mHintView != null)
            removeView(mHintView);

        if (hintView == null || !(hintView instanceof HintView))
            return;
        mHintView = (View) hintView;
        loadHintView();
    }

    /**
     * 加载 hintView 容器
     */
    private void loadHintView() {
        addView(mHintView);
        mHintView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mHintView.setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setAlpha((int) (alpha / 0.4)); // 将原本的0-255限制在0-100之间，以便和UI设计的一致
        mHintView.setBackgroundDrawable(gradientDrawable);
        mHintViewDelegate.initView(mAdapter == null ? 0 : mAdapter.getCount(), gravity, (HintView) mHintView);

    }

    /**
     * 初始化attrs属性
     *
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs) {
        paddingLeft = DensityUtils.dp2px(wfContext.get(), 10);
        paddingTop = DensityUtils.dp2px(wfContext.get(), 10);
        paddingRight = DensityUtils.dp2px(wfContext.get(), 10);
        paddingBottom = DensityUtils.dp2px(wfContext.get(), 10);
        TypedArray typedArray = wfContext.get().obtainStyledAttributes(attrs, R.styleable.CarouseView);
        try {
            gravity = typedArray.getLayoutDimension(R.styleable.CarouseView_carouseGravity, gravity);
            delay = typedArray.getInteger(R.styleable.CarouseView_carousePlayDelay, 1);
            color = typedArray.getColor(R.styleable.CarouseView_carousePlayColor, color);
            alpha = typedArray.getInt(R.styleable.CarouseView_carouseHintAlpha, alpha);
            automatic = typedArray.getBoolean(R.styleable.CarouseView_automatic, false);
            paddingLeft = (int) typedArray.getDimension(R.styleable.CarouseView_carouseHintPaddingLeft, paddingLeft);
            paddingTop = (int) typedArray.getDimension(R.styleable.CarouseView_carouseHintPaddingTop, paddingTop);
            paddingRight = (int) typedArray.getDimension(R.styleable.CarouseView_carouseHintPaddingRight, paddingRight);
            paddingBottom = (int) typedArray.getDimension(R.styleable.CarouseView_carouseHintPaddingBottom, paddingBottom);
            padding = (int) typedArray.getDimension(R.styleable.CarouseView_carousePadding, 0);
            if (padding > 0) {
                paddingLeft = padding;
                paddingTop = padding;
                paddingRight = padding;
                paddingBottom = padding;
            }
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * 开始播放
     */
    public void startPlay() {
        if (delay <= 0 || mAdapter == null || mAdapter.getCount() <= 1) {
            return;
        }
        if (mAdapter instanceof LoopPageAdapter && ((LoopPageAdapter) mAdapter).getRealCount() <= 1) {
            return;
        }
        if (timer != null)
            timer.cancel();
        timer = new Timer();
        timer.schedule(new WeakTimerTask(this), delay, delay);
    }

    /**
     * 停止播放
     */
    public void stopPlay() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        if (isPlaying())
            stopPlay();
    }

    /**
     * 回复播放
     */
    public void resume() {
        if (automatic)
            startPlay();
    }

    // set attributes

    /**
     * 时候是在播放
     *
     * @return
     */
    public boolean isPlaying() {
        return timer != null;
    }

    /**
     * 设置提示view的位置
     */
    public void setHintPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
        mHintView.setPadding(DensityUtils.dp2px(getContext(), paddingLeft), DensityUtils.dp2px(getContext(), paddingTop), DensityUtils.dp2px(getContext(), paddingRight), DensityUtils.dp2px(getContext(), paddingBottom));
    }

    public void setPadding(int padding) {
        paddingLeft = padding;
        paddingTop = padding;
        paddingRight = padding;
        paddingBottom = padding;
        mHintView.setPadding(DensityUtils.dp2px(getContext(), paddingLeft), DensityUtils.dp2px(getContext(), paddingTop), DensityUtils.dp2px(getContext(), paddingRight), DensityUtils.dp2px(getContext(), paddingBottom));
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 设置自动轮播的时间
     *
     * @param delay
     */
    public void setPlayDelay(int delay) {
        if (!automatic)
            return;
        this.delay = delay;
        startPlay();
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public void setAdapter(PagerAdapter adapter) {
        adapter.registerDataSetObserver(new JPageObserver());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        dataSetChanger();
    }

    public void setHintView(HintView hintView) {
        if (hintView == null)
            return;
        initHint(hintView);
    }

    /**
     * 用来实现adapter的notifyDataChanged通知HintView变化
     */
    private void dataSetChanger() {
        if (mHintView != null) {
            mHintViewDelegate.initView(((LoopPageAdapter) mAdapter).getRealCount(), gravity, (HintView) mHintView);
            mHintViewDelegate.setCurrentItem(mViewPager.getCurrentItem(), (HintView) mHintView);
        }
        if (automatic)
            startPlay();
    }

    /**
     * 为了实现触摸时和过后一定时间内不滑动，这里拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mRecentTouchTime = System.currentTimeMillis();
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mAdapter instanceof LoopPageAdapter)
            mHintViewDelegate.setCurrentItem(position % ((LoopPageAdapter) (mAdapter)).getRealCount(), (HintView) mHintView);
        else mHintViewDelegate.setCurrentItem(position, (HintView) mHintView);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setHintViewDelegate(HintViewDelegate hintViewDelegate) {
        this.hintViewDelegate = hintViewDelegate;
    }

    public interface HintViewDelegate {
        void setCurrentItem(int position, HintView hintView);

        void initView(int length, int gravity, HintView hintView);
    }

    private static class WeakTimerTask extends TimerTask {
        private WeakReference<CarouseView> wfCarouseView;

        public WeakTimerTask(CarouseView carouseView) {
            wfCarouseView = new WeakReference<>(carouseView);
        }

        @Override
        public void run() {
            CarouseView carouseView = wfCarouseView.get();
            if (carouseView != null) {
                if (carouseView.isShown() && System.currentTimeMillis() - carouseView.mRecentTouchTime > carouseView.delay) {
                    carouseView.mHandler.sendEmptyMessage(0);
                }
            } else cancel();
        }
    }

    private class JPageObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            dataSetChanger();
        }

        @Override
        public void onInvalidated() {
            dataSetChanger();
        }
    }

    private final class TimeTaskHandler extends Handler {
        private WeakReference<CarouseView> wfCarouseView;

        public TimeTaskHandler(CarouseView carouseView) {
            wfCarouseView = new WeakReference<>(carouseView);
        }

        @Override
        public void handleMessage(Message msg) {
            CarouseView carouseView = wfCarouseView.get();
            int current = carouseView.getViewPager().getCurrentItem() + 1;
            if (current > carouseView.mAdapter.getCount()) {
                current = 0;
            }
            carouseView.getViewPager().setCurrentItem(current);
            carouseView.mHintViewDelegate.setCurrentItem(current, (HintView) carouseView.mHintView);
            if (carouseView.mAdapter.getCount() <= 1) carouseView.stopPlay();
        }
    }
}
