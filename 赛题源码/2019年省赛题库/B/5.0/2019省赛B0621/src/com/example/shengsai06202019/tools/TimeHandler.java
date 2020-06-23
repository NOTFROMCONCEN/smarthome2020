package com.example.shengsai06202019.tools;

import java.text.SimpleDateFormat;

import android.os.Handler;
import android.os.Message;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 获取时间的线程
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019省赛B0620
 * @file_name TimeHandler.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class TimeHandler {

	public static void start_time() {
		// TODO Auto-generated method stub
		handler.post(timeRunnable);
	}

	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			AppConfig.time = simpleDateFormat.format(new java.util.Date());
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
