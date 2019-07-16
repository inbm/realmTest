package com.inbm.inbmstarter.inbm;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class CustomTimer {

	private TimerTask mTask;
	private Timer mTimer;

	private WeakReferenceHandler handler;

	private ITimerHandler timerHandler = null;

	private int durationTime = 0;


	public CustomTimer(ITimerHandler context){
		timerHandler = context;

	}


	public void start(int start, int dur){

		mTask = new TimerTask() {
			@Override
			public void run() {
				durationTime = durationTime + 1000;
				try{
					handler.sendEmptyMessage(durationTime);
				}catch (Exception e){
					_log.e("타이머 error::"+ e.toString());
				}

			}
		};

		mTimer = new Timer();
		mTimer.schedule(mTask, start, dur);

		handler = new WeakReferenceHandler(this){
			public void handleMessage(Message msg){

				try{
					super.handleMessage(msg);

					int dur = msg.what;
					timerHandler.handleMessage(dur);
				} catch(Exception e){
					_log.e("error::"+ e.toString());
				}
			}
		};
	}

	public void cancle(){
		_log.e("타이머 종료 ::");
		if(mTimer != null){
			mTimer.cancel();
			mTimer = null;
		}

		if(handler != null){
			handler.removeMessages(0);
			handler = null;
		}

		mTask = null;
	}

	public class WeakReferenceHandler extends Handler
	{
		WeakReference<Object> reference;

		WeakReferenceHandler(Object o)
		{
			reference = new WeakReference<Object>(o);
		}
	}

	public interface ITimerHandler {
		public void handleMessage(int time);
	}
}
