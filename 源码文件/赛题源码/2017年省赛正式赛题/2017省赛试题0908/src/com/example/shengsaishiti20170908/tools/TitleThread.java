package com.example.shengsaishiti20170908.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class TitleThread {

	static Handler handler;
	static Runnable timeRunnable;

	public static void start(final Activity activity) {
		handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy��MM��dd�� HHʱmm��ss��");
				activity.setTitle("----��ӭʹ�����ܼҾӿ���ϵͳ----"
						+ simpleDateFormat.format(new Date()));
				handler.postDelayed(timeRunnable, 500);
			}
		};
		timeRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		};
		handler.post(timeRunnable);
	}

}
