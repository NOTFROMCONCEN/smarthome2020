package com.example.shengsaic07022019;

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
	private TextView tv_loading_text;
	private int number = 0;

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
				tv_loading_text.setText("正在加载串口配置......");
				break;
			case 20:
				tv_loading_text.setText("串口配置加载完成......");
				break;
			case 30:
				tv_loading_text.setText("正在加载界面配置......");
				break;
			case 50:
				tv_loading_text.setText("界面配置加载完成......");
				break;
			case 60:
				tv_loading_text.setText("正在初始化界面......");
				break;
			case 80:
				tv_loading_text.setText("界面初始化完成......");
				break;
			case 99:
				tv_loading_text.setText("正在进入系统......");
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what <= 100) {
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
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
	}
}
