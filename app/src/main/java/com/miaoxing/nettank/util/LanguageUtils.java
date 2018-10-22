package com.miaoxing.nettank.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.miaoxing.nettank.constant.Constant;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class LanguageUtils {

    public static HashMap<String, Locale> sLanguages = new HashMap<String, Locale>(2) {
        {
            put("简体中文", Locale.SIMPLIFIED_CHINESE);
            put("English", Locale.ENGLISH);
        }
    };

    public static void changeLocale(Context context, String s) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = LanguageUtils.sLanguages.get(s);
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
        SPUtils.put(context, Constant.PREFERENCES_LANGUAGE_KEY, s);
    }

    public static Context createLocale(Context context,String s){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = LanguageUtils.sLanguages.get(s);
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }

}
