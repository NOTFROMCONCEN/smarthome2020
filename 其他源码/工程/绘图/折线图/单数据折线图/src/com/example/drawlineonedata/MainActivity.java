package com.example.drawlineonedata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	Random random = new Random();
	public static String random_number = "";
	private SQLiteDatabase db;
	private MySQL mySQL;
	private ListView lv_draw_data;
	private TextView tv_random_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		lv_draw_data.setStackFromBottom(true);// ListView底部对齐
		handler.post(timeRunnable);// 启动进程
	}

	private void initView() {
		// TODO Auto-generated method stub
		/**
		 * 数据库
		 */
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
		lv_draw_data = (ListView) findViewById(R.id.lv_draw_data);
		tv_random_number = (TextView) findViewById(R.id.tv_random_number);
	}

	private void set_data() {
		// TODO Auto-generated method stub
		SimpleAdapter simpleAdapter = new SimpleAdapter(
				getApplicationContext(), MyView.list, R.layout.layout_list,
				new String[] { "number", "name", "data" }, new int[] {
						R.id.tv_number, R.id.tv_name, R.id.tv_data });
		lv_draw_data.setAdapter(simpleAdapter);
	}

	private void insert_data() {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		db.execSQL("insert into data (number,name,data)values(?,?,?)",
				new String[] { simpleDateFormat.format(new java.util.Date()),
						"随机生成", random_number });
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			random_number = String.valueOf(random.nextInt(256));// 生成一次随机数
			tv_random_number.setText(random_number);
			insert_data();
			set_data();
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
