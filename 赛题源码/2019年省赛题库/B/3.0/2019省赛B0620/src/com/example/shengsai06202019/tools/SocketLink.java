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
 * @Todo TODO ���ݲɼ�����������
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019ʡ��0620
 * @file_name SocketLink.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class SocketLink {
	/**
	 * ��ȡ����
	 * 
	 * @param activity
	 */
	private static void start_getdata(final Activity activity) {
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
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());
						}
						// Co2
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							AppConfig.co = Float.valueOf(getdata.getCo2());
						}
						// ȼ��
						if (!TextUtils.isEmpty(getdata.getGas())) {
							AppConfig.gas = Float.valueOf(getdata.getGas());
						}
						// ʪ��
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							AppConfig.hum = Float.valueOf(getdata.getHumidity());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());
						}
						// PM2.5
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							AppConfig.pm = Float.valueOf(getdata.getPM25());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							AppConfig.smo = Float.valueOf(getdata.getSmoke());
						}
						// �¶�
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());
						}
						// �������
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
	 * ��������
	 * 
	 * @param activity
	 */
	public static void start_web(final Activity activity) {
		// �����û��������롢IP
		ControlUtils.setUser("bizideal", "123456", AppConfig.IP);
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
							// �ɹ���������ʾ
							DiyToast.showToast(activity, "�������ӳɹ�");
							// �������ӳɹ�ʱ��������ȡ�����߳�
							start_getdata(activity);
						} else {
							// ʧ�ܣ�������ʾ
							DiyToast.showToast(activity, "��������ʧ��");
						}
					}
				});
			}
		});
	}

}
