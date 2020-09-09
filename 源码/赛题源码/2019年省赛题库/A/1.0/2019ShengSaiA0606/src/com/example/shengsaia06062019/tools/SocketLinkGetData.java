package com.example.shengsaia06062019.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaia06062019.AppConfig;
import com.example.shengsaia06062019.DiyToast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 网络连接、数据采集
 * @package_name com.example.shengsaia06062019.tools
 * @project_name 2019ShengSaiA0606
 * @file_name SocketLinkGetData.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class SocketLinkGetData {

	/**
	 * 连接网络
	 * 
	 * @param activity
	 * @param ip
	 * @return
	 */
	public static void start_link_server(final Activity activity, String ip) {
		/**
		 * 开始连接网络
		 */
		ControlUtils.setUser("bizideal", "123456", ip);// 设置USER
		SocketClient.getInstance().creatConnect();// 启动连接
		// 创建LoginCallback方法
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				// runOnUiThread用于更新UI
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {// 成功
							start_thread(activity);// 开始获取数据
							DiyToast.showToast(activity, "组网成功");
						} else {// 失败
							DiyToast.showToast(activity, "组网失败");
						}
					}
				});
			}
		});
	}

	/**
	 * 获取服务器数据
	 * 
	 * @param activity
	 */
	public static void start_thread(final Activity activity) {
		ControlUtils.getData();// 启动获取数据方法
		// 创建DataCallback方法
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				// runOnUiThread用于更新UI
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							AppConfig.co = Float.valueOf(getdata.getCo2());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							AppConfig.gas = Float.valueOf(getdata.getGas());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							AppConfig.hum = Float.valueOf(getdata.getHumidity());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							AppConfig.pm = Float.valueOf(getdata.getPM25());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							AppConfig.smo = Float.valueOf(getdata.getSmoke());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());// 赋值
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							AppConfig.press = Float.valueOf(getdata
									.getStateHumanInfrared());// 赋值
						}
					}
				});
			}
		});
	}
}
