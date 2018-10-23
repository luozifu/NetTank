package com.miaoxing.nettank.ui.info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Tank;

import java.math.BigDecimal;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Administrator
 * @Date : 2018/10/14 0014
 */
public class TankAdapter extends RecyclerView.Adapter<TankAdapter.TankHolder> {

    private List<Tank> mTankList;

    public TankAdapter(List<Tank> tankList) {
        mTankList = tankList;
    }

    @NonNull
    @Override
    public TankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_tank, parent, false);
        return new TankHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TankHolder holder, int position) {
        Tank tank = mTankList.get(position);
        Context context = holder.itemView.getContext();
        holder.tvCapacity.setText(context.getString(R.string.capacity) + tank.capacity);
        holder.tvFuelLevel.setText(context.getString(R.string.fuel_level) + tank.fuelLevel);
        holder.tvWaterLevel.setText(context.getString(R.string.water_level) + tank.waterLevel);
        //holder.tvTemperature.setText(context.getString(R.string.temperature)+tank.waterVol);
        holder.tvTankName.setText(tank.tankName);
        holder.tvFuelVol.setText(tank.fuelVol + "L");
        holder.tvFuelName.setText(tank.fuelName);
        double percent = tank.fuelVol * 100 / tank.capacity;
        BigDecimal bg = new BigDecimal(percent);
        percent = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.tvPercent.setText(percent + "%");
        holder.pbTank.setProgress((int) percent);
    }

    @Override
    public int getItemCount() {
        return mTankList.size();
    }

    class TankHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tank_name)
        TextView tvTankName;
        @BindView(R.id.tv_fuel_vol)
        TextView tvFuelVol;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.tv_fuel_name)
        TextView tvFuelName;
        @BindView(R.id.tv_fuel_level)
        TextView tvFuelLevel;
        @BindView(R.id.tv_capacity)
        TextView tvCapacity;
        @BindView(R.id.tv_water_level)
        TextView tvWaterLevel;
        @BindView(R.id.tv_temperature)
        TextView tvTemperature;
        @BindView(R.id.pb_tank)
        ProgressBar pbTank;

        public TankHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
