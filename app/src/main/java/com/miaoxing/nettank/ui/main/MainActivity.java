package com.miaoxing.nettank.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.base.BaseActivity;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Station;
import com.miaoxing.nettank.model.StationStat;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.StationInfoActivity;
import com.miaoxing.nettank.ui.main.adapter.FuelAdapter;
import com.miaoxing.nettank.ui.main.adapter.StationAdapter;
import com.miaoxing.nettank.ui.setting.SettingActivity;
import com.miaoxing.nettank.util.SPUtils;

import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    private StationStat mStationStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //分割线
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.s_fuel_divider));
        rvFuel.addItemDecoration(divider);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mock();
    }

    private void mock() {
        String userID = (String) SPUtils.get(getContext(), Constant.PREFERENCES_USER_KEY, "");
        ApiClient.getService()
                .getMain(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<MainResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(Result<MainResponse> mainResponseResult) {
                        if (mainResponseResult.getCode() == Constant.CODE_SUCCESS) {
                            fuelList = mainResponseResult.getData().mFuelList;
                            stationList = mainResponseResult.getData().mStationList;
                            mStationStat = mainResponseResult.getData().mStationStat;
                            initView();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        LinearLayoutManager fuelManger = new LinearLayoutManager(getContext());
        fuelManger.setOrientation(RecyclerView.VERTICAL);
        rvFuel.setLayoutManager(fuelManger);
        rvFuel.setAdapter(new FuelAdapter(fuelList));

        StationAdapter stationAdapter = new StationAdapter(stationList);
        stationAdapter.setItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), StationInfoActivity.class);
            intent.putExtra("stationID", stationList.get(position).stationId);
            startActivity(intent);
        });
        LinearLayoutManager stationManger = new LinearLayoutManager(getContext());
        stationManger.setOrientation(RecyclerView.VERTICAL);
        rvStation.setLayoutManager(stationManger);
        rvStation.setAdapter(stationAdapter);

        tvStationAmount.setText(getString(R.string.station_amount) + mStationStat.stationCount);
        tvOnline.setText(getString(R.string.online) + mStationStat.stationUp);
        tvOffline.setText(getString(R.string.offline) + mStationStat.stationDown);
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        //跳转到设置界面
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
