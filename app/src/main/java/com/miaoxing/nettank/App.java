package com.miaoxing.nettank;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.util.ConfigurationUtils;
import com.miaoxing.nettank.util.SPUtils;

import androidx.annotation.Nullable;

public class App extends Application {

    private static App mContext;

    private String language;

    private static final String TAG = "APP";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Log.e(TAG,"onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        language = (String) SPUtils.get(base,
                Constant.PREFERENCES_LANGUAGE_KEY, "简体中文");
        super.attachBaseContext(ConfigurationUtils.changeLocale(base,language));
        Log.e(TAG,"attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Nullable
    public static Context getContext() {
        return mContext;
    }

}
