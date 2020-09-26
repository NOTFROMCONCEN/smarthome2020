package com.example.guosai2019d0926.tools;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;

public class AppConfig {

	public static float temp = 0;
	public static float hum = 0;
	public static float smo = 0;
	public static float gas = 0;
	public static float ill = 0;
	public static float per = 0;
	public static float pm = 0;
	public static float co = 0;
	public static float press = 0;

	public static void web_server(final Activity activity) {
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String aString) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (aString.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(activity, "网络连接成功");
							getdata(activity);
						} else {
							DiyToast.showToast(activity, "网络连接失败");
							getdata(activity);
						}
					}
				});
			}
		});
	}

	private static void getdata(final Activity activity) {

	}

}
