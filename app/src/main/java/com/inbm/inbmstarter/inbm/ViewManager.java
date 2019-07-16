package com.inbm.inbmstarter.inbm;

import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by inbm on 2017. 4. 7..
 */

public class ViewManager {

    HashMap<View, _props_> hm = new HashMap<>();

    private static ViewManager _this = null;
    private ViewManager(){

    }

    public static ViewManager getInstance() {

        if(_this == null)
            _this =  new ViewManager();

        return _this;
    }



    class _props_{
        public int x, y, w, h;

        public _props_(int h, int w, int x, int y) {
            this.h = h;
            this.w = w;
            this.x = x;
            this.y = y;
        }
    }

    public void addView(View v){
        hm.put(v, new _props_(
                v.getHeight(),
                v.getWidth(),
                (int) v.getX(),
                (int) v.getY()
        ));
    }

    public _props_ getProps(View v){
        return hm.get(v);
    }

    public void adjustLayout(View v, int x, int y, int h, int w){
        v.setLayoutParams(new ViewGroup.LayoutParams(w, h));
        v.requestLayout();
        v.setX(x);
        v.setY(y);

    }
}
