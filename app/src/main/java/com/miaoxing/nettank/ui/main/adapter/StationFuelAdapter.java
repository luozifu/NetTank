package com.miaoxing.nettank.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Fuel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class StationFuelAdapter extends RecyclerView.Adapter<StationFuelAdapter.StationFuelHolder> {

    private List<Fuel> mFuelList;

    public StationFuelAdapter(List<Fuel> fuelList) {
        mFuelList = fuelList;
    }

    @NonNull
    @Override
    public StationFuelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_station_fuel, parent, false);
        return new StationFuelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationFuelHolder holder, int position) {
        Context context = holder.itemView.getContext();
        holder.mTvFuelName.setText(mFuelList.get(position).fuelName);
        holder.mTvFuelVol.setText(context.getString(R.string.vol_short)
                + mFuelList.get(position).fuelVol);
        holder.mTvCapacity.setText(context.getString(R.string.capacity_short)
                + mFuelList.get(position).capacity);
    }

    @Override
    public int getItemCount() {
        return mFuelList.size();
    }

    class StationFuelHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fuel_name)
        TextView mTvFuelName;
        @BindView(R.id.tv_fuel_vol)
        TextView mTvFuelVol;
        @BindView(R.id.tv_capacity)
        TextView mTvCapacity;

        public StationFuelHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
