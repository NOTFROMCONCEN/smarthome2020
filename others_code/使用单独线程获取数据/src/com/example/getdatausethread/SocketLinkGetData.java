package com.example.getdatausethread;

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
			 * 创建ProgressBar
			 */
			final ProgressDialog progressDialog = new ProgressDialog(activity);
			progressDialog.setTitle("加载中");
			progressDialog.setMessage("正在连接服务器，请稍后");
			progressDialog.show();
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
							if (arg0.equals(ConstantUtil.SUCCESS)) {
								start_thread(activity);
								progressDialog.dismiss();
								Toast.makeText(activity, "连接成功",
										Toast.LENGTH_LONG);
							} else {
								// progressDialog.dismiss();
								progressDialog.setMessage("连接错误：" + arg0
										+ "\n请检查服务器设置和嵌入式套件系统配置");
								Toast.makeText(activity, "网络连接错误",
										Toast.LENGTH_LONG);
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
						final ProgressDialog progressDialog = new ProgressDialog(
								activity);
						/**
						 * /** 正式开始获取各个传感器的数据
						 */
						// 气压
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());
						}
						// 温度
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 气压
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());
						}
						// 光照
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());
						}
						// 烟雾
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							AppConfig.smo = Float.valueOf(getdata.getSmoke());
						}
						// 气压
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							AppConfig.co = Float.valueOf(getdata.getSmoke());
						}
					}
				});
			}
		});
	}
}
