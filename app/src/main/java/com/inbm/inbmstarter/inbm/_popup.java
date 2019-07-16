package com.inbm.inbmstarter.inbm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class _popup {
	public static  void configNetwork(Context context
			, DialogInterface.OnClickListener config
			, DialogInterface.OnClickListener finish){
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(context);
		alt_bld.setMessage("msg_disconnect_internet")
		.setCancelable(false)
		.setPositiveButton("config", config)
		.setNegativeButton("exit", finish);
		
		AlertDialog alert = alt_bld.create();

		alert.setTitle("Title");
		alert.show();
	}
	
	public static  void dofinish(Context context, DialogInterface.OnClickListener finish){
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(context);
		alt_bld.setMessage("msg_disconnect_internet")
		.setCancelable(false)
		.setPositiveButton("yes", finish);
	
		
		AlertDialog alert = alt_bld.create();

		alert.setTitle("Title");
		alert.show();
	}
	
}
