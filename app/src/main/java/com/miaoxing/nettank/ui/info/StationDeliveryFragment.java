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
import com.miaoxing.nettank.util.DateTimeUtils;
import com.miaoxing.nettank.util.ToastUtils;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StationDeliveryFragment extends Fragment {
    @BindView(R.id.tv_tank)
    TextView tvTank;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    TimePickerView startPicker;
    TimePickerView endPicker;

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
        return view;
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
                break;
            //选择开始时间
            case R.id.tv_start_time:
                startPicker = new TimePickerBuilder(getContext(), (date, v) -> {
                    tvStartTime.setText(DateTimeUtils.formatDateTime(date,DateTimeUtils.DF_YYYY_MM_DD));
                })
                        .setDate(Calendar.getInstance())
                        .build();
                startPicker.show();
                break;
            //选择结束时间
            case R.id.tv_end_time:
                endPicker = new TimePickerBuilder(getContext(), (date, v) -> {
                    tvEndTime.setText(DateTimeUtils.formatDateTime(date,DateTimeUtils.DF_YYYY_MM_DD));
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
                break;
            //查询
            case R.id.tv_search:
                String startTime = tvStartTime.getText().toString();
                if(TextUtils.isEmpty(startTime)){
                    ToastUtils.showToast(getContext(),R.string.tip_start_time);
                    return;
                }
                String endTime = tvEndTime.getText().toString();
                if(TextUtils.isEmpty(endTime)){
                    ToastUtils.showToast(getContext(),R.string.tip_end_time);
                    return;
                }
                //todo 调用接口显示查询结果
                break;
        }
    }
}
