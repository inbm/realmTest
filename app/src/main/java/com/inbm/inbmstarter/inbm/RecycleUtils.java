package com.inbm.inbmstarter.inbm;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

/**
 * @author givenjazz
 *
 */
public class RecycleUtils {
       
    private RecycleUtils(){}

    public static void recursiveRecycle(View root) {
        if (root == null)
            return;
        
        //root.setBackground(null);
        
        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)root;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                recursiveRecycle(group.getChildAt(i));
            }
 
            if (!(root instanceof AdapterView)) {
                group.removeAllViews();
            }
 
        }
       
        if (root instanceof ImageView) {
            ((ImageView)root).setImageDrawable(null);
        }
 


 
        root = null;
 
        return;
    }
}