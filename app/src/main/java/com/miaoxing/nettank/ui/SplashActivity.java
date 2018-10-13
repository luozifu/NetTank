package com.miaoxing.nettank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.ui.main.MainActivity;
import com.miaoxing.nettank.ui.login.LoginActivity;
import com.miaoxing.nettank.util.SPUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Message message = new Message();
        new Handler(msg -> {
            jump2Activity();
            return false;
        }).sendMessageDelayed(message,2000);
    }

    private void jump2Activity() {
        String user = (String) SPUtils.get(getContext(),
                Constant.PREFERENCES_USER_KEY,"");
        Intent intent;
        if(TextUtils.isEmpty(user)){
            intent = new Intent(getContext(),LoginActivity.class);
        }else {
            intent = new Intent(getContext(),MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
