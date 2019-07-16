package com.inbm.inbmstarter.inbm.ViewEx;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.util.Locale;

/**
 * Created by inbm on 2017. 3. 17..
 */

public class TextViewEx extends AppCompatTextView {
    public TextViewEx(Context context) {
        super(context);
        setFont(context);
    }

    private void setFont(Context context) {
        Locale current = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
        if(current == null)
            return;
    }

    public TextViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public TextViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }


    public float getTextWidth(){

        Rect bounds = new Rect();

        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bounds);

        return bounds.width();

//        if(current.equals(  ))
//        {
//            Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/tharlon.ttf");
//            icBtnFindPlaces.setTypeface(tf);
//        }
//        else{
//            Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/inconsolata.ttf");
//            icBtnFindPlaces.setTypeface(tf);
//        }
    }
}
