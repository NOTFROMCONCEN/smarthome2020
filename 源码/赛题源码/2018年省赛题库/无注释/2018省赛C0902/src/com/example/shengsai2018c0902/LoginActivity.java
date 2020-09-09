package com.example.shengsai2018c0902;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018c0902.fragment.BarActivity;
import com.example.shengsai2018c0902.tools.AppConfig;
import com.example.shengsai2018c0902.tools.DiyToast;
import com.example.shengsai2018c0902.tools.MyDataBaseHelper;

public class LoginActivity extends Activity {

	private Button btn_login;
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember, cb_autologin;
	public static SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		if (sharedPreferences.getBoolean("rember", false) == true) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
			cb_rember.setChecked(true);
		}
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
				} else {
					cb_autologin.setChecked(false);
				}
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					DiyToast.showTaost(getApplicationContext(), "�����пհ���");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						} else {
							sharedPreferences
									.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						}
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(LoginActivity.this,
								BarActivity.class));
						finish();
					} else {
						DiyToast.showTaost(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login = (Button) findViewById(R.id.btn_login);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
