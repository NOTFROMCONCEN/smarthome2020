package com.example.shengsaia06132019;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.shengsaia06132019.tools.AppConfig;
import com.example.shengsaia06132019.tools.DiyToast;
import com.example.shengsaia06132019.tools.MySQL;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��¼����
 * @package_name com.example.shengsaia06132019
 * @project_name 2019ShengSaiA0613
 * @file_name MainActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	private EditText et_ip;
	private Button btn_login;
	private SeekBar sk_1;
	// ��ʼ�����ݿ�
	MySQL mySQL;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		sk_1.setProgress(0);// Ĭ��
		sk_1.setMax(100);// ���
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (sk_1.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "IP�Ƿ�");
					} else {
						AppConfig.IP = et_ip.getText().toString();// ��ȡIP��ַ
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
						finish();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "����ɻ�������");
				}
			}
		});
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "����ɻ�������");
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// ��ʼ�����ݿ�
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
