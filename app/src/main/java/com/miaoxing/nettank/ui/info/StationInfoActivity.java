package com.miaoxing.nettank.ui.info;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.util.ScreenUtils;

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
    @BindView(R.id.iv_refresh)
    ImageView mIvRefresh;

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

        //默认选中油罐信息界面
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
                    mIvRefresh.setVisibility(View.VISIBLE);
                    break;
                //报警
                case R.id.rb_alarm:
                    if (null == mStationAlarmFragment) {
                        mStationAlarmFragment = StationAlarmFragment.newInstance();
                    }
                    switchFragment(mStationAlarmFragment);
                    mTvTitle.setText(R.string.alarm);
                    mIvRefresh.setVisibility(View.GONE);
                    break;
                //进油记录
                case R.id.rb_record:
                    if (null == mStationDeliveryFragment) {
                        mStationDeliveryFragment = StationDeliveryFragment.newInstance();
                    }
                    switchFragment(mStationDeliveryFragment);
                    mTvTitle.setText(R.string.delivery);
                    mIvRefresh.setVisibility(View.GONE);
                    break;
            }
        });
    }

    private void initRadioBtn() {
        int px = ScreenUtils.dp2px(30);
        Drawable drawable1 = getResources().getDrawable(R.drawable.sl_tank);
        drawable1.setBounds(0, 0, px, px);
        rbDetail.setCompoundDrawables(null, drawable1, null, null);

        Drawable drawable2 = getResources().getDrawable(R.drawable.sl_alarm);
        drawable2.setBounds(0, 0, px, px);
        rbAlarm.setCompoundDrawables(null, drawable2, null, null);

        Drawable drawable3 = getResources().getDrawable(R.drawable.sl_fuel);
        drawable3.setBounds(0, 0, px, px);
        rbRecord.setCompoundDrawables(null, drawable3, null, null);
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


    @OnClick({R.id.iv_back, R.id.iv_refresh})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            //返回
            case R.id.iv_back:
                finish();
                break;
            //刷新
            case R.id.iv_refresh:
                mStationDetailFragment.onClickRefresh();
                break;
        }
    }

}
