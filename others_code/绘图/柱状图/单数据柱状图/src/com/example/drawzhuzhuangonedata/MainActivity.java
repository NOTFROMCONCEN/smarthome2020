package com.example.drawzhuzhuangonedata;

import java.text.SimpleDateFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private Random random = new Random();// �����
	private MySQL mySQL;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private ListView lv_show_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// �󶨿ؼ�
		handler.post(timeRunnable);// ��������
	}

	/**
	 * ��
	 */
	private void initView() {
		// TODO Auto-generated method stub
		mySQL = new MySQL(getApplicationContext(), MyConfig.sql_name, null, 2);
		db = mySQL.getWritableDatabase();
		lv_show_data = (ListView) findViewById(R.id.lv_show_data);
		lv_show_data.setStackFromBottom(true);// ListView�ײ�����
	}

	/**
	 * ����Adapter
	 */
	private void set_data() {
		// TODO Auto-generated method stub
		SimpleAdapter simpleAdapter = new SimpleAdapter(
				getApplicationContext(), MyView.list, R.layout.layout_list,
				new String[] { "number", null, "data" }, new int[] {
						R.id.tv_number, R.id.tv_name, R.id.tv_data });
		lv_show_data.setAdapter(simpleAdapter);
	}

	/**
	 * �����ṩʵʱ���ݵ��߳�
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String nuString = String.valueOf(random.nextInt(256));
			MyView.get_number(String.valueOf(nuString));
			set_data();
			handler.postDelayed(timeRunnable, MyConfig.runthread_time);
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
}
