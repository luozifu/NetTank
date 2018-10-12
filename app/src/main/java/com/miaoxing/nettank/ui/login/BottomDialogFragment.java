package com.miaoxing.nettank.ui.login;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author : Administrator
 * @Date : 2018/10/12 0012
 */
public class BottomDialogFragment extends DialogFragment {

    public BottomDialogFragment(){

    }

    public static BottomDialogFragment newInstance() {
        
        Bundle args = new Bundle();
        BottomDialogFragment fragment = new BottomDialogFragment();
        args.putString("param","BottomDialogFragment");
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }
}
