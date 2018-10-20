package com.miaoxing.nettank.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.constant.Constant;
import com.miaoxing.nettank.model.Station;
import com.miaoxing.nettank.view.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Administrator
 * @Date : 2018/10/14 0014
 */
public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationHolder> {

    private List<Station> mStationList;
    private OnItemClickListener itemClickListener;

    public StationAdapter(List<Station> stationList) {
        mStationList = stationList;
    }

    @NonNull
    @Override
    public StationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_station, parent, false);
        return new StationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationHolder holder, int position) {
        holder.itemView.setTag(position);
        Context context = holder.itemView.getContext();
        Station station = mStationList.get(position);
        holder.mTvStationName.setText(station.stationName);
        if (station.status == Constant.STATUS_ONLINE) {
            holder.mTvStatus.setText(context.getString(R.string.online_bracket));
            holder.mTvStatus.setTextColor(context.getResources().getColor(R.color.green_app));
        } else {
            holder.mTvStatus.setText(context.getString(R.string.offline_bracket));
            holder.mTvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.mRvFuel.setLayoutManager(layoutManager);
        holder.mRvFuel.setAdapter(new StationFuelAdapter(station.fuelList));

    }

    @Override
    public int getItemCount() {
        return mStationList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class StationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_station_name)
        TextView mTvStationName;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.rv_fuel)
        RecyclerView mRvFuel;

        public StationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (null != itemClickListener) {
                    int position = (int) itemView.getTag();
                    itemClickListener.onClick(v, position);
                }
            });
        }
    }

}
