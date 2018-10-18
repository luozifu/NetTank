package com.miaoxing.nettank.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaoxing.nettank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择列表
 *
 * @author : WangYi
 * @date : 2018/9/19
 */
public class OptionListAdapter extends RecyclerView.Adapter<
        OptionListAdapter.ViewHolder> {

    private String[] mOptionArray;

    private int mSelectedPosition;

    private OnItemSelectedListener mOnItemSelectedListener;

    public OptionListAdapter() {
    }

    public OptionListAdapter(final String[] optionArray) {
        mOptionArray = optionArray;
    }

    public void setSelectedPosition(final int selectedPosition) {
        if (mOptionArray != null && selectedPosition > 0
                && selectedPosition < mOptionArray.length) {
            mSelectedPosition = selectedPosition;
        }
    }

    @Override
    public OptionListAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                           final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_picker_option, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OptionListAdapter.ViewHolder holder,
                                 final int position) {
        holder.itemView.setTag(position);
        holder.mTvContent.setText(mOptionArray[position]);

        holder.itemView.setSelected(position == mSelectedPosition);
    }

    @Override
    public int getItemCount() {
        return mOptionArray == null ? 0 : mOptionArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content)
        TextView mTvContent;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this::onClick);
        }

        public void onClick(View view) {
            int prePosition = mSelectedPosition;
            mSelectedPosition = (int) view.getTag();
            if (mSelectedPosition == prePosition) {
                return;
            }

            notifyItemChanged(prePosition);
            view.setSelected(true);

            if (mOnItemSelectedListener != null) {
                mOnItemSelectedListener.onItemSelected(mOptionArray[mSelectedPosition],
                        mSelectedPosition);
            }
        }
    }

    public void setOnItemSelectedListener(final OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(String text, int position);
    }
}
