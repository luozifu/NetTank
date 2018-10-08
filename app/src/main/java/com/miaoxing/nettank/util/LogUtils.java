package com.miaoxing.nettank.util;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

/**
 *
 * @Date : 2018/8/9
 * @Desc : 打印工具类
 */
public class LogUtils {
    /**
     * 打印Bundle中内容
     */
    public static void logBundle(String tag, Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n======Bundle======");
        for (String key : bundle.keySet()) {
            sb.append("\nkey: ").append(key);
            sb.append(" ; value: ").append(bundle.get(key));
        }
        sb.append("\n======Bundle======\n");
        Log.d(tag, sb.toString());
    }

    /**
     * 打印Map中内容
     */
    public static void logMap(String tag, Map map) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n======Map======");
        for (Object key : map.keySet()) {
            sb.append("\nkey: ").append(key);
            sb.append(" ; value: ").append(map.get(key));
        }
        sb.append("\n======Map======\n");
        Log.d(tag, sb.toString());
    }
}
