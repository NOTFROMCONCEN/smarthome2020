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
 * @Todo TODO App�������
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018ʡ��B0912
 * @file_name AppConfig.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class AppConfig {

	public static float temp = 0;// �¶�
	public static float hum = 0;// ʪ��
	public static float ill = 0;// ����
	public static float smo = 0;// ����
	public static float press = 0;// ��ѹ
	public static float pm = 0;// PM2.5
	public static float co = 0;// Co2
	public static float gas = 0;// ȼ��
	public static float per = 0;// �������

	/**
	 * ��ȡ���ݵ��߳�
	 * 
	 * @param activity
	 */
	private static void data_start(final Activity activity) {
		ControlUtils.getData();// ��ȡ����
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
						// Pm2.5
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
	 * �������ӵ��߳�
	 * 
	 * @param activity
	 */
	public static void web_start(final Activity activity) {
		// �������������˺š����롢IP��ַ
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		// ��������
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
							// �ɹ�
							DiyToast.showToast(activity, "�������ӳɹ�");
							data_start(activity);// ������ȡ�����߳�
						} else if (arg0.equals(ConstantUtil.FAILURE)) {
							// ʧ��
							DiyToast.showToast(activity, "��������ʧ��");
						} else {
							// δ֪״̬
							DiyToast.showToast(activity, "������");
							data_start(activity);// ������ȡ�����߳�
						}
					}
				});
			}
		});
	}
}
