package com.example.shengsai2018b0912;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018b0912.tools.DiyToast;
import com.example.shengsai2018b0912.tools.MyDataBaseHelper;
import com.example.shengsai2018b0912.tools.TextChanger;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��������� ��¼����
 * @package_name com.example.shengsai2018b0912
 * @project_name 2018ʡ��B0912
 * @file_name MainActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	// ��ť
	private Button btn_login, btn_reg;
	// �ı���
	private EditText et_user, et_pass, et_port, et_ip;
	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// �󶨿ؼ�
		// ���sharedPreferences���д洢����Ϣ������䣨��ס���룩
		et_user.setText(sharedPreferences.getString("user", null));// �û���
		et_ip.setText(sharedPreferences.getString("ip", null));// IP��ַ
		et_pass.setText(sharedPreferences.getString("pass", null));// ����
		et_port.setText(sharedPreferences.getString("port", null));// �˿ں�
		// ע�ᰴť
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ת��ע�����
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
			}
		});
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �ж��û��������롢IP��ַ�Ͷ˿ں��Ƿ�Ϊ��
				if (et_user.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()
						|| et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�����пհ���");
				} else {
					// �½����ݿ��α�
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					// �α��ƶ������ݿ���ƥ�����ݣ�
					if (cursor.moveToNext()) {
						// �����������ӵ�IP
						SocketClient.ip = et_ip.getText().toString();
						// ��sharedPreferences�д洢��Ϣ
						sharedPreferences
								.edit()
								.putString("user", et_user.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("ip", et_ip.getText().toString())
								.putString("port", et_port.getText().toString())
								.commit();
						// ��ת���˵�ѡ�����
						startActivity(new Intent(getApplicationContext(),
								MenuActivity.class));
						finish();
					} else {
						// �α�δ�ƶ����޷�ƥ�䵽������ݣ�
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
		// �����ַ�ת��Ϊ*
		et_pass.setTransformationMethod(new TextChanger());
	}

	/**
	 * �󶨿ؼ�
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
