package com.inbm.inbmstarter.inbm.ViewEx;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import java.util.Locale;

public class EditTextEx extends AppCompatEditText {

    public EditTextEx(Context context) {
        super(context);
        setFont(context);
    }

    public EditTextEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public EditTextEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }
    private void setFont(Context context) {
        Locale current = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
        if(current == null)
            return;
    }
}
