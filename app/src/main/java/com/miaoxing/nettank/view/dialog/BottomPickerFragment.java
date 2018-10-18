package com.miaoxing.nettank.view.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.miaoxing.nettank.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 底部选择弹窗
 *
 * @author : WangYi
 * @date : 2018/9/26
 */
public class BottomPickerFragment extends BottomSheetDialogFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_option_list)
    RecyclerView mRvOptionList;

    private String mTitle;

    private String[] mOptionArray;

    private int mSelectedPosition;

    private OptionListAdapter mAdapter;
    private OptionListAdapter.OnItemSelectedListener mOnItemSelectedListener;

    private DialogInterface.OnDismissListener mOnDismissListener;

    public void setTitle(final String title) {
        mTitle = title;
    }

    public void setOptionArray(final String[] optionArray) {
        mOptionArray = optionArray;

        if (mRvOptionList != null) {
            initData();
        }
    }

    public void setSelectedPosition(final int selectedPosition) {
        mSelectedPosition = selectedPosition;

        if (mAdapter != null) {
            mAdapter.setSelectedPosition(selectedPosition);
        }
    }

    public void setOnItemSelectedListener(OptionListAdapter.OnItemSelectedListener listener) {
        mOnItemSelectedListener = listener;
        if (mAdapter != null) {
            mAdapter.setOnItemSelectedListener(listener);
        }
    }

    public void setOnDismissListener(final DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mRvOptionList.setLayoutManager(new LinearLayoutManager(getContext()));
        Activity activity = getActivity();
        if (activity != null) {
            mRvOptionList.addItemDecoration(new DividerItemDecoration(activity,
                    DividerItemDecoration.VERTICAL));
        }
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(mRvOptionList);

        if (!TextUtils.isEmpty(mTitle)) {
            mTvTitle.setText(mTitle);
        }

        if (mOptionArray != null) {
            initData();
        }
    }

    private void initData() {
        mAdapter = new OptionListAdapter(mOptionArray);
        mAdapter.setSelectedPosition(mSelectedPosition);
        mAdapter.setOnItemSelectedListener((text, position) -> mSelectedPosition = position);
        mRvOptionList.setAdapter(mAdapter);
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        dismiss();
        if (view.getId() == R.id.tv_confirm && mOnItemSelectedListener != null) {
            mOnItemSelectedListener.onItemSelected(mOptionArray[mSelectedPosition],
                    mSelectedPosition);
        }
    }
}
