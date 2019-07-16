package com.inbm.inbmstarter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inbm.inbmstarter.inbm.recycler.AbsAdapter4Header;
import com.inbm.inbmstarter.inbm.recycler.AbsViewHolder;

import java.util.ArrayList;

public class Adapter4Flower extends AbsAdapter4Header {

    ArrayList<_data_> datas;
    public Adapter4Flower(Context context, ArrayList<_data_> datas, AbsAdapter4Header.OnClicked clicklistener) {
        super(context, clicklistener);
        this.datas = datas;
    }

    @Override
    protected int getLayout() {
        return R.layout.row_4_flower;
    }

    @Override
    protected int getHeadLayout() {
        return 0;
    }

    @Override
    protected AbsViewHolder getViewHolder(View v) {
        return new VH4Flower(v);
    }

    @Override
    protected void display(AbsViewHolder holder, int position) {

        _data_ data = datas.get(position);
        VH4Flower vh = (VH4Flower) holder;
        Glide.with(context).load(data.url).into(vh.iv);
        vh.tv.setText(data.title);

    }

    @Override
    protected int getCount() {
        return datas.size();
    }


    static class _data_{
        String url;
        String title;

        public _data_(String url, String title) {
            this.url = url;
            this.title = title;
        }
    }

    class VH4Flower extends AbsViewHolder {

        ImageView iv;
        TextView tv;
        public VH4Flower(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_flower);
            tv  = itemView.findViewById(R.id.tv_flower);
        }

        @Override
        protected void ui4FocusIn(View v, AbsViewHolder holder) {

        }

        @Override
        protected void ui4FocusOut(View v, AbsViewHolder holder) {

        }
    }
}
