package com.example.shengsai2019b0915.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class TimeThread {

	private static Handler handler;
	private static Runnable timeRunnable;
	public static String time = "";

	public static void start(Context context, final TextView tv_time) {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyƒÍMM‘¬dd»’ HH:mm:ss");
				tv_time.setText(simpleDateFormat.format(new Date()));
				time = simpleDateFormat.format(new Date());
				handler.postDelayed(timeRunnable, 1000);
			}
		};
		timeRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msgMessage = handler.obtainMessage();
				handler.sendMessage(msgMessage);
			}
		};
		handler.post(timeRunnable);
	};

}
