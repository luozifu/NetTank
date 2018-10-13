package com.miaoxing.nettank.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Station;
import com.miaoxing.nettank.ui.info.StationInfoActivity;
import com.miaoxing.nettank.ui.main.adapter.FuelAdapter;
import com.miaoxing.nettank.ui.main.adapter.StationAdapter;
import com.miaoxing.nettank.ui.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_station_amount)
    TextView tvStationAmount;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.tv_offline)
    TextView tvOffline;
    @BindView(R.id.rv_station)
    RecyclerView rvStation;
    @BindView(R.id.rv_fuel)
    RecyclerView rvFuel;

    private List<Fuel> fuelList;
    private List<Station> stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mock();
        initView();
    }

    private void mock() {
        fuelList = new ArrayList<>();
        stationList = new ArrayList<>();
        for(int i=0;i<3;i++){
            Fuel fuel = new Fuel();
            fuel.fuelName = i+"#";
            fuel.capacity = 3000.0;
            fuel.fuelVol = 1131.2;
            fuelList.add(fuel);
        }
        for(int i=0;i<2;i++){
            Station station = new Station();
            station.stationName = "station"+i;
            stationList.add(station);
        }
    }

    private void initView() {
        LinearLayoutManager fuelManger = new LinearLayoutManager(getContext());
        fuelManger.setOrientation(RecyclerView.VERTICAL);
        rvFuel.setLayoutManager(fuelManger);
        rvFuel.setAdapter(new FuelAdapter(fuelList));
        StationAdapter stationAdapter = new StationAdapter(stationList);
        stationAdapter.setItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(),StationInfoActivity.class);
            startActivity(intent);
        });
        LinearLayoutManager stationManger = new LinearLayoutManager(getContext());
        stationManger.setOrientation(RecyclerView.VERTICAL);
        rvStation.setLayoutManager(stationManger);
        rvStation.setAdapter(stationAdapter);

        tvStationAmount.setText(getString(R.string.station_amount)+stationList.size());
        tvOnline.setText(getString(R.string.online)+stationList.size());
        tvOffline.setText(getString(R.string.offline)+0);
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        //跳转到设置界面
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
