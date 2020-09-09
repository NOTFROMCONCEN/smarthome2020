package com.example.shengsaid07032019;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_loading_text;
	private ProgressBar pg_1;
	private int number = 0;
	private String string = "正在初始化";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 10:
				string = "正在初始化";
				break;
			case 21:
				string = "正在进入系统";
				break;
			case 51:
				string = "正在连接服务器";
				break;
			default:
				break;
			}
			tv_loading_text.setText(string + "......"
					+ String.valueOf(msg.what) + "%");
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (number <= 100) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_number);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
	}
}
