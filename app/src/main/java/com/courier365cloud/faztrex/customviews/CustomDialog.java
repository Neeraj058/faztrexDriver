package com.courier365cloud.faztrex.customviews;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;

public abstract class CustomDialog {

    private final String TAG = this.getClass().getSimpleName();

    private Dialog mDialog;

    protected CustomDialog(Context context) {
        mDialog = new Dialog(context);
        setUpView();
    }

    private synchronized void setUpView() {

        try {

            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(setDialogContentView());
            initDialogView();
            mDialog.setCancelable(setDialogCancelable());
            mDialog.setCanceledOnTouchOutside(setDialogCancelableTouchOutside());
            mDialog.setOnDismissListener(onDialogDismissListener());

            mDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public abstract int setDialogContentView();

    public abstract boolean setDialogCancelable();

    public abstract boolean setDialogCancelableTouchOutside();

    public abstract void initDialogView();

    public abstract DialogInterface.OnDismissListener onDialogDismissListener();

    public Dialog getDialog() {
        return mDialog;
    }
}
