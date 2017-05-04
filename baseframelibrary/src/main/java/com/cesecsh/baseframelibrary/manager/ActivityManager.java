package com.cesecsh.baseframelibrary.manager;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by 上海中电
 * on 2017/1/4
 */

public class ActivityManager {
    private ArrayList<Activity> mActivities;
    private static ActivityManager activityManager = null;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (ActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

    public void setActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new ArrayList<>();
        }
        mActivities.add(activity);
    }

    public Activity getActivity(int position) {
        if (mActivities != null && mActivities.size() > position)
            return mActivities.get(position);
        return null;
    }

    public void remove(Activity activity) {
        if (mActivities != null && mActivities.contains(activity))
            mActivities.remove(activity);
    }


    public Activity getTaskTop() {
        if (mActivities != null)
            return mActivities.get(mActivities.size() - 1);
        return null;
    }

    public void closeAllActivity() {
        if (mActivities != null && mActivities.size() > 0) {
            mActivities.stream().filter(mActivity -> !mActivity.isFinishing()).forEach(Activity::finish);
//            for (Activity mActivity : mActivities) {
//                if (!mActivity.isFinishing()) {
//                    mActivity.finish();
//                }
//            }
        }
    }
}
