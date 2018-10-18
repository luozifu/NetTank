package com.miaoxing.nettank.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author : Luozifu
 * @date : 2018/10/18
 */

public class ConfigurationUtils {

    public static HashMap<String, Locale> sLanguages = new HashMap<String, Locale>(2) {
        {
            put("简体中文", Locale.SIMPLIFIED_CHINESE);
            put("English", Locale.ENGLISH);
        }
    };

    public static Context changeLocale(Context context, String s) {

        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = ConfigurationUtils.sLanguages.get(s);
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
        }
        return context.createConfigurationContext(config);
    }

}
