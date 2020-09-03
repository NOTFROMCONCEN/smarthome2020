package com.example.shengsai2018a0901;

import com.example.shengsai2018a0901.tools.MyDataBaseHelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 进度条加载界面
 * @package_name com.example.shengsai2018a0901
 * @project_name 2018省赛A0901
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;// 进度条
	private TextView tv_loading_text;// 加载进度文本
	private int number = 0;// 加载进度
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			tv_loading_text.setText("加载中..." + String.valueOf(msg.what) + "%");
			handler.postDelayed(timeRunnable, 10);
			if (msg.what >= 100) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (number <= 100) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
			}
		}
	};

	private void init() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(MainActivity.this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
	}
}
