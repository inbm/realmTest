package com.inbm.inbmstarter.inbm;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inbm.inbmstarter.R;

public class _actionbar {

    private int menu;
    private int textColor;
    private OnMenuIconClickListener onMenuIconClickListener;
    private SearchView.OnQueryTextListener onQueryTextListener;

    private _actionbar(Builder builder) {
        this.menu = builder.menu;
        this.onMenuIconClickListener = builder.onMenuIconClickListener;
        this.onQueryTextListener = builder.onQueryTextListener;
    }

    public int getMenu() {
        return menu;
    }

    public OnMenuIconClickListener getOnMenuIconClickListener() {
        return onMenuIconClickListener;
    }

    public interface OnMenuIconClickListener {
        boolean onMenuIconClick(MenuItem item);
    }

    public static class Builder {

        private AppCompatActivity view;
        private int menu;

        private String title;
        private boolean backIcon;

        private OnMenuIconClickListener onMenuIconClickListener;

        private SearchView.OnQueryTextListener onQueryTextListener;

        public Builder(AppCompatActivity view) {
            this.view = view;
        }

//        public Builder setTitle(String title) {
//            this.title = title;
//            return this;
//        }

        public Builder setBackIcon(boolean backIcon) {
            this.backIcon = backIcon;
            return this;
        }

        public Builder setMenu(int menu, OnMenuIconClickListener onMenuIconClickListener) {
            this.menu = menu;
            this.onMenuIconClickListener = onMenuIconClickListener;
            return this;
        }

        public Builder setSearchView(SearchView.OnQueryTextListener onQueryTextListener){


            return this;
        }
//        public _actionbar build() {
//
//            ActionBar actionBar = view.getSupportActionBar();
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setElevation(0);
//            if (title != null) {
//                actionBar.setTitle(title);
//                actionBar.setDisplayShowTitleEnabled(false);
//            }
//            if (backIcon > 0) {
//                actionBar.setHomeAsUpIndicator(backIcon);
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }
//            return new _actionbar(this);
//        }

        public _actionbar build(Context context, String title) {

            ActionBar actionBar = view.getSupportActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setElevation(0);

            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            View mCustomView = LayoutInflater.from(context).inflate(R.layout.actionbar_title, null);

            TextView tv = mCustomView.findViewById(R.id.text);
            tv.setText(title);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            if (backIcon) {
                actionBar.setHomeAsUpIndicator(R.drawable.arrow_back);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            actionBar.setCustomView(mCustomView, params);
            return new _actionbar(this);
        }


        public _actionbar build(Context context, String title, String title2) {

            ActionBar actionBar = view.getSupportActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setElevation(0);

            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            View mCustomView = LayoutInflater.from(context).inflate(R.layout.actionbar_title, null);

            TextView tv = mCustomView.findViewById(R.id.text);
            tv.setText(title);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            TextView tv2 = mCustomView.findViewById(R.id.text2);
            tv2.setText(title2);
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv2.setVisibility(View.VISIBLE);

            if (backIcon) {
                actionBar.setHomeAsUpIndicator(R.drawable.arrow_back);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            actionBar.setCustomView(mCustomView, params);
            return new _actionbar(this);
        }


        //TODO 대표님께 서치뷰 liflate 물어보기
        public _actionbar build(Context context, Activity activity) {

            ActionBar actionBar = view.getSupportActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setElevation(0);


            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            View mCustomView = LayoutInflater.from(context).inflate(R.layout.actionbar_searchview, null);
            //Only For MainActivity..




            if (backIcon) {
                actionBar.setHomeAsUpIndicator(R.drawable.arrow_back);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            actionBar.setCustomView(mCustomView, params);
            return new _actionbar(this);
        }


    }
}