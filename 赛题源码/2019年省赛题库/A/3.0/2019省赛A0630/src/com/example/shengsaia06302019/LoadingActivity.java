package com.example.shengsaia06302019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 加载进度条
 * @package_name com.example.shengsaia06302019
 * @project_name 2019省赛A0630
 * @file_name LoadingActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class LoadingActivity extends Activity {

	private ProgressBar pg_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number < 102) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);
							tv_loading_number.setText(String.valueOf(number)
									+ "%");
						}
					});
					if (number > 10) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								tv_loading_number.setText("100%");
								new AlertDialog.Builder(LoadingActivity.this)
										.setTitle("登录成功")
										.setMessage("欢迎回来")
										.setPositiveButton("OK",
												new OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
														startActivity(new Intent(
																getApplicationContext(),
																IndexActivity.class));
														finish();
													}
												}).show();
							}
						});
						break;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					number++;
				}
			}
		}).start();
	}
}
