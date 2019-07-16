package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.inbm.inbmstarter.inbm.ViewEx.TextViewEx;

/**
 * Created by inbm on 2017. 3. 9..
 */

public class FrameLayout4Floating extends FrameLayout {

    TextViewEx mTV = null;
    Context context;
    String mTitle = "";

    View currentView = null;

    public FrameLayout4Floating(Context context) {
        super(context);
        this.context = context;
    }

    public FrameLayout4Floating(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTV = new TextViewEx(context);
        this.addView(mTV);
        //mTV.setVisibility(INVISIBLE);
    }

    public FrameLayout4Floating(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void setTitle(String str) {

        mTitle = str;

    }

    public String getTitle() {
        return mTitle;
    }

    public void showTitleAtView(String title, View v) {

        currentView = v;

        if (v != null) {
            mTV.setText(title);
            mTitle = title;

        }

        adjustTitlePosition();

//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 300);

    }


    public void hideTitle() {
        if (mTV != null)
            mTV.setText("");
    }

    public void adjustTitlePosition() {

        if(currentView == null)
            return ;

        float w = currentView.getWidth();
        //float h = currentView.getHeight();



        float h = 0;

        if(this.getWidth() > 1280)
            h = 440f;
        else
            h = 290f;
        float y = currentView.getY();
        float x = currentView.getX();


        float tv_w = mTV.getTextWidth();
        float this_w = this.getWidth();

        float tx = x + w/2 - tv_w/2;

        float padding = 10;
        float y_distance = 35;
        if(tx < 0)
            tx = padding;

        if( (tx + tv_w) > this_w)
            tx = this_w - tv_w - padding;

        float ty = h + y- y_distance;




        //_log.e("getX:" + x + "  getY:" + y + "  width:" + w + "  tv_w:" + tv_w + "  height:" + this.getHeight() + "  tx:" + tx + "  this_w:" + this_w);

        if (mTV != null) {


            mTV.setY(ty);
            mTV.setX(tx);
        }
    }


}
