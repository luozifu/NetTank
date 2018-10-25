package com.miaoxing.nettank.ui.info.adapter;

import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Fuel;
import com.miaoxing.nettank.util.ScreenUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Administrator
 * @Date : 2018/10/13 0013
 */
public class HistogramAdapter extends RecyclerView.Adapter<HistogramAdapter.HistogramHolder> {

    private List<Fuel> mFuelList;

    public HistogramAdapter(List<Fuel> fuelList) {
        mFuelList = fuelList;
    }

    @NonNull
    @Override
    public HistogramHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_histogram, parent, false);
        return new HistogramHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistogramHolder holder, int position) {
        Fuel fuel = mFuelList.get(position);
        holder.tvFuelName.setText(fuel.fuelName);
        holder.tvCapacity.setText(fuel.capacity+"");
        holder.tvFuelVol.setText(fuel.fuelVol+"");

        int height = ScreenUtils.dp2px(150);
        height = (int) (height * fuel.fuelVol / fuel.capacity);
        ValueAnimator animator = ValueAnimator.ofInt(0,height);
        animator.setDuration(500);
        animator.setStartDelay(200);
        animator.setRepeatCount(0);
        animator.start();
        ViewGroup.LayoutParams layoutParams = holder.tvFuel.getLayoutParams();
        animator.addUpdateListener(animation -> {
            layoutParams.height = (int) animation.getAnimatedValue();
            holder.tvFuel.setLayoutParams(layoutParams);
        });
    }

    @Override
    public int getItemCount() {
        return mFuelList.size();
    }

    class HistogramHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_capacity)
        TextView tvCapacity;
        @BindView(R.id.tv_fuel_vol)
        TextView tvFuelVol;
        @BindView(R.id.tv_fuel_name)
        TextView tvFuelName;
        @BindView(R.id.tv_fuel)
        TextView tvFuel;

        public HistogramHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
