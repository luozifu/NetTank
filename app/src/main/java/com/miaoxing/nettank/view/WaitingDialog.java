package com.miaoxing.nettank.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.miaoxing.nettank.R;

/**
 * @author : Administrator
 * @Date : 2018/10/22 0022
 */
public class WaitingDialog extends Dialog {
    public WaitingDialog(Context context) {
        super(context, R.style.WaitingDialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_waiting);
    }
}
