package com.inbm.inbmstarter.inbm;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * Created by inbm on 2017. 3. 17..
 */

public class _animation {
    public static class scale{
        public static Animation simple(float scale){
            final ScaleAnimation animation = new ScaleAnimation(1f, scale, 1f, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            //final ScaleAnimation animation = new ScaleAnimation(1f, scale, 1f, scale);

            animation.setFillAfter(true);
            animation.setDuration(100);

            return animation;
        }
    }


    public static class rotate {
        public static Animation simple(float degree){
            RotateAnimation anim = new RotateAnimation(0, degree,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);

            anim.setFillAfter(true);
            anim.setDuration(300);
            return anim;
        }

    }

    public static class translate {

    }

    public static class alpah{

    }
}
