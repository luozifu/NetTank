package com.miaoxing.nettank.ui.info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miaoxing.nettank.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class StationDetailFragment extends Fragment {

    @BindView(R.id.rv_histogram)
    RecyclerView rvHistogram;
    @BindView(R.id.rv_tank)
    RecyclerView rvTank;

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
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
