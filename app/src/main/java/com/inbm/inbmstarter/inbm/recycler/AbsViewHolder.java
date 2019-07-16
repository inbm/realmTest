package com.inbm.inbmstarter.inbm.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by inbm on 2017. 2. 14..
 */

public abstract class AbsViewHolder extends RecyclerView.ViewHolder {

    public int position;

    View itemView;


    protected OnClick listener4Click;

    public AbsViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;


    }

    public View getItemView(){
        return itemView;
    }

    protected abstract void ui4FocusIn(View v, AbsViewHolder holder);
    protected abstract void ui4FocusOut(View v, AbsViewHolder holder);

    public void setListener(final OnClick listener4Click) {
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    ui4FocusIn(v, AbsViewHolder.this);
                } else {
                    ui4FocusOut(v, AbsViewHolder.this);
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener4Click.onClick(v, AbsViewHolder.this);
            }
        });
    }

    public interface OnClick {
        void onClick(View v, AbsViewHolder vh);
    }


    public void setPosition(int position){
        this.position = position;

    }


}
