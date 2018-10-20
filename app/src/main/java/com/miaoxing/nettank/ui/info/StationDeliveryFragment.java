package com.miaoxing.nettank.ui.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.adapter.RecordAdapter;
import com.miaoxing.nettank.ui.info.response.RecordResponse;
import com.miaoxing.nettank.ui.info.response.TankResponse;
import com.miaoxing.nettank.util.DateTimeUtils;
import com.miaoxing.nettank.util.ToastUtils;
import com.miaoxing.nettank.view.dialog.BottomPickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StationDeliveryFragment extends Fragment {
    @BindView(R.id.tv_tank)
    TextView tvTank;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rv_result)
    RecyclerView mRvResult;

    TimePickerView startPicker;
    TimePickerView endPicker;
    private List<TankResponse> mTankResponseList;
    private String stationID;
    private BottomPickerFragment pickerFragment;
    private String[] optionArray;
    private int mPosition = -1;
    private int page = 1;
    private List<RecordResponse> mRecordResponseList;
    private RecordAdapter mRecordAdapter;

    public StationDeliveryFragment() {

    }

    public static StationDeliveryFragment newInstance() {
        Bundle args = new Bundle();
        StationDeliveryFragment fragment = new StationDeliveryFragment();
        args.putString("param", "StationDeliveryFragment");
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_delivery, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        stationID = getActivity().getIntent().getStringExtra("stationID");
        mTankResponseList = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.tv_tank, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_reset, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //选择油罐
            case R.id.tv_tank:
                getTankList();
                break;
            //选择开始时间
            case R.id.tv_start_time:
                startPicker = new TimePickerBuilder(getContext(), (date, v) -> {
                    tvStartTime.setText(DateTimeUtils.formatDateTime(date, DateTimeUtils.DF_YYYY_MM_DD));
                })
                        .setDate(Calendar.getInstance())
                        .build();
                startPicker.show();
                break;
            //选择结束时间
            case R.id.tv_end_time:
                endPicker = new TimePickerBuilder(getContext(), (date, v) -> {
                    tvEndTime.setText(DateTimeUtils.formatDateTime(date, DateTimeUtils.DF_YYYY_MM_DD));
                })
                        .setDate(Calendar.getInstance())
                        .build();
                endPicker.show();
                break;
            //重置查询条件
            case R.id.tv_reset:
                tvTank.setText(R.string.all);
                tvStartTime.setText("");
                tvEndTime.setText("");
                mPosition = -1;
                break;
            //查询
            case R.id.tv_search:
                String startTime = tvStartTime.getText().toString();
                if (TextUtils.isEmpty(startTime)) {
                    ToastUtils.showToast(getContext(), R.string.tip_start_time);
                    return;
                }
                String endTime = tvEndTime.getText().toString();
                if (TextUtils.isEmpty(endTime)) {
                    ToastUtils.showToast(getContext(), R.string.tip_end_time);
                    return;
                }
                mRecordResponseList = new ArrayList<>();
                getRecords(startTime, endTime);

                break;
        }
    }

    private void getRecords(String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        if (mPosition != -1) {
            map.put("TankID", mTankResponseList.get(mPosition).tankID);
        }
        map.put("StationID", stationID);
        map.put("start_time", startTime);
        map.put("end_time", endTime);
        map.put("page", page);
        ApiClient.getService()
                .getRecordList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<RecordResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<RecordResponse>> result) {
                        if (result.getCode() == Constant.CODE_SUCCESS) {
                            mRecordResponseList = result.getData();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRvResult.setLayoutManager(layoutManager);
                            mRecordAdapter = new RecordAdapter(mRecordResponseList);
                            mRvResult.setAdapter(mRecordAdapter);
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

    private void getTankList() {
        ApiClient.getService()
                .getTankList(stationID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<TankResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TankResponse>> tankResponseResult) {
                        pop(tankResponseResult);
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

    private void pop(Result<List<TankResponse>> tankResponseResult) {
        if (tankResponseResult.getCode() == Constant.CODE_SUCCESS) {
            mTankResponseList = tankResponseResult.getData();
            if (null == pickerFragment) {
                pickerFragment = new BottomPickerFragment();
            }
            optionArray = new String[mTankResponseList.size() + 1];
            optionArray[0] = getString(R.string.all);
            for (int i = 0; i < mTankResponseList.size(); i++) {
                optionArray[i + 1] = mTankResponseList.get(i).tankName;
            }
            pickerFragment.setOptionArray(optionArray);
            pickerFragment.setSelectedPosition(0);
            pickerFragment.setOnItemSelectedListener((text, position) -> {
                tvTank.setText(text);
                mPosition = position - 1;
            });
            pickerFragment.show(getFragmentManager(), "alarm");
        }
    }
}
