package com.example.shengsaib06232019.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.os.Message;

public class TimeThread {

	public static void start_time() {
		handler.post(timeRunnable);
	}

	private static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyyÄêMMÔÂddÈÕ HH:mm:ss");
			AppConfig.time = simpleDateFormat.format(new Date());
			System.out.println(AppConfig.time);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	private static Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

}
