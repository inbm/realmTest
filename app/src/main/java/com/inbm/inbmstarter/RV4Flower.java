package com.inbm.inbmstarter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.inbm.inbmstarter.inbm._header_;
import com.inbm.inbmstarter.inbm.recycler.AbsGridRV;

import java.util.ArrayList;

public class RV4Flower extends AbsGridRV {
    public RV4Flower(Context context) {
        super(context);
    }

    public RV4Flower(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RV4Flower(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getSpanCount() {
        return 4;
    }

    @Override
    protected ArrayList<_header_> getHeaders() {
        return new ArrayList<>();
    }
}
