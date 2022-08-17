package com.courier365cloud.faztrex.customviews;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.courier365cloud.faztrex.R;

public class CustomProgressDialog extends Dialog {

    private CustomProgressDialog(@NonNull Context context) {
        super(context, R.style.ThemeCustomProgressDialog);
    }

    @NonNull
    public static CustomProgressDialog show(@NonNull Context mContext, boolean isCancelable) {

        CustomProgressDialog customProgressDialog = new CustomProgressDialog(mContext);

        try {

            customProgressDialog = new CustomProgressDialog(mContext);
            customProgressDialog.setCancelable(isCancelable);
            customProgressDialog.addContentView(new ProgressBar(mContext), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            customProgressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MyProgressDialogError", e.getLocalizedMessage());
        }

        return customProgressDialog;
    }
}