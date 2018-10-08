package com.miaoxing.nettank.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 *
 * @Date : 2018/8/9
 * @Desc :
 */
public class ToastUtils {
    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, int msgRes) {
        if (sToast == null) {
            sToast = Toast.makeText(context, msgRes, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msgRes);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();
    }

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();
    }
}
