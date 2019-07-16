package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.inbm.inbmstarter.inbm.FrameLayout4Floating;
import com.inbm.inbmstarter.inbm._header_;

import java.util.ArrayList;


/**
 * Created by inbm on 2017. 3. 19..
 */

public abstract class AbsRV extends RecyclerView {
    private OnClick4Main listener4Main;

    public interface OnClick4Main { void onClick(View v, int position); }

    public AbsRV(Context context) {
        super(context);
    }


    public AbsRV(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    LayoutManager mLayout;
    Context mContext;
    AbsAdapter4Header mAdapter;
    FrameLayout4Floating parent;


    public void _click(OnClick4Main listener4Main){
        this.listener4Main = listener4Main;
    }


    //protected abstract void _adapter(OnData data);

    public AbsRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

/*

        _adapter(new OnData() {
            @Override
            public void ready(AbsAdapter4Header adapter) {
                setAdapter(adapter);
            }
        });
*/
    }


    //protected abstract void _click(View v, AbsViewHolder holder);
    //protected abstract void _focus(View v, boolean hasFocus, AbsViewHolder holder);


    protected abstract LayoutManager _layout_manager();
    protected abstract void _focued(View v);
    protected abstract int getOffset();

    protected abstract ArrayList<_header_> getHeaders();
    @Override
    public void setAdapter(final Adapter adapter) {

        mAdapter = (AbsAdapter4Header)adapter;
        //mLayout = new GridLayoutManager4Focus(mContext, 6, this);
        //mLayout = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mAdapter.setHeaders(getHeaders());
        mLayout = _layout_manager();
        mAdapter.notifyDataSetChanged();
        setHasFixedSize(true);
        setLayoutManager(mLayout);



        int offset = getOffset();
        if (offset > 0) {
            addItemDecoration(new StartOffsetItemDecoration(offset));
            addItemDecoration(new EndOffsetItemDecoration(offset));
        }

//        주석처리 해야함
//        mAdapter.setClicklistener(new AbsAdapter4Header.OnClicked() {
//            @Override
//            public void onClick(View v, AbsViewHolder holder) {
//
//                //_click(v, holder);
//                listener4Main.onClick(v, holder.getAdapterPosition());
//            }
//        });


//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                View v = findViewHolderForAdapterPosition(0).itemView;
//                v.requestFocus();
//                //v.setBackgroundColor(Color.rgb(25, 26, 28));
//
//
//                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//
//            }
//        });

        super.setAdapter(adapter);
    }

    public void setParent(FrameLayout4Floating parent){
        this.parent = parent;
    }


    @Override
    public void onScrolled(int dx, int dy) {

        if(parent != null)
            parent.adjustTitlePosition();

        super.onScrolled(dx, dy);
    }



    @Override
    public void onScrollStateChanged(int state) {

//        _log.m("rv_scroll_state", state);

        if(state == 0) {
            View v = getFocusedChild();
            if(v == null)
                return;

            int[] originalPos = new int[2];
            v.getLocationInWindow(originalPos);
            int x = originalPos[0];
            int y = originalPos[1];


            //parent.showTitle("test", x, y);
        }

        super.onScrollStateChanged(state);
    }

    public interface OnData {
        void ready(AbsAdapter4Header adapter);
    }
}
