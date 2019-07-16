package com.inbm.inbmstarter.inbm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.lang.ref.WeakReference;


abstract public class ActionAfterCallRemote {

	private String html = "{\"a\":\"aaa\", \"b\":\"bbb\"}";
	private WeakReferenceHandler handler;
	public abstract <T> T callRemote() throws JSONException;


	public interface OnDataReceiveListener {
		<T> void onDataReceived(T t);
	}

	static class WeakReferenceHandler extends Handler
	{
		WeakReference<Object> reference;

		WeakReferenceHandler(Object o)
		{
			reference = new WeakReference<Object>(o);
		}
	}

	public ActionAfterCallRemote(final OnDataReceiveListener listener)  {

		
		
		handler = new WeakReferenceHandler(this){
			public void handleMessage(Message msg){

				try{
					listener.onDataReceived( callRemote());
					super.handleMessage(msg);
				} catch(Exception e){
					_log.inCatch("data : " + e.getMessage());
				}


			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					Looper.prepare();
					handler.sendEmptyMessage(0);
					Looper.loop();

				} catch (Exception e) {
					_log.inCatch(""  + e.getMessage());
				}

			}
		}).start();
	}
}
