package com.example.drawzhuzhuangmoredata;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends Activity {
	MySQL mySQL;
	SQLiteDatabase db;
	Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					AppConfig.data1 = Float.valueOf(random.nextInt(149));
					AppConfig.data2 = Float.valueOf(random.nextInt(149));
					AppConfig.data3 = Float.valueOf(random.nextInt(149));
					AppConfig.data4 = Float.valueOf(random.nextInt(149));
					AppConfig.data5 = Float.valueOf(random.nextInt(149));
				}
			}
		}).start();
	}

	private void initView() {
		// TODO Auto-generated method stub'
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
