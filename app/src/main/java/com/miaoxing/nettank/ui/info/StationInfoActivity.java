package com.miaoxing.nettank.ui.info;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StationInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.rb_detail)
    RadioButton rbDetail;
    @BindView(R.id.rb_alarm)
    RadioButton rbAlarm;
    @BindView(R.id.rb_record)
    RadioButton rbRecord;

    private StationDetailFragment mStationDetailFragment;
    private StationAlarmFragment mStationAlarmFragment;
    private StationDeliveryFragment mStationDeliveryFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statin_info);
        ButterKnife.bind(this);

        initRadioBtn();

        //默认选中油管信息界面
        mStationDetailFragment = StationDetailFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, mStationDetailFragment);
        transaction.commit();
        currentFragment = mStationDetailFragment;

        mRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                //罐存详情
                case R.id.rb_detail:
                    if (null == mStationDetailFragment) {
                        mStationDetailFragment = StationDetailFragment.newInstance();
                    }
                    switchFragment(mStationDetailFragment);
                    mTvTitle.setText(R.string.inv);
                    break;
                //报警
                case R.id.rb_alarm:
                    if (null == mStationAlarmFragment) {
                        mStationAlarmFragment = StationAlarmFragment.newInstance();
                    }
                    switchFragment(mStationAlarmFragment);
                    mTvTitle.setText(R.string.alarm);
                    break;
                //进油记录
                case R.id.rb_record:
                    if (null == mStationDeliveryFragment) {
                        mStationDeliveryFragment = StationDeliveryFragment.newInstance();
                    }
                    switchFragment(mStationDeliveryFragment);
                    mTvTitle.setText(R.string.delivery);
                    break;
            }
        });
    }

    private void initRadioBtn() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_tank_pressed);
        drawable.setBounds(0,0,20,20);
        rbDetail.setCompoundDrawablesRelativeWithIntrinsicBounds(null,drawable,null,null);
    }

    //Fragment切换
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.content, targetFragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
