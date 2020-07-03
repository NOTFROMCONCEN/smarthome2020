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
 * @Todo TODO ����
 * @package_name com.example.shengsaia06302019.tools
 * @project_name 2019ʡ��A0630
 * @file_name AppTools.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class AppTools {
	// IP��ַ
	public static String IP = "";
	// �������
	public static String room_number = "";
	// �¶�
	public static float temp = 0;
	// ʪ��
	public static float hum = 0;
	// ȼ��
	public static float gas = 0;
	// ����
	public static float ill = 0;
	// ����
	public static float smo = 0;
	// ��ѹ
	public static float press = 0;
	// PM2.5
	public static float pm = 0;
	// CO2
	public static float co = 0;
	// �������
	public static float per = 0;

	/**
	 * ��ȡ����
	 * 
	 * @param activity
	 */
	private static void getdata(final Activity activity) {
		// ��ȡ����
		ControlUtils.getData();
		// ���Դӷ�������ȡ����
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// ��ѹ
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {
							press = Float.valueOf(getdata.getAirPressure());
						}
						// Co2
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							co = Float.valueOf(getdata.getCo2());
						}
						// ȼ��
						if (!TextUtils.isEmpty(getdata.getGas())) {
							gas = Float.valueOf(getdata.getGas());
						}
						// ʪ��
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							hum = Float.valueOf(getdata.getHumidity());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							ill = Float.valueOf(getdata.getIllumination());
						}
						// PM2.5
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							pm = Float.valueOf(getdata.getPM25());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							smo = Float.valueOf(getdata.getSmoke());
						}
						// �¶�
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							temp = Float.valueOf(getdata.getTemperature());
						}
						// �������
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							per = Float.valueOf(getdata.getStateHumanInfrared());
						}
					}
				});
			}
		});
	}

	/**
	 * ����
	 * 
	 * @param activity
	 */
	public static void start_link(final Activity activity) {
		// ���ò�����IP
		ControlUtils.setUser("bizideal", "123456", IP);
		// ��������
		SocketClient.getInstance().creatConnect();
		// ���Ե�¼������
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							// ����SUCCESS--�����ɹ�
							DiyToast.showToast(activity, "�����ɹ�");
							getdata(activity);
						} else {
							// ����ʧ��
							DiyToast.showToast(activity, "����ʧ�ܣ�" + arg0);
							getdata(activity);
						}
					}
				});
			}
		});
	}
}
