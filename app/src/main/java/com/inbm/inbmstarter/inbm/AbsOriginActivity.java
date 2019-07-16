package com.inbm.inbmstarter.inbm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by inbm on 2017. 3. 10..
 */

public class AbsOriginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    private View oldFocus = null;
    private View newFocus = null;


    public void requestFocus(View newFocus){
        _log.e(newFocus.toString());

        oldFocus = getCurrentFocus();
        this.newFocus = newFocus;
        this.newFocus.requestFocus();
    }


    public void saveFocus(View newFocus){
        _log.e("save: " + newFocus);
        oldFocus = newFocus;
    }


    public void recoverFocus(){


        if(oldFocus != null)
            oldFocus.requestFocus();


        View temp = null;

        temp = newFocus;
        newFocus = oldFocus;
        oldFocus = temp;

        _log.e("old: " + oldFocus + "-->");
        _log.e("-->new: " + newFocus);



    }


    public float _dimen(int i){
        return getResources().getDimension(i);
    }





    interface OnLayout { void onLayout();}

    public void addOnLayout(final View v, final OnLayout listener){
        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                listener.onLayout();
                v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }





    public  void printMultiReturn(ArrayList<String[]> arr){
        for(String[] strs : arr)
            for(String str : strs)
                _log.e("ret : " + str);
    }


    public void printSingleReturn(String[] strs){
        for(String str : strs)
            _log.e("ret : " + str);
    }

    protected FrameLayout _fl(int fl) {
        return (FrameLayout)findViewById(fl);
    }

    protected LinearLayout _ll(int ll) {
        return (LinearLayout) findViewById(ll);
    }

    protected RelativeLayout _rl(int rl) {
        return (RelativeLayout)findViewById(rl);
    }
}
