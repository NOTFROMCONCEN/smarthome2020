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
			 * ����ProgressBar
			 */
			final ProgressDialog progressDialog = new ProgressDialog(activity);
			progressDialog.setTitle("������");
			progressDialog.setMessage("�������ӷ����������Ժ�");
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
								Toast.makeText(activity, "���ӳɹ�",
										Toast.LENGTH_LONG);
							} else {
								// progressDialog.dismiss();
								progressDialog.setMessage("���Ӵ���" + arg0
										+ "\n������������ú�Ƕ��ʽ�׼�ϵͳ����");
								Toast.makeText(activity, "�������Ӵ���",
										Toast.LENGTH_LONG);
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
						final ProgressDialog progressDialog = new ProgressDialog(
								activity);
						/**
						 * /** ��ʽ��ʼ��ȡ����������������
						 */
						// ��ѹ
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							AppConfig.press = Float.valueOf(getdata
									.getAirPressure());
						}
						// �¶�
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// ��ѹ
							AppConfig.temp = Float.valueOf(getdata
									.getTemperature());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							AppConfig.ill = Float.valueOf(getdata
									.getIllumination());
						}
						// ����
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							AppConfig.smo = Float.valueOf(getdata.getSmoke());
						}
						// ��ѹ
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							AppConfig.co = Float.valueOf(getdata.getSmoke());
						}
					}
				});
			}
		});
	}
}
