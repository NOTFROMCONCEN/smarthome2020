package com.example.shengsai2019a0914;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		setTitle("载入中......");
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(number);
			tv_loading_number.setText(String.valueOf(number) + "%");
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			if (number <= 100) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
				new AlertDialog.Builder(LoadingActivity.this)
						.setTitle("登录成功")
						.setMessage("欢迎回来")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										startActivity(new Intent(
												getApplicationContext(),
												IndexActivity.class));
										finish();
									}
								}).show();
			}
		}
	};
}
