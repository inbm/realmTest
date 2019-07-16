package com.inbm.inbmstarter.inbm;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


public class _permission {

    private static OnPermission onPermission;

    public static boolean hasPermission(Context context, String[] permissions) {
        boolean hasPermission = true;

        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);

            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                hasPermission = false;
                break;
            }
        }

        return hasPermission;
    }

    public static void requestPermission(Activity activity, int requestCode, String[] permissions) {
        if (hasPermission(activity, permissions)) {
            OnPermission onPermission = (OnPermission) activity;
            onPermission.onPermissionGranted(requestCode);
        }
        else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    public static void requestPermission(Fragment fragment, int requestCode, String[] permissions) {
        if (hasPermission(fragment.getContext(), permissions)) {
            OnPermission onPermission = (OnPermission) fragment;
            onPermission.onPermissionGranted(requestCode);
        }
        else {
            fragment.requestPermissions(permissions, requestCode);
        }
    }
}
