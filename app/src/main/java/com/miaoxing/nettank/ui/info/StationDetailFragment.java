package com.miaoxing.nettank.ui.info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.model.Tank;
import com.miaoxing.nettank.ui.info.adapter.HistogramAdapter;
import com.miaoxing.nettank.ui.info.adapter.TankAdapter;

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


public class StationDetailFragment extends Fragment {


    @BindView(R.id.tv_collect_time)
    TextView tvCollectTime;
    @BindView(R.id.rv_histogram)
    RecyclerView rvHistogram;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rv_tank)
    RecyclerView rvTank;

    private List<Tank> tankList;
    private List<Fuel> fuelList;

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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mock();

        LinearLayoutManager histogramManager = new LinearLayoutManager(getContext());
        histogramManager.setOrientation(RecyclerView.HORIZONTAL);
        rvHistogram.setLayoutManager(histogramManager);
        rvHistogram.setAdapter(new HistogramAdapter(fuelList));

        LinearLayoutManager tankManager = new LinearLayoutManager(getContext());
        tankManager.setOrientation(RecyclerView.VERTICAL);
        rvTank.setLayoutManager(tankManager);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.s_divider));
        rvTank.addItemDecoration(divider);
        rvTank.setAdapter(new TankAdapter(tankList));
    }

    private void mock(){

        tankList = new ArrayList<>();
        fuelList = new ArrayList<>();
        for(int i = 0;i<2;i++){
            Tank tank = new Tank();
            tank.capacity = 3000;
            tank.fuelLevel = 200;
            tank.waterLevel = 10;
            tank.temperature = 27;
            tank.fuleName = i+"#";
            tank.tankName = i+"罐";
            tank.fuelVol = 1000;
            Fuel fuel = new Fuel();
            fuel.fuelName = tank.fuleName;
            fuel.capacity = tank.capacity;
            fuel.fuelVol = tank.fuelVol;
            tankList.add(tank);
            fuelList.add(fuel);
        }

    }

}
