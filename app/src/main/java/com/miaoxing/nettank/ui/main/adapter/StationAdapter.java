package com.miaoxing.nettank.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Station;
import com.miaoxing.nettank.view.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
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
        String stationName = mStationList.get(position).stationName;
        holder.tvStationName.setText(stationName);
    }

    @Override
    public int getItemCount() {
        return mStationList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class StationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_station_name)
        TextView tvStationName;

        public StationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(v -> {
                if(null != itemClickListener){
                    int position = (int) itemView.getTag();
                    itemClickListener.onClick(v,position);
                }
            });
        }
    }

}
