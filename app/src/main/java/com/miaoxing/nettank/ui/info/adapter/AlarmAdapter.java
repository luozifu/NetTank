package com.miaoxing.nettank.ui.info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.ui.info.response.AlarmResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class AlarmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlarmResponse> mAlarmResponseList;
    private Map<Integer, String> mMap;

    public AlarmAdapter(List<AlarmResponse> alarmResponseList, Context context) {
        mAlarmResponseList = alarmResponseList;
        mMap = new HashMap<>();
        mMap.put(Constant.ALARM_WATER_FLOAT, context.getString(R.string.alarm_water_float));
        mMap.put(Constant.ALARM_ALPENSTOCK, context.getString(R.string.alarm_probe));
        mMap.put(Constant.ALARM_LOW_TMP, context.getString(R.string.alarm_low_tmp));
        mMap.put(Constant.ALARM_HIGH_TMP, context.getString(R.string.alarm_high_tmp));
        mMap.put(Constant.ALARM_WATER_LOW, context.getString(R.string.alarm_water_low));
        mMap.put(Constant.ALARM_WATER_HIGH, context.getString(R.string.alarm_water_high));
        mMap.put(Constant.ALARM_FUEL_LOW, context.getString(R.string.alarm_fuel_low));
        mMap.put(Constant.ALARM_FUEL_HIGH, context.getString(R.string.alarm_fuel_high));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constant.ITEM_NORMAL) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_alarm_response, parent, false);
            return new AlarmHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_footer, parent, false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlarmHolder) {
            AlarmHolder alarmHolder = (AlarmHolder) holder;
            alarmHolder.mTvFuelName.setText(mAlarmResponseList.get(position).fuelName);
            alarmHolder.mTvTankName.setText(mAlarmResponseList.get(position).tankName);
            alarmHolder.mTvTime.setText(mAlarmResponseList.get(position).alarmTime);
            alarmHolder.mTvAlarm.setText(mMap.get(mAlarmResponseList.get(position).alarmInfo));
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mAlarmResponseList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return Constant.ITEM_FOOTER;
        } else {
            return Constant.ITEM_NORMAL;
        }
    }

    class AlarmHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tank_name)
        TextView mTvTankName;
        @BindView(R.id.tv_fuel_name)
        TextView mTvFuelName;
        @BindView(R.id.tv_alarm)
        TextView mTvAlarm;
        @BindView(R.id.tv_time)
        TextView mTvTime;

        public AlarmHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tip_load)
        TextView tvTipLoad;

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
