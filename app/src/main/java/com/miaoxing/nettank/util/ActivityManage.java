package com.miaoxing.nettank.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class ActivityManage {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
