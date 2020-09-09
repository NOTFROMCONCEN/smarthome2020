package com.example.shengsaib06282019;

import com.example.shengsaib06282019.tools.TimeThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	ProgressBar pg_1;
	TextView tv_1;
	int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_1 = (TextView) findViewById(R.id.tv_progress_text);
		pg_1.setMax(105);
		pg_1.setProgress(0);
		TimeThread.start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number <= 100) {
					number++;
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (number > 100) {
						startActivity(new Intent(getApplicationContext(),
								LoginActivity.class));
						finish();
						break;
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);
							switch (number) {
							case 10:
								tv_1.setText("正在加载串口配置......");
								break;
							case 20:
								tv_1.setText("串口配置加载完成......");
								break;
							case 30:
								tv_1.setText("正在加载界面配置......");
								break;
							case 50:
								tv_1.setText("界面配置加载完成......");
								break;
							case 60:
								tv_1.setText("正在初始化界面......");
								break;
							case 80:
								tv_1.setText("界面初始化完成......");
								break;
							case 99:
								tv_1.setText("进入系统中......");
								break;
							default:
								break;
							}
						}
					});
				}
			}
		}).start();
	}
}
