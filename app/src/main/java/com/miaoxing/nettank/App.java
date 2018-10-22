package com.miaoxing.nettank;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.Nullable;

public class App extends Application {

    private static App mContext;

    private static final String TAG = "APP";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
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
