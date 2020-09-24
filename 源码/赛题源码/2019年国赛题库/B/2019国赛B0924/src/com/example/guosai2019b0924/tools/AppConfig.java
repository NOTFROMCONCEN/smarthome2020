package com.example.guosai2019b0924.tools;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.text.TextUtils;

/**
 * App参数配置
 * 
 * @author 10976
 * 
 */
public class AppConfig {
	// 房间号
	public static String room_number = "";
	// 房间模式
	public static String room_state = "基本";
	// 参数
	public static float temp = 0;// 温度
	public static float hum = 0;// 湿度
	public static float press = 0;// 气压
	public static float pm = 0;// PM
	public static float co = 0;// Co2
	public static float gas = 0;// 燃气
	public static float ill = 0;// 光照
	public static float per = 0;// 人体红外
	public static float smo = 0;// 厌恶

	/**
	 * 获取数据
	 * 
	 * @param activity
	 */
	private static void getdata(final Activity activity) {
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
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {
							gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							per = Float.valueOf(getdata.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}

	/**
	 * 网络链接
	 * 
	 * @param activity
	 */
	public static void web_state(final Activity activity) {
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
							DiyToast.showToast(activity, "组网成功");
							getdata(activity);
						} else {
							DiyToast.showToast(activity, "组网失败");
							getdata(activity);
						}
					}
				});
			}
		});
	}
}
