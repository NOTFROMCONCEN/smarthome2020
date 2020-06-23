package com.example.shengsaib06232019;

import com.example.shengsaib06232019.tools.TimeThread;

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
		initView();
		pg_1.setMax(100);
		pg_1.setProgress(0);
		tv_loading_number.setText("正在加载串口配置......");
		handler.post(tiemRunnable);
		TimeThread.start_time();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 10:
				tv_loading_number.setText("正在加载串口配置......");
				break;
			case 20:
				tv_loading_number.setText("串口配置加载完成......");
				break;
			case 30:
				tv_loading_number.setText("正在加载界面配置......");
				break;
			case 50:
				tv_loading_number.setText("界面配置加载完成......");
				break;
			case 60:
				tv_loading_number.setText("正在加载界面......");
				break;
			case 80:
				tv_loading_number.setText("界面加载完成......");
				break;
			case 100:
				tv_loading_number.setText("正在进入系统......");
				break;
			default:
				break;
			}
			if (msg.what > 100) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
			handler.postDelayed(tiemRunnable, 10);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what < 104) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(tiemRunnable);
			}
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		pg_1 = (ProgressBar) findViewById(R.id.pg_loadgin);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_text);
	}
}
