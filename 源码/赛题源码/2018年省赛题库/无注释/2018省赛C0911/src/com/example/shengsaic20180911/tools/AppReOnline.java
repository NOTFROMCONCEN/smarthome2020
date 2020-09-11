package com.example.shengsaic20180911.tools;

import android.app.Activity;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class AppReOnline {
	public static void check(final Activity activity) {
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(activity, "网络连接成功");
						} else if (arg0.equals(ConstantUtil.FAILURE)) {
							DiyToast.showToast(activity, "网络连接失败");
						} else {
							DiyToast.showToast(activity, "正在重连");
						}
					}
				});
			}
		});
	}
}
