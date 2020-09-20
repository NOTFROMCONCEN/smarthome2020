package com.example.shengsai2019d0920;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_text);
		tv_loading_number.setText("正在初始化");
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(number);
			switch (number) {
			case 21:
				tv_loading_number.setText("正在载入系统");
				break;
			case 51:
				tv_loading_number.setText("正在连接服务器");
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msgMessage = handler.obtainMessage();
			if (number > 100) {
				handler.removeCallbacks(timeRunnable);
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			} else {
				handler.sendMessage(msgMessage);
			}
		}
	};
}
