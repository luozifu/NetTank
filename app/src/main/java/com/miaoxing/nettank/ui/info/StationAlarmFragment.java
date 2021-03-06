package com.miaoxing.nettank.ui.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.net.ApiClient;
import com.miaoxing.nettank.net.Result;
import com.miaoxing.nettank.ui.info.adapter.AlarmAdapter;
import com.miaoxing.nettank.ui.info.response.AlarmResponse;
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

public class StationAlarmFragment extends Fragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.tv_tank)
    TextView tvTank;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rv_result)
    XRecyclerView mRvResult;

    TimePickerView startPicker;
    TimePickerView endPicker;
    private List<TankResponse> mTankResponseList;
    private String stationID;
    private BottomPickerFragment pickerFragment;
    private String[] optionArray;
    private int mPosition = -1;
    private int page;
    private List<AlarmResponse> mAlarmResponseList;
    private AlarmAdapter mAlarmAdapter;
    private String startTime;
    private String endTime;

    public StationAlarmFragment() {

    }

    public static StationAlarmFragment newInstance() {
        Bundle args = new Bundle();
        StationAlarmFragment fragment = new StationAlarmFragment();
        args.putString("param", "StationAlarmFragment");
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
                startTime = tvStartTime.getText().toString();
                if (TextUtils.isEmpty(startTime)) {
                    ToastUtils.showToast(getContext(), R.string.tip_start_time);
                    return;
                }
                endTime = tvEndTime.getText().toString();
                if (TextUtils.isEmpty(endTime)) {
                    ToastUtils.showToast(getContext(), R.string.tip_end_time);
                    return;
                }
                mAlarmResponseList = new ArrayList<>();
                page = 1;
                mRvResult.setNoMore(false);
                getRecords(startTime, endTime);
                break;
        }
    }

    /**
     * 获取记录
     *
     * @param startTime
     * @param endTime
     */
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
                .getAlarmList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<AlarmResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<AlarmResponse>> result) {
                        if (result.getCode() != Constant.CODE_SUCCESS) {
                            return;
                        }
                        if (page == 1) {
                            mAlarmResponseList = result.getData();
                            initRecycler();
                            if (mAlarmResponseList.size() == 0) {
                                ToastUtils.showToast(getContext(), R.string.tip_result_null);
                            }
                        } else {
                            if (result.getData().size() == 0) {
                                mRvResult.setNoMore(true);
                            } else {
                                mAlarmResponseList.addAll(result.getData());
                                mRvResult.loadMoreComplete();
                            }
                            mAlarmAdapter.notifyDataSetChanged();
                        }
                        ++page;
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(getContext(), R.string.tip_net_error);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRvResult.setLayoutManager(layoutManager);
        mRvResult.setPullRefreshEnabled(false);
        mRvResult.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRvResult.getDefaultFootView().setLoadingHint(getString(R.string.loading));
        mRvResult.getDefaultFootView().setNoMoreHint(getString(R.string.load_no_more));
        mRvResult.setLoadingListener(this);

        mAlarmAdapter = new AlarmAdapter(mAlarmResponseList,getContext());
        mRvResult.setAdapter(mAlarmAdapter);
    }

    /**
     * 获取油罐列表
     */
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
                        ToastUtils.showToast(getContext(), R.string.tip_net_error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 弹出油罐列表
     *
     * @param tankResponseResult
     */
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getRecords(startTime, endTime);
    }
}
