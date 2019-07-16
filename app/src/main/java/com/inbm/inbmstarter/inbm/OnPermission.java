package com.inbm.inbmstarter.inbm;

public interface OnPermission {
    void onPermissionGranted(int requestCode);
    void onPermissionDenied(int requestCode);
}