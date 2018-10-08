package com.miaoxing.nettank.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 *
 * @Date : 2018/8/4
 * @Desc : 屏幕尺寸获取和计算工具
 */
public class ScreenUtils {

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        Resources res = Resources.getSystem();
        int resId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return res.getDimensionPixelSize(resId);
        }
        return dp2px(25);
    }

    /**
     * 获取状态栏高度
     */
    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        int height = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            height = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return height == 0 ? dp2px(56) : height;
    }

    public static int dp2px(float dipValue) {
        Resources res = Resources.getSystem();
        return (int) (dipValue * res.getDisplayMetrics().density);
    }
}
