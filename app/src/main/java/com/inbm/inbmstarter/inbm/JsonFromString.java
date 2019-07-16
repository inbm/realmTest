package com.inbm.inbmstarter.inbm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;


abstract public class JsonFromString {

	private String html = "";
	private WeakReferenceHandler handler;
	public abstract <T> T parse(String json_string) throws JSONException;


	private String[] strs;
	public interface OnJsonLoadListener {
		<T> void onJsonLoad(T t);
	}

	static class WeakReferenceHandler extends Handler
	{
		WeakReference<Object> reference;

		WeakReferenceHandler(Object o)
		{
			reference = new WeakReference<Object>(o);
		}
	}

	public JsonFromString(final OnJsonLoadListener listener, final String... strs)  {

		this.strs = strs;
		
		handler = new WeakReferenceHandler(this){
			public void handleMessage(Message msg){

				try{
					listener.onJsonLoad( parse(html));
					super.handleMessage(msg);
				} catch(Exception e){
					_log.inCatch("json ?????? : " + e.getMessage());
				}


			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					for(String url:strs ) {
						_log.e("--->url : " + url);
						BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "euc-kr"));

						String line = "";
						while (null != (line = br.readLine())) {
							html += line;

						}
						br.close();
					}

					Looper.prepare();
					handler.sendEmptyMessage(0);
					Looper.loop();

				} catch (Exception e) {
					_log.inCatch("인터넷 접속 불가 : " + e.getMessage());
				}

			}
		}).start();
	}
}
