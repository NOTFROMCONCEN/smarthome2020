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
 * @Todo TODO �������ӡ����ݲɼ�
 * @package_name com.example.shengsaia06062019.tools
 * @project_name 2019ShengSaiA0606
 * @file_name SocketLinkGetData.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class SocketLinkGetData {

	/**
	 * ��������
	 * 
	 * @param activity
	 * @param ip
	 * @return
	 */
	public static void start_link_server(final Activity activity, String ip) {
		/**
		 * ��ʼ��������
		 */
		ControlUtils.setUser("bizideal", "123456", ip);// ����USER
		SocketClient.getInstance().creatConnect();// ��������
		// ����LoginCallback����
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				// runOnUiThread���ڸ���UI
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {// �ɹ�
							start_thread(activity);// ��ʼ��ȡ����
							DiyToast.showToast(activity, "�����ɹ�");
						} else {// ʧ��
							DiyToast.showToast(activity, "����ʧ��");
						}
					}
				});
			}
		});
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param activity
	 */
	public static void start_thread(final Activity activity) {
		ControlUtils.getData();// ������ȡ���ݷ���
		// ����DataCallback����
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				// runOnUiThread���ڸ���UI
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							AppConfig.co = Float.valueOf(getdata.getCo2());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							AppConfig.gas = Float.valueOf(getdata.getGas());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							AppConfig.hum = Float.valueOf(getdata.getHumidity());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							AppConfig.pm = Float.valueOf(getdata.getPM25());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							AppConfig.smo = Float.valueOf(getdata.getSmoke());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());// ��ֵ
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
							AppConfig.press = Float.valueOf(getdata
									.getStateHumanInfrared());// ��ֵ
						}
					}
				});
			}
		});
	}
}
