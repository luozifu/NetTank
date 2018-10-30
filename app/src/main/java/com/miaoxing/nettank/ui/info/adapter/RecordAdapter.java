package com.miaoxing.nettank.ui.info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.ui.info.response.RecordResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Luozifu
 * @date : 2018/10/20
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<RecordResponse> mRecordResponseList;

    public RecordAdapter(List<RecordResponse> recordResponseList) {
        mRecordResponseList = recordResponseList;
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_record_response, parent, false);
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        Context context = holder.itemView.getContext();
        holder.mNum.setText((position+1)+"");
        holder.mTvTankName.setText(mRecordResponseList.get(position).tankName);
        holder.mTvFuelName.setText(mRecordResponseList.get(position).fuelName);
        holder.mTvAddBefore.setText(context.getString(R.string.add_before)
                + mRecordResponseList.get(position).addBefore);
        holder.mTvAddAfter.setText(context.getString(R.string.add_after)
                + mRecordResponseList.get(position).addAfter);
        holder.mTvVolume.setText(context.getString(R.string.volume)
                + mRecordResponseList.get(position).volume);
        holder.mTvOperator.setText(context.getString(R.string.operator)
                + mRecordResponseList.get(position).operator);
        holder.mTvTime.setText(mRecordResponseList.get(position).updateTime);
    }

    @Override
    public int getItemCount() {
        return mRecordResponseList.size();
    }

    class RecordHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tank_name)
        TextView mTvTankName;
        @BindView(R.id.tv_fuel_name)
        TextView mTvFuelName;
        @BindView(R.id.tv_operator)
        TextView mTvOperator;
        @BindView(R.id.tv_volume)
        TextView mTvVolume;
        @BindView(R.id.tv_add_before)
        TextView mTvAddBefore;
        @BindView(R.id.tv_add_after)
        TextView mTvAddAfter;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_num)
        TextView mNum;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
