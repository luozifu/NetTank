package com.miaoxing.nettank.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    private View mLoadingView;

    protected void showLoading() {
        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        if (mLoadingView == null) {
            mLoadingView = new ProgressBar(this);
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        decorView.addView(mLoadingView, params);
    }

    protected void hideLoading() {
        if (mLoadingView != null) {
            FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
            decorView.removeView(mLoadingView);
        }
    }

    public Context getContext() {
        return this;
    }

}
