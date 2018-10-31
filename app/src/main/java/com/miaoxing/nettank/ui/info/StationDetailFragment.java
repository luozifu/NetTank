package com.miaoxing.nettank.ui.info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Tank;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.widget.CombinedChartManager;
import com.miaoxing.nettank.ui.info.adapter.HistogramAdapter;
import com.miaoxing.nettank.ui.info.adapter.TankAdapter;
import com.miaoxing.nettank.ui.info.response.StationInfoResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class StationDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.tv_collect_time)
    TextView tvCollectTime;
    @BindView(R.id.rv_histogram)
    CombinedChart rvHistogram;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rv_tank)
    RecyclerView rvTank;

    private List<Tank> tankList;
    private List<Fuel> fuelList;

    private String stationID;
    private HistogramAdapter histogramAdapter;
    private TankAdapter tankAdapter;

    public StationDetailFragment() {

    }

    public static StationDetailFragment newInstance() {

        Bundle args = new Bundle();
        StationDetailFragment fragment = new StationDetailFragment();
        args.putString("param", "StationDetailFragment");
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_detail, container, false);
        ButterKnife.bind(this, view);
        stationID = getActivity().getIntent().getStringExtra("stationID");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe.setOnRefreshListener(this);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.s_divider));
        rvTank.addItemDecoration(divider);
        swipe.setRefreshing(true);
        mock();
    }

    private void initView() {
        /*LinearLayoutManager histogramManager = new LinearLayoutManager(getContext());
        histogramManager.setOrientation(RecyclerView.HORIZONTAL);
        rvHistogram.setLayoutManager(histogramManager);
        histogramAdapter = new HistogramAdapter(fuelList);
        rvHistogram.setAdapter(histogramAdapter);*/
        //x轴数据
        List<String> xData = new ArrayList<>();
        List<List<Float>> yBarDatas = new ArrayList<>();
        for (int i = 0; i < fuelList.size(); i++) {
            xData.add(fuelList.get(i).fuelName);
        }
        //2种直方图
        for (int i = 0; i < 2; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j < fuelList.size(); j++) {
                if(i == 0) {
                    yData.add((float) fuelList.get(j).capacity);
                }else {
                    yData.add((float) fuelList.get(j).fuelVol);
                }
            }
            yBarDatas.add(yData);
        }
        //名字集合
        List<String> barNames = new ArrayList<>();
        barNames.add(getString(R.string.total));
        barNames.add(getString(R.string.fuel_vol));
        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.gray_light));
        colors.add(getResources().getColor(R.color.green_app));
        CombinedChartManager manager = new CombinedChartManager(rvHistogram);
        manager.showCombinedChart(xData,yBarDatas,barNames,colors);


        LinearLayoutManager tankManager = new LinearLayoutManager(getContext());
        tankManager.setOrientation(RecyclerView.VERTICAL);
        rvTank.setLayoutManager(tankManager);
        tankAdapter = new TankAdapter(tankList);
        rvTank.setAdapter(tankAdapter);
    }

    private void mock() {
        tankList = new ArrayList<>();
        fuelList = new ArrayList<>();

        ApiClient.getService()
                .getStationInfo(stationID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<StationInfoResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<StationInfoResponse> stationInfoResponseResult) {
                        if (stationInfoResponseResult.getCode() == Constant.CODE_SUCCESS) {
                            tankList = stationInfoResponseResult.getData().mTankList;
                            fuelList = stationInfoResponseResult.getData().mFuelList;
                            initView();
                            tvCollectTime.setText(getString(R.string.collect_time)
                                    + stationInfoResponseResult.getData().updateTime);
                            swipe.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        mock();
    }

    public void onClickRefresh() {
        swipe.setRefreshing(true);
        mock();
    }
}
