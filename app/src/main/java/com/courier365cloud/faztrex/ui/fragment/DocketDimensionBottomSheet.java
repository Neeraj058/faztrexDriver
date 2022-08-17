package com.courier365cloud.faztrex.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.databinding.BottomsheetDocketDimensionBinding;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DocketDimensionBottomSheet extends BottomSheetDialogFragment {
    private final String TAG = this.getClass().getSimpleName();
    private BottomsheetDocketDimensionBinding binding;
    private DrsDocket drsDocket;

    public DocketDimensionBottomSheet(DrsDocket drsDocket) {
        this.drsDocket = drsDocket;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottomsheet_docket_dimension, container, false);
        binding.setDrsDetail(drsDocket.getDocketDimension());
        return binding.getRoot();
    }
}
