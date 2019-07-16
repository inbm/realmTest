package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class AbsAdapter4Multi<VH extends AbsViewHolder4Multi> extends RecyclerView.Adapter {

    protected Context context;
//    protected int layout;

    OnClicked clicklistener;

    public interface OnClicked {
        void onClick(View v, AbsViewHolder4Multi holder);
    }

    public interface OnFocused {
        void onFocus(View v, boolean hasFocus, AbsViewHolder4Multi holder);
    }

    public AbsAdapter4Multi(Context context, OnClicked clicklistener) {
        this.context = context;
//        this.layout = getLayout();
        this.clicklistener = clicklistener;
    }

    public AbsAdapter4Multi(Context context) {
        this.context = context;
//        this.layout = getLayout();
    }

    public void setClicklistener(OnClicked listener) {
        this.clicklistener = listener;
    }

    public abstract int getViewType(int position);

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    protected abstract int getLayout(int viewType);

    abstract protected VH getViewHolder(View v, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VH holder;

        View itemView = LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false);

        holder = getViewHolder(itemView, viewType);

        holder.setListener((v, vh) -> clicklistener.onClick(v, (AbsViewHolder4Multi) vh));

        return holder;
    }

    abstract protected void display(VH holder, int position);

    abstract protected int getCount();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((AbsViewHolder4Multi) holder).setPosition(position);
        display((VH) holder, position);
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

}
