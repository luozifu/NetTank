package com.miaoxing.nettank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.main.MainActivity;
import com.miaoxing.nettank.util.LanguageUtils;
import com.miaoxing.nettank.util.SPUtils;
import com.miaoxing.nettank.util.ToastUtils;
import com.miaoxing.nettank.view.dialog.BottomPickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_language)
    TextView tvLanguage;

    private BottomPickerFragment mPickerFragment;

    private String mLanguage;
    private String[] optionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //初始化
        mLanguage = (String) SPUtils.get(getContext(),
                Constant.PREFERENCES_LANGUAGE_KEY, "简体中文");
        tvLanguage.setText(mLanguage);
        optionArray = getResources().getStringArray(R.array.language);
    }

    @OnClick({R.id.btn_login, R.id.tv_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击登录
            case R.id.btn_login:
                String userName = etName.getText().toString();
                String password = etPwd.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.showToast(this, R.string.tip_user_name);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToast(this, R.string.tip_password);
                    return;
                }
                //发起登录，成功则跳转到主界面
                showWaitingDialog();
                ApiClient.getService()
                        .login(userName, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result<LoginResponse>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result<LoginResponse> result) {
                                hideWaitingDialog();
                                if (result.getCode() == Constant.CODE_SUCCESS) {
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    SPUtils.put(getContext(), Constant.PREFERENCES_USER_KEY, userName);
                                    finish();
                                } else {
                                    ToastUtils.showToast(getContext(), getString(R.string.tip_account_error));
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                hideWaitingDialog();
                                ToastUtils.showToast(getContext(), R.string.tip_net_error);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            //点击选择语言
            case R.id.tv_language:
                if (null == mPickerFragment) {
                    mPickerFragment = new BottomPickerFragment();
                }
                mPickerFragment.setOptionArray(optionArray);
                if (mLanguage.equals("简体中文")) {
                    mPickerFragment.setSelectedPosition(0);
                } else {
                    mPickerFragment.setSelectedPosition(1);
                }
                mPickerFragment.setOnItemSelectedListener((text, position) -> {
                    String temp = optionArray[position];
                    if (!temp.equals(mLanguage)) {
                        mLanguage = temp;
                        LanguageUtils.changeLocale(getContext(),mLanguage);
                        Intent intent = new Intent(this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                mPickerFragment.show(getSupportFragmentManager(), TAG);
                break;
        }
    }
}
