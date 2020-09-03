package com.example.shengsaib06282019.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.os.Message;

public class TimeThread {

	public static void start() {
		handler.post(timeRunnable);
	}

	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyyÄêMMÔÂddÈÕ HH:mm:ss");
			String string = simpleDateFormat.format(new Date());
			AppConfig.time = string;
			handler.postDelayed(timeRunnable, 500);
		}
	};
	static Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
