package com.courier365cloud.faztrex.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.BottomsheetDocketDetailBinding;
import com.courier365cloud.faztrex.network.model.response.docket.tracking.DocketTracking;
import com.courier365cloud.faztrex.utils.AppConstant;

import java.util.Objects;

import static com.courier365cloud.faztrex.utils.AppUtils.convertDateFormat;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class DocketDetailsBottomSheet extends BottomSheetDialogFragment {

    private final String TAG = this.getClass().getSimpleName();

    private BottomsheetDocketDetailBinding binding;

    private DocketTracking docketTracking;

    public DocketDetailsBottomSheet(DocketTracking docketTracking) {
        this.docketTracking = docketTracking;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.bottomsheet_docket_detail, container, false);

        // hide keyboard
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // call method to start operational flow
        doYourWork();

        return binding.getRoot();
    }

    private void doYourWork() {

        // set docket details
        setDocketDetails();

        binding.ivClose.setOnClickListener(v -> {
            BaseActivity.hideSoftKeyboard();
            dismiss();
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {

            /*BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
            FrameLayout bottomSheet = dialog.findViewById(android.support.design.R.id.design_bottom_sheet);
            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int screenHeight = displaymetrics.heightPixels;
            behavior.setPeekHeight(screenHeight);*/
        });
    }

    private void setDocketDetails() {

        try {

            if (docketTracking != null) {

                Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText(getStringValue(docketTracking.getDocketNumber()));
                Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(convertDateFormat(AppConstant.TRACKING_API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(docketTracking.getBookingDate())));
                Objects.requireNonNull(binding.tnlCustomerType.getEditText()).setText(getStringValue(docketTracking.getCustomerType()).toUpperCase());
                Objects.requireNonNull(binding.tnlDispatchMode.getEditText()).setText(getStringValue(docketTracking.getDispatchMode()).toUpperCase());
                Objects.requireNonNull(binding.tnlPaymentType.getEditText()).setText(getStringValue(docketTracking.getPaymentType()).toUpperCase());
                Objects.requireNonNull(binding.tnlDeliveryType.getEditText()).setText(getStringValue(docketTracking.getDeliveryType()));
                Objects.requireNonNull(binding.tnlOrigin.getEditText()).setText(getStringValue(docketTracking.getOrigin()));
                Objects.requireNonNull(binding.tnlDestination.getEditText()).setText(getStringValue(docketTracking.getDestination()));
                Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText(getStringValue(docketTracking.getConsignor()));
                Objects.requireNonNull(binding.tnlConsignee.getEditText()).setText(getStringValue(docketTracking.getConsignee()));
                Objects.requireNonNull(binding.tnlNoOfBoxes.getEditText()).setText(getStringValue(docketTracking.getNoOfPackages()));
                Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText(convertDateFormat(AppConstant.TRACKING_API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(docketTracking.getEstimatedDeliveryDate())));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
