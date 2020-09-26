package com.example.guosaic20190925.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class TimeTherad {

	static Handler handler;
	static Runnable timeRunnable;

	public static void start(final TextView tv_time) {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyÄêMMÔÂddÈÕ HH:mm:ss");
				tv_time.setText(simpleDateFormat.format(new Date()));
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
	}
}
