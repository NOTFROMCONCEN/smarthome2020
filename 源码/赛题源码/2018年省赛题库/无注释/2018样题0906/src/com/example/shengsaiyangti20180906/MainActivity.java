package com.example.shengsaiyangti20180906;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_loading;
	private ProgressBar pg_1;
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
			tv_loading.setText(String.valueOf(msg.what) + "%");
			pg_1.setProgress(msg.what);
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message message = handler.obtainMessage();
			message.what = number;
			if (number >= 100) {
				handler.removeCallbacks(timeRunnable);
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			} else {
				handler.sendMessage(message);
			}
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		tv_loading = (TextView) findViewById(R.id.tv_loading);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
	}

}
