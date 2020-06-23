package com.example.shengsai06202019.tools;

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
 * @Todo TODO 数据采集、网络连接
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019省赛0620
 * @file_name SocketLink.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class SocketLink {
	/**
	 * 获取数据
	 * 
	 * @param activity
	 */
	private static void start_getdata(final Activity activity) {
		ControlUtils.getData();// 获取数据
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 气压
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());
						}
						// Co2
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							AppConfig.co = Float.valueOf(getdata.getCo2());
						}
						// 燃气
						if (!TextUtils.isEmpty(getdata.getGas())) {
							AppConfig.gas = Float.valueOf(getdata.getGas());
						}
						// 湿度
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							AppConfig.hum = Float.valueOf(getdata.getHumidity());
						}
						// 光照
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());
						}
						// PM2.5
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							AppConfig.pm = Float.valueOf(getdata.getPM25());
						}
						// 烟雾
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							AppConfig.smo = Float.valueOf(getdata.getSmoke());
						}
						// 温度
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());
						}
						// 人体红外
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							AppConfig.per = Float.valueOf(getdata
									.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}

	/**
	 * 连接网络
	 * 
	 * @param activity
	 */
	public static void start_web(final Activity activity) {
		// 设置用户名、密码、IP
		ControlUtils.setUser("bizideal", "123456", AppConfig.IP);
		// 创建连接
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
							// 成功，给出提示
							DiyToast.showToast(activity, "网络连接成功");
							// 网络连接成功时，启动获取数据线程
							start_getdata(activity);
						} else {
							// 失败，给出提示
							DiyToast.showToast(activity, "网络连接失败");
						}
					}
				});
			}
		});
	}

}
