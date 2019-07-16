package com.inbm.inbmstarter.inbm;

import android.util.Log;

public class _log {
	
	static boolean isEnabled = true;
	
	public static void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}
	
	public static void withThrowable(String descrition){
		if (isEnabled) {
			Log.e("com/inbm/inbmstarter/inbm", descrition, new ThrowableWithLog());
		}
	}

	public static void e(String str){
		if (isEnabled) {
			StackTraceElement[] e = new Exception().getStackTrace();
			Log.e("com/inbm/inbmstarter/inbm", str + " at " + e[1]);
		}
	}

	public static void special(String str){
		if (isEnabled) {
			StackTraceElement[] e = new Exception().getStackTrace();
			Log.e("now", str + " at " + e[1]);
		}
	}

	public static void m(String str, Object... objs){
		if (isEnabled) {


			String[] strs = str.split(" ");

			String ret = " ";
			for(int i=0; i<strs.length-1; i++){
				ret += strs[i] + ":" + objs[i] + " ";
			}

			StackTraceElement[] e = new Exception().getStackTrace();
			if(e.length >= 1)
				Log.e("com/inbm/inbmstarter/inbm", ret + " at " + e[1]);
			else
				Log.e("com/inbm/inbmstarter/inbm", str);
		}
	}

	public static void obj(Object...objects){
		if (isEnabled) {

			String str  = "";


			for(Object o:objects){
				_log.e(o.getClass().toString());
			}



		}

	}


	public static void memory(){
		if (isEnabled) {
			String total, max, free, alloc;

			total = Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB ";
			max = Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB ";
			free = Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB ";
			alloc = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "MB";
			
			String str = total + max + free + alloc;

			StackTraceElement[] e = new Exception().getStackTrace();
			Log.e("com/inbm/inbmstarter/inbm", str +  " : " + e[1]);
		}
	}

	public static void simple(String str){
		if (isEnabled) {
			Log.e("com/inbm/inbmstarter/inbm", str);
		}
	}

	public static void inCatch(String descrition){
		if (isEnabled) {
			Log.e("com/inbm/inbmstarter/inbm", "catch {");
			Log.e("com/inbm/inbmstarter/inbm", descrition, new Throwable());
			Log.e("com/inbm/inbmstarter/inbm", "}");
		}
	}

}
