package com.example.shengsaia06062019;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 加载界面
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name LoadingActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class LoadingActivity extends Activity {

	private ProgressBar pg_1;// 进度条
	private TextView tv_loading_text;// 文本框
	int number = 0;// 进度

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		setTitle("载入中......");// 设置ActionBar的title文本
		// 绑定控件
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		// 进度条线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub\
				while (number < 100) {
					number++;// 线程运行一次number + 1
					try {
						Thread.sleep(20);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (number > 100) {
						break;
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);
							tv_loading_text.setText(String.valueOf(number)
									+ "%");
							if (number >= 100) {
								number = 999;
								tv_loading_text.setText("100%");
								AlertDialog builder = new AlertDialog.Builder(
										LoadingActivity.this)
										.setTitle("登陆成功")
										.setMessage("欢迎回来")
										.setPositiveButton(
												"确定",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
														startActivity(new Intent(
																LoadingActivity.this,
																RoomIndexActivity.class));
														finish();
													}
												}).show();
							}
						}
					});
				}
			}
		}).start();
	}
}
