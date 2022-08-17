package com.courier365cloud.faztrex.helper;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.courier365cloud.faztrex.listener.general.ConfirmationDialogClickListener;

public class ConfirmationDialogManager {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @SuppressLint("StaticFieldLeak")
    private static ConfirmationDialogManager confirmationDialogManager;

    private static ConfirmationDialogClickListener listener;

    public static ConfirmationDialogManager getInstance(Context mContext) {

        if (confirmationDialogManager == null)
            confirmationDialogManager = new ConfirmationDialogManager();
        ConfirmationDialogManager.mContext = mContext;
        ConfirmationDialogManager.listener = null;
        return confirmationDialogManager;
    }

    public static ConfirmationDialogManager getInstance(Context mContext, ConfirmationDialogClickListener listener) {

        if (confirmationDialogManager == null)
            confirmationDialogManager = new ConfirmationDialogManager();
        ConfirmationDialogManager.mContext = mContext;
        ConfirmationDialogManager.listener = listener;
        return confirmationDialogManager;
    }

    public void askConsent(String confirmationMessage) {

//        DialogConfirmationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_confirmation, null, false);

        if (listener == null)
            listener = (ConfirmationDialogClickListener) mContext;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Confirmation!!!");
        builder.setMessage(confirmationMessage);
        builder.setPositiveButton("Proceed", (dialog, which) -> {
            listener.onConfirmationPositiveClick();
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            listener.onConfirmationNegativeClick();
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        /*final Dialog confirmationDialog = AppUtils.setDialog(mContext, binding.getRoot(), true);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setCanceledOnTouchOutside(false);
        confirmationDialog.show();
        binding.tvDialogTitle.setText(confirmationMessage);
        binding.fabApprove.setOnClickListener(view -> {
            listener.onConfirmationPositiveClick();
            confirmationDialog.dismiss();
        });

        binding.fabCancel.setOnClickListener(view -> {
            listener.onConfirmationNegativeClick();
            confirmationDialog.dismiss();
        });*/
    }
}
