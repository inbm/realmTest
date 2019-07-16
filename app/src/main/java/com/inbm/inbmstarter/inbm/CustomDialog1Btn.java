package com.inbm.inbmstarter.inbm;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.inbm.inbmstarter.R;

public class CustomDialog1Btn extends DialogFragment {
    private static final String ARG_DIALOG_CONTENT_MSG = "dialog_content_msg";
    private static final String ARG_DIALOG_DESCRIPTION_MSG = "dialog_description_msg";
    private static final String ARG_DIALOG_BTN = "dialog_btn";
    private String mContentMsg;
    private String mDecMsg;
    private String btn;
    private OnCustomDialogClickListener mListener;

    public interface OnCustomDialogClickListener {
        void onButtonPressed();
    }

    public CustomDialog1Btn() {
        //empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mContentMsg = getArguments().getString(ARG_DIALOG_CONTENT_MSG);
            if (getArguments().getString(ARG_DIALOG_DESCRIPTION_MSG) != null)
                mDecMsg = getArguments().getString(ARG_DIALOG_DESCRIPTION_MSG);
            btn = getArguments().getString(ARG_DIALOG_BTN);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = getResources().getDimensionPixelSize(R.dimen.dialog_fragment_width);
//        lp.height = getResources().getDimensionPixelSize(R.dimen.dialog_fragment_height);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0.6f;
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getDialog().getWindow().setAttributes(lp);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_radius_white);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_1btn, null);
        ((TextView) view.findViewById(R.id.btn_dialog)).setText(btn);
        if (mContentMsg != null) {
            ((TextView) view.findViewById(R.id.tv_content_dialog)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tv_content_dialog)).setText(mContentMsg);
        } else ((TextView) view.findViewById(R.id.tv_content_dialog)).setVisibility(View.GONE);
        TextView tv_content = view.findViewById(R.id.tv_discription_dialog);
        if (mDecMsg != null) {
            tv_content.setVisibility(View.VISIBLE);
            tv_content.setText(mDecMsg);
        } else tv_content.setVisibility(View.GONE);
        view.findViewById(R.id.btn_dialog).setOnClickListener(view1 -> {
            mListener.onButtonPressed();
            dismissDialog();
        });
        builder.setView(view);
        return builder.create();
    }

    private void dismissDialog() {
        this.dismiss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public static class Builder {
        private final Bundle arguments;


        private OnCustomDialogClickListener onCustomDialogClickListener;

        public Builder() {
            arguments = new Bundle();
        }

        public Builder setButtonOnclick(OnCustomDialogClickListener onCustomDialogClickListener) {
            this.onCustomDialogClickListener = onCustomDialogClickListener;
            return this;
        }

        public Builder setContent(String content) {
            arguments.putString(ARG_DIALOG_CONTENT_MSG, content);
            return this;
        }

        public Builder setDescription(String description) {
            arguments.putString(ARG_DIALOG_DESCRIPTION_MSG, description);
            return this;
        }

        public Builder setBtnName(String btnLeft) {
            arguments.putString(ARG_DIALOG_BTN, btnLeft);
            return this;
        }


        public CustomDialog1Btn build() {
            CustomDialog1Btn fragment = new CustomDialog1Btn();
            fragment.setArguments(arguments);
            fragment.mListener = onCustomDialogClickListener;
            return fragment;
        }
    }
}
