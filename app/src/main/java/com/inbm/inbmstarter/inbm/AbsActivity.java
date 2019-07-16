package com.inbm.inbmstarter.inbm;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbsActivity extends AppCompatActivity {

    protected _actionbar actionbar;
    protected Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        if (actionbar != null && actionbar.getMenu() != 0) {
            getMenuInflater().inflate(actionbar.getMenu(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionbar != null && actionbar.getOnMenuIconClickListener() != null) {
            return actionbar.getOnMenuIconClickListener().onMenuIconClick(item);
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayout();

    protected abstract void buildUI();

    protected abstract _actionbar getCustomActionBar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getLayout());
        actionbar = getCustomActionBar();
        if (actionbar == null) {
//            getSupportActionBar().hide();
        }
        buildUI();
        //NetworkManager.checkNetworkAvailable(this, () -> buildUI());
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

}
