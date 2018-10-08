package com.miaoxing.nettank;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

public class App extends Application {

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Nullable
    public static Context getContext() {
        return mContext;
    }

}
