package com.example.shengsaib06192019.tools;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.text.TextUtils;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 网络连接、数据采集
 * @package_name com.example.shengsaib06192019.tools
 * @project_name 2019省赛B0619
 * @file_name SocketLink.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class SocketLink {
	public static void start_link(final Activity activity) {
		ControlUtils.setUser(AppConfig.username, AppConfig.passward,
				AppConfig.IP);
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
							start_getdata(activity);
						} else {
							DiyToast.showToast(activity, "网络连接失败");
						}
					}
				});
			}
		});
	}

	public static void start_getdata(final Activity activity) {
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							AppConfig.co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {
							AppConfig.gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							AppConfig.hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							AppConfig.pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							AppConfig.smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							AppConfig.per = Float.valueOf(getdata
									.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}

}
