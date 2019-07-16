package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inbm.inbmstarter.R;
import com.inbm.inbmstarter.inbm._header_;

import java.util.ArrayList;


public abstract class AbsAdapter4Header<VH extends AbsViewHolder> extends RecyclerView.Adapter {


    protected Context context;
    protected int layout;
    protected int headerLayout;


    protected ArrayList<_header_> headers = null;
    OnClicked clicklistener;


    public interface OnClicked {
        void onClick(View v, AbsViewHolder holder);

    }

    public interface OnFocused {
        void onFocus(View v, boolean hasFocus, AbsViewHolder holder);
    }

    public AbsAdapter4Header(Context context, OnClicked clicklistener) {
        headers = new ArrayList<>();
        this.context = context;
        this.layout = getLayout();
        this.headerLayout = getHeadLayout();
        this.clicklistener = clicklistener;
    }

    public AbsAdapter4Header(Context context) {
        headers = new ArrayList<>();
        this.context = context;
        this.layout = getLayout();
        this.headerLayout = getHeadLayout();
    }

    public void setClicklistener(OnClicked listener) {
        this.clicklistener = listener;
    }


    protected abstract int getLayout();

    protected abstract int getHeadLayout();

    abstract protected VH getViewHolder(View v);

    public int getHeaderCount() {
        return headers.size();
    }

    public ArrayList<_header_> getHeader() {
        return headers;
    }

    public void setHeaders(ArrayList<_header_> headers) {

        this.headers = headers;


    }


    @Override
    public int getItemViewType(int position) {

        if (headers == null)
            return super.getItemViewType(position);
        else {
            for (_header_ h : headers) {
                if (position == h.pos)
                    return IS_HEADER;
            }
            return NOT_HEADER;
        }
    }

    final int NOT_HEADER = 0;
    final int IS_HEADER = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);

        VH holder;

        if (viewType == IS_HEADER) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(headerLayout, parent, false);

            holder = getViewHolder(itemView);


        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);


            holder = getViewHolder(itemView);


            holder.setListener(new AbsViewHolder.OnClick() {
                @Override
                public void onClick(View v, AbsViewHolder vh) {
                    clicklistener.onClick(v, vh);
                }


            });


        }

        return holder;
    }


    abstract protected void display(VH holder, int position);

    abstract protected int getCount();


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        _log.e("" + position);
        ((AbsViewHolder) holder).setPosition(position);

        if (headers != null) {
            for (_header_ h : headers) {

                if (h.pos == position) {
//                    ((TextView) ((AbsViewHolder) holder).itemView.findViewById(R.id.tv_header)).setText(h.str_header);
                    return;
                }
            }
        }

        display((VH) holder, position);


    }

    @Override
    public int getItemCount() {
        return getCount();
    }


}
