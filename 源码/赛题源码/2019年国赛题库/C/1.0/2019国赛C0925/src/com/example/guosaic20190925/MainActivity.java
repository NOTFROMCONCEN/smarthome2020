package com.example.guosaic20190925;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * APP主入口、加载界面
 * 
 * @author 10976
 * 
 */
public class MainActivity extends Activity {

	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_number);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(number);
			switch (number) {
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
				tv_loading_text.setText("进入系统中......");
				break;
			default:
				break;
			}
			handler.post(timeRunnable);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msgMessage = handler.obtainMessage();
			if (number > 100) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			} else {
				handler.sendMessage(msgMessage);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
