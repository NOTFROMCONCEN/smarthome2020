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
 * @Todo TODO ����
 * @package_name com.example.shengsai06202019
 * @project_name 2019ʡ��0620
 * @file_name MainActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	private ProgressBar pg_1;// ������
	private TextView tv_loading_text;// ��������ʾ
	private int number = 0;// ����������
	private MySQL mySQL;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("���ܼҾ�");// ����title
		TimeHandler.start_time();// ����ʱ��
		initView();// �󶨿ؼ�
		/**
		 * ���ؽ������߳�
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number <= 103) {
					number++;// ѭ��һ������һ��number��ֵ
					try {
						Thread.sleep(30);// ����
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (number > 100) {
						// ��ת
						startActivity(new Intent(getApplicationContext(),
								LoginActivity.class));
						finish();
						break;// ����ѭ��
					}
					// ����UI���߳�
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);// ���ý���
							// ѭ���жϵ�ǰֵ
							switch (number) {
							case 10:
								tv_loading_text.setText("���ڼ��ش�������......");
								break;
							case 20:
								tv_loading_text.setText("�������ü������......");
								break;
							case 30:
								tv_loading_text.setText("���ڼ��ؽ�������......");
								break;
							case 50:
								tv_loading_text.setText("�������ü������......");
								break;
							case 60:
								tv_loading_text.setText("���ڳ�ʼ������......");
								break;
							case 80:
								tv_loading_text.setText("�����ʼ�����......");
								break;
							case 99:
								tv_loading_text.setText("���ڽ���ϵͳ......");
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
	 * �󶨿ؼ�
	 */
	private void initView() {
		// TODO Auto-generated method stub
		pg_1 = (ProgressBar) findViewById(R.id.pg_loadgin);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
