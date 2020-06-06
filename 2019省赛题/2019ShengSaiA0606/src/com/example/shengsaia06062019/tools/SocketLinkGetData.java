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
 * @Todo TODO 网络连接和数据获取
 * @package_name com.example.getdatausethread
 * @project_name 使用单独线程获取数据
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
		try {
			/**
			 * 开始连接网络
			 */
			ControlUtils.setUser("bizideal", "123456", ip);
			SocketClient.getInstance().creatConnect();
			SocketClient.getInstance().login(new LoginCallback() {

				@Override
				public void onEvent(final String arg0) {
					// TODO Auto-generated method stub
					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (arg0.equals(ConstantUtil.SUCCESS)) {// 成功
								start_thread(activity);
								DiyToast.showToast(activity, "组网成功");
							} else {// 失败
								// progressDialog.dismiss();
								DiyToast.showToast(activity, "组网失败");
							}
						}
					});
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(activity, "运行错误", Toast.LENGTH_LONG);
		}
	}

	/**
	 * 获取服务器数据
	 * 
	 * @param activity
	 */
	public static void start_thread(final Activity activity) {
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
							AppConfig.press = Float.valueOf(getdata
									.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}
}
