package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.inbm.inbmstarter.R;

public class _dialog {

    public static void notitle_show(Context context, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive)
                .setNegativeButton("취소", (dialog, whichButton) -> dialog.cancel());

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public static void notitle_show_with_oneBtn(Context context, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive);
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }


    public static void show(Context context, String title, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);     // 여기서 this는 Activity의 this
        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle(title)
                .setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive)
                .setNegativeButton("취소", (dialog, whichButton) -> dialog.cancel());

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public static void onCreateDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_base, null))
                // Add action buttons
                .setPositiveButton("삭제", (dialog, id) -> {
                    // sign in the user ...
                })
                .setNegativeButton("취소", (dialog, id) -> dialog.cancel());
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.horizontalMargin = 50;
        dialog.getWindow().setAttributes(params);
        dialog.show();    // 알림창 띄우기
    }
}
