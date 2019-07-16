package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.inbm.inbmstarter.inbm.FrameLayout4Floating;


/**
 * Created by inbm on 2017. 3. 19..
 */

public abstract class AbsRV4Multi extends RecyclerView {
    private OnClick4Main listener4Main;

    public interface OnClick4Main { void onClick(View v, int position); }
    
    public AbsRV4Multi(Context context) {
        super(context);
    }


    public AbsRV4Multi(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    LayoutManager mLayout;
    Context mContext;
    AbsAdapter4Multi mAdapter;
    FrameLayout4Floating parent;

    public void _click(OnClick4Main listener4Main){
        this.listener4Main = listener4Main;
    }

    public AbsRV4Multi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

/*

        _adapter(new OnData() {
            @Override
            public void ready(AbsAdapter4Multi adapter) {
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

    @Override
    public void setAdapter(final Adapter adapter) {

        mAdapter = (AbsAdapter4Multi) adapter;
        mLayout = _layout_manager();
        mAdapter.notifyDataSetChanged();
        setHasFixedSize(true);
        setLayoutManager(mLayout);



        int offset = getOffset();
        if (offset > 0) {
            addItemDecoration(new StartOffsetItemDecoration(offset));
            addItemDecoration(new EndOffsetItemDecoration(offset));
        }


        mAdapter.setClicklistener(new AbsAdapter4Multi.OnClicked() {
            @Override
            public void onClick(View v, AbsViewHolder4Multi holder) {
                listener4Main.onClick(v, holder.position);
            }
        });

//        mAdapter.setFocuslistener(new AbsAdapter4Multi.OnFocused() {
//            @Override
//            public void onFocus(final View v, boolean hasFocus, AbsViewHolder holder) {
//
//
//                if(hasFocus) {
//                    _focued(v);
//
//                }
//
//
//                //_focus(v, hasFocus, holder);
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
        void ready(AbsAdapter4Multi adapter);
    }
}
