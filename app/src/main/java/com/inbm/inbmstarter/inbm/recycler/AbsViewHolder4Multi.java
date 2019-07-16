package com.inbm.inbmstarter.inbm.recycler;

import android.view.View;

public abstract class AbsViewHolder4Multi extends AbsViewHolder {
    public int viewType;
    FocusListener focusListener;

    interface FocusListener {
        void focusIn(View v, AbsViewHolder4Multi holder);
        void focusOut(View v, AbsViewHolder4Multi holder);
    }

    public AbsViewHolder4Multi(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
    }

    public void setFocusListener(FocusListener focusListener) {
        this.focusListener = focusListener;
    }

    @Override
    protected void ui4FocusIn(View v, AbsViewHolder holder) {
        if (focusListener != null) {
            focusListener.focusIn(v, (AbsViewHolder4Multi) holder);
        }
    }

    @Override
    protected void ui4FocusOut(View v, AbsViewHolder holder) {
        if (focusListener != null) {
            focusListener.focusOut(v, (AbsViewHolder4Multi) holder);
        }
    }
}
