package com.inbm.inbmstarter.inbm;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

abstract public class AbsWebProcess {

	private String html = "";

	OnJsonLoadListener listener;
	private WeakReferenceHandler handler;

	public abstract <T> T postProcess() throws Exception;

	public abstract void process();


	public interface OnJsonLoadListener {
		<T> void onJsonLoad(T t);
	}


	static class WeakReferenceHandler extends Handler {
		WeakReference<Object> reference;

		WeakReferenceHandler(Object o) {
			reference = new WeakReference<Object>(o);
		}
	}


	public AbsWebProcess(final OnJsonLoadListener listener) {

		handler = new WeakReferenceHandler(this) {
			public void handleMessage(Message msg) {

				try {
					_log.e("html : " + html);
					listener.onJsonLoad(postProcess());
					super.handleMessage(msg);
				} catch (Exception e) {
					_log.e("json 파싱실패" + e.getMessage());
				}


			}
		};


	}

	public void run() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process();

					handler.sendEmptyMessage(0);

				} catch (Exception e) {
					_log.inCatch("인터넷 접속 확인 : " + e.getMessage());
				}

			}
		}).start();
	}
}
