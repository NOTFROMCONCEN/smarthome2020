package com.example.shengsai06202019;

import com.example.shengsai06202019.tools.MySQL;
import com.example.shengsai06202019.tools.TimeHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 加载
 * @package_name com.example.shengsai06202019
 * @project_name 2019省赛0620
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	private ProgressBar pg_1;// 进度条
	private TextView tv_loading_text;// 进度条提示
	private int number = 0;// 进度条进度
	private MySQL mySQL;// 数据库
	private SQLiteDatabase db;// 数据库

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("智能家居");// 设置title
		TimeHandler.start_time();// 启动时间
		initView();// 绑定控件
		/**
		 * 加载进度条线程
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number <= 103) {
					number++;// 循环一次增加一次number数值
					try {
						Thread.sleep(30);// 休眠
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (number > 100) {
						// 跳转
						startActivity(new Intent(getApplicationContext(),
								LoginActivity.class));
						finish();
						break;// 跳出循环
					}
					// 操作UI的线程
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);// 设置进度
							// 循环判断当前值
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
								tv_loading_text.setText("正在进入系统......");
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

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		pg_1 = (ProgressBar) findViewById(R.id.pg_loadgin);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
