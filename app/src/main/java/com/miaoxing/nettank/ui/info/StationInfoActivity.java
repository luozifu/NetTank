package com.miaoxing.nettank.ui.info;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;

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

    private StationDetailFragment mStationDetailFragment;
    private StationAlarmFragment mStationAlarmFragment;
    private StationRecordFragment mStationRecordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statin_info);
        ButterKnife.bind(this);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    //罐存详情
                    case R.id.rb_detail:
                        if(null == mStationDetailFragment){
                        }
                        break;
                    //报警
                    case R.id.rb_alarm:
                        break;
                    //进油记录
                    case R.id.rb_record:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
