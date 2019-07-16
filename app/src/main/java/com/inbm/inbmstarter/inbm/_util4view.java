package com.inbm.inbmstarter.inbm;

import android.util.TypedValue;

public class _util4view {

    public static int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, App.getStaticContext().getResources().getDisplayMetrics());

    }

    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, App.getStaticContext().getResources().getDisplayMetrics());
    }
}
