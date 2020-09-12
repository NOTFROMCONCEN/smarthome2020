package com.example.shengsai2018b0912.tools;

import android.app.Activity;
import android.text.TextUtils;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO App配置相关
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018省赛B0912
 * @file_name AppConfig.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class AppConfig {

	public static float temp = 0;// 温度
	public static float hum = 0;// 湿度
	public static float ill = 0;// 光照
	public static float smo = 0;// 烟雾
	public static float press = 0;// 气压
	public static float pm = 0;// PM2.5
	public static float co = 0;// Co2
	public static float gas = 0;// 燃气
	public static float per = 0;// 人体红外

	/**
	 * 获取数据的线程
	 * 
	 * @param activity
	 */
	private static void data_start(final Activity activity) {
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
							press = Float.valueOf(getdata.getAirPressure());
						}
						// Co2
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							co = Float.valueOf(getdata.getCo2());
						}
						// 燃气
						if (!TextUtils.isEmpty(getdata.getGas())) {
							gas = Float.valueOf(getdata.getGas());
						}
						// 湿度
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							hum = Float.valueOf(getdata.getHumidity());
						}
						// 光照
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							ill = Float.valueOf(getdata.getIllumination());
						}
						// Pm2.5
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							pm = Float.valueOf(getdata.getPM25());
						}
						// 烟雾
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							smo = Float.valueOf(getdata.getSmoke());
						}
						// 温度
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							temp = Float.valueOf(getdata.getTemperature());
						}
						// 人体红外
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							per = Float.valueOf(getdata.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}

	/**
	 * 网络连接的线程
	 * 
	 * @param activity
	 */
	public static void web_start(final Activity activity) {
		// 设置网络连接账号、密码、IP地址
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		// 开启连接
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
							// 成功
							DiyToast.showToast(activity, "网络连接成功");
							data_start(activity);// 开启获取数据线程
						} else if (arg0.equals(ConstantUtil.FAILURE)) {
							// 失败
							DiyToast.showToast(activity, "网络连接失败");
						} else {
							// 未知状态
							DiyToast.showToast(activity, "重连中");
							data_start(activity);// 开启获取数据线程
						}
					}
				});
			}
		});
	}
}
