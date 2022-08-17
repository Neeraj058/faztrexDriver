package com.courier365cloud.faztrex.adapter;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppUtils;

public class CustomBindAdapter {

    @BindingAdapter("convertDateFormat")
    public static void convertDateFormat(AppCompatTextView textView, String inputString) {
        textView.setText(AppUtils.convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.DISPLAY_DATE_FORMAT_1, inputString));
    }

    @BindingAdapter("convertLongDateFormat")
    public static void convertLongDateFormat(AppCompatTextView textView, String inputString) {
        textView.setText(AppUtils.convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.DISPLAY_DATE_FORMAT_3, inputString));
    }

    @BindingAdapter(value = {"convertToFormattedString", "formatPattern"})
    public static void convertToFormattedString(AppCompatTextView textView, String inputString, String formatPattern) {
        textView.setText(AppUtils.getFormattedString(inputString, formatPattern));
    }
}
