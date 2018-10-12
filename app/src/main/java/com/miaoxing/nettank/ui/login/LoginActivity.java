package com.miaoxing.nettank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.ui.MainActivity;
import com.miaoxing.nettank.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_language)
    TextView tvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        
    }

    @OnClick({R.id.btn_login, R.id.tv_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击登录
            case R.id.btn_login:
                String userName = etName.getText().toString();
                String password = etPwd.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    ToastUtils.showToast(this,R.string.tip_user_name);
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    ToastUtils.showToast(this,R.string.tip_password);
                    return;
                }
                //todo 发起登录，成功则跳转到主界面
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            //点击选择语言
            case R.id.tv_language:
                //todo 选择语言弹出框
                
                break;
        }
    }
}
