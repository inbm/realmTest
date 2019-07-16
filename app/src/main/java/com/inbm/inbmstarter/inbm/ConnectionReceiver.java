package com.inbm.inbmstarter.inbm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {

	private static final int STATE_NONE = 0;
	private static final int STATE_WIFI_CONNECTED = 1;
	private static final int STATE_MOBILE_CONNECTED = 2;
	private int state = STATE_NONE;

	Context context;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		this.context = context;

		try
		{
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION))
			{

				ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo niWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				NetworkInfo niMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (state == STATE_NONE)
				{
					if (niWifi.getState() == State.CONNECTED)
					{
						state = STATE_WIFI_CONNECTED;
						_log.e("WIFI로 연결");
						_toast("WIFI로 연결");

					}
					else if (niMobile.getState() == State.CONNECTED)
					{
						state = STATE_MOBILE_CONNECTED;
						_log.e("3G로 연결");
						_toast("3G/LTE로 연결");

					}
				}
				else if (state == STATE_WIFI_CONNECTED)
				{
					if (niWifi.getState() == State.DISCONNECTED || niWifi.getState() == State.DISCONNECTING)
					{
						state = STATE_NONE;
						_log.e("WIFI 연결끊김");
						_toast("WIFI 연결끊김");

						// 데이터 네트워크 사용 허용 다이얼로그

					}
				}
				else if (state == STATE_MOBILE_CONNECTED)
				{
					if (niMobile.getState() == State.DISCONNECTED || niMobile.getState() == State.DISCONNECTING)
					{
						state = STATE_NONE;
						_log.e("3G 연결끊김");
						_toast("3G/LTE 연결끊김");

						// 데이터 네트워크 사용 불가 팝업

					}
				}
			}
		}
		catch (Exception e)
		{
			_log.inCatch(e.getMessage());
		}
	}

	private void _toast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
