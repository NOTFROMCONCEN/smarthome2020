package com.example.shengsaia06302019.tools;

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
 * @Todo TODO 工具
 * @package_name com.example.shengsaia06302019.tools
 * @project_name 2019省赛A0630
 * @file_name AppTools.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class AppTools {
	// IP地址
	public static String IP = "";
	// 房间号码
	public static String room_number = "";
	// 温度
	public static float temp = 0;
	// 湿度
	public static float hum = 0;
	// 燃气
	public static float gas = 0;
	// 光照
	public static float ill = 0;
	// 烟雾
	public static float smo = 0;
	// 气压
	public static float press = 0;
	// PM2.5
	public static float pm = 0;
	// CO2
	public static float co = 0;
	// 人体红外
	public static float per = 0;

	/**
	 * 获取数据
	 * 
	 * @param activity
	 */
	private static void getdata(final Activity activity) {
		// 获取数据
		ControlUtils.getData();
		// 尝试从服务器获取数据
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
						// PM2.5
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
	 * 联网
	 * 
	 * @param activity
	 */
	public static void start_link(final Activity activity) {
		// 设置参数、IP
		ControlUtils.setUser("bizideal", "123456", IP);
		// 开启连接
		SocketClient.getInstance().creatConnect();
		// 尝试登录服务器
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							// 返回SUCCESS--组网成功
							DiyToast.showToast(activity, "组网成功");
							getdata(activity);
						} else {
							// 组网失败
							DiyToast.showToast(activity, "组网失败：" + arg0);
							getdata(activity);
						}
					}
				});
			}
		});
	}
}
