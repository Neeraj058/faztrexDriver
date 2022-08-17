package com.courier365cloud.faztrex.customviews;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

public abstract class SimpleAlertDialog {

    private final String TAG = this.getClass().getSimpleName();

    private AlertDialog.Builder mAlertDialog;

    protected SimpleAlertDialog(Context context) {
        mAlertDialog = new AlertDialog.Builder(context);
        setUserInterface();
    }

    private synchronized void setUserInterface() {

        try {

            mAlertDialog.setCancelable(setDialogCancelable());
            mAlertDialog.setTitle(setDialogTitle());
            mAlertDialog.setMessage(setDialogMessage());
            mAlertDialog.setIcon(setDialogIcon());
            mAlertDialog.setPositiveButton(setDialogPositiveButtonText(), onDialogPositiveButtonClick());
            mAlertDialog.setNegativeButton(setDialogNegativeButtonText(), onDialogNegativeButtonClick());
            mAlertDialog.setNeutralButton(setDialogNeutralButtonText(), onDialogNeutralButtonClick());
            mAlertDialog.setOnDismissListener(onDialogDismissListener());

            mAlertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public AlertDialog.Builder getDialog() {
        return mAlertDialog;
    }

    public abstract boolean setDialogCancelable();

    public abstract String setDialogTitle();

    public abstract String setDialogMessage();

    public abstract Drawable setDialogIcon();

    public abstract String setDialogPositiveButtonText();

    public abstract DialogInterface.OnClickListener onDialogPositiveButtonClick();

    public abstract String setDialogNegativeButtonText();

    public abstract DialogInterface.OnClickListener onDialogNegativeButtonClick();

    public abstract String setDialogNeutralButtonText();

    public abstract DialogInterface.OnClickListener onDialogNeutralButtonClick();

    public abstract DialogInterface.OnDismissListener onDialogDismissListener();
}
