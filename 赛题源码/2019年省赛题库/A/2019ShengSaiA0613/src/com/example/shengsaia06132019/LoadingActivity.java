package com.example.shengsaia06132019;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 加载界面
 * @package_name com.example.shengsaia06132019
 * @project_name 2019ShengSaiA0613
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
		initView();// 绑定
		/**
		 * 开启加载进度条线程
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number < 100) {
					// 休眠
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 操作UI线程
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);
							tv_loading_number.setText(String.valueOf(number)
									+ "%");
							// 判断大小
							if (number == 100) {
								// 弹出提示框
								AlertDialog.Builder builder = new AlertDialog.Builder(
										LoadingActivity.this);
								builder.setTitle("登录成功");
								builder.setMessage("欢迎回来");
								builder.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												startActivity(new Intent(
														getApplicationContext(),
														RoomIndexActivity.class));
											}
										});
								builder.show();
							}
						}
					});
					number++;
				}
			}
		}).start();
	}

	private void initView() {
		// TODO Auto-generated method stub
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_loading, menu);
		return true;
	}

}
