package com.courier365cloud.faztrex.ui.fragment.docket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseFragment;
import com.courier365cloud.faztrex.databinding.FragmentDocketChargesBinding;
import com.courier365cloud.faztrex.network.model.response.docket.DocketCalculation;
import com.courier365cloud.faztrex.utils.AppConstant;

import java.util.Objects;

import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class DocketChargesFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static FragmentDocketChargesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docket_charges, container, false);
        View view = binding.getRoot();

        // hide keyboard
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return view;
    }

    /*
     * Method to set docket charges in second fragment
     *
     *
     * */
    static void setDocketCharges(DocketCalculation docketCalculation) {

        try {

            if (docketCalculation != null) {

                binding.tvBasicCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getBasicChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvValueSurcharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getValueSurchargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvDocketCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getDocketChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvGreenTax.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getGreenChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvDeliveryCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getDeliveryChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvOdaCharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getOdaChargesAmount()))), AppConstant.FORMAT_2_F));
                binding.tvToPayCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getToPayChargesAmount()))), AppConstant.FORMAT_2_F));
                binding.tvPackageHandlingCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getPkgHandlingChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvHardCopyCharges.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getHardCopyChargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvSubtotal.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSubTotalAmount()))), AppConstant.FORMAT_2_F));
                binding.tvSurcharge.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSurchargeAmount()))), AppConstant.FORMAT_2_F));
                binding.tvTotalFreightAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalFreightAmount()))), AppConstant.FORMAT_2_F));
                binding.tvSgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSgstPercentage()))), AppConstant.FORMAT_0_F));
                binding.tvSgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getSgstAmount()))), AppConstant.FORMAT_2_F));
                binding.tvCgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getCgstPercentage()))), AppConstant.FORMAT_0_F));
                binding.tvCgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getCgstAmount()))), AppConstant.FORMAT_2_F));
                binding.tvIgstPercentage.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getIgstPercentage()))), AppConstant.FORMAT_0_F));
                binding.tvIgstAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getIgstAmount()))), AppConstant.FORMAT_2_F));
                binding.tvSummaryTotalFreightAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalFreightAmount()))), AppConstant.FORMAT_2_F));
                binding.tvSummaryTotalTaxAmount.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getTotalGstAmount()))), AppConstant.FORMAT_2_F));
                binding.tvGrandTotal.setText(getFormattedString(String.valueOf(castToDouble(getStringValue(docketCalculation.getNetAmount()))), AppConstant.FORMAT_2_F));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(DocketChargesFragment.class.getSimpleName(), e.getLocalizedMessage());
        }
    }
}