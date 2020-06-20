package com.example.shengsaib06192019;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shengsaib06192019.tools.MySQL;

public class MainActivity extends Activity {

	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;
	private MySQL mySQL;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (number <= 100) {
					number++;
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							pg_1.setProgress(number);
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
								tv_loading_text.setText("����ϵͳ��......");
								break;
							case 100:
								startActivity(new Intent(
										getApplicationContext(),
										LoginActivity.class));
								finish();
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

	private void initView() {
		// TODO Auto-generated method stub
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
