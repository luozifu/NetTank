package com.miaoxing.nettank.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miaoxing.nettank.R;
import com.miaoxing.nettank.model.Fuel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Administrator
 * @Date : 2018/10/14 0014
 */
public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.FuelHolder> {

    private List<Fuel> mFuelList;

    public FuelAdapter(List<Fuel> fuelList) {
        mFuelList = fuelList;
    }

    @NonNull
    @Override
    public FuelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_fuel, parent, false);
        return new FuelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuelHolder holder, int position) {
        Fuel fuel = mFuelList.get(position);
        Context context = holder.itemView.getContext();
        holder.tvFuelName.setText(fuel.fuelName);
        holder.tvFuelVol.setText(context.getString(R.string.fuel_vol)+fuel.fuelVol);
        holder.tvCapacity.setText(context.getString(R.string.total)+fuel.capacity);
        holder.pb.setProgress((int) (fuel.fuelVol*100/fuel.capacity));
    }

    @Override
    public int getItemCount() {
        return mFuelList.size();
    }

    class FuelHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fuel_name)
        TextView tvFuelName;
        @BindView(R.id.pb)
        ProgressBar pb;
        @BindView(R.id.tv_fuel_vol)
        TextView tvFuelVol;
        @BindView(R.id.tv_capacity)
        TextView tvCapacity;

        public FuelHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
