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
 * @Todo TODO �������Ӻ����ݻ�ȡ
 * @package_name com.example.getdatausethread
 * @project_name ʹ�õ����̻߳�ȡ����
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
		try {
			/**
			 * ��ʼ��������
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
							if (arg0.equals(ConstantUtil.SUCCESS)) {// �ɹ�
								start_thread(activity);
								DiyToast.showToast(activity, "�����ɹ�");
							} else {// ʧ��
								// progressDialog.dismiss();
								DiyToast.showToast(activity, "����ʧ��");
							}
						}
					});
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(activity, "���д���", Toast.LENGTH_LONG);
		}
	}

	/**
	 * ��ȡ����������
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
