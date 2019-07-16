package com.inbm.inbmstarter.inbm;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;


@SuppressLint("ValidFragment")
public class AbsFragment extends android.support.v4.app.Fragment {
    public FragmentActivity mActivity;

    protected Fragmentable listener;


    protected _actionbar actionbar;

    public AbsFragment() {
        super();
    }

    public AbsFragment(Context listener) {
        if (listener instanceof Fragmentable)
            this.listener = (Fragmentable) listener;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (actionbar != null && actionbar.getMenu() != 0) {
            inflater.inflate(actionbar.getMenu(), menu);
        }
//        return super.onCreateOptionsMenu(menu);
    }

    protected Context _context() {
        return getContext();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (this instanceof OnPermission) {
            OnPermission onPermission = (OnPermission) this;

            boolean isPermissionGranted = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    isPermissionGranted = false;
                    break;
                }
            }
            if (isPermissionGranted) {
                // permission granted
                onPermission.onPermissionGranted(requestCode);
            } else {
                // permission denied
                onPermission.onPermissionDenied(requestCode);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (FragmentActivity) context;
        }
    }
}
