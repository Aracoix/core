package com.ihomefnt.commonlib.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by Aracoix on 2016/7/18.
 */

public class ActivityLifecycleHelper implements Application.ActivityLifecycleCallbacks {

    private static Stack<Activity> activityStack;
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;
    public ActivityLifecycleHelper() {
        activityStack = new Stack<>();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
    }
    public  boolean isApplicationVisible() {
        return started > stopped;
    }

    public  boolean isApplicationInForeground() {
        // 当所有 Activity 的状态中处于 resumed 的大于 paused 状态的，即可认为有Activity处于前台状态中
        return resumed > paused;
    }
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }
    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        if (activityStack != null&&activityStack.size()!=0) {
                return activityStack.lastElement();
        }else {
            return null;
        }
    }
    public Stack<Activity> getActivityStack(){
        if (activityStack!=null){
            return activityStack;
        }else {
            return null;
        }
    }
    public Activity getPreActivity() {
        int size = activityStack.size();
        if(size < 2)return null;
        return activityStack.elementAt(size - 2);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                Activity activity = activityStack.get(i);
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }

    public void removeActivity(Activity activity) {
        if (activity != null&&activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }
    public boolean hasActivity(Activity activity) {
        return activity != null && activityStack.contains(activity);
    }
    public void removeAllWithoutItself(Activity activity) {
        activityStack.clear();
        addActivity(activity);
    }
}
