package com.courier365cloud.faztrex.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.listener.PermissionGrantedListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class PermissionManager {

    private final Context mContext;
    private PermissionGrantedListener listener;

    public PermissionManager(Context mContext) {
        this.mContext = mContext;
    }

    public PermissionManager(Context mContext, PermissionGrantedListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    //region Check Single Permission by Type

    public void checkSinglePermission(String Type) {

        Dexter.withActivity((Activity) mContext)
                .withPermission(Type)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        if (listener == null)
                            listener = (PermissionGrantedListener) mContext;

                        listener.onSinglePermissionGranted(response.getPermissionName());
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        if (response.isPermanentlyDenied())
                            showSettingsDialog();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }

                })
                .withErrorListener(error -> Toast.makeText(mContext, "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show())
                .check();
    }

    //endregion

    //region Check Multiple Permissions
    public void checkMultiplePermissions(String[] types) {

        try {

            Dexter.withActivity((Activity) mContext)
                    .withPermissions(types)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {

                            if (listener == null) {
                                listener = (PermissionGrantedListener) mContext;
                            }

                            if (report.getGrantedPermissionResponses() != null && report.getGrantedPermissionResponses().size() > 0) {

                                String[] grantedPermission = new String[report.getGrantedPermissionResponses().size()];

                                int i = 0;

                                for (PermissionGrantedResponse permission : report.getGrantedPermissionResponses()) {
                                    grantedPermission[i++] = permission.getPermissionName();
                                }

                                listener.onMultiplePermissionGranted(grantedPermission);
                            }

                            if (report.isAnyPermissionPermanentlyDenied()) {
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }

                    })
                    .withErrorListener(error -> Toast.makeText(mContext, "Error occurred! ", Toast.LENGTH_SHORT).show())
                    .onSameThread()
                    .check();

        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
    //endregion

    //region Display GOTO SETTINGS dialog if Permission is denied permanently
    private void showSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");

        builder.setPositiveButton(mContext.getResources().getString(R.string.btn_title_go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });

        builder.setNegativeButton(mContext.getResources().getString(R.string.btn_title_cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void openSettings() {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
        intent.setData(uri);

        if (mContext instanceof Activity) {
            ((Activity) mContext).startActivityForResult(intent, 101);
        } else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.err_msg_api_response_failure), Toast.LENGTH_SHORT).show();
        }
    }
    //endregion
}
