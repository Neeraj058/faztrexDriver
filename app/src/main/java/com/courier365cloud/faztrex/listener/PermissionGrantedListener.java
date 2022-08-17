package com.courier365cloud.faztrex.listener;

public interface PermissionGrantedListener {

    void onSinglePermissionGranted(String permissionName);

    void onMultiplePermissionGranted(String[] permissions);
}
