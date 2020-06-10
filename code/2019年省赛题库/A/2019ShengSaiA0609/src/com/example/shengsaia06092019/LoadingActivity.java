package com.example.shengsaia06092019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends Activity {
	int number = 0;
	private ProgressBar pg_1;
	private TextView tv_loading_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		setTitle("加载中......");
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		pg_1.setMax(100);
		pg_1.setProgress(0);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number < 100) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					number++;
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_loading_number.setText(String.valueOf(number)
									+ "%");
							pg_1.setProgress(number);
							if (number >= 100) {
								AlertDialog.Builder builder = new AlertDialog.Builder(
										LoadingActivity.this);
								builder.setTitle("登录成功");
								builder.setMessage("欢迎回来");
								builder.setNegativeButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												startActivity(new Intent(
														getApplicationContext(),
														IndexActivity.class));
												finish();
											}
										});
								builder.show();
							}
						}
					});
				}
			}
		}).start();
	}
}
