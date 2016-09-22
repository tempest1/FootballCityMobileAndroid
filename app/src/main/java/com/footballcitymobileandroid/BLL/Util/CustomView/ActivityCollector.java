package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/4/19.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static List<Activity> activityNew = new ArrayList<Activity>();

    public static List<Activity> activitieInvestment = new ArrayList<Activity>();

    public static List<Activity> activitieCommon = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }
    public static void addInvestmentActivity(Activity activity) {
        activitieInvestment.add(activity);
    }
    public static void addActivityNew(Activity activity) {
        activityNew.add(activity);
    }

    public static void addActivityCommon(Activity activity) {
        activitieCommon.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void removeActivityNew(Activity activity) {
        activityNew.remove(activity);
    }



    public static void removeInvestmentActivity(Activity activity) {
        activitieInvestment.remove(activity);
    }
    public static void removeCommonActivity(Activity activity) {
        activitieCommon.remove(activity);
    }
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
    public static void finishAllNew() {
        for (Activity activity : activityNew) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
    public static void finishAllInvestment() {
        for (Activity activity : activitieInvestment) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishAllCommon() {
        for (Activity activity : activitieCommon) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
