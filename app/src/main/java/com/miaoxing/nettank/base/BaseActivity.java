package com.miaoxing.nettank.base;

import android.content.Context;

import com.miaoxing.nettank.view.WaitingDialog;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    private WaitingDialog mWaitingDialog;

    protected void showWaitingDialog() {
        if (mWaitingDialog == null) {
            mWaitingDialog = new WaitingDialog(this);
        } else if (mWaitingDialog.isShowing()) {
            return;
        }

        mWaitingDialog.show();
    }

    protected void hideWaitingDialog() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        hideWaitingDialog();
        super.onPause();
    }

    public Context getContext() {
        return this;
    }

}
