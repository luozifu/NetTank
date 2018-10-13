package com.miaoxing.nettank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.ui.setting.SettingActivity;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_oil)
    RecyclerView rvOil;
    @BindView(R.id.tv_station_amount)
    TextView tvStationAmount;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.tv_offline)
    TextView tvOffline;
    @BindView(R.id.rv_station)
    RecyclerView rvStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        //跳转到设置界面
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }
}
