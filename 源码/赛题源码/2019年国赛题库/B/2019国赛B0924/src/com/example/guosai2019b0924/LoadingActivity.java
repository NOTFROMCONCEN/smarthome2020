package com.example.guosai2019b0924;

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

/**
 * 加载界面
 * 
 * @author 10976
 * 
 */
public class LoadingActivity extends Activity {
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			((TextView) findViewById(R.id.tv_loading_text)).setText(String
					.valueOf(number) + "%");
			((ProgressBar) findViewById(R.id.progressBar1)).setProgress(number);
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			if (number > 100) {
				handler.removeCallbacks(timeRunnable);
				new AlertDialog.Builder(LoadingActivity.this)
						.setTitle("登陆成功")
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
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
