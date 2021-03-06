package com.miaoxing.nettank.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.service.PullService;
import com.miaoxing.nettank.service.PullUtils;
import com.miaoxing.nettank.ui.login.LoginActivity;
import com.miaoxing.nettank.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.sw)
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        boolean isChecked = (Boolean) SPUtils.get(this,
                Constant.PREFERENCES_ALARM_KEY,true);
        sw.setChecked(isChecked);
        sw.setOnCheckedChangeListener((buttonView, isChecked1) -> {
            //todo 开启或者关闭推送
            if(isChecked1){
                PullUtils.startPullService(this,30,PullService.class,PullService.ACTION);
            }else {
                PullUtils.stopPullService(this,PullService.class,PullService.ACTION);
            }
            SPUtils.put(this,Constant.PREFERENCES_ALARM_KEY,isChecked);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SPUtils.put(this,Constant.PREFERENCES_ALARM_KEY,sw.isChecked());
    }

    @OnClick({R.id.iv_back, R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回上一页
            case R.id.iv_back:
                break;
            //退出登录
            case R.id.tv_quit:
                SPUtils.put(getContext(),Constant.PREFERENCES_USER_KEY,"");
                Intent intent = new Intent(this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
        finish();
    }
}
