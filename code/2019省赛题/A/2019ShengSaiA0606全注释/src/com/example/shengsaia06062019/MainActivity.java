package com.example.shengsaia06062019;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaia06062019.sql.MySQL;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��¼����
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name MainActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP��ַ�ı���
	private SeekBar sk_1;// SeekBar
	private Button btn_login;// ��¼��ť
	// ���ݿ�
	private MySQL mySQL;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// �󶨿ؼ�
		setTitle("");// ����ActionBar��title�ı�
		// ����
		sk_1.setProgress(0);// ����Ĭ�Ͻ���
		sk_1.setMax(101);// ����������
		// ����SeekBar��OnSeekBar�¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����ʱ
				// TODO Auto-generated method stub
				if (seekBar.getProgress() < 100) {
					DiyToast.showToast(getApplicationContext(), "�뻬�������֤");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����ʱ
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// ���ȸı�ʱ
				// TODO Auto-generated method stub
			}
		});
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// ���IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "IP��ַ�Ƿ�");
				} else {
					if (sk_1.getProgress() < 100) {// ���SeekBar����С��100
						DiyToast.showToast(getApplicationContext(), "�뻬�������֤");
						sk_1.setProgress(0);
					} else {
						AppConfig.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
						finish();
					}
				}
			}
		});
	}

	/**
	 * �󶨿ؼ�
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// ��ʼ�����ݿ�
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
