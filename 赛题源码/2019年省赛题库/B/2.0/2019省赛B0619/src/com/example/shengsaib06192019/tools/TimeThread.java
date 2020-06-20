package com.example.shengsaib06192019.tools;

import java.text.SimpleDateFormat;

import android.os.Handler;
import android.os.Message;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 时间线程
 * @package_name com.example.shengsaib06192019.tools
 * @project_name 2019省赛B0619
 * @file_name TimeThread.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class TimeThread {

	public static void start_hand() {
		handler.post(timeRunnable);
	}

	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			AppConfig.time = simpleDateFormat.format(new java.util.Date());
			System.out.println(AppConfig.time);
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
